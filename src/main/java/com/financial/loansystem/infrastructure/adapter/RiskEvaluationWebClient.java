package com.financial.loansystem.infrastructure.adapter;

import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.port.RiskEvaluationClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Component
public class RiskEvaluationWebClient implements RiskEvaluationClient {

    private static final Logger log = LoggerFactory.getLogger(RiskEvaluationWebClient.class);
    private final WebClient webClient;
    private final String riskEvaluationBaseUrl;

    public RiskEvaluationWebClient(
            WebClient.Builder webClientBuilder,
            @Value("${external-services.risk-evaluation.base-url:http://risk-evaluation-service:8080}") String riskEvaluationBaseUrl) {
        this.webClient = webClientBuilder.build();
        this.riskEvaluationBaseUrl = riskEvaluationBaseUrl;
    }

    @Override
    @CircuitBreaker(name = "riskEvaluationCircuitBreaker", fallbackMethod = "evaluateRiskFallback")
    @Retry(name = "riskEvaluationRetry")
    public Mono<RiskEvaluationResponse> evaluateRisk(LoanRequest loanRequest) {
        log.info("Sending loan request {} to risk evaluation service", loanRequest.id());

        RiskEvaluationRequest request = new RiskEvaluationRequest(
                loanRequest.id().toString(),
                loanRequest.customerId(),
                loanRequest.requestedAmount(),
                loanRequest.termMonths(),
                loanRequest.purpose()
        );

        return webClient.post()
                .uri(riskEvaluationBaseUrl + "/api/v1/risk-evaluation")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RiskEvaluationResponse.class)
                .doOnSuccess(response -> log.info("Risk evaluation completed for loan {}: risk score = {}", 
                        loanRequest.id(), response.riskScore()))
                .doOnError(error -> log.error("Error during risk evaluation for loan {}: {}", 
                        loanRequest.id(), error.getMessage()));
    }

    @Override
    public Mono<RiskEvaluationResponse> getEvaluationStatus(String evaluationId) {
        log.info("Fetching risk evaluation status for evaluationId: {}", evaluationId);

        return webClient.get()
                .uri(riskEvaluationBaseUrl + "/api/v1/risk-evaluation/{evaluationId}", evaluationId)
                .retrieve()
                .bodyToMono(RiskEvaluationResponse.class)
                .doOnSuccess(response -> log.info("Risk evaluation status retrieved: {}", response.status()))
                .doOnError(error -> log.error("Error fetching risk evaluation status: {}", error.getMessage()));
    }

    private Mono<RiskEvaluationResponse> evaluateRiskFallback(LoanRequest loanRequest, Throwable error) {
        log.warn("Circuit breaker fallback triggered for loan request {}: {}", loanRequest.id(), error.getMessage());
        return Mono.just(new RiskEvaluationResponse(
                "FALLBACK-" + UUID.randomUUID().toString(),
                loanRequest.id().toString(),
                0.0,
                "FALLBACK",
                "Risk evaluation service unavailable, request queued for later processing",
                null
        ));
    }

    public record RiskEvaluationRequest(
            String loanRequestId,
            String customerId,
            java.math.BigDecimal requestedAmount,
            Integer termMonths,
            String purpose
    ) {}

    public record RiskEvaluationResponse(
            String evaluationId,
            String loanRequestId,
            Double riskScore,
            String riskLevel,
            String recommendation,
            String evaluatedAt
    ) {}
}