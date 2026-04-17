# Copilot Code Review Instructions

## Context
This repository contains a Java Spring Boot backend using:

- Spring Web
- Spring Data JPA
- Kafka
- PostgreSQL
- Clean Architecture
- Domain-Driven Design

---

## Review Goal
Review pull requests focusing on:

- Bugs and correctness issues
- Security vulnerabilities
- Architectural consistency
- Code quality and maintainability

---

## Review Priorities

### 1. Correctness & Bugs
- Null handling issues
- Improper Optional usage
- Unhandled exceptions
- Concurrency issues
- Date/time handling
- Numeric conversions

---

### 2. Security
- Missing authorization checks
- Input validation issues
- SQL injection risks
- Sensitive data exposure
- Misconfigured endpoints

---

### 3. Persistence (JPA)
- N+1 query problems
- Missing pagination
- Incorrect cascade usage
- Transaction boundaries
- Entity mapping issues

---

### 4. API Contracts
- DTO validation annotations
- Consistent HTTP responses
- Proper error handling
- Idempotency where required

---

### 5. Architecture
- Controllers must be thin
- Business logic in services/domain
- Repositories only for persistence
- Follow Clean Architecture layers

---

### 6. Tests
- Missing test coverage
- Edge cases not covered
- Suggest integration tests when needed

---

### 7. Observability
- Proper logging levels
- No sensitive data in logs
- Suggest metrics where relevant

---

## Review Style
- Be concise and direct
- Prioritize high-impact issues first
- Suggest concrete fixes
- Reference specific code lines

---

## Checklist
- Validation present in endpoints
- Authorization enforced
- No N+1 queries
- Transactions correctly applied
- Logs are safe
- Code follows architecture layers