package ch.studior2.buildingpermitmonitor.contracts.event;

import java.time.Instant;
import java.util.Map;

public record BuildingPermitRawEvent(
    String source, String externalId, Instant fetchedAt, Map<String, String> payload) {}
