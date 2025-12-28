# LipaMdogoMdogo — Backend (side project)

A small Spring Boot backend side-project I built to polish my backend skills while progressing toward fintech developer roles. The app models users and loans (simple micro-lending concepts) and exposes a small REST API to create users, request loans, and retrieve loans.

This README documents what I implemented, how to run the project locally (including a Docker Compose Postgres instance), the main endpoints, data shapes, and next steps / limitations.

---

## What I built

- Spring Boot (Web + Data JPA) application.
- Postgres database for persistence.
- Basic domain models:
  - User — stores user profile, credit limit and loans.
  - Loan — loan request linked to borrower, with amount, term, payment plan, etc.
  - Payment — (model exists for future use).
- DTOs and small utility mapper to convert between entities and response DTOs.
- Minimal REST controller to:
  - create users,
  - request loans for users,
  - list and fetch loans for a user.

Files worth checking:
- [infra/docker-compose.yaml](https://github.com/OmariJuma/lipaMdogoMdogo/blob/master/infra/docker-compose.yaml)
- [src/main/java/com/example/lipaMdogoMdogo/controller/LipaMdogoMdogoController.java](https://github.com/OmariJuma/lipaMdogoMdogo/blob/master/src/main/java/com/example/lipaMdogoMdogo/controller/LipaMdogoMdogoController.java)
- [src/main/java/com/example/lipaMdogoMdogo/models](https://github.com/OmariJuma/lipaMdogoMdogo/tree/master/src/main/java/com/example/lipaMdogoMdogo/models)
- [src/main/resources/application.properties](https://github.com/OmariJuma/lipaMdogoMdogo/blob/master/src/main/resources/application.properties)

---

## Tech stack

- Java 21
- Spring Boot (starter-web, starter-data-jpa)
- PostgreSQL
- Maven
- Lombok (to reduce boilerplate)

---

## Prerequisites

- Java 21 (JDK)
- Maven
- Docker & Docker Compose (optional, recommended for Postgres)
- curl or HTTP client for testing endpoints

---

## Configuration / defaults

The project uses `src/main/resources/application.properties` with the following defaults:

- server port: `8081`
- datasource URL: `jdbc:postgresql://localhost:5432/lipamdogo_database`
- datasource user: `lmm_admin`
- datasource password: `1234pass`

These values are chosen to match the provided Docker Compose configuration.

Postgres settings in `infra/docker-compose.yaml`:
- image: `postgres:latest`
- POSTGRES_USER: `lmm_admin`
- POSTGRES_PASSWORD: `1234pass`
- POSTGRES_DB: `lipamdogo_database`
- port mapping: `5432:5432`

---

## Quick start (recommended)

1. Clone the repository and change into it:

   git clone https://github.com/OmariJuma/lipaMdogoMdogo.git
   cd lipaMdogoMdogo

2. Start Postgres with Docker Compose (from project root):

   docker compose -f infra/docker-compose.yaml up -d

   - This will start Postgres on port 5432 using the credentials above.
   - If you already have a Postgres instance, ensure the DB and credentials match `application.properties` or update the properties.

3. Build and run the application:

   - Option A — run via Maven:
     mvn spring-boot:run

   - Option B — build jar and run:
     mvn clean package
     java -jar target/lipaMdogoMdogo-0.0.1-SNAPSHOT.jar

4. The API will be available at:

   http://localhost:8081/api/v1/users

---

## Running tests

Execute:

mvn test

(Current test suite is minimal — only Spring context smoke test.)

---

## API — Endpoints & example payloads

Base path: `/api/v1/users`

1) Create a user
- Method: POST
- Path: /api/v1/users/create
- Body: JSON representing a user (controller currently accepts the User entity JSON)

Example:
curl -X POST http://localhost:8081/api/v1/users/create \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","secondName":"Doe","idNo":"12345678","msisdn":"0722000000","role":"USER"}'

Response: UserResDto (created user)

2) List all users
- Method: GET
- Path: /api/v1/users

Example:
curl http://localhost:8081/api/v1/users

3) Get user by id
- Method: GET
- Path: /api/v1/users/{id}

Example:
curl http://localhost:8081/api/v1/users/00000000-0000-0000-0000-000000000000

4) Apply for a loan (create loan for a user)
- Method: POST
- Path: /api/v1/users/{id}/loans
- Body: LoanReqDto JSON

LoanReqDto fields:
- amount (Double) — required by Loan entity
- loanPurpose (String)
- paymentPlan (Double)
- termInMonths (Integer)
- approverId (UUID) — optional

Example:
curl -X POST http://localhost:8081/api/v1/users/{userId}/loans \
  -H "Content-Type: application/json" \
  -d '{"amount":5000.0,"loanPurpose":"buy stock","paymentPlan":5500.0,"termInMonths":3}'

Response: LoanResDto (created loan)

5) List all loans for a user
- Method: GET
- Path: /api/v1/users/{id}/loans

Example:
curl http://localhost:8081/api/v1/users/{userId}/loans

6) Get specific loan for a user
- Method: GET
- Path: /api/v1/users/{id}/loans/{loanId}

Example:
curl http://localhost:8081/api/v1/users/{userId}/loans/{loanId}

Notes:
- The controller enforces that a loan requested by id belongs to the user when fetching a specific loan.
- When creating a loan the controller maps a LoanReqDto to a Loan entity and saves it.

---

## Data model highlights

- User:
  - id, firstName, secondName, idNo, msisdn, creditLimit
  - one-to-many relationship: User -> Loan
  - role: enum (USER, ADMIN, LOAN_APPROVER, SUPER_ADMIN)

- Loan:
  - id, borrower (User), amount, loanPurpose, paymentPlan, isApproved, approverId, termInMonths, issuedAt, createdAt, updatedAt

- Payment: model present but payment flow not implemented yet.

---

## Known limitations & next steps

- Validation: minimal/no request validation; add request validation (Bean Validation) and better DTO usage.
- Authentication/Authorization: none; endpoints are open.
- Error handling: basic string messages and HTTP status; replace with a structured error response and exception handlers.
- Business logic:
  - Loan approval workflow not implemented (isApproved is present but there is no endpoint to approve/reject).
  - Credit limit calculations not enforced.
  - Payment handling, schedules and repayment tracking to be added.
- Use Docker for the application image in addition to DB for fully reproducible environment.
- Add integration tests for endpoints.

---

## Development notes

- Mapped files and paths are in the source tree; for example the main controller is:
  - [LipaMdogoMdogoController.java](https://github.com/OmariJuma/lipaMdogoMdogo/blob/master/src/main/java/com/example/lipaMdogoMdogo/controller/LipaMdogoMdogoController.java)

- Database connection is in:
  - [application.properties](https://github.com/OmariJuma/lipaMdogoMdogo/blob/master/src/main/resources/application.properties)

- Docker Compose for Postgres:
  - [infra/docker-compose.yaml](https://github.com/OmariJuma/lipaMdogoMdogo/blob/master/infra/docker-compose.yaml)

---

## How you can contribute / ideas I want to try

- Add request/response DTOs consistently and validation with @Valid.
- Implement loan approval endpoints and role-based checks (LOAN_APPROVER).
- Add integration tests, Postman collection and CI workflow.
- Add Swagger/OpenAPI documentation.
- Add payment flow, repayment schedule calculation and notifications.

---

## Contact / author

This is a personal learning project — a fun side project while I continue my journey towards fintech developer roles.

Author: Omari Juma (repo owner)

---

Thank you for checking the project — more improvements to come as I keep learning and building.
