package ch.studior2.buildingpermitmonitor.contracts.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
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
    @DisplayName("should keep raw CSV event values")
    void shouldKeepRawEventValues(BuildingPermitRawEvent event, String expectedBusinessKey) {
      assertEquals("00002982", event.id());
      assertEquals("00006183", event.publicationNumber());
      assertEquals(expectedBusinessKey, event.externalId());
      assertEquals("Thalwil", event.municipalityName());
    }

    static Stream<Arguments> rawEvents() {
      return Stream.of(
          arguments(
              named(
                  "raw event from Kanton Zürich",
                  new BuildingPermitRawEvent(
                      "00002982",
                      "00006183",
                      null,
                      null,
                      null,
                      null,
                      "Thalwil",
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      "Umbau Wohnung",
                      null,
                      "Eisenbahnstrasse",
                      "27",
                      8800,
                      "Thalwil",
                      null,
                      null,
                      null,
                      null,
                      null)),
              "00002982:00006183"));
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
