# Library Management API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![Maven](https://img.shields.io/badge/build-Maven-red)
![Status](https://img.shields.io/badge/status-in%20progress-yellow)

REST API for managing a library — books, physical copies, members, and loans.

Each member has a borrowing limit (max 5 active loans). Members with overdue books or a suspended status cannot borrow
until the issue is resolved.

## Tech stack

- Java 21
- Spring Boot 3.4.5
- Maven

## Running locally

Open the project in IntelliJ and run `BookingApiApplication`. The server starts on
`http://localhost:8080`.

## API

### Books

| Method | Endpoint             | Description                 |
|--------|----------------------|-----------------------------|
| GET    | `/books`             | List all books              |
| GET    | `/books/{id}`        | Get bookEntity by ID        |
| POST   | `/books`             | Add a new bookEntity        |
| GET    | `/books/{id}/copies` | List copies of a bookEntity |
| POST   | `/books/{id}/copies` | Add a physical copy         |

### Members

| Method | Endpoint   | Description           |
|--------|------------|-----------------------|
| GET    | `/members` | List all members      |
| POST   | `/members` | Register a new member |

### Loans

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| POST   | `/loans`             | Borrow a bookEntity copy |
| POST   | `/loans/{id}/return` | Return a borrowed copy   |

## Business rules

- A copy must be `AVAILABLE` to be borrowed
- A member can have at most **5 active loans** at once
- A `SUSPENDED` member cannot borrow
- A member with any **overdue** loan is blocked from new loans
- Loan period is **14 days** from the borrow date
