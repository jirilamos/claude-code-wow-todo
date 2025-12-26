# Claude Code WOW Demo — Java Frontend (Vaadin) + Java Backend (Spring Boot REST) — Todo App

This repo is intentionally minimal: **this README is the spec**.
Goal: open Claude Code (web), say “Implement this repository according to README”, and get a working full-stack app.

## 1) What you will build

A simple **Todo List** application consisting of:

### A) Frontend — Java (Vaadin)
- Web UI written **entirely in Java** using **Vaadin** (runs as a Spring Boot app)
- Pages:
  - **Todos view**: list items, add item, toggle done, delete item
  - (Optional) simple filter: All / Active / Done
- Communicates with backend via HTTP REST calls

### B) Backend — Java (Spring Boot REST)
- REST API for todos (Spring Boot)
- Persistence in **SQLite**
- DB migrations (Flyway preferred)
- Validation + sensible errors
- OpenAPI/Swagger (nice to have)

### C) DevOps / Run
- One-command start via **Docker Compose**
- Clean project structure
- Basic tests (at least backend API tests)
- Clear logs and health endpoints

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
   - `POST /api/todos`
   - `PATCH /api/todos/{id}`
   - `DELETE /api/todos/{id}`
6. Repo includes:
   - `.env.example`
   - `docker-compose.yml`
   - `backend/README.md` and `frontend/README.md`
   - minimal tests

---

## 3) Tech stack requirements

### Frontend (Java)
- Java 17+
- Spring Boot 3.x
- Vaadin 24+
- Maven

## UI Theme note

This demo uses the default Vaadin (Lumo) theme.
A custom theme (`todo-theme`) was intentionally removed to keep the Docker production build fast and reliable for live demos.

If you want to add a custom theme later, make sure it is configured correctly for Vaadin production builds (Vite/Rollup),
otherwise `mvn package -Pproduction` inside Docker may fail.


### Backend (Java)
- Java 17+
- Spring Boot 3.x
- SQLite JDBC
- Flyway
- JUnit 5 + Spring Boot Test

---

## 4) Data model

**Todo**
- `id` (integer, autoincrement)
- `title` (string, 1..200)
- `done` (boolean, default false)
- `created_at` (datetime)

---

## 5) REST API contract

Base URL: `http://localhost:8081`

### GET /api/todos
Returns list of todos.

### POST /api/todos
Creates todo.

### PATCH /api/todos/{id}
Updates todo.

### DELETE /api/todos/{id}
Deletes todo.

---

## 6) Repository structure
/
docker-compose.yml
.env.example
backend/
frontend/


---

## 7) How to run

`bash`
docker compose up --build

## 8) Instruction to Claude Code

Implement the entire repository according to README.md.
Ensure docker compose up --build results in a working app.
Keep it minimal, clean, and production-like.

That’s it.


