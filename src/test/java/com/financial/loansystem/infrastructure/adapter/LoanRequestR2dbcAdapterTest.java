package com.financial.loansystem.infrastructure.adapter;

import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.model.LoanRequest.LoanStatus;
import com.financial.loansystem.domain.port.LoanRequestRepository;
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
@DisplayName("LoanRequestR2dbcAdapter - Tests de persistencia reactiva")
class LoanRequestR2dbcAdapterTest {

    @Mock
    private LoanRequestRepository repository;

    private LoanRequestR2dbcAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new LoanRequestR2dbcAdapter(repository);
    }

    @Test
    @DisplayName("Debe guardar solicitud de préstamo exitosamente")
    void shouldSaveLoanRequestSuccessfully() {
        LoanRequest request = createTestRequest();

        when(repository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request));

        StepVerifier.create(adapter.save(request))
            .assertNext(saved -> {
                assertThat(saved.id()).isEqualTo(request.id());
                assertThat(saved.clientId()).isEqualTo(request.clientId());
                assertThat(saved.requestedAmount()).isEqualTo(request.requestedAmount());
            })
            .verifyComplete();

        verify(repository).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe encontrar solicitud por ID exitosamente")
    void shouldFindLoanRequestByIdSuccessfully() {
        UUID requestId = UUID.randomUUID();
        LoanRequest request = createTestRequestWithId(requestId);

        when(repository.findById(eq(requestId)))
            .thenReturn(Mono.just(request));

        StepVerifier.create(adapter.findById(requestId))
            .assertNext(found -> {
                assertThat(found.id()).isEqualTo(requestId);
                assertThat(found.status()).isEqualTo(LoanStatus.PENDING);
            })
            .verifyComplete();

        verify(repository).findById(eq(requestId));
    }

    @Test
    @DisplayName("Debe verificar existencia de clave de idempotencia")
    void shouldCheckIdempotencyKeyExists() {
        IdempotencyKey key = IdempotencyKey.fromCompositeKey("OP-2024-TEST|channel-web");

        when(repository.existsByIdempotencyKey(eq(key)))
            .thenReturn(Mono.just(true));

        StepVerifier.create(adapter.existsByIdempotencyKey(key))
            .assertNext(exists -> assertThat(exists).isTrue())
            .verifyComplete();

        verify(repository).existsByIdempotencyKey(eq(key));
    }

    @Test
    @DisplayName("Debe retornar false para clave de idempotencia inexistente")
    void shouldReturnFalseForNonExistentIdempotencyKey() {
        IdempotencyKey key = IdempotencyKey.fromCompositeKey("OP-2024-NEW|channel-mobile");

        when(repository.existsByIdempotencyKey(eq(key)))
            .thenReturn(Mono.just(false));

        StepVerifier.create(adapter.existsByIdempotencyKey(key))
            .assertNext(exists -> assertThat(exists).isFalse())
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe encontrar solicitud por clave de idempotencia")
    void shouldFindLoanRequestByIdempotencyKey() {
        IdempotencyKey key = IdempotencyKey.fromCompositeKey("OP-2024-KEY|channel-api");
        LoanRequest request = createTestRequestWithOperationNumber("OP-2024-KEY", "channel-api");

        when(repository.findByIdempotencyKey(eq(key)))
            .thenReturn(Mono.just(request));

        StepVerifier.create(adapter.findByIdempotencyKey(key))
            .assertNext(found -> {
                assertThat(found.operationNumber()).isEqualTo("OP-2024-KEY");
                assertThat(found.channel()).isEqualTo("channel-api");
            })
            .verifyComplete();

        verify(repository).findByIdempotencyKey(eq(key));
    }

    @Test
    @DisplayName("Debe actualizar estado de solicitud exitosamente")
    void shouldUpdateStatusSuccessfully() {
        UUID requestId = UUID.randomUUID();
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest updatedRequest = request.withStatus(LoanStatus.APPROVED);

        when(repository.updateStatus(eq(requestId), eq(LoanStatus.APPROVED)))
            .thenReturn(Mono.just(updatedRequest));

        StepVerifier.create(adapter.updateStatus(requestId, LoanStatus.APPROVED))
            .assertNext(updated -> {
                assertThat(updated.id()).isEqualTo(requestId);
                assertThat(updated.status()).isEqualTo(LoanStatus.APPROVED);
            })
            .verifyComplete();

        verify(repository).updateStatus(eq(requestId), eq(LoanStatus.APPROVED));
    }

    @Test
    @DisplayName("Debe actualizar con evaluación de riesgo exitosamente")
    void shouldUpdateWithRiskEvaluationSuccessfully() {
        UUID requestId = UUID.randomUUID();
        String riskEvaluationId = "RISK-EVAL-999";
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest updatedRequest = request.withRiskEvaluationId(riskEvaluationId).withStatus(LoanStatus.RISK_ASSESSED);

        when(repository.updateWithRiskEvaluation(eq(requestId), eq(riskEvaluationId)))
            .thenReturn(Mono.just(updatedRequest));

        StepVerifier.create(adapter.updateWithRiskEvaluation(requestId, riskEvaluationId))
            .assertNext(updated -> {
                assertThat(updated.riskEvaluationId()).isEqualTo(riskEvaluationId);
                assertThat(updated.status()).isEqualTo(LoanStatus.RISK_ASSESSED);
            })
            .verifyComplete();

        verify(repository).updateWithRiskEvaluation(eq(requestId), eq(riskEvaluationId));
    }

    @Test
    @DisplayName("Debe actualizar con aprobación exitosamente")
    void shouldUpdateWithApprovalSuccessfully() {
        UUID requestId = UUID.randomUUID();
        String riskEvaluationId = "RISK-EVAL-888";
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest approvedRequest = request.withApprovedAmount(new BigDecimal("45000"));
        LoanRequest updatedRequest = approvedRequest.withStatus(LoanStatus.APPROVED);

        when(repository.updateWithApproval(eq(requestId), eq(riskEvaluationId), any(LoanRequest.class)))
            .thenReturn(Mono.just(updatedRequest));

        StepVerifier.create(adapter.updateWithApproval(requestId, riskEvaluationId, approvedRequest))
            .assertNext(updated -> {
                assertThat(updated.status()).isEqualTo(LoanStatus.APPROVED);
                assertThat(updated.approvedAmount()).isEqualTo(new BigDecimal("45000"));
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe actualizar con rechazo exitosamente")
    void shouldUpdateWithRejectionSuccessfully() {
        UUID requestId = UUID.randomUUID();
        String riskEvaluationId = "RISK-EVAL-777";
        String rejectionReason = "Riesgo crediticio alto";
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest rejectedRequest = request.withStatus(LoanStatus.REJECTED).withRejectionReason(rejectionReason);

        when(repository.updateWithRejection(eq(requestId), eq(riskEvaluationId), eq(rejectionReason)))
            .thenReturn(Mono.just(rejectedRequest));

        StepVerifier.create(adapter.updateWithRejection(requestId, riskEvaluationId, rejectionReason))
            .assertNext(updated -> {
                assertThat(updated.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(updated.rejectionReason()).isEqualTo(rejectionReason);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar Mono.empty cuando no encuentra solicitud por ID")
    void shouldReturnEmptyWhenRequestNotFoundById() {
        UUID requestId = UUID.randomUUID();

        when(repository.findById(eq(requestId)))
            .thenReturn(Mono.empty());

        StepVerifier.create(adapter.findById(requestId))
            .verifyComplete();
    }

    private LoanRequest createTestRequest() {
        return new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-TEST",
            new BigDecimal("25000"),
            24,
            "PERSONAL",
            "channel-web",
            "OP-2024-TEST",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }

    private LoanRequest createTestRequestWithId(UUID id) {
        return new LoanRequest(
            id,
            "CLIENT-TEST",
            new BigDecimal("25000"),
            24,
            "PERSONAL",
            "channel-web",
            "OP-2024-ID",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }

    private LoanRequest createTestRequestWithOperationNumber(String operationNumber, String channel) {
        return new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-TEST",
            new BigDecimal("25000"),
            24,
            "PERSONAL",
            channel,
            operationNumber,
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }
}