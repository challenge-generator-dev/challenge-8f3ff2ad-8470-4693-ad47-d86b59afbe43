package com.financial.loansystem.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanRequest(
    @NotNull(message = "Loan request ID cannot be null")
    UUID id,

    @NotNull(message = "Customer ID cannot be null")
    UUID customerId,

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "1000.00", message = "Amount must be at least 1000")
    @DecimalMax(value = "1000000.00", message = "Amount must be at most 1000000")
    BigDecimal amount,

    @NotNull(message = "Term cannot be null")
    @Min(value = 6, message = "Term must be at least 6 months")
    @Max(value = 84, message = "Term must be at most 84 months")
    Integer term,

    @NotNull(message = "Interest rate cannot be null")
    @DecimalMin(value = "0.01", message = "Interest rate must be at least 0.01%")
    @DecimalMax(value = "36.00", message = "Interest rate must be at most 36%")
    BigDecimal interestRate,

    @NotNull(message = "Purpose cannot be null")
    @Size(min = 3, max = 100, message = "Purpose must be between 3 and 100 characters")
    String purpose,

    @NotBlank(message = "Channel cannot be blank")
    String channel,

    @NotNull(message = "Creation date cannot be null")
    LocalDate creationDate,

    @NotNull(message = "Status cannot be null")
    LoanStatus status,

    String riskEvaluationId,
    BigDecimal approvedAmount,
    LocalDate approvalDate,
    String rejectionReason
) {
    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED,
        IN_REVIEW
    }

    public LoanRequest withRiskEvaluationId(String riskEvaluationId) {
        return new LoanRequest(
            this.id,
            this.customerId,
            this.amount,
            this.term,
            this.interestRate,
            this.purpose,
            this.channel,
            this.creationDate,
            this.status,
            riskEvaluationId,
            this.approvedAmount,
            this.approvalDate,
            this.rejectionReason
        );
    }

    public LoanRequest withStatus(LoanStatus status) {
        return new LoanRequest(
            this.id,
            this.customerId,
            this.amount,
            this.term,
            this.interestRate,
            this.purpose,
            this.channel,
            this.creationDate,
            status,
            this.riskEvaluationId,
            this.approvedAmount,
            this.approvalDate,
            this.rejectionReason
        );
    }

    public LoanRequest withApprovedAmount(BigDecimal approvedAmount) {
        return new LoanRequest(
            this.id,
            this.customerId,
            this.amount,
            this.term,
            this.interestRate,
            this.purpose,
            this.channel,
            this.creationDate,
            LoanStatus.APPROVED,
            this.riskEvaluationId,
            approvedAmount,
            LocalDate.now(),
            null
        );
    }

    public LoanRequest withRejectionReason(String rejectionReason) {
        return new LoanRequest(
            this.id,
            this.customerId,
            this.amount,
            this.term,
            this.interestRate,
            this.purpose,
            this.channel,
            this.creationDate,
            LoanStatus.REJECTED,
            this.riskEvaluationId,
            null,
            null,
            rejectionReason
        );
    }
}