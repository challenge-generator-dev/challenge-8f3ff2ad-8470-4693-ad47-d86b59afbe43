package com.financial.loansystem.domain.port;

import com.financial.loansystem.domain.model.LoanRequest;
import reactor.core.publisher.Mono;

/**
 * Puerto para la comunicación con el sistema externo de evaluación de riesgos.
 * Define el contrato que la infraestructura debe implementar para invocar
 * al motor de scoring crediticio y obtener la evaluación de riesgo del solicitante.
 */
public interface RiskEvaluationClient {

    /**
     * Envía una solicitud de préstamo al sistema de evaluación de riesgos
     * y obtiene la evaluación correspondiente.
     *
     * @param loanRequest la solicitud de préstamo enriquecida con datos del cliente
     * @return Mono que emite la solicitud actualizada con el ID de evaluación de riesgos
     */
    Mono<LoanRequest> evaluateRisk(LoanRequest loanRequest);

    /**
     * Verifica la conectividad con el sistema de evaluación de riesgos.
     * Utilizado para health checks y validación de disponibilidad del servicio externo.
     *
     * @return Mono que emite true si el servicio está disponible, false en caso contrario
     */
    Mono<Boolean> isAvailable();
}