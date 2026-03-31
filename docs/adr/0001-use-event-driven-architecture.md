# ADR 0001 - Use Event-Driven Architecture

## Status
Accepted

## Context
The platform requires scalable communication between order, inventory, payment and notification services.

## Decision
Use asynchronous communication with Kafka protocol through Azure Event Hubs.

## Consequences
- Better scalability
- Loose coupling
- Easier retry strategy
- Supports future saga patterns