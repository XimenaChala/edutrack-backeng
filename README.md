# EduTrack

Plataforma distribuida de seguimiento escolar en tiempo casi real para padres y tutores.

Proyecto de la asignatura **Sistemas Distribuidos 2026-B** — Grupo G1.

## 1. ¿Qué resuelve?

EduTrack centraliza en un solo lugar el seguimiento escolar de los hijos: tareas, calificaciones, asistencia y comunicación con profesores, evitando depender de WhatsApp, mensajes informales o plataformas pensadas solo para profesores (Google Classroom, Moodle).

## 2. Arquitectura

El sistema aplica **Domain-Driven Design** y **arquitectura hexagonal**, dividido en 5 bounded contexts:

| Módulo | Responsabilidad |
|---|---|
| Identity & Accounts | Usuarios, padres, estudiantes y colegios |
| Academic Records | Tareas y calificaciones |
| Attendance | Registro y consulta de asistencia |
| Notifications | Alertas para padres (push, email) |
| Communication | Mensajería directa padre-profesor |

Cada módulo sigue esta estructura interna:
src/
├── domain/ # entidades, value objects, eventos — sin I/O
├── application/ # casos de uso, ports
└── adapters/ # REST APIs, persistencia, mensajería

Comunicación entre módulos:
- **Síncrona (REST):** cuando se necesita respuesta inmediata (ej. consultar calificaciones)
- **Asíncrona (eventos):** `GradeCreated`, `StudentAbsent` — procesadas por Notifications con idempotencia y retries

Patrones de resiliencia aplicados: Outbox, Retry + Backoff, Circuit Breaker, Saga (donde aplica).

## 3. Stack técnico

> Completar con el stack real del equipo (lenguaje/framework backend, frontend, base de datos, broker de mensajería).

## 4. Estrategia de ramas (Git flow)

3 ramas de larga duración: `develop` → `qa` → `main`.

- **Trabajo nuevo:** `feat/HU-XX` (funcionalidad) o `fix/HU-XX` (corrección), creada desde `develop` actualizado → PR a `develop`.
- **Promoción a QA:** desde `qa` actualizado, crear `HU-XX-qa` → PR a `qa`.
- **Release:** solo al liberar, desde `main` se crea `release/x.x.x` (rama temporal, no permanente), acumula todas las HU aprobadas del corte → PR a `main`.

`develop`, `qa` y `main` están protegidas: no se permite push directo, todo pasa por Pull Request.

## 5. Cómo levantar el proyecto localmente

```bash
git clone https://github.com/XimenaChala/edutrack.git
cd edutrack
# instalar dependencias (ajustar según el stack elegido)
# configurar variables de entorno (nunca subir secretos al repo)
```

## 6. Convenciones

- **Commits:** [Conventional Commits](https://www.conventionalcommits.org/) — `type(scope): summary` (ej. `feat(attendance): registrar ausencia`)
- **Ramas:** `feat/HU-XX`, `fix/HU-XX`, `release/x.x.x`
- **Labels de Issues:** `hu-001`…`hu-005`, `module:identity`, `module:academic`, `module:attendance`, `module:notifications`, `module:communication`, `type:feature`, `type:test`, `type:docs`, `type:setup`

## 7. Equipo

Grupo G1 — Sistemas Distribuidos 2026-B. Proyecto trabajado por 3 integrantes.

## 8. Alcance MVP 1

Incluye: gestión básica de usuarios, relación padre-estudiante, registro/consulta de calificaciones y asistencia, notificaciones de calificación y ausencia, mensajería básica padre-profesor, comunicación por eventos, idempotencia, retries y outbox.

Fuera de alcance (v2+): predicción de riesgo académico con IA, calendario escolar compartido, reportes automáticos en PDF.
