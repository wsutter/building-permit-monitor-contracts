package ch.studior2.buildingpermitmonitor.contracts.event;

import static org.assertj.core.api.Assertions.assertThat;

import ch.studior2.buildingpermitmonitor.contracts.config.JacksonConfig;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

@DisplayName("BuildingPermitRawEvent deserialization from OGD CSV headers")
class BuildingPermitRawEventDeserializationTest {

  // Mirrors the production mapper bean (contracts JacksonConfig) used by the ingestor.
  private final JsonMapper jsonMapper = new JacksonConfig().jsonMapper();

  @Test
  @DisplayName("should bind all 41 real OGD CSV headers to their record components")
  void shouldBindAllOgdHeadersToRecordComponents() {
    BuildingPermitRawEvent event =
        jsonMapper.convertValue(fullCsvRow(), BuildingPermitRawEvent.class);

    // Verbatim-matching headers.
    assertThat(event.id()).isEqualTo("00002982");
    assertThat(event.publicationNumber()).isEqualTo("00006183");
    assertThat(event.publicationDate()).isEqualTo(LocalDate.of(2026, 5, 17));
    assertThat(event.entryDeadline()).isEqualTo(LocalDate.of(2026, 6, 10));
    assertThat(event.expirationDate()).isEqualTo(LocalDate.of(2026, 12, 31));
    assertThat(event.projectDescription()).isEqualTo("Umbau Wohnung");

    // Derived business key.
    assertThat(event.externalId()).isEqualTo("00002982:00006183");

    // bfs / municipality.
    assertThat(event.bfsNr()).isEqualTo(141);
    assertThat(event.municipalityName()).isEqualTo("Thalwil");

    // buildingContractor_* group.
    assertThat(event.buildingContractorLegalEntitySelectType()).isEqualTo("COMPANY");
    assertThat(event.buildingContractorNoUid()).isTrue();
    assertThat(event.buildingContractorIndex()).isEqualTo(1);
    assertThat(event.buildingContractorCompanyLegalForm()).isEqualTo(2);
    assertThat(event.buildingContractorCompanyLegalFormDe()).isEqualTo("Aktiengesellschaft");
    assertThat(event.buildingContractorCompanyAddressSwissZipCode()).isEqualTo(8800);
    assertThat(event.buildingContractorCompanyAddressTown()).isEqualTo("Thalwil");

    // projectFramer_* group.
    assertThat(event.projectFramerSelectType()).isEqualTo("PERSON");
    assertThat(event.projectFramerLegalEntitySelectType()).isEqualTo("COMPANY");
    assertThat(event.projectFramerNoUid()).isFalse();
    assertThat(event.projectFramerIndex()).isEqualTo(2);
    assertThat(event.projectFramerCompanyLegalForm()).isEqualTo(3);
    assertThat(event.projectFramerCompanyLegalFormDe()).isEqualTo("GmbH");
    assertThat(event.projectFramerCompanyAddressSwissZipCode()).isEqualTo(8001);
    assertThat(event.projectFramerCompanyAddressTown()).isEqualTo("Zürich");

    // delegation_buildingContractor_* group.
    assertThat(event.delegationSelectType()).isEqualTo("DELEGATED");
    assertThat(event.delegationBuildingContractorLegalEntitySelectType()).isEqualTo("COMPANY");
    assertThat(event.delegationBuildingContractorNoUid()).isTrue();
    assertThat(event.delegationBuildingContractorIndex()).isEqualTo(3);
    assertThat(event.delegationBuildingContractorCompanyLegalForm()).isEqualTo(4);
    assertThat(event.delegationBuildingContractorCompanyLegalFormDe()).isEqualTo("Einzelfirma");
    assertThat(event.delegationBuildingContractorCompanyAddressSwissZipCode()).isEqualTo(8002);
    assertThat(event.delegationBuildingContractorCompanyAddressTown()).isEqualTo("Winterthur");

    // projectLocation_address_* group (geocoding input).
    assertThat(event.projectLocationAddressIndex()).isEqualTo(4);
    assertThat(event.projectLocationAddressStreet()).isEqualTo("Eisenbahnstrasse");
    assertThat(event.projectLocationAddressHouseNumber()).isEqualTo("27");
    assertThat(event.projectLocationAddressSwissZipCode()).isEqualTo(8800);
    assertThat(event.projectLocationAddressTown()).isEqualTo("Thalwil");

    // districtCadastre_relation_* group.
    assertThat(event.districtCadastreRelationCadastre()).isEqualTo("CAD-1");
    assertThat(event.districtCadastreRelationCadastreRaw()).isEqualTo("CAD-1-RAW");
    assertThat(event.districtCadastreRelationBuildingZone()).isEqualTo("W2");
    assertThat(event.districtCadastreRelationDistrict()).isEqualTo("Bezirk Horgen");

    // last_updated.
    assertThat(event.lastUpdated()).isEqualTo(LocalDate.of(2026, 5, 18));
  }

  // All 41 real Kanton ZH OGD CSV header names (dataset KTZH_00002982), each with a distinct value
  // so a wrong @JsonProperty mapping surfaces as a null/mismatched component.
  private static Map<String, String> fullCsvRow() {
    Map<String, String> row = new HashMap<>();
    row.put("id", "00002982");
    row.put("publicationNumber", "00006183");
    row.put("publicationDate", "2026-05-17");
    row.put("entryDeadline", "2026-06-10");
    row.put("expirationDate", "2026-12-31");
    row.put("bfs_nr", "141");
    row.put("municipality_name", "Thalwil");
    row.put("buildingContractor_legalEntity_selectType", "COMPANY");
    row.put("buildingContractor_noUID", "true");
    row.put("buildingContractor_index", "1");
    row.put("buildingContractor_company_legalForm", "2");
    row.put("buildingContractor_company_legalForm_de", "Aktiengesellschaft");
    row.put("buildingContractor_company_address_swissZipCode", "8800");
    row.put("buildingContractor_company_address_town", "Thalwil");
    row.put("projectFramer_selectType", "PERSON");
    row.put("projectFramer_legalEntity_selectType", "COMPANY");
    row.put("projectFramer_noUID", "false");
    row.put("projectFramer_index", "2");
    row.put("projectFramer_company_legalForm", "3");
    row.put("projectFramer_company_legalForm_de", "GmbH");
    row.put("projectFramer_company_address_swissZipCode", "8001");
    row.put("projectFramer_company_address_town", "Zürich");
    row.put("delegation_selectType", "DELEGATED");
    row.put("delegation_buildingContractor_legalEntity_selectType", "COMPANY");
    row.put("delegation_buildingContractor_noUID", "true");
    row.put("delegation_buildingContractor_index", "3");
    row.put("delegation_buildingContractor_company_legalForm", "4");
    row.put("delegation_buildingContractor_company_legalForm_de", "Einzelfirma");
    row.put("delegation_buildingContractor_company_address_swissZipCode", "8002");
    row.put("delegation_buildingContractor_company_address_town", "Winterthur");
    row.put("projectDescription", "Umbau Wohnung");
    row.put("projectLocation_address_index", "4");
    row.put("projectLocation_address_street", "Eisenbahnstrasse");
    row.put("projectLocation_address_houseNumber", "27");
    row.put("projectLocation_address_swissZipCode", "8800");
    row.put("projectLocation_address_town", "Thalwil");
    row.put("districtCadastre_relation_cadastre", "CAD-1");
    row.put("districtCadastre_relation_cadastre_raw", "CAD-1-RAW");
    row.put("districtCadastre_relation_buildingZone", "W2");
    row.put("districtCadastre_relation_district", "Bezirk Horgen");
    row.put("last_updated", "2026-05-18");
    return row;
  }
}
