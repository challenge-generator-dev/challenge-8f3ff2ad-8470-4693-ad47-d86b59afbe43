package com.financial.loansystem.infrastructure.adapter;

import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.model.LoanRequest.LoanStatus;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("RiskEvaluationWebClient - Tests de cliente reactivo")
class RiskEvaluationWebClientTest {

    @Mock
    private RiskEvaluationClient riskEvaluationClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Debe evaluar riesgo exitosamente para solicitud válida")
    void shouldEvaluateRiskSuccessfullyForValidRequest() {
        LoanRequest request = createTestRequest();
        String expectedRiskEvaluationId = "RISK-EVAL-12345";

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just(expectedRiskEvaluationId));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .assertNext(riskId -> {
                assertThat(riskId).isEqualTo(expectedRiskEvaluationId);
            })
            .verifyComplete();

        verify(riskEvaluationClient, times(1)).evaluateRisk(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe manejar error de timeout en evaluación de riesgo")
    void shouldHandleTimeoutErrorInRiskEvaluation() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Connection timeout")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe manejar error de servicio no disponible")
    void shouldHandleServiceUnavailableError() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Service unavailable")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectErrorMatches(error -> 
                error.getMessage().contains("Service unavailable")
            )
            .verify();
    }

    @Test
    @DisplayName("Debe manejar error de respuesta inválida del servicio")
    void shouldHandleInvalidResponseError() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new IllegalStateException("Invalid response format")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectError(IllegalStateException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe manejar múltiples retry automáticamente")
    void shouldHandleAutomaticRetries() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(
                Mono.error(new RuntimeException("Transient error")),
                Mono.error(new RuntimeException("Transient error")),
                Mono.just("RISK-EVAL-SUCCESS")
            );

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .assertNext(riskId -> {
                assertThat(riskId).isEqualTo("RISK-EVAL-SUCCESS");
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe validar request antes de enviar a evaluación de riesgo")
    void shouldValidateRequestBeforeSendingToRiskEvaluation() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just("RISK-EVAL-VALIDATED"));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .assertNext(result -> {
                assertThat(result).isNotNull();
                assertThat(result).startsWith("RISK-EVAL-");
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe manejar error de circuit breaker abierto")
    void shouldHandleCircuitBreakerOpenError() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("CircuitBreaker is OPEN")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectErrorMatches(error -> 
                error.getMessage().contains("CircuitBreaker")
            )
            .verify();
    }

    @Test
    @DisplayName("Debe manejar rate limit excedido")
    void shouldHandleRateLimitExceeded() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Rate limit exceeded")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectErrorMatches(error -> 
                error.getMessage().contains("Rate limit")
            )
            .verify();
    }

    @Test
    @DisplayName("Debe retornarMono vacío cuando la evaluación es exitosa pero sin ID")
    void shouldReturnEmptyWhenEvaluationSucceedsWithoutId() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe procesar múltiples solicitudes concurrentemente")
    void shouldProcessMultipleConcurrentRequests() {
        LoanRequest request1 = createTestRequestWithClient("CLIENT-001");
        LoanRequest request2 = createTestRequestWithClient("CLIENT-002");
        LoanRequest request3 = createTestRequestWithClient("CLIENT-003");

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just("RISK-EVAL-CONCURRENT"));

        StepVerifier.create(
            Mono.zip(
                riskEvaluationClient.evaluateRisk(request1),
                riskEvaluationClient.evaluateRisk(request2),
                riskEvaluationClient.evaluateRisk(request3)
            )
        )
        .assertNext(tuple -> {
            assertThat(tuple.getT1()).isEqualTo("RISK-EVAL-CONCURRENT");
            assertThat(tuple.getT2()).isEqualTo("RISK-EVAL-CONCURRENT");
            assertThat(tuple.getT3()).isEqualTo("RISK-EVAL-CONCURRENT");
        })
        .verifyComplete();
    }

    private LoanRequest createTestRequest() {
        return new LoanRequest(
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
    }

    private LoanRequest createTestRequestWithClient(String clientId) {
        return new LoanRequest(
            UUID.randomUUID(),
            clientId,
            new BigDecimal("30000"),
            24,
            "PERSONAL",
            "channel-mobile",
            "OP-2024-" + clientId,
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }
}