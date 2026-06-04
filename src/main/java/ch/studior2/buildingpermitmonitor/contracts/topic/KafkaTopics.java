package ch.studior2.buildingpermitmonitor.contracts.topic;

public final class KafkaTopics {

  public static final String RAW = "building-permit.raw";
  public static final String NORMALIZED = "building-permit.normalized";
  public static final String ENRICHED = "building-permit.enriched";
  public static final String RAW_DLQ = "building-permit.raw.dlq";
  public static final String NORMALIZED_DLQ = "building-permit.normalized.dlq";
  public static final String ENRICHED_DLQ = "building-permit.enriched.dlq";

  private KafkaTopics() {}
}
