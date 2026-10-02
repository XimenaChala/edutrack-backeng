# EduTrack — Communication Microservice (Backend)
Distributed school-tracking platform for parents and guardians — Distributed Systems 2026-B, Team G1.

## 🚀 Tecnologías
- **Java 21 + Spring Boot 3.3.3**
- **Arquitectura Hexagonal (Puertos y Adaptadores)**
- **Spring Data JPA + PostgreSQL 16**
- **Docker Compose**

## 🌐 Endpoints REST
- `GET  /api/v1/messages/health` — Verificación de estado del microservicio.
- `GET  /api/v1/messages/conversation?user1={uuid}&user2={uuid}` — Historial de mensajes entre dos usuarios.
- `POST /api/v1/messages` — Envío de mensaje. Body:
  ```json
  {
    "senderId": "11111111-1111-1111-1111-111111111111",
    "receiverId": "22222222-2222-2222-2222-222222222222",
    "subjectId": null,
    "content": "Hola profesor"
  }
  ```

## 📦 Ejecución con Docker Compose (Stack Completo)
Desde esta carpeta (`edutrack-backeng`):
```bash
docker compose up --build -d
```
Esto levantará automáticamente:
1. `edutrack-postgres` (PostgreSQL en puerto 5433 local)
2. `edutrack-communication` (Backend Spring Boot en puerto 8085)
3. `edutrack-frontend` (Frontend Vite/Nginx en puerto 3000)

## 💻 Ejecución Local (Desarrollo)
Requisitos: Java 21 y PostgreSQL corriendo en puerto 5432 (o base de datos en Docker).
```powershell
.\mvnw.cmd spring-boot:run
```
