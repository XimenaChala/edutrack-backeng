# HU-005 — Comunicación padre-profesor

**Módulo:** `communication`  
**Etiqueta GitHub:** `hu-005`, `module:communication`  
**Prioridad MVP:** Alta

## Historia de usuario

> Como **padre o tutor**, quiero **enviar y recibir mensajes directos con el profesor de mi hijo**, para **resolver dudas escolares sin depender de canales informales** (WhatsApp, llamadas).

## Criterios de aceptación

1. Un padre autenticado puede iniciar una conversación con el profesor vinculado a su hijo.
2. Un padre puede listar sus conversaciones activas ordenadas por fecha de último mensaje.
3. Un padre puede enviar un mensaje de texto (máx. 2000 caracteres) dentro de una conversación existente.
4. Un padre puede leer el historial de mensajes de una conversación (paginado).
5. El profesor puede responder mensajes dentro de las conversaciones de sus estudiantes.
6. Los mensajes quedan persistidos con remitente, destinatario, contenido y marca de tiempo.

## Fuera de alcance (MVP)

- Adjuntos (imágenes, PDF).
- Notificaciones push en tiempo real (cubierto por módulo `notifications`).
- Mensajes grupales o broadcast.

## Notas de arquitectura

- Bounded context: `communication`.
- Capas hexagonales: `domain/`, `application/`, `adapters/`.
- Integración con `identity` vía REST para validar relación padre-estudiante-profesor.
