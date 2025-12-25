# Todo Backend - Spring Boot REST API

A RESTful API for managing todos, built with Spring Boot and SQLite.

## Features

- Full CRUD operations for todos
- SQLite database with Flyway migrations
- Input validation and error handling
- Health check endpoint
- OpenAPI/Swagger documentation
- Comprehensive test suite

## Tech Stack

- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- SQLite
- Flyway
- SpringDoc OpenAPI
- JUnit 5

## API Endpoints

### Todo Operations

- `GET /api/todos` - Get all todos
- `GET /api/todos/{id}` - Get a specific todo
- `POST /api/todos` - Create a new todo
- `PATCH /api/todos/{id}` - Update a todo
- `DELETE /api/todos/{id}` - Delete a todo

### Health & Documentation

- `GET /actuator/health` - Health check endpoint
- `GET /swagger-ui.html` - Swagger UI
- `GET /api-docs` - OpenAPI specification

## Data Model

```json
{
  "id": 1,
  "title": "Buy groceries",
  "done": false,
  "createdAt": "2025-12-25T10:30:00"
}
```

## Running Locally

### Prerequisites

- Java 17+
- Maven 3.6+

### Build and Run

```bash
mvn clean package
java -jar target/todo-backend-1.0.0.jar
```

The API will be available at `http://localhost:8081`

## Running Tests

```bash
mvn test
```

## Docker

Build and run with Docker:

```bash
docker build -t todo-backend .
docker run -p 8081:8081 -v $(pwd)/data:/app/data todo-backend
```

## Configuration

Key configuration properties in `application.properties`:

- `server.port=8081` - Server port
- `spring.datasource.url=jdbc:sqlite:/app/data/todos.db` - Database location
- `spring.flyway.enabled=true` - Enable Flyway migrations

## Database

The SQLite database file is stored at `/app/data/todos.db`. Data persists across restarts when using Docker volumes.
