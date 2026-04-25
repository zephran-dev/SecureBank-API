# SecureBank API

A senior-level, highly secure banking REST API built with Java 21, Spring Boot 3.x, and Clean Architecture principles.

## Features
- **Clean Architecture**: Domain-driven approach with strict separation of layers.
- **Robust Security**: 
  - Spring Security with JWT (JSON Web Tokens).
  - BCrypt for password hashing.
  - AES-256 for symmetric encryption of sensitive PII data (e.g., identity documents) at the infrastructure level.
- **OWASP Best Practices**: 
  - Global error handling (no stack traces leaked).
  - Rate Limiting via **Bucket4j** (mitigates DDoS and brute force).
  - Audit Logging of all incoming requests.
- **Infrastructure Ready**:
  - `docker-compose` for isolated PostgreSQL execution.
  - Integrated with **Testcontainers** for automated integration testing.
  - Swagger/OpenAPI 3.0 documentation.

## Tech Stack
- Java 21
- Spring Boot 3.2+
- Spring Security & JWT
- PostgreSQL
- Bucket4j (Rate Limiting)
- Testcontainers & JUnit 5
- MapStruct / Lombok
- Docker & Docker Compose

## How to Run Locally

### 1. Start the Database
The project includes a `docker-compose.yml` to easily spin up the database.
```bash
docker-compose up -d
```

### 2. Run the Application
You can run the API locally using Maven wrapper:
```bash
./mvnw spring-boot:run
```
> **Note**: For local development, the `application.yml` uses fallback default values for `JWT_SECRET`, `CRYPTO_SECRET`, and `DB_PASSWORD`. In a production environment, you **must** inject these via environment variables.

### 3. Access Swagger UI
Once the application is running on port 8080, access the interactive API documentation at:
http://localhost:8080/swagger-ui.html

## Security Disclaimer
This repository contains safe, dummy fallback keys in its configuration files solely for ease of local testing. Do not use the default fallback `JWT_SECRET` or `CRYPTO_SECRET` in a live production environment.
