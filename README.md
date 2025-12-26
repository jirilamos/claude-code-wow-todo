# Claude Code WOW Demo — Java Frontend (Vaadin) + Java Backend (Spring Boot REST) — Todo App

This repo is intentionally minimal: this README is the spec.

**Goal:** Open Claude Code (web), say:  
> “Implement this repository according to README.md”  
…and get a working full-stack app you can run locally with Docker in one command.

---

## 1) What you will build

A simple **Todo List** application consisting of:

### A) Frontend — Java (Vaadin)
- Web UI written in Java using **Vaadin** (runs as a Spring Boot app)
- Features:
  - List todos
  - Add todo
  - Toggle done/undone (checkbox)
  - Delete todo
- Communicates with backend via HTTP REST calls

### B) Backend — Java (Spring Boot REST)
- REST API for todos
- Persistence in **SQLite**
- DB migrations using **Flyway**
- Validation + sensible JSON errors
- Health check endpoint (`/actuator/health`)
- Optional: OpenAPI/Swagger

### C) DevOps / Run
- One-command start via Docker Compose
- Clean project structure
- Minimal tests (at least backend API tests)
- Clear logs

---

## 2) Acceptance criteria (Definition of Done)

After implementation:

1. `docker compose up --build` starts everything.
2. Frontend available at: `http://localhost:8080`
3. Backend available at: `http://localhost:8081`
4. You can:
   - add a todo
   - mark it done/undone
   - delete it
   - refresh page and data persists (SQLite)
5. Backend exposes:
   - `GET /actuator/health`
   - `GET /api/todos`
   - `GET /api/todos/{id}` (recommended)
   - `POST /api/todos`
   - `PUT /api/todos/{id}`  ✅ used by frontend checkbox toggle
   - `DELETE /api/todos/{id}`
6. Repo includes:
   - `.env.example`
   - `docker-compose.yml`
   - `backend/README.md` and `frontend/README.md`
   - minimal tests

---

## 3) Tech stack requirements

### Frontend
- Java 17+
- Spring Boot 3.x
- Vaadin 24+
- Maven

**UI Theme note**
- This demo uses the default Vaadin (Lumo) theme.
- Custom theme was intentionally removed to keep Docker production build fast + reliable for live demos.

### Backend
- Java 17+
- Spring Boot 3.x
- SQLite JDBC
- Flyway
- JUnit 5 + Spring Boot Test

---

## 4) Data model

Todo:
- `id` (integer/autoincrement)
- `title` (string, 1..200)
- `done` (boolean, default false)
- `created_at` (datetime)

---

## 5) REST API contract

Base URL: `http://localhost:8081`

### GET /api/todos
Returns list of todos.

### GET /api/todos/{id}
Returns single todo (recommended for frontend updates / debugging).

### POST /api/todos
Creates todo.
Request example:
`json`
{ "title": "Buy milk", "done": false }

PUT /api/todos/{id}

Updates a todo.

Important: The frontend uses PUT for checkbox toggle and sends both title and done.

Why not PATCH?

Java/Spring RestTemplate using the default JDK HTTP stack may fail with:
Invalid HTTP method: PATCH

Using PUT avoids this and is more demo-proof.

Request example:

`json`
{ "title": "Buy milk", "done": true }

DELETE /api/todos/{id}

Deletes todo.

## 6) Demo stability notes (important)
### A) Hibernate schema validation

For demo reliability, backend should rely on Flyway as the source of truth.

Recommendation for backend config:

spring.jpa.hibernate.ddl-auto=none

This avoids startup failures if SQLite column affinity differs from Hibernate expectations.

### B) Docker build uses local sources

docker compose up --build builds from your local working directory (not from GitHub), based on the compose build contexts.

## 7) How to run
Start
docker compose up --build


Then open:

Frontend: http://localhost:8080

Backend: http://localhost:8081

Health: http://localhost:8081/actuator/health

Stop
docker compose down

Reset database (delete persisted SQLite volume)
docker compose down --volumes

### 8) Instruction to Claude Code (WOW prompt)

Implement the entire repository according to README.md.

Ensure docker compose up --build results in a working app.

Keep it minimal, clean, and production-like.

That’s it.
