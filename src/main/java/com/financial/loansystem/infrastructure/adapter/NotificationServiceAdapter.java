package com.financial.loansystem.infrastructure.adapter;

import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.port.NotificationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Component
public class NotificationServiceAdapter implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceAdapter.class);
    private final WebClient webClient;
    private final String notificationServiceBaseUrl;
    private final boolean notificationEnabled;

    public NotificationServiceAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${external-services.notification.base-url:http://notification-service:8080}") String notificationServiceBaseUrl,
            @Value("${external-services.notification.enabled:true}") boolean notificationEnabled) {
        this.webClient = webClientBuilder.build();
        this.notificationServiceBaseUrl = notificationServiceBaseUrl;
        this.notificationEnabled = notificationEnabled;
    }

    @Override
    @CircuitBreaker(name = "notificationCircuitBreaker", fallbackMethod = "sendNotificationFallback")
    @RateLimiter(name = "notificationRateLimiter")
    public Mono<NotificationResponse> sendApprovalNotification(LoanRequest loanRequest) {
        if (!notificationEnabled) {
            log.debug("Notifications disabled, skipping approval notification for loan: {}", loanRequest.id());
            return Mono.just(new NotificationResponse("SKIPPED", loanRequest.id().toString(), "Notifications disabled"));
        }

        log.info("Sending approval notification for loan: {}", loanRequest.id());

        NotificationRequest request = new NotificationRequest(
                loanRequest.customerId(),
                "LOAN_APPROVED",
                "Your loan request has been approved",
                Map.of(
                        "loanId", loanRequest.id().toString(),
                        "approvedAmount", loanRequest.approvedAmount().toString(),
                        "termMonths", loanRequest.termMonths().toString()
                )
        );

        return webClient.post()
                .uri(notificationServiceBaseUrl + "/api/v1/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(NotificationResponse.class)
                .doOnSuccess(response -> log.info("Approval notification sent for loan: {}", loanRequest.id()))
                .doOnError(error -> log.error("Error sending approval notification: {}", error.getMessage()));
    }

    @Override
    @CircuitBreaker(name = "notificationCircuitBreaker", fallbackMethod = "sendNotificationFallback")
    @RateLimiter(name = "notificationRateLimiter")
    public Mono<NotificationResponse> sendRejectionNotification(LoanRequest loanRequest) {
        if (!notificationEnabled) {
            log.debug("Notifications disabled, skipping rejection notification for loan: {}", loanRequest.id());
            return Mono.just(new NotificationResponse("SKIPPED", loanRequest.id().toString(), "Notifications disabled"));
        }

        log.info("Sending rejection notification for loan: {}", loanRequest.id());

        NotificationRequest request = new NotificationRequest(
                loanRequest.customerId(),
                "LOAN_REJECTED",
                "Your loan request has been rejected",
                Map.of(
                        "loanId", loanRequest.id().toString(),
                        "rejectionReason", loanRequest.rejectionReason() != null ? loanRequest.rejectionReason() : "Not specified"
                )
        );

        return webClient.post()
                .uri(notificationServiceBaseUrl + "/api/v1/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(NotificationResponse.class)
                .doOnSuccess(response -> log.info("Rejection notification sent for loan: {}", loanRequest.id()))
                .doOnError(error -> log.error("Error sending rejection notification: {}", error.getMessage()));
    }

    @Override
    @CircuitBreaker(name = "notificationCircuitBreaker", fallbackMethod = "sendNotificationFallback")
    @RateLimiter(name = "notificationRateLimiter")
    public Mono<NotificationResponse> sendStatusUpdateNotification(LoanRequest loanRequest, String previousStatus) {
        if (!notificationEnabled) {
            log.debug("Notifications disabled, skipping status update notification for loan: {}", loanRequest.id());
            return Mono.just(new NotificationResponse("SKIPPED", loanRequest.id().toString(), "Notifications disabled"));
        }

        log.info("Sending status update notification for loan: {} ({} -> {})", 
                loanRequest.id(), previousStatus, loanRequest.status());

        String message = String.format("Your loan request status has been updated from %s to %s", 
                previousStatus, loanRequest.status());

        NotificationRequest request = new NotificationRequest(
                loanRequest.customerId(),
                "LOAN_STATUS_UPDATE",
                message,
                Map.of(
                        "loanId", loanRequest.id().toString(),
                        "previousStatus", previousStatus,
                        "currentStatus", loanRequest.status().name()
                )
        );

        return webClient.post()
                .uri(notificationServiceBaseUrl + "/api/v1/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(NotificationResponse.class)
                .doOnSuccess(response -> log.info("Status update notification sent for loan: {}", loanRequest.id()))
                .doOnError(error -> log.error("Error sending status update notification: {}", error.getMessage()));
    }

    private Mono<NotificationResponse> sendNotificationFallback(LoanRequest loanRequest, Throwable error) {
        log.warn("Notification fallback triggered for loan {}: {}", loanRequest.id(), error.getMessage());
        return Mono.just(new NotificationResponse(
                "FALLBACK",
                loanRequest.id().toString(),
                "Notification service unavailable, notification queued for retry"
        ));
    }

    public record NotificationRequest(
            String recipientId,
            String notificationType,
            String message,
            Map<String, String> metadata
    ) {}

    public record NotificationResponse(
            String status,
            String referenceId,
            String message
    ) {}
}