# Contracts Module

## Purpose

The `contracts` module contains all shared contracts used by the Building Permit Monitor platform.

It provides a single source of truth for:

* Kafka topic definitions
* Kafka consumer group identifiers
* Event schemas
* Shared DTOs
* Cross-module configuration
* etc.

All other modules depend on this module.

## Responsibilities

### Event Definitions

The module contains all event classes exchanged through Kafka:

* `BuildingPermitRawEvent`
* `BuildingPermitNormalizedEvent`
* `BuildingPermitEnrichedEvent`

These classes define the event-driven contract between services.

### Kafka Topics

Kafka topics are centrally defined in `KafkaTopics`.

Example:

```java
KafkaTopics.RAW
KafkaTopics.NORMALIZED
KafkaTopics.ENRICHED
```

This prevents duplicated string literals across services.

### Consumer Groups

Kafka consumer groups are centrally defined in `KafkaGroupIDs`.

```java
KafkaGroupIDs.NORMALIZER
KafkaGroupIDs.ENRICHER
KafkaGroupIDs.PERSISTENCE
```

### Dead Letter Queue Configuration

The module provides a shared Kafka error handling configuration.

Each service automatically routes failed events to its dedicated Dead Letter Queue:

| Service     | DLQ                              |
| ----------- | -------------------------------- |
| Normalizer  | `building-permit.raw.dlq`        |
| Enricher    | `building-permit.normalized.dlq` |
| Persistence | `building-permit.enriched.dlq`   |

## Dependency Usage

All application modules depend on this module:

```text
contracts
├── ingestor
├── normalizer
├── enricher
└── persistence
```

## Technologies / Frameworks

* Java 25
* Spring Boot 4
* Spring Kafka
* Jackson
