package ch.studior2.buildingpermitmonitor.contracts.event;

import ch.studior2.buildingpermitmonitor.contracts.geocoding.GeocodingProvider;
import ch.studior2.buildingpermitmonitor.contracts.geocoding.GeocodingQuality;
import java.time.LocalDate;

public record BuildingPermitEnrichedEvent(
    String permitId,
    String source,
    String externalId,
    String title,
    String description,
    String category,
    String status,
    String municipality,
    LocalDate publishedDate,
    String address,
    Double latitude,
    Double longitude,
    GeocodingProvider geocodingProvider,
    GeocodingQuality geocodingQuality) {}
