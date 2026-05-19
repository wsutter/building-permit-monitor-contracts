package ch.studior2.buildingpermitmonitor.contracts.topic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("KafkaTopics")
class KafkaTopicsTest {

  @Nested
  @DisplayName("topic constants")
  class TopicConstants {

    @ParameterizedTest(name = "{0}")
    @MethodSource("topicConstants")
    @DisplayName("should expose stable topic names")
    void shouldExposeStableTopicNames(String actual, String expected) {
      assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> topicConstants() {
      return Stream.of(
          arguments(named("raw topic", KafkaTopics.RAW), "building-permit.raw"),
          arguments(
              named("normalized topic", KafkaTopics.NORMALIZED), "building-permit.normalized"),
          arguments(named("enriched topic", KafkaTopics.ENRICHED), "building-permit.enriched"),
          arguments(named("dead-letter topic", KafkaTopics.DLQ), "building-permit.dlq"));
    }
  }
}
