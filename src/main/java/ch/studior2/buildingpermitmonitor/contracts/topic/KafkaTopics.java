package ch.studior2.buildingpermitmonitor.contracts.topic;

public final class KafkaTopics {

  public static final String RAW = "building-permit.raw";
  public static final String NORMALIZED = "building-permit.normalized";
  public static final String ENRICHED = "building-permit.enriched";
  public static final String DLQ = "building-permit.dlq";

  private KafkaTopics() {}
}
