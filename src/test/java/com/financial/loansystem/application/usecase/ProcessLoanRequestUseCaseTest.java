package com.financial.loansystem.application.usecase;

import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.model.LoanRequest.LoanStatus;
import com.financial.loansystem.domain.port.LoanRequestRepository;
import com.financial.loansystem.domain.port.NotificationService;
import com.financial.loansystem.domain.port.RiskEvaluationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessLoanRequestUseCase - Tests de integración")
class ProcessLoanRequestUseCaseTest {

    @Mock
    private LoanRequestRepository loanRequestRepository;

    @Mock
    private RiskEvaluationClient riskEvaluationClient;

    @Mock
    private NotificationService notificationService;

    private ProcessLoanRequestUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProcessLoanRequestUseCase(
            loanRequestRepository,
            riskEvaluationClient,
            notificationService
        );
    }

    @Test
    @DisplayName("Debe procesar solicitud nueva exitosamente cuando no existe clave de idempotencia")
    void shouldProcessNewRequestSuccessfullyWhenIdempotencyKeyDoesNotExist() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-001",
            new BigDecimal("50000"),
            36,
            "PERSONAL",
            "channel-web",
            "OP-2024-001",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        IdempotencyKey idempotencyKey = IdempotencyKey.fromCompositeKey("OP-2024-001|card-web");

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just("RISK-EVAL-001"));
        when(loanRequestRepository.updateWithRiskEvaluation(any(UUID.class), eq("RISK-EVAL-001")))
            .thenReturn(Mono.just(request.withRiskEvaluationId("RISK-EVAL-001").withStatus(LoanStatus.RISK_ASSESSED)));
        when(loanRequestRepository.updateWithApproval(any(UUID.class), eq("RISK-EVAL-001"), any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.APPROVED).withApprovedAmount(new BigDecimal("50000"))));
        when(notificationService.sendApprovalNotification(any(LoanRequest.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.APPROVED);
                assertThat(result.approvedAmount()).isEqualTo(new BigDecimal("50000"));
            })
            .verifyComplete();

        verify(loanRequestRepository).save(any(LoanRequest.class));
        verify(riskEvaluationClient).evaluateRisk(any(LoanRequest.class));
        verify(notificationService).sendApprovalNotification(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe retornar solicitud existente cuando la clave de idempotencia ya existe")
    void shouldReturnExistingRequestWhenIdempotencyKeyAlreadyExists() {
        LoanRequest existingRequest = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-001",
            new BigDecimal("50000"),
            36,
            "PERSONAL",
            "channel-web",
            "OP-2024-001",
            LocalDate.now(),
            LoanStatus.APPROVED,
            null,
            new BigDecimal("50000"),
            null
        );

        IdempotencyKey idempotencyKey = IdempotencyKey.fromCompositeKey("OP-2024-001|card-web");

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(true));
        when(loanRequestRepository.findByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(existingRequest));

        StepVerifier.create(useCase.execute(existingRequest))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.APPROVED);
                assertThat(result.id()).isEqualTo(existingRequest.id());
            })
            .verifyComplete();

        verify(loanRequestRepository).existsByIdempotencyKey(any(IdempotencyKey.class));
        verify(loanRequestRepository).findByIdempotencyKey(any(IdempotencyKey.class));
    }

    @Test
    @DisplayName("Debe rechazar solicitud cuando la evaluación de riesgos falla")
    void shouldRejectRequestWhenRiskEvaluationFails() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-002",
            new BigDecimal("100000"),
            48,
            "PERSONAL",
            "channel-mobile",
            "OP-2024-002",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Risk evaluation service unavailable")));
        when(loanRequestRepository.updateWithRejection(any(UUID.class), any(String.class), any(String.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.REJECTED).withRejectionReason("Risk evaluation service unavailable")));
        when(notificationService.sendRejectionNotification(any(LoanRequest.class), any(String.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(result.rejectionReason()).contains("Risk evaluation service unavailable");
            })
            .verifyComplete();

        verify(notificationService).sendRejectionNotification(any(LoanRequest.class), any(String.class));
    }

    @Test
    @DisplayName("Debe manejar solicitud con monto superior al límite máximo")
    void shouldHandleRequestAboveMaximumLimit() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-003",
            new BigDecimal("1000000"),
            60,
            "PERSONAL",
            "channel-web",
            "OP-2024-003",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(loanRequestRepository.updateWithRejection(any(UUID.class), any(String.class), any(String.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.REJECTED).withRejectionReason("Monto solicitada excede el límite máximo permitido")));

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(result.rejectionReason()).contains("excede el límite");
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe validar correctamente la clave de idempotencia compuesta")
    void shouldValidateCompositeIdempotencyKeyCorrectly() {
        String operationNumber = "OP-2024-100";
        String channel = "channel-api";
        String expectedCompositeKey = operationNumber + "|" + channel;

        IdempotencyKey key = IdempotencyKey.fromCompositeKey(expectedCompositeKey);
        assertThat(key.toCompositeKey()).isEqualTo(expectedCompositeKey);
    }

    @Test
    @DisplayName("Debe rechazar solicitud con plazo fuera del rango válido")
    void shouldRejectRequestWithInvalidTerm() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-004",
            new BigDecimal("30000"),
            120,
            "PERSONAL",
            "channel-web",
            "OP-2024-004",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(loanRequestRepository.updateWithRejection(any(UUID.class), any(String.class), any(String.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.REJECTED).withRejectionReason("El plazo solicitado excede el máximo permitido de 60 meses")));

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(result.rejectionReason()).contains("plazo");
            })
            .verifyComplete();
    }
}