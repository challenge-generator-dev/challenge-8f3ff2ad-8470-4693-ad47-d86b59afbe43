# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Validación de extremo a extremo en sistema de préstamos**.

| | |
|---|---|
| Tema | e2e inval |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas reactivas para manejo de alto volumen |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-data-r2dbc 3.5.6
- io.r2dbc:r2dbc-postgresql 1.0.5.RELEASE
- org.springframework.boot:spring-boot-starter-validation n/a
- io.projectreactor:reactor-test n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.springdoc:springdoc-openapi-starter-webflux-ui 2.9.1
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- io.github.resilience4j:resilience4j-reactor 2.2.0
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-data-redis-reactive n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Recepción y persistencia idempotente de solicitudes**: Sistema que recibe y persiste solicitudes de préstamo con idempotencia.
- **Fase 2 — Validación y enriquecimiento de solicitudes**: Sistema que valida y enriquece solicitudes de préstamo antes de enviarlas al sistema de evaluación de riesgos.
- **Fase 3 — Envío y manejo de respuestas del sistema de evaluación de riesgos**: Sistema que envía solicitudes al sistema de evaluación de riesgos y maneja las respuestas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/financial/loansystem/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (71)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/financial/loansystem/LoanSystemApplication.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.OpenAPIDefinition pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/domain/port/LoanRequestRepository.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/domain/port/RiskEvaluationClient.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/domain/port/NotificationService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `reactor.util.retry`
      El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/config/IdempotencyConfig.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClientTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.customerId`
      Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.loanTermMonths`
      Se invoca `loanTermMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.loanPurpose`
      Se invoca `loanPurpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.channel`
      Se invoca `channel` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.operationNumber`
      Se invoca `operationNumber` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.id`
      Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.riskEvaluationId`
      Se invoca `riskEvaluationId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.status`
      Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.id`
      Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.customerId`
      Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.termMonths`
      Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.purpose`
      Se invoca `purpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.status`
      Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.riskEvaluationId`
      Se invoca `riskEvaluationId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.approvedAmount`
      Se invoca `approvedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.rejectionReason`
      Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanStatus.name`
      Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.id`
      Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.customerId`
      Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.termMonths`
      Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.purpose`
      Se invoca `purpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.id`
      Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.customerId`
      Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.approvedAmount`
      Se invoca `approvedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.termMonths`
      Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.rejectionReason`
      Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.status`
      Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequestRequest.operationNumber`
      Se invoca `operationNumber` sobre `LoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `IdempotencyService.checkAndStore`
      Se invoca `checkAndStore` sobre `IdempotencyService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequestRequest.toDomain`
      Se invoca `toDomain` sobre `LoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `ProcessLoanRequestUseCase.findById`
      Se invoca `findById` sobre `ProcessLoanRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.id`
      Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.applicantName`
      Se invoca `applicantName` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.applicantEmail`
      Se invoca `applicantEmail` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.applicantId`
      Se invoca `applicantId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.loanTermMonths`
      Se invoca `loanTermMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.status`
      Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.approvedAmount`
      Se invoca `approvedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.rejectionReason`
      Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.createdAt`
      Se invoca `createdAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.updatedAt`
      Se invoca `updatedAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `NotificationService.sendApprovalNotification`
      Se invoca `sendApprovalNotification` sobre `NotificationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `LoanRequest.id`
      Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `NotificationService.sendRejectionNotification`
      Se invoca `sendRejectionNotification` sobre `NotificationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `LoanRequest.id`
      Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `LoanRequest.clientId`
      Se invoca `clientId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `LoanRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`
      io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (18)

- `src/main/java/com/financial/loansystem/LoanSystemApplication.java`
- `src/main/resources/application.yml`
- `pom.xml`
- `src/main/java/com/financial/loansystem/domain/model/LoanRequest.java`
- `src/main/java/com/financial/loansystem/domain/model/IdempotencyKey.java`
- `src/main/java/com/financial/loansystem/domain/port/LoanRequestRepository.java`
- `src/main/java/com/financial/loansystem/domain/port/RiskEvaluationClient.java`
- `src/main/java/com/financial/loansystem/domain/port/NotificationService.java`
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java`
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java`
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java`
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java`
- `src/main/java/com/financial/loansystem/infrastructure/config/IdempotencyConfig.java`
- `src/main/java/com/financial/loansystem/infrastructure/config/ResilienceConfig.java`
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java`
- `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java`
- `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java`
- `src/test/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClientTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/financial/loansystem`
- `src/main/java/com/financial/loansystem/domain`
- `src/main/java/com/financial/loansystem/domain/model`
- `src/main/java/com/financial/loansystem/domain/port`
- `src/main/java/com/financial/loansystem/application`
- `src/main/java/com/financial/loansystem/application/usecase`
- `src/main/java/com/financial/loansystem/infrastructure`
- `src/main/java/com/financial/loansystem/infrastructure/adapter`
- `src/main/java/com/financial/loansystem/infrastructure/config`
- `src/main/resources`
- `src/test/java/com/financial/loansystem`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas reactivas para manejo de alto volumen**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2
- Brecha que el reto ataca: x

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
