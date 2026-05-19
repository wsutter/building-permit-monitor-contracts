package ch.studior2.buildingpermitmonitor.contracts.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Building permit contract events")
class BuildingPermitEventTest {

  @Nested
  @DisplayName("raw events")
  class RawEvents {

    @ParameterizedTest(name = "{0}")
    @MethodSource("rawEvents")
    @DisplayName("should keep source, external id and payload unchanged")
    void shouldKeepRawEventValues(BuildingPermitRawEvent event, String expectedExternalId) {
      assertEquals("kt-zh", event.source());
      assertEquals(expectedExternalId, event.externalId());
      assertEquals("Thalwil", event.payload().get("gemeinde"));
    }

    static Stream<Arguments> rawEvents() {
      return Stream.of(
          arguments(
              named(
                  "raw event from Kanton Zürich",
                  new BuildingPermitRawEvent(
                      "kt-zh",
                      "123456",
                      Instant.parse("2026-05-17T18:30:00Z"),
                      Map.of("gemeinde", "Thalwil"))),
              "123456"));
    }
  }

  @Nested
  @DisplayName("normalized events")
  class NormalizedEvents {

    @ParameterizedTest(name = "{0}")
    @MethodSource("normalizedEvents")
    @DisplayName("should keep stable permit id")
    void shouldKeepStablePermitId(BuildingPermitNormalizedEvent event, String expectedPermitId) {
      assertEquals(expectedPermitId, event.permitId());
      assertEquals(LocalDate.of(2026, 5, 17), event.publishedDate());
    }

    static Stream<Arguments> normalizedEvents() {
      return Stream.of(
          arguments(
              named(
                  "normalized renovation event",
                  new BuildingPermitNormalizedEvent(
                      "kt-zh:123456",
                      "kt-zh",
                      "123456",
                      "Umbau Wohnung",
                      "Umbau Wohnung, Balkoninstandsetzung",
                      "RENOVATION",
                      "SUBMITTED",
                      "Thalwil",
                      LocalDate.of(2026, 5, 17),
                      "Eisenbahnstrasse 27, 8800 Thalwil")),
              "kt-zh:123456"));
    }
  }
}
