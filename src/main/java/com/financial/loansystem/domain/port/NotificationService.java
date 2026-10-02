package com.financial.loansystem.domain.port;

import com.financial.loansystem.domain.model.LoanRequest;
import reactor.core.publisher.Mono;

/**
 * Puerto para el envío de notificaciones al cliente sobre el estado de su solicitud.
 * Define el contrato que la infraestructura debe implementar para comunicar
 * los resultados del procesamiento de préstamos al solicitante.
 */
public interface NotificationService {

    /**
     * Envía una notificación al cliente indicando que su solicitud fue recibida
     * y está siendo procesada.
     *
     * @param loanRequest la solicitud de préstamo recibida
     * @return Mono que completa cuando la notificación fue enviada exitosamente
     */
    Mono<Void> notifyRequestReceived(LoanRequest loanRequest);

    /**
     * Envía una notificación al cliente con el resultado de la evaluación de riesgos.
     *
     * @param loanRequest la solicitud con el resultado de la evaluación
     * @return Mono que completa cuando la notificación fue enviada exitosamente
     */
    Mono<Void> notifyRiskEvaluationComplete(LoanRequest loanRequest);

    /**
     * Envía una notificación al cliente indicando que su solicitud fue aprobada.
     *
     * @param loanRequest la solicitud aprobada con el monto aprobado
     * @return Mono que completa cuando la notificación fue enviada exitosamente
     */
    Mono<Void> notifyApproved(LoanRequest loanRequest);

    /**
     * Envía una notificación al cliente indicando que su solicitud fue rechazada.
     *
     * @param loanRequest la solicitud rechazada con la razón del rechazo
     * @return Mono que completa cuando la notificación fue enviada exitosamente
     */
    Mono<Void> notifyRejected(LoanRequest loanRequest);
}