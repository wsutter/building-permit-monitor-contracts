package ch.studior2.buildingpermitmonitor.contracts.config;

import ch.studior2.buildingpermitmonitor.contracts.topic.KafkaTopics;
import java.util.Map;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

  private static final Map<String, String> DLQ_BY_SOURCE_TOPIC =
      Map.of(
          KafkaTopics.RAW, KafkaTopics.RAW_DLQ,
          KafkaTopics.NORMALIZED, KafkaTopics.NORMALIZED_DLQ,
          KafkaTopics.ENRICHED, KafkaTopics.ENRICHED_DLQ);

  @Bean
  public DefaultErrorHandler kafkaErrorHandler(KafkaTemplate<Object, Object> kafkaTemplate) {
    DeadLetterPublishingRecoverer recoverer =
        new DeadLetterPublishingRecoverer(
            kafkaTemplate,
            (record, exception) -> {
              String dlqTopic =
                  DLQ_BY_SOURCE_TOPIC.getOrDefault(record.topic(), record.topic() + ".dlq");

              return new TopicPartition(dlqTopic, record.partition());
            });

    return new DefaultErrorHandler(recoverer, new FixedBackOff(1_000L, 3));
  }
}
