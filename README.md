# Hexagonal Architecture Example

A Spring Boot application demonstrating Hexagonal Architecture (Ports and Adapters) with a Task management example.

## What is Hexagonal Architecture?

Hexagonal Architecture, also known as Ports and Adapters, is an architectural pattern that aims to create loosely coupled application components that can be easily connected to their software environment by means of ports and adapters. The main goal is to isolate the core business logic from external concerns like databases, frameworks, and UI.

## Project Structure

This project is organized into three main layers:

### 1. Domain Layer (`domains/`)
The core business logic layer that contains:
- **Models**: Business entities (e.g., `TaskModel`)
- **Ports**: Interfaces that define contracts (e.g., `TaskRepositoryPort`)
- **Exceptions**: Domain-specific exceptions
- **Enums**: Domain constants and messages

This layer has no dependencies on frameworks or external libraries.

### 2. Use Cases Layer (`use_cases/`)
The application business logic layer that:
- Implements use case ports (e.g., `CreateTaskPort`, `FindTaskPort`)
- Contains adapters that orchestrate domain logic (e.g., `TaskPortAdapter`)
- Defines application-specific business rules

### 3. Infrastructure Layer (`infrastructure/`)
The technical implementation layer divided into:

**Entrypoints** (Driving Adapters):
- REST controllers
- Request/Response DTOs
- Exception handlers
- Configuration classes
- Swagger documentation

**Driven Adapters** (e.g., `postgres_driven/`):
- Database repositories
- JPA entities
- Database mappers
- External service integrations

## How It Works

The application follows the dependency rule: dependencies point inward toward the domain.

```
External World → Entrypoints → Use Cases → Domain ← Driven Adapters ← External World
                    ↓                           ↑
                 Controllers              Repository Adapters
```

1. **Request Flow**: HTTP Request → Controller → Service → Use Case Adapter → Domain
2. **Response Flow**: Domain → Repository Adapter → Database

## Example: Task Management

The project implements a simple task management system:

- **Domain**: `TaskModel` represents a task with properties like id, name, description, status
- **Port**: `TaskRepositoryPort` interface defines data operations
- **Use Case**: `TaskPortAdapter` implements business logic
- **Entrypoint**: REST controllers expose HTTP endpoints
- **Driven Adapter**: `TaskRepositoryAdapter` implements database persistence

## Technology Stack

- Java 25
- Spring Boot
- PostgreSQL
- Gradle
- JUnit 5 with JaCoCo for testing
- Swagger/OpenAPI for API documentation
- Docker for containerization

## Running the Application

1. Start PostgreSQL:
```bash
docker-compose up -d
```

2. Run the application:
```bash
./gradlew bootRun
```

3. Access the API at `http://localhost:8080`
4. View Swagger UI at `http://localhost:8080/swagger-ui.html`

## API Endpoints

- `POST /tasks` - Create a new task
- `GET /tasks` - Get all tasks
- `GET /health` - Health check endpoint

## Testing

Run tests:
```bash
./gradlew test
```

Generate coverage report:
```bash
./gradlew jacocoTestReport
```

## Benefits of This Architecture

1. **Testability**: Business logic can be tested without databases or HTTP
2. **Maintainability**: Clear separation of concerns
3. **Flexibility**: Easy to swap implementations (e.g., change database)
4. **Technology Independence**: Core logic doesn't depend on frameworks
5. **Clear Boundaries**: Well-defined interfaces between layers

## Key Principles

- Business logic in the domain is isolated from technical details
- Dependencies point inward (toward the domain)
- Ports define interfaces, adapters implement them
- The domain layer has no external dependencies
- Infrastructure details are pluggable through adapters
