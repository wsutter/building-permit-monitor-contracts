package ch.studior2.buildingpermitmonitor.contracts.event;

import java.time.LocalDate;

public record BuildingPermitNormalizedEvent(
    String permitId,
    String source,
    String externalId,
    String title,
    String description,
    String category,
    String status,
    String municipality,
    LocalDate publishedDate,
    String address) {}
