module ch.studior2.buildingpermitmonitor.contracts {
  requires com.fasterxml.jackson.annotation;
  requires tools.jackson.databind;
  requires spring.context;

  exports ch.studior2.buildingpermitmonitor.contracts.event;
  exports ch.studior2.buildingpermitmonitor.contracts.model;
  exports ch.studior2.buildingpermitmonitor.contracts.topic;
  exports ch.studior2.buildingpermitmonitor.contracts.config;
  exports ch.studior2.buildingpermitmonitor.contracts.group;
}
