# Validación de extremo a extremo en sistema de préstamos

El sistema de préstamos de una entidad financiera requiere una validación exhaustiva de extremo a extremo para asegurar que todas las solicitudes de préstamos se procesan correctamente desde la recepción hasta la aprobación o rechazo. Los actores involucrados son el solicitante del préstamo, el sistema de evaluación de riesgos, el motor de aprobación y el sistema de notificación al cliente. Las solicitudes deben ser idempotentes con una clave compuesta por el número de operación y el canal de solicitud. El sistema debe manejar un volumen de 1 500 solicitudes por segundo en hora pico y garantizar una latencia máxima de 2 segundos desde la recepción hasta la respuesta al cliente.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | e2e inval |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Recepción y persistencia idempotente de solicitudes

**Objetivo:** Asegurar que las solicitudes de préstamo se reciban y persistan de manera idempotente.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los canales de entrada de solicitudes de préstamo.
- Definir la clave de idempotencia para cada solicitud.
- Implementar la recepción y persistencia de solicitudes, asegurando que solicitudes repetidas con la misma clave no generen duplicados.

**Entregable:** Sistema que recibe y persiste solicitudes de préstamo con idempotencia.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo manejar solicitudes repetidas con la misma clave de idempotencia.
- Piensa en cómo asegurar que la persistencia sea eficiente y escalable.

</details>

### Fase 2: Validación y enriquecimiento de solicitudes

**Objetivo:** Validar y enriquecer las solicitudes de préstamo antes de su envío al sistema de evaluación de riesgos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Identificar las reglas de validación para las solicitudes de préstamo.
- Implementar la validación de las solicitudes recibidas.
- Enriquecer las solicitudes con información adicional necesaria para la evaluación de riesgos.

**Entregable:** Sistema que valida y enriquece solicitudes de préstamo antes de enviarlas al sistema de evaluación de riesgos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las reglas de negocio que deben aplicarse a cada solicitud.
- Piensa en cómo manejar errores de validación y enriquecimiento.

</details>

### Fase 3: Envío y manejo de respuestas del sistema de evaluación de riesgos

**Objetivo:** Enviar las solicitudes validadas y enriquecidas al sistema de evaluación de riesgos y manejar las respuestas.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Identificar el sistema de evaluación de riesgos y sus endpoints.
- Implementar el envío de solicitudes al sistema de evaluación de riesgos.
- Manejar las respuestas del sistema de evaluación de riesgos y notificar al cliente.

**Entregable:** Sistema que envía solicitudes al sistema de evaluación de riesgos y maneja las respuestas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo asegurar que las solicitudes se envíen de manera eficiente y escalable.
- Piensa en cómo manejar errores de comunicación con el sistema de evaluación de riesgos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la validación de extremo a extremo en el contexto del sistema de préstamos?
- **paraQueSirve**: ¿Para qué sirve la validación de extremo a extremo en el sistema de préstamos?
- **comoSeUsa**: ¿Cómo se usa la validación de extremo a extremo para asegurar la correcta procesamiento de solicitudes de préstamo?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir durante la validación de extremo a extremo y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de la validación de extremo a extremo en el sistema de préstamos?

## Criterios de Evaluacion

- Implementar la recepción y persistencia idempotente de solicitudes de préstamo.
- Validar y enriquecer las solicitudes de préstamo antes de enviarlas al sistema de evaluación de riesgos.
- Enviar las solicitudes validadas y enriquecidas al sistema de evaluación de riesgos y manejar las respuestas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
