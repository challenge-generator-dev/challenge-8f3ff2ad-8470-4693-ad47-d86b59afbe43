package com.financial.loansystem.infrastructure.adapter;


import com.financial.loansystem.domain.model.LoanStatus;
import com.financial.loansystem.application.usecase.ProcessLoanRequestUseCase;
import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.infrastructure.config.IdempotencyConfig.IdempotencyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loan-requests")
public class LoanRequestController {

    private final ProcessLoanRequestUseCase processLoanRequestUseCase;
    private final IdempotencyService idempotencyService;

    public LoanRequestController(ProcessLoanRequestUseCase processLoanRequestUseCase,
                                  IdempotencyService idempotencyService) {
        this.processLoanRequestUseCase = processLoanRequestUseCase;
        this.idempotencyService = idempotencyService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LoanRequestResponse>> createLoanRequest(
            @Valid @RequestBody LoanRequestRequest request,
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKeyHeader,
            @RequestHeader(value = "X-Channel", defaultValue = "WEB") String channel) {

        UUID requestId = UUID.randomUUID();

        IdempotencyKey idempotencyKey = buildIdempotencyKey(
                request.operationNumber(),
                channel,
                idempotencyKeyHeader
        );

        return idempotencyService.checkAndStore(idempotencyKey, requestId)
                .flatMap(check -> {
                    if (check.isDuplicate()) {
                        return processLoanRequestUseCase.execute(request.toDomain(requestId))
                                .map(this::toResponse)
                                .map(ResponseEntity::ok);
                    }

                    LoanRequest loanRequest = request.toDomain(requestId);
                    return processLoanRequestUseCase.execute(loanRequest)
                            .map(saved -> {
                                LoanRequestResponse response = toResponse(saved);
                                return ResponseEntity.status(HttpStatus.CREATED).body(response);
                            });
                });
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LoanRequestResponse>> getLoanRequest(@PathVariable UUID id) {
        return processLoanRequestUseCase.findById(id)
                .map(loanRequest -> ResponseEntity.ok(toResponse(loanRequest)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitud de préstamo no encontrada")));
    }

    private IdempotencyKey buildIdempotencyKey(String operationNumber, String channel, String customKey) {
        String keyValue = customKey != null ? customKey : operationNumber;
        return new IdempotencyKey(keyValue, channel);
    }

    private LoanRequestResponse toResponse(LoanRequest loanRequest) {
        return new LoanRequestResponse(
                loanRequest.id().toString(),
                loanRequest.applicantName(),
                loanRequest.applicantEmail(),
                loanRequest.applicantId(),
                loanRequest.requestedAmount(),
                loanRequest.loanTermMonths(),
                loanRequest.status().name(),
                loanRequest.approvedAmount(),
                loanRequest.rejectionReason(),
                loanRequest.createdAt().toString(),
                loanRequest.updatedAt() != null ? loanRequest.updatedAt().toString() : null
        );
    }

    public record LoanRequestRequest(
            String operationNumber,
            String applicantName,
            String applicantId,
            String applicantEmail,
            String applicantPhone,
            BigDecimal requestedAmount,
            Integer loanTermMonths,
            String loanPurpose,
            BigDecimal annualIncome,
            LocalDate birthDate,
            String employmentStatus,
            BigDecimal monthlyExpenses
    ) {
        public LoanRequest toDomain(UUID requestId) {
            return new LoanRequest(
                    requestId,
                    operationNumber,
                    applicantName,
                    applicantId,
                    applicantEmail,
                    applicantPhone,
                    requestedAmount,
                    loanTermMonths,
                    loanPurpose,
                    annualIncome,
                    birthDate,
                    employmentStatus,
                    monthlyExpenses,
                    new IdempotencyKey(operationNumber, "WEB"),
                    LoanRequest.LoanStatus.PENDING,
                    null,
                    null,
                    null,
                    java.time.Instant.now(),
                    null
            );
        }
    }

    public record LoanRequestResponse(
            String id,
            String applicantName,
            String applicantEmail,
            String applicantId,
            BigDecimal requestedAmount,
            Integer loanTermMonths,
            String status,
            BigDecimal approvedAmount,
            String rejectionReason,
            String createdAt,
            String updatedAt
    ) {}
}