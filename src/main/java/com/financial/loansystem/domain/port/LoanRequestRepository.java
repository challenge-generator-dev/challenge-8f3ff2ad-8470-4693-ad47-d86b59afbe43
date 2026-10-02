package com.financial.loansystem.domain.port;


import com.financial.loansystem.domain.model.LoanStatus;
import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface LoanRequestRepository {
    Mono<LoanRequest> save(LoanRequest loanRequest);

    Mono<LoanRequest> findById(UUID id);

    Mono<Boolean> existsByIdempotencyKey(IdempotencyKey idempotencyKey);

    Mono<LoanRequest> findByIdempotencyKey(IdempotencyKey idempotencyKey);

    Mono<LoanRequest> updateStatus(UUID id, LoanRequest.LoanStatus status);

    Mono<LoanRequest> updateWithRiskEvaluation(UUID id, String riskEvaluationId);

    Mono<LoanRequest> updateWithApproval(UUID id, String riskEvaluationId, LoanRequest approvedRequest);

    Mono<LoanRequest> updateWithRejection(UUID id, String riskEvaluationId, String rejectionReason);
}