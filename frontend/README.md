# Todo Frontend - Vaadin Application

A modern web UI for managing todos, built entirely in Java using Vaadin.

## Features

- Clean, responsive UI built with Vaadin components
- Add new todos
- Mark todos as done/undone
- Delete todos
- Real-time updates
- Persistent data (via backend API)

## Tech Stack

- Java 17
- Spring Boot 3.2.1
- Vaadin 24.3.1
- Maven

## Project Structure

```
src/main/java/com/example/todofrontend/
├── TodoFrontendApplication.java    # Main application class
├── model/
│   └── Todo.java                   # Todo model
├── service/
│   └── TodoService.java            # REST client service
└── view/
    └── TodoView.java               # Main UI view
```

## Running Locally

### Prerequisites

- Java 17+
- Maven 3.6+
- Backend API running at `http://localhost:8081`

### Build and Run

```bash
mvn clean package -Pproduction
java -jar target/todo-frontend-1.0.0.jar
```

The application will be available at `http://localhost:8080`

### Development Mode

For development with hot-reload:

```bash
mvn spring-boot:run
```

## Docker

Build and run with Docker:

```bash
docker build -t todo-frontend .
docker run -p 8080:8080 -e BACKEND_URL=http://backend:8081 todo-frontend
```

## Configuration

Key configuration properties in `application.properties`:

- `server.port=8080` - Server port
- `backend.url=${BACKEND_URL:http://localhost:8081}` - Backend API URL
- `vaadin.productionMode=true` - Enable production mode

## Environment Variables

- `BACKEND_URL` - URL of the backend API (default: `http://localhost:8081`)

## UI Features

### Add Todo
- Enter text in the input field
- Click "Add" button or press Enter
- Validation ensures title is not empty and not longer than 200 characters

### Toggle Done
- Click the checkbox next to a todo to mark it as done/undone
- Done todos are shown with strikethrough text

### Delete Todo
- Click the trash icon to delete a todo
- Deletion is immediate

## Architecture

The frontend communicates with the backend via REST API:

- `TodoService` - REST client for backend communication
- `TodoView` - Main Vaadin view component
- Uses Spring's `RestTemplate` for HTTP requests
