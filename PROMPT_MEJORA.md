# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/financial/loansystem/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/financial/loansystem/LoanSystemApplication.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.OpenAPIDefinition pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/domain/port/LoanRequestRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/domain/port/RiskEvaluationClient.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/domain/port/NotificationService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/config/IdempotencyConfig.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClientTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.customerId`: Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.requestedAmount`: Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.loanTermMonths`: Se invoca `loanTermMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.loanPurpose`: Se invoca `loanPurpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.channel`: Se invoca `channel` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.operationNumber`: Se invoca `operationNumber` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.id`: Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.riskEvaluationId`: Se invoca `riskEvaluationId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java` — `LoanRequest.status`: Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.id`: Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.customerId`: Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.requestedAmount`: Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.termMonths`: Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.purpose`: Se invoca `purpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.status`: Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.riskEvaluationId`: Se invoca `riskEvaluationId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.approvedAmount`: Se invoca `approvedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanRequest.rejectionReason`: Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java` — `LoanStatus.name`: Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.id`: Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.customerId`: Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.requestedAmount`: Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.termMonths`: Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java` — `LoanRequest.purpose`: Se invoca `purpose` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.id`: Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.customerId`: Se invoca `customerId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.approvedAmount`: Se invoca `approvedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.termMonths`: Se invoca `termMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.rejectionReason`: Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java` — `LoanRequest.status`: Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequestRequest.operationNumber`: Se invoca `operationNumber` sobre `LoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `IdempotencyService.checkAndStore`: Se invoca `checkAndStore` sobre `IdempotencyService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequestRequest.toDomain`: Se invoca `toDomain` sobre `LoanRequestRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `ProcessLoanRequestUseCase.findById`: Se invoca `findById` sobre `ProcessLoanRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.id`: Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.applicantName`: Se invoca `applicantName` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.applicantEmail`: Se invoca `applicantEmail` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.applicantId`: Se invoca `applicantId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.requestedAmount`: Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.loanTermMonths`: Se invoca `loanTermMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.status`: Se invoca `status` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.approvedAmount`: Se invoca `approvedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.rejectionReason`: Se invoca `rejectionReason` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.createdAt`: Se invoca `createdAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java` — `LoanRequest.updatedAt`: Se invoca `updatedAt` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `NotificationService.sendApprovalNotification`: Se invoca `sendApprovalNotification` sobre `NotificationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `LoanRequest.id`: Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java` — `NotificationService.sendRejectionNotification`: Se invoca `sendRejectionNotification` sobre `NotificationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `LoanRequest.id`: Se invoca `id` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `LoanRequest.clientId`: Se invoca `clientId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java` — `LoanRequest.requestedAmount`: Se invoca `requestedAmount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`: io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2

### Brecha de conocimiento
x

### Reto
- Tema: e2e inval
- Seniority: senior-l2
- Tipo: practical
- Título: Validación de extremo a extremo en sistema de préstamos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Recepción y persistencia idempotente de solicitudes — objetivo: Asegurar que las solicitudes de préstamo se reciban y persistan de manera idempotente. — entregable (NO resolver): Sistema que recibe y persiste solicitudes de préstamo con idempotencia.
- Fase 2: Validación y enriquecimiento de solicitudes — objetivo: Validar y enriquecer las solicitudes de préstamo antes de su envío al sistema de evaluación de riesgos. — entregable (NO resolver): Sistema que valida y enriquece solicitudes de préstamo antes de enviarlas al sistema de evaluación de riesgos.
- Fase 3: Envío y manejo de respuestas del sistema de evaluación de riesgos — objetivo: Enviar las solicitudes validadas y enriquecidas al sistema de evaluación de riesgos y manejar las respuestas. — entregable (NO resolver): Sistema que envía solicitudes al sistema de evaluación de riesgos y maneja las respuestas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/main/java/com/financial/loansystem/LoanSystemApplication.java ===
package com.financial.loansystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
@EnableWebFlux
@OpenAPIDefinition(info = @Info(title = "Loan System API", version = "1.0", description = "API para procesamiento de solicitudes de préstamos con validación e2e"))
public class LoanSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanSystemApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> healthCheckRoute() {
        return route(GET("/health"),
            request -> ServerResponse.ok().bodyValue("Loan System is running"));
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: loan-system
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/loan_db
    username: loan_user
    password: loan_password
    pool:
      enabled: true
      initial-size: 10
      max-size: 50
      max-idle-time: 30m
      validation-query: SELECT 1
  redis:
    host: localhost
    port: 6379
    timeout: 5000ms
    lettuce:
      pool:
        max-active: 16
        max-idle: 8
        min-idle: 4
  webflux:
    base-path: /api/loans

resilience4j:
  circuitbreaker:
    instances:
      riskEvaluation:
        registerHealthIndicator: true
        slidingWindowType: TIME_BASED
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
      loanProcessing:
        registerHealthIndicator: true
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 20
        minimumNumberOfCalls: 10
        permittedNumberOfCallsInHalfOpenState: 5
        waitDurationInOpenState: 10s
        failureRateThreshold: 60
  retry:
    instances:
      riskEvaluation:
        maxAttempts: 3
        waitDuration: 1s
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
  ratelimiter:
    instances:
      loanRequests:
        limitForPeriod: 1500
        limitRefreshPeriod: 1s
        timeoutDuration: 0
  bulkhead:
    instances:
      loanProcessing:
        maxConcurrentCalls: 100
        maxWaitDuration: 10ms

server:
  port: 8080
  netty:
    connection-timeout: 2000ms
    idle-timeout: 30000ms

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: alpha
    tagsSorter: alpha
    disable-swagger-default-url: true

logging:
  level:
    org.springframework.r2dbc: DEBUG
    com.financial.loansystem: INFO
    io.r2dbc.postgresql.QUERY: DEBUG
    io.r2dbc.postgresql.PARAM: DEBUG
    reactor.netty.http.client: DEBUG

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.financial</groupId>
    <artifactId>loan-system</artifactId>
    <version>1.0.0</version>
    <name>loan-system</name>
    <description>Sistema de préstamos con validación e2e reactiva</description>

    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>io.r2dbc</groupId>
            <artifactId>r2dbc-postgresql</artifactId>
            <version>1.0.5.RELEASE</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>2.2.0</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>2.2.0</version>
        </dependency>

        <!-- Documentation -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
            <version>2.9.1</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <compilerArgs>
                        <arg>--enable-preview</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/financial/loansystem/domain/model/LoanRequest.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/domain/model/IdempotencyKey.java ===
package com.financial.loansystem.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public record IdempotencyKey(
    @NotBlank(message = "Operation cannot be blank")
    @Size(max = 50, message = "Operation must be at most 50 characters")
    String operation,

    @NotBlank(message = "Channel cannot be blank")
    @Size(max = 20, message = "Channel must be at most 20 characters")
    String channel
) {
    public IdempotencyKey {
        Objects.requireNonNull(operation, "Operation cannot be null");
        Objects.requireNonNull(channel, "Channel cannot be null");
    }

    public String toCompositeKey() {
        return operation + "#" + channel;
    }

    public static IdempotencyKey fromCompositeKey(String compositeKey) {
        String[] parts = compositeKey.split("#");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid composite key format");
        }
        return new IdempotencyKey(parts[0], parts[1]);
    }
}

// === ARCHIVO: src/main/java/com/financial/loansystem/domain/port/LoanRequestRepository.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/domain/port/RiskEvaluationClient.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/domain/port/NotificationService.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCase.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapter.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClient.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/infrastructure/adapter/NotificationServiceAdapter.java ===
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

// === ARCHIVO: src/main/java/com/financial/loansystem/infrastructure/config/IdempotencyConfig.java ===
package com.financial.loansystem.infrastructure.config;

import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.port.LoanRequestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class IdempotencyConfig {

    private static final Duration IDEMPOTENCY_TTL = Duration.ofHours(24);

    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveStringRedisTemplate(connectionFactory);
    }

    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        StringRedisSerializer serializer = new StringRedisSerializer();
        RedisSerializationContext<String, String> context = RedisSerializationContext
                .<String, String>newSerializationContext(serializer)
                .key(serializer)
                .value(serializer)
                .hashKey(serializer)
                .hashValue(serializer)
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }

    @Bean
    public IdempotencyService idempotencyService(
            ReactiveRedisTemplate<String, String> redisTemplate,
            LoanRequestRepository loanRequestRepository) {
        return new IdempotencyService(redisTemplate, loanRequestRepository);
    }

    public static class IdempotencyService {

        private final ReactiveRedisTemplate<String, String> redisTemplate;
        private final LoanRequestRepository loanRequestRepository;

        public IdempotencyService(ReactiveRedisTemplate<String, String> redisTemplate,
                                   LoanRequestRepository loanRequestRepository) {
            this.redisTemplate = redisTemplate;
            this.loanRequestRepository = loanRequestRepository;
        }

        public Mono<IdempotencyCheck> checkAndStore(IdempotencyKey key, UUID requestId) {
            String compositeKey = key.toCompositeKey();

            return redisTemplate.hasKey(compositeKey)
                    .flatMap(exists -> {
                        if (Boolean.TRUE.equals(exists)) {
                            String storedRequestId = redisTemplate.opsForValue().get(compositeKey).block();
                            return Mono.just(new IdempotencyCheck(
                                    true,
                                    storedRequestId != null ? UUID.fromString(storedRequestId) : null,
                                    "Ya existe una solicitud con esta clave de idempotencia"
                            ));
                        }
                        return redisTemplate.opsForValue()
                                .set(compositeKey, requestId.toString(), IDEMPOTENCY_TTL)
                                .then(Mono.just(new IdempotencyCheck(false, null, null)));
                    });
        }

        public Mono<Boolean> existsInDatabase(IdempotencyKey key) {
            return loanRequestRepository.existsByIdempotencyKey(key);
        }

        public record IdempotencyCheck(boolean isDuplicate, UUID existingRequestId, String message) {}
    }
}

// === ARCHIVO: src/main/java/com/financial/loansystem/infrastructure/config/ResilienceConfig.java ===
package com.financial.loansystem.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(60))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(100)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(500))
                .build();
        return RateLimiterRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/financial/loansystem/infrastructure/adapter/LoanRequestController.java ===
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

// === ARCHIVO: src/test/java/com/financial/loansystem/application/usecase/ProcessLoanRequestUseCaseTest.java ===
package com.financial.loansystem.application.usecase;

import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.model.LoanRequest.LoanStatus;
import com.financial.loansystem.domain.port.LoanRequestRepository;
import com.financial.loansystem.domain.port.NotificationService;
import com.financial.loansystem.domain.port.RiskEvaluationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessLoanRequestUseCase - Tests de integración")
class ProcessLoanRequestUseCaseTest {

    @Mock
    private LoanRequestRepository loanRequestRepository;

    @Mock
    private RiskEvaluationClient riskEvaluationClient;

    @Mock
    private NotificationService notificationService;

    private ProcessLoanRequestUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProcessLoanRequestUseCase(
            loanRequestRepository,
            riskEvaluationClient,
            notificationService
        );
    }

    @Test
    @DisplayName("Debe procesar solicitud nueva exitosamente cuando no existe clave de idempotencia")
    void shouldProcessNewRequestSuccessfullyWhenIdempotencyKeyDoesNotExist() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-001",
            new BigDecimal("50000"),
            36,
            "PERSONAL",
            "channel-web",
            "OP-2024-001",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        IdempotencyKey idempotencyKey = IdempotencyKey.fromCompositeKey("OP-2024-001|card-web");

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just("RISK-EVAL-001"));
        when(loanRequestRepository.updateWithRiskEvaluation(any(UUID.class), eq("RISK-EVAL-001")))
            .thenReturn(Mono.just(request.withRiskEvaluationId("RISK-EVAL-001").withStatus(LoanStatus.RISK_ASSESSED)));
        when(loanRequestRepository.updateWithApproval(any(UUID.class), eq("RISK-EVAL-001"), any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.APPROVED).withApprovedAmount(new BigDecimal("50000"))));
        when(notificationService.sendApprovalNotification(any(LoanRequest.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.APPROVED);
                assertThat(result.approvedAmount()).isEqualTo(new BigDecimal("50000"));
            })
            .verifyComplete();

        verify(loanRequestRepository).save(any(LoanRequest.class));
        verify(riskEvaluationClient).evaluateRisk(any(LoanRequest.class));
        verify(notificationService).sendApprovalNotification(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe retornar solicitud existente cuando la clave de idempotencia ya existe")
    void shouldReturnExistingRequestWhenIdempotencyKeyAlreadyExists() {
        LoanRequest existingRequest = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-001",
            new BigDecimal("50000"),
            36,
            "PERSONAL",
            "channel-web",
            "OP-2024-001",
            LocalDate.now(),
            LoanStatus.APPROVED,
            null,
            new BigDecimal("50000"),
            null
        );

        IdempotencyKey idempotencyKey = IdempotencyKey.fromCompositeKey("OP-2024-001|card-web");

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(true));
        when(loanRequestRepository.findByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(existingRequest));

        StepVerifier.create(useCase.execute(existingRequest))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.APPROVED);
                assertThat(result.id()).isEqualTo(existingRequest.id());
            })
            .verifyComplete();

        verify(loanRequestRepository).existsByIdempotencyKey(any(IdempotencyKey.class));
        verify(loanRequestRepository).findByIdempotencyKey(any(IdempotencyKey.class));
    }

    @Test
    @DisplayName("Debe rechazar solicitud cuando la evaluación de riesgos falla")
    void shouldRejectRequestWhenRiskEvaluationFails() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-002",
            new BigDecimal("100000"),
            48,
            "PERSONAL",
            "channel-mobile",
            "OP-2024-002",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Risk evaluation service unavailable")));
        when(loanRequestRepository.updateWithRejection(any(UUID.class), any(String.class), any(String.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.REJECTED).withRejectionReason("Risk evaluation service unavailable")));
        when(notificationService.sendRejectionNotification(any(LoanRequest.class), any(String.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(result.rejectionReason()).contains("Risk evaluation service unavailable");
            })
            .verifyComplete();

        verify(notificationService).sendRejectionNotification(any(LoanRequest.class), any(String.class));
    }

    @Test
    @DisplayName("Debe manejar solicitud con monto superior al límite máximo")
    void shouldHandleRequestAboveMaximumLimit() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-003",
            new BigDecimal("1000000"),
            60,
            "PERSONAL",
            "channel-web",
            "OP-2024-003",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(loanRequestRepository.updateWithRejection(any(UUID.class), any(String.class), any(String.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.REJECTED).withRejectionReason("Monto solicitada excede el límite máximo permitido")));

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(result.rejectionReason()).contains("excede el límite");
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe validar correctamente la clave de idempotencia compuesta")
    void shouldValidateCompositeIdempotencyKeyCorrectly() {
        String operationNumber = "OP-2024-100";
        String channel = "channel-api";
        String expectedCompositeKey = operationNumber + "|" + channel;

        IdempotencyKey key = IdempotencyKey.fromCompositeKey(expectedCompositeKey);
        assertThat(key.toCompositeKey()).isEqualTo(expectedCompositeKey);
    }

    @Test
    @DisplayName("Debe rechazar solicitud con plazo fuera del rango válido")
    void shouldRejectRequestWithInvalidTerm() {
        LoanRequest request = new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-004",
            new BigDecimal("30000"),
            120,
            "PERSONAL",
            "channel-web",
            "OP-2024-004",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );

        when(loanRequestRepository.existsByIdempotencyKey(any(IdempotencyKey.class)))
            .thenReturn(Mono.just(false));
        when(loanRequestRepository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.VALIDATED)));
        when(loanRequestRepository.updateWithRejection(any(UUID.class), any(String.class), any(String.class)))
            .thenReturn(Mono.just(request.withStatus(LoanStatus.REJECTED).withRejectionReason("El plazo solicitado excede el máximo permitido de 60 meses")));

        StepVerifier.create(useCase.execute(request))
            .assertNext(result -> {
                assertThat(result.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(result.rejectionReason()).contains("plazo");
            })
            .verifyComplete();
    }
}

// === ARCHIVO: src/test/java/com/financial/loansystem/infrastructure/adapter/LoanRequestR2dbcAdapterTest.java ===
package com.financial.loansystem.infrastructure.adapter;

import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.model.LoanRequest.LoanStatus;
import com.financial.loansystem.domain.port.LoanRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("LoanRequestR2dbcAdapter - Tests de persistencia reactiva")
class LoanRequestR2dbcAdapterTest {

    @Mock
    private LoanRequestRepository repository;

    private LoanRequestR2dbcAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new LoanRequestR2dbcAdapter(repository);
    }

    @Test
    @DisplayName("Debe guardar solicitud de préstamo exitosamente")
    void shouldSaveLoanRequestSuccessfully() {
        LoanRequest request = createTestRequest();

        when(repository.save(any(LoanRequest.class)))
            .thenReturn(Mono.just(request));

        StepVerifier.create(adapter.save(request))
            .assertNext(saved -> {
                assertThat(saved.id()).isEqualTo(request.id());
                assertThat(saved.clientId()).isEqualTo(request.clientId());
                assertThat(saved.requestedAmount()).isEqualTo(request.requestedAmount());
            })
            .verifyComplete();

        verify(repository).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe encontrar solicitud por ID exitosamente")
    void shouldFindLoanRequestByIdSuccessfully() {
        UUID requestId = UUID.randomUUID();
        LoanRequest request = createTestRequestWithId(requestId);

        when(repository.findById(eq(requestId)))
            .thenReturn(Mono.just(request));

        StepVerifier.create(adapter.findById(requestId))
            .assertNext(found -> {
                assertThat(found.id()).isEqualTo(requestId);
                assertThat(found.status()).isEqualTo(LoanStatus.PENDING);
            })
            .verifyComplete();

        verify(repository).findById(eq(requestId));
    }

    @Test
    @DisplayName("Debe verificar existencia de clave de idempotencia")
    void shouldCheckIdempotencyKeyExists() {
        IdempotencyKey key = IdempotencyKey.fromCompositeKey("OP-2024-TEST|channel-web");

        when(repository.existsByIdempotencyKey(eq(key)))
            .thenReturn(Mono.just(true));

        StepVerifier.create(adapter.existsByIdempotencyKey(key))
            .assertNext(exists -> assertThat(exists).isTrue())
            .verifyComplete();

        verify(repository).existsByIdempotencyKey(eq(key));
    }

    @Test
    @DisplayName("Debe retornar false para clave de idempotencia inexistente")
    void shouldReturnFalseForNonExistentIdempotencyKey() {
        IdempotencyKey key = IdempotencyKey.fromCompositeKey("OP-2024-NEW|channel-mobile");

        when(repository.existsByIdempotencyKey(eq(key)))
            .thenReturn(Mono.just(false));

        StepVerifier.create(adapter.existsByIdempotencyKey(key))
            .assertNext(exists -> assertThat(exists).isFalse())
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe encontrar solicitud por clave de idempotencia")
    void shouldFindLoanRequestByIdempotencyKey() {
        IdempotencyKey key = IdempotencyKey.fromCompositeKey("OP-2024-KEY|channel-api");
        LoanRequest request = createTestRequestWithOperationNumber("OP-2024-KEY", "channel-api");

        when(repository.findByIdempotencyKey(eq(key)))
            .thenReturn(Mono.just(request));

        StepVerifier.create(adapter.findByIdempotencyKey(key))
            .assertNext(found -> {
                assertThat(found.operationNumber()).isEqualTo("OP-2024-KEY");
                assertThat(found.channel()).isEqualTo("channel-api");
            })
            .verifyComplete();

        verify(repository).findByIdempotencyKey(eq(key));
    }

    @Test
    @DisplayName("Debe actualizar estado de solicitud exitosamente")
    void shouldUpdateStatusSuccessfully() {
        UUID requestId = UUID.randomUUID();
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest updatedRequest = request.withStatus(LoanStatus.APPROVED);

        when(repository.updateStatus(eq(requestId), eq(LoanStatus.APPROVED)))
            .thenReturn(Mono.just(updatedRequest));

        StepVerifier.create(adapter.updateStatus(requestId, LoanStatus.APPROVED))
            .assertNext(updated -> {
                assertThat(updated.id()).isEqualTo(requestId);
                assertThat(updated.status()).isEqualTo(LoanStatus.APPROVED);
            })
            .verifyComplete();

        verify(repository).updateStatus(eq(requestId), eq(LoanStatus.APPROVED));
    }

    @Test
    @DisplayName("Debe actualizar con evaluación de riesgo exitosamente")
    void shouldUpdateWithRiskEvaluationSuccessfully() {
        UUID requestId = UUID.randomUUID();
        String riskEvaluationId = "RISK-EVAL-999";
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest updatedRequest = request.withRiskEvaluationId(riskEvaluationId).withStatus(LoanStatus.RISK_ASSESSED);

        when(repository.updateWithRiskEvaluation(eq(requestId), eq(riskEvaluationId)))
            .thenReturn(Mono.just(updatedRequest));

        StepVerifier.create(adapter.updateWithRiskEvaluation(requestId, riskEvaluationId))
            .assertNext(updated -> {
                assertThat(updated.riskEvaluationId()).isEqualTo(riskEvaluationId);
                assertThat(updated.status()).isEqualTo(LoanStatus.RISK_ASSESSED);
            })
            .verifyComplete();

        verify(repository).updateWithRiskEvaluation(eq(requestId), eq(riskEvaluationId));
    }

    @Test
    @DisplayName("Debe actualizar con aprobación exitosamente")
    void shouldUpdateWithApprovalSuccessfully() {
        UUID requestId = UUID.randomUUID();
        String riskEvaluationId = "RISK-EVAL-888";
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest approvedRequest = request.withApprovedAmount(new BigDecimal("45000"));
        LoanRequest updatedRequest = approvedRequest.withStatus(LoanStatus.APPROVED);

        when(repository.updateWithApproval(eq(requestId), eq(riskEvaluationId), any(LoanRequest.class)))
            .thenReturn(Mono.just(updatedRequest));

        StepVerifier.create(adapter.updateWithApproval(requestId, riskEvaluationId, approvedRequest))
            .assertNext(updated -> {
                assertThat(updated.status()).isEqualTo(LoanStatus.APPROVED);
                assertThat(updated.approvedAmount()).isEqualTo(new BigDecimal("45000"));
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe actualizar con rechazo exitosamente")
    void shouldUpdateWithRejectionSuccessfully() {
        UUID requestId = UUID.randomUUID();
        String riskEvaluationId = "RISK-EVAL-777";
        String rejectionReason = "Riesgo crediticio alto";
        LoanRequest request = createTestRequestWithId(requestId);
        LoanRequest rejectedRequest = request.withStatus(LoanStatus.REJECTED).withRejectionReason(rejectionReason);

        when(repository.updateWithRejection(eq(requestId), eq(riskEvaluationId), eq(rejectionReason)))
            .thenReturn(Mono.just(rejectedRequest));

        StepVerifier.create(adapter.updateWithRejection(requestId, riskEvaluationId, rejectionReason))
            .assertNext(updated -> {
                assertThat(updated.status()).isEqualTo(LoanStatus.REJECTED);
                assertThat(updated.rejectionReason()).isEqualTo(rejectionReason);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar Mono.empty cuando no encuentra solicitud por ID")
    void shouldReturnEmptyWhenRequestNotFoundById() {
        UUID requestId = UUID.randomUUID();

        when(repository.findById(eq(requestId)))
            .thenReturn(Mono.empty());

        StepVerifier.create(adapter.findById(requestId))
            .verifyComplete();
    }

    private LoanRequest createTestRequest() {
        return new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-TEST",
            new BigDecimal("25000"),
            24,
            "PERSONAL",
            "channel-web",
            "OP-2024-TEST",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }

    private LoanRequest createTestRequestWithId(UUID id) {
        return new LoanRequest(
            id,
            "CLIENT-TEST",
            new BigDecimal("25000"),
            24,
            "PERSONAL",
            "channel-web",
            "OP-2024-ID",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }

    private LoanRequest createTestRequestWithOperationNumber(String operationNumber, String channel) {
        return new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-TEST",
            new BigDecimal("25000"),
            24,
            "PERSONAL",
            channel,
            operationNumber,
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }
}

// === ARCHIVO: src/test/java/com/financial/loansystem/infrastructure/adapter/RiskEvaluationWebClientTest.java ===
package com.financial.loansystem.infrastructure.adapter;

import com.financial.loansystem.domain.model.LoanRequest;
import com.financial.loansystem.domain.model.LoanRequest.LoanStatus;
import com.financial.loansystem.domain.port.RiskEvaluationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("RiskEvaluationWebClient - Tests de cliente reactivo")
class RiskEvaluationWebClientTest {

    @Mock
    private RiskEvaluationClient riskEvaluationClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Debe evaluar riesgo exitosamente para solicitud válida")
    void shouldEvaluateRiskSuccessfullyForValidRequest() {
        LoanRequest request = createTestRequest();
        String expectedRiskEvaluationId = "RISK-EVAL-12345";

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just(expectedRiskEvaluationId));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .assertNext(riskId -> {
                assertThat(riskId).isEqualTo(expectedRiskEvaluationId);
            })
            .verifyComplete();

        verify(riskEvaluationClient, times(1)).evaluateRisk(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe manejar error de timeout en evaluación de riesgo")
    void shouldHandleTimeoutErrorInRiskEvaluation() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Connection timeout")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe manejar error de servicio no disponible")
    void shouldHandleServiceUnavailableError() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Service unavailable")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectErrorMatches(error -> 
                error.getMessage().contains("Service unavailable")
            )
            .verify();
    }

    @Test
    @DisplayName("Debe manejar error de respuesta inválida del servicio")
    void shouldHandleInvalidResponseError() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new IllegalStateException("Invalid response format")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectError(IllegalStateException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe manejar múltiples retry automáticamente")
    void shouldHandleAutomaticRetries() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(
                Mono.error(new RuntimeException("Transient error")),
                Mono.error(new RuntimeException("Transient error")),
                Mono.just("RISK-EVAL-SUCCESS")
            );

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .assertNext(riskId -> {
                assertThat(riskId).isEqualTo("RISK-EVAL-SUCCESS");
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe validar request antes de enviar a evaluación de riesgo")
    void shouldValidateRequestBeforeSendingToRiskEvaluation() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just("RISK-EVAL-VALIDATED"));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .assertNext(result -> {
                assertThat(result).isNotNull();
                assertThat(result).startsWith("RISK-EVAL-");
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe manejar error de circuit breaker abierto")
    void shouldHandleCircuitBreakerOpenError() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("CircuitBreaker is OPEN")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectErrorMatches(error -> 
                error.getMessage().contains("CircuitBreaker")
            )
            .verify();
    }

    @Test
    @DisplayName("Debe manejar rate limit excedido")
    void shouldHandleRateLimitExceeded() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.error(new RuntimeException("Rate limit exceeded")));

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .expectErrorMatches(error -> 
                error.getMessage().contains("Rate limit")
            )
            .verify();
    }

    @Test
    @DisplayName("Debe retornarMono vacío cuando la evaluación es exitosa pero sin ID")
    void shouldReturnEmptyWhenEvaluationSucceedsWithoutId() {
        LoanRequest request = createTestRequest();

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(riskEvaluationClient.evaluateRisk(request))
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe procesar múltiples solicitudes concurrentemente")
    void shouldProcessMultipleConcurrentRequests() {
        LoanRequest request1 = createTestRequestWithClient("CLIENT-001");
        LoanRequest request2 = createTestRequestWithClient("CLIENT-002");
        LoanRequest request3 = createTestRequestWithClient("CLIENT-003");

        when(riskEvaluationClient.evaluateRisk(any(LoanRequest.class)))
            .thenReturn(Mono.just("RISK-EVAL-CONCURRENT"));

        StepVerifier.create(
            Mono.zip(
                riskEvaluationClient.evaluateRisk(request1),
                riskEvaluationClient.evaluateRisk(request2),
                riskEvaluationClient.evaluateRisk(request3)
            )
        )
        .assertNext(tuple -> {
            assertThat(tuple.getT1()).isEqualTo("RISK-EVAL-CONCURRENT");
            assertThat(tuple.getT2()).isEqualTo("RISK-EVAL-CONCURRENT");
            assertThat(tuple.getT3()).isEqualTo("RISK-EVAL-CONCURRENT");
        })
        .verifyComplete();
    }

    private LoanRequest createTestRequest() {
        return new LoanRequest(
            UUID.randomUUID(),
            "CLIENT-001",
            new BigDecimal("50000"),
            36,
            "PERSONAL",
            "channel-web",
            "OP-2024-001",
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }

    private LoanRequest createTestRequestWithClient(String clientId) {
        return new LoanRequest(
            UUID.randomUUID(),
            clientId,
            new BigDecimal("30000"),
            24,
            "PERSONAL",
            "channel-mobile",
            "OP-2024-" + clientId,
            LocalDate.now(),
            LoanStatus.PENDING,
            null, null, null
        );
    }
}
```
