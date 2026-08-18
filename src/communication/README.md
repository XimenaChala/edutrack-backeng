# Módulo Communication

Bounded context responsable de la **mensajería directa padre-profesor** (HU-005).

## Estructura prevista

```text
src/communication/
├── domain/         # Conversation, Message, eventos MessageSent
├── application/    # SendMessage, ListConversations, GetMessageHistory
└── adapters/       # REST API, persistencia, integración con identity
```

## Casos de uso (MVP)

| Caso de uso | Descripción |
|---|---|
| `SendMessage` | Envía un mensaje de texto en una conversación |
| `ListConversations` | Lista conversaciones del usuario autenticado |
| `GetMessageHistory` | Obtiene mensajes paginados de una conversación |

## Dependencias

- **identity:** validar que el padre tiene relación con el estudiante y el profesor destinatario.
