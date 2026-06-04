module ch.studior2.buildingpermitmonitor.contracts {
  requires com.fasterxml.jackson.annotation;
  requires tools.jackson.databind;
  requires spring.context;
  requires spring.kafka;
  requires spring.core;
  requires kafka.clients;

  exports ch.studior2.buildingpermitmonitor.contracts.event;
  exports ch.studior2.buildingpermitmonitor.contracts.model;
  exports ch.studior2.buildingpermitmonitor.contracts.topic;
  exports ch.studior2.buildingpermitmonitor.contracts.config;
  exports ch.studior2.buildingpermitmonitor.contracts.group;
  exports ch.studior2.buildingpermitmonitor.contracts.geocoding;
}
