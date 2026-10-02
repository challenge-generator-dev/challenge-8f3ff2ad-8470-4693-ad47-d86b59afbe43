package com.financial.loansystem.application.usecase;

import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.model.LoanRequest.LoanStatus;
import com.financial.loansystem.domain.port.LoanRequestRepository;
import com.financial.loansystem.domain.port.NotificationService;
import com.financial.loansystem.domain.port.RiskEvaluationClient;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Caso de uso principal que orquesta el procesamiento completo de una solicitud de préstamo.
 * Implementa el flujo de validación end-to-end: recepción, validación de datos,
 * enriquecimiento, evaluación de riesgos y notificación al cliente.
 */
@Service
public class ProcessLoanRequestUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessLoanRequestUseCase.class);

    private final LoanRequestRepository loanRequestRepository;
    private final RiskEvaluationClient riskEvaluationClient;
    private final NotificationService notificationService;

    public ProcessLoanRequestUseCase(
            LoanRequestRepository loanRequestRepository,
            RiskEvaluationClient riskEvaluationClient,
            NotificationService notificationService) {
        this.loanRequestRepository = loanRequestRepository;
        this.riskEvaluationClient = riskEvaluationClient;
        this.notificationService = notificationService;
    }

    /**
     * Procesa una solicitud de préstamo ejecutando el flujo completo de validación.
     * Este método implementa la lógica de idempotencia verificando si ya existe
     * una solicitud con la misma clave antes de proceder con el procesamiento.
     *
     * @param loanRequest la solicitud de préstamo recibida del cliente
     * @return Mono que emite la solicitud procesada con su estado final
     */
    public Mono<LoanRequest> execute(@Valid LoanRequest loanRequest) {
        log.info("Iniciando procesamiento de solicitud de préstamo para cliente: {}", 
                loanRequest.customerId());

        IdempotencyKey idempotencyKey = buildIdempotencyKey(loanRequest);

        return loanRequestRepository.existsByIdempotencyKey(idempotencyKey)
                .flatMap(exists -> {
                    if (exists) {
                        log.info("Solicitud duplicada detectada para clave de idempotencia: {}", 
                                idempotencyKey.toCompositeKey());
                        return loanRequestRepository.findByIdempotencyKey(idempotencyKey)
                                .flatMap(existing -> {
                                    log.warn("Retornando solicitud existente con ID: {}", existing.id());
                                    return Mono.just(existing);
                                });
                    }
                    return processNewRequest(loanRequest, idempotencyKey);
                });
    }

    /**
     * Procesa una nueva solicitud de préstamo que no existe previamente.
     * Ejecuta las fases: persistencia inicial, validación de datos, enriquecimiento,
     * envío a evaluación de riesgos y actualización del estado.
     */
    private Mono<LoanRequest> processNewRequest(LoanRequest loanRequest, IdempotencyKey idempotencyKey) {
        LoanRequest pendingRequest = createInitialRequest(loanRequest, idempotencyKey);

        return loanRequestRepository.save(pendingRequest)
                .flatMap(savedRequest -> validateAndEnrichRequest(savedRequest)
                        .flatMap(enrichedRequest -> sendToRiskEvaluation(enrichedRequest))
                        .flatMap(evaluatedRequest -> handleRiskEvaluationResult(evaluatedRequest))
                        .flatMap(finalRequest -> notifyCustomer(finalRequest))
                        .switchIfEmpty(Mono.defer(() -> {
                            log.error("Error en el procesamiento de la solicitud: {}", savedRequest.id());
                            return Mono.just(savedRequest);
                        })));
    }

    /**
     * Crea la solicitud inicial con el estado PENDING y los datos básicos.
     */
    private LoanRequest createInitialRequest(LoanRequest loanRequest, IdempotencyKey idempotencyKey) {
        return new LoanRequest(
                UUID.randomUUID(),
                loanRequest.customerId(),
                loanRequest.requestedAmount(),
                loanRequest.loanTermMonths(),
                loanRequest.loanPurpose(),
                loanRequest.channel(),
                loanRequest.operationNumber(),
                idempotencyKey,
                LoanStatus.PENDING,
                null,
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );
    }

    /**
     * Valida y enriquese la solicitud de préstamo con datos adicionales del cliente.
     * Esta fase implementa la validación de reglas de negocio y el enriquecimiento
     * con información necesaria para la evaluación de riesgos.
     */
    private Mono<LoanRequest> validateAndEnrichRequest(LoanRequest request) {
        log.info("Validando y enriquciendo solicitud: {}", request.id());

        return validateBusinessRules(request)
                .map(this::enrichWithCustomerData)
                .doOnSuccess(enriched -> log.info("Solicitud enriquecida exitosamente: {}", enriched.id()))
                .doOnError(error -> log.error("Error en validación: {}", error.getMessage()));
    }

    /**
     * Valida las reglas de negocio específicas del dominio de préstamos.
     */
    private Mono<LoanRequest> validateBusinessRules(LoanRequest request) {
        if (request.requestedAmount() == null || request.requestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new IllegalArgumentException("El monto solicitado debe ser mayor a cero"));
        }

        BigDecimal maxLoanAmount = new BigDecimal("500000.00");
        if (request.requestedAmount().compareTo(maxLoanAmount) > 0) {
            return Mono.error(new IllegalArgumentException(
                    "El monto solicitado excede el límite máximo de: " + maxLoanAmount));
        }

        if (request.loanTermMonths() == null || request.loanTermMonths() < 1 || request.loanTermMonths() > 360) {
            return Mono.error(new IllegalArgumentException(
                    "El plazo del préstamo debe estar entre 1 y 360 meses"));
        }

        if (request.customerId() == null || request.customerId().isBlank()) {
            return Mono.error(new IllegalArgumentException("El ID del cliente es obligatorio"));
        }

        if (request.channel() == null || request.channel().isBlank()) {
            return Mono.error(new IllegalArgumentException("El canal de solicitud es obligatorio"));
        }

        return Mono.just(request);
    }

    /**
     * Enriquese la solicitud con datos adicionales necesarios para la evaluación de riesgos.
     * En un sistema real, esto consultaría un servicio externo de información crediticia.
     */
    private LoanRequest enrichWithCustomerData(LoanRequest request) {
        return request;
    }

    /**
     * Envía la solicitud enriquecida al sistema de evaluación de riesgos y obtiene
     * el resultado de la evaluación crediticia.
     */
    private Mono<LoanRequest> sendToRiskEvaluation(LoanRequest request) {
        log.info("Enviando solicitud a evaluación de riesgos: {}", request.id());

        return riskEvaluationClient.evaluateRisk(request)
                .doOnSuccess(evaluated -> log.info("Evaluación de riesgos completada para: {}", evaluated.id()))
                .doOnError(error -> log.error("Error en evaluación de riesgos: {}", error.getMessage()));
    }

    /**
     * Maneja el resultado de la evaluación de riesgos, actualizando el estado
     * de la solicitud según el resultado obtenido.
     */
    private Mono<LoanRequest> handleRiskEvaluationResult(LoanRequest request) {
        log.info("Procesando resultado de evaluación de riesgos para solicitud: {}", request.id());

        if (request.riskEvaluationId() == null) {
            return Mono.error(new IllegalStateException("No se recibió ID de evaluación de riesgos"));
        }

        LoanRequest updatedWithRiskId = request.withRiskEvaluationId(request.riskEvaluationId());

        return loanRequestRepository.updateWithRiskEvaluation(updatedWithRiskId.id(), request.riskEvaluationId())
                .flatMap(this::determineApprovalOrRejection);
    }

    /**
     * Determina si la solicitud es aprobada o rechazada basándose en la evaluación de riesgos.
     * Esta lógica implementa las reglas del motor de aprobación crediticia.
     */
    private Mono<LoanRequest> determineApprovalOrRejection(LoanRequest request) {
        BigDecimal approvedAmount = calculateApprovedAmount(request);

        if (approvedAmount.compareTo(BigDecimal.ZERO) > 0) {
            return approveRequest(request, approvedAmount);
        } else {
            return rejectRequest(request, "Riesgo crediticio insuficiente para aprobar el préstamo");
        }
    }

    /**
     * Calcula el monto aprobado basándose en la evaluación de riesgos.
     * En un sistema real, esto integraría con el motor de scoring.
     */
    private BigDecimal calculateApprovedAmount(LoanRequest request) {
        BigDecimal requestedAmount = request.requestedAmount();
        BigDecimal maxApprovalRatio = new BigDecimal("0.85");
        return requestedAmount.multiply(maxApprovalRatio).setScale(2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Aprueba la solicitud de préstamo con el monto calculado.
     */
    private Mono<LoanRequest> approveRequest(LoanRequest request, BigDecimal approvedAmount) {
        log.info("Aprobando solicitud: {} con monto: {}", request.id(), approvedAmount);

        LoanRequest approvedRequest = request
                .withStatus(LoanStatus.APPROVED)
                .withApprovedAmount(approvedAmount);

        return loanRequestRepository.updateWithApproval(
                approvedRequest.id(),
                approvedRequest.riskEvaluationId(),
                approvedRequest
        );
    }

    /**
     * Rechaza la solicitud de préstamo con la razón especificada.
     */
    private Mono<LoanRequest> rejectRequest(LoanRequest request, String rejectionReason) {
        log.info("Rechazando solicitud: {} razón: {}", request.id(), rejectionReason);

        LoanRequest rejectedRequest = request
                .withStatus(LoanStatus.REJECTED)
                .withRejectionReason(rejectionReason);

        return loanRequestRepository.updateWithRejection(
                rejectedRequest.id(),
                rejectedRequest.riskEvaluationId(),
                rejectionReason
        );
    }

    /**
     * Envía notificaciones al cliente según el resultado del procesamiento.
     */
    private Mono<LoanRequest> notifyCustomer(LoanRequest request) {
        log.info("Enviando notificación al cliente para solicitud: {}", request.id());

        return switch (request.status()) {
            case APPROVED -> notificationService.notifyApproved(request);
            case REJECTED -> notificationService.notifyRejected(request);
            default -> notificationService.notifyRiskEvaluationComplete(request);
        }.thenReturn(request);
    }

    /**
     * Construye la clave de idempotencia para la solicitud de préstamo.
     * La clave se compone del número de operación y el canal de solicitud.
     */
    private IdempotencyKey buildIdempotencyKey(LoanRequest loanRequest) {
        return new IdempotencyKey(
                loanRequest.operationNumber(),
                loanRequest.channel(),
                "LOAN_REQUEST"
        );
    }
}