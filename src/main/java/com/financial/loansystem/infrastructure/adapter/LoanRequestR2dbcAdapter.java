package com.financial.loansystem.infrastructure.adapter;


import com.financial.loansystem.domain.model.LoanStatus;
import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.port.LoanRequestRepository;
import io.r2dbc.postgresql.codec.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Repository
public class LoanRequestR2dbcAdapter implements LoanRequestRepository {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestR2dbcAdapter.class);
    private final DatabaseClient databaseClient;

    public LoanRequestR2dbcAdapter(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<LoanRequest> save(LoanRequest loanRequest) {
        log.info("Persisting loan request with idempotency key: {}", loanRequest.idempotencyKey());
        String sql = """
            INSERT INTO loan_requests (
                id, idempotency_key, customer_id, requested_amount, term_months,
                purpose, status, risk_evaluation_id, approved_amount, rejection_reason,
                created_at, updated_at
            ) VALUES (
                :id, :idempotencyKey, :customerId, :requestedAmount, :termMonths,
                :purpose, :status, :riskEvaluationId, :approvedAmount, :rejectionReason,
                :createdAt, :updatedAt
            )
            """;

        return databaseClient.sql(sql)
                .bind("id", loanRequest.id())
                .bind("idempotencyKey", loanRequest.idempotencyKey().toCompositeKey())
                .bind("customerId", loanRequest.customerId())
                .bind("requestedAmount", loanRequest.requestedAmount().doubleValue())
                .bind("termMonths", loanRequest.termMonths())
                .bind("purpose", loanRequest.purpose())
                .bind("status", loanRequest.status().name())
                .bind("riskEvaluationId", loanRequest.riskEvaluationId() != null ? loanRequest.riskEvaluationId() : "")
                .bind("approvedAmount", loanRequest.approvedAmount() != null ? loanRequest.approvedAmount().doubleValue() : 0.0)
                .bind("rejectionReason", loanRequest.rejectionReason() != null ? loanRequest.rejectionReason() : "")
                .bind("createdAt", Instant.now())
                .bind("updatedAt", Instant.now())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> Mono.just(loanRequest))
                .doOnSuccess(saved -> log.info("Loan request saved successfully: {}", saved.id()))
                .doOnError(error -> log.error("Error saving loan request: {}", error.getMessage()));
    }

    @Override
    public Mono<LoanRequest> findById(UUID id) {
        log.debug("Finding loan request by id: {}", id);
        String sql = """
            SELECT id, idempotency_key, customer_id, requested_amount, term_months,
                   purpose, status, risk_evaluation_id, approved_amount, rejection_reason,
                   created_at, updated_at
            FROM loan_requests WHERE id = :id
            """;

        return databaseClient.sql(sql)
                .bind("id", id)
                .map((row, metadata) -> mapRowToLoanRequest(row))
                .first()
                .doOnSuccess(request -> log.debug("Found loan request: {}", request != null ? request.id() : null))
                .doOnError(error -> log.error("Error finding loan request by id: {}", error.getMessage()));
    }

    @Override
    public Mono<Boolean> existsByIdempotencyKey(IdempotencyKey idempotencyKey) {
        log.debug("Checking idempotency key existence: {}", idempotencyKey.toCompositeKey());
        String sql = "SELECT COUNT(*) FROM loan_requests WHERE idempotency_key = :idempotencyKey";

        return databaseClient.sql(sql)
                .bind("idempotencyKey", idempotencyKey.toCompositeKey())
                .map((row, metadata) -> row.get("count", Long.class) > 0)
                .first()
                .defaultIfEmpty(false);
    }

    @Override
    public Mono<LoanRequest> findByIdempotencyKey(IdempotencyKey idempotencyKey) {
        log.debug("Finding loan request by idempotency key: {}", idempotencyKey.toCompositeKey());
        String sql = """
            SELECT id, idempotency_key, customer_id, requested_amount, term_months,
                   purpose, status, risk_evaluation_id, approved_amount, rejection_reason,
                   created_at, updated_at
            FROM loan_requests WHERE idempotency_key = :idempotencyKey
            """;

        return databaseClient.sql(sql)
                .bind("idempotencyKey", idempotencyKey.toCompositeKey())
                .map((row, metadata) -> mapRowToLoanRequest(row))
                .first()
                .doOnSuccess(request -> log.debug("Found loan request by idempotency key: {}", 
                    request != null ? request.id() : null));
    }

    @Override
    public Mono<LoanRequest> updateStatus(UUID id, LoanRequest.LoanStatus status) {
        log.info("Updating loan request status to {} for id: {}", status, id);
        String sql = "UPDATE loan_requests SET status = :status, updated_at = :updatedAt WHERE id = :id";

        return databaseClient.sql(sql)
                .bind("id", id)
                .bind("status", status.name())
                .bind("updatedAt", Instant.now())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> findById(id))
                .doOnSuccess(updated -> log.info("Status updated successfully for loan request: {}", id));
    }

    @Override
    public Mono<LoanRequest> updateWithRiskEvaluation(UUID id, String riskEvaluationId) {
        log.info("Updating loan request with risk evaluation id: {} for loan: {}", riskEvaluationId, id);
        String sql = """
            UPDATE loan_requests 
            SET risk_evaluation_id = :riskEvaluationId, updated_at = :updatedAt 
            WHERE id = :id
            """;

        return databaseClient.sql(sql)
                .bind("id", id)
                .bind("riskEvaluationId", riskEvaluationId)
                .bind("updatedAt", Instant.now())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> findById(id))
                .doOnSuccess(updated -> log.info("Risk evaluation id added to loan request: {}", id));
    }

    @Override
    public Mono<LoanRequest> updateWithApproval(UUID id, String riskEvaluationId, LoanRequest approvedRequest) {
        log.info("Updating loan request with approval for id: {}", id);
        String sql = """
            UPDATE loan_requests 
            SET status = :status, 
                risk_evaluation_id = :riskEvaluationId,
                approved_amount = :approvedAmount,
                rejection_reason = '',
                updated_at = :updatedAt 
            WHERE id = :id
            """;

        return databaseClient.sql(sql)
                .bind("id", id)
                .bind("status", LoanRequest.LoanStatus.APPROVED.name())
                .bind("riskEvaluationId", riskEvaluationId)
                .bind("approvedAmount", approvedRequest.approvedAmount().doubleValue())
                .bind("updatedAt", Instant.now())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> findById(id))
                .doOnSuccess(updated -> log.info("Loan request approved: {}", id));
    }

    @Override
    public Mono<LoanRequest> updateWithRejection(UUID id, String riskEvaluationId, String rejectionReason) {
        log.info("Updating loan request with rejection for id: {}", id);
        String sql = """
            UPDATE loan_requests 
            SET status = :status, 
                risk_evaluation_id = :riskEvaluationId,
                approved_amount = 0,
                rejection_reason = :rejectionReason,
                updated_at = :updatedAt 
            WHERE id = :id
            """;

        return databaseClient.sql(sql)
                .bind("id", id)
                .bind("status", LoanRequest.LoanStatus.REJECTED.name())
                .bind("riskEvaluationId", riskEvaluationId)
                .bind("rejectionReason", rejectionReason)
                .bind("updatedAt", Instant.now())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> findById(id))
                .doOnSuccess(updated -> log.info("Loan request rejected: {}", id));
    }

    private LoanRequest mapRowToLoanRequest(io.r2dbc.spi.Row row) {
        UUID id = row.get("id", UUID.class);
        String idempotencyKeyStr = row.get("idempotency_key", String.class);
        String customerId = row.get("customer_id", String.class);
        BigDecimal requestedAmount = BigDecimal.valueOf(row.get("requested_amount", Double.class));
        Integer termMonths = row.get("term_months", Integer.class);
        String purpose = row.get("purpose", String.class);
        String statusStr = row.get("status", String.class);
        String riskEvaluationId = row.get("risk_evaluation_id", String.class);
        BigDecimal approvedAmount = row.get("approved_amount", Double.class) != null && row.get("approved_amount", Double.class) > 0
            ? BigDecimal.valueOf(row.get("approved_amount", Double.class))
            : null;
        String rejectionReason = row.get("rejection_reason", String.class);

        LoanRequest.LoanStatus status = LoanRequest.LoanStatus.valueOf(statusStr);
        IdempotencyKey idempotencyKey = IdempotencyKey.fromCompositeKey(idempotencyKeyStr);

        LoanRequest.LoanRequestBuilder builder = LoanRequest.builder()
                .id(id)
                .idempotencyKey(idempotencyKey)
                .customerId(customerId)
                .requestedAmount(requestedAmount)
                .termMonths(termMonths)
                .purpose(purpose)
                .status(status);

        if (riskEvaluationId != null && !riskEvaluationId.isEmpty()) {
            builder.riskEvaluationId(riskEvaluationId);
        }
        if (approvedAmount != null) {
            builder.approvedAmount(approvedAmount);
        }
        if (rejectionReason != null && !rejectionReason.isEmpty()) {
            builder.rejectionReason(rejectionReason);
        }

        return builder.build();
    }
}