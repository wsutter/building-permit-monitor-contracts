package ch.studior2.buildingpermitmonitor.contracts.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * Raw building-permit event mapped 1:1 from the Kanton Zürich open-data CSV (dataset
 * KTZH_00002982).
 *
 * <p>The {@link JsonProperty} names bind each component to the exact CSV column header. The source
 * file uses underscore-separated path segments with intra-segment camelCase (e.g. {@code
 * buildingContractor_company_address_swissZipCode}) and the {@code noUID} casing, which no standard
 * {@code PropertyNamingStrategy} reproduces — hence explicit annotations. Only {@code id}, {@code
 * publicationNumber}, {@code publicationDate}, {@code entryDeadline}, {@code expirationDate} and
 * {@code projectDescription} match the component name verbatim.
 */
public record BuildingPermitRawEvent(
    @JsonProperty("id") String id,
    @JsonProperty("publicationNumber") String publicationNumber,
    @JsonProperty("publicationDate") LocalDate publicationDate,
    @JsonProperty("entryDeadline") LocalDate entryDeadline,
    @JsonProperty("expirationDate") LocalDate expirationDate,
    @JsonProperty("bfs_nr") Integer bfsNr,
    @JsonProperty("municipality_name") String municipalityName,
    @JsonProperty("buildingContractor_legalEntity_selectType")
        String buildingContractorLegalEntitySelectType,
    @JsonProperty("buildingContractor_noUID") Boolean buildingContractorNoUid,
    @JsonProperty("buildingContractor_index") Integer buildingContractorIndex,
    @JsonProperty("buildingContractor_company_legalForm")
        Integer buildingContractorCompanyLegalForm,
    @JsonProperty("buildingContractor_company_legalForm_de")
        String buildingContractorCompanyLegalFormDe,
    @JsonProperty("buildingContractor_company_address_swissZipCode")
        Integer buildingContractorCompanyAddressSwissZipCode,
    @JsonProperty("buildingContractor_company_address_town")
        String buildingContractorCompanyAddressTown,
    @JsonProperty("projectFramer_selectType") String projectFramerSelectType,
    @JsonProperty("projectFramer_legalEntity_selectType") String projectFramerLegalEntitySelectType,
    @JsonProperty("projectFramer_noUID") Boolean projectFramerNoUid,
    @JsonProperty("projectFramer_index") Integer projectFramerIndex,
    @JsonProperty("projectFramer_company_legalForm") Integer projectFramerCompanyLegalForm,
    @JsonProperty("projectFramer_company_legalForm_de") String projectFramerCompanyLegalFormDe,
    @JsonProperty("projectFramer_company_address_swissZipCode")
        Integer projectFramerCompanyAddressSwissZipCode,
    @JsonProperty("projectFramer_company_address_town") String projectFramerCompanyAddressTown,
    @JsonProperty("delegation_selectType") String delegationSelectType,
    @JsonProperty("delegation_buildingContractor_legalEntity_selectType")
        String delegationBuildingContractorLegalEntitySelectType,
    @JsonProperty("delegation_buildingContractor_noUID") Boolean delegationBuildingContractorNoUid,
    @JsonProperty("delegation_buildingContractor_index") Integer delegationBuildingContractorIndex,
    @JsonProperty("delegation_buildingContractor_company_legalForm")
        Integer delegationBuildingContractorCompanyLegalForm,
    @JsonProperty("delegation_buildingContractor_company_legalForm_de")
        String delegationBuildingContractorCompanyLegalFormDe,
    @JsonProperty("delegation_buildingContractor_company_address_swissZipCode")
        Integer delegationBuildingContractorCompanyAddressSwissZipCode,
    @JsonProperty("delegation_buildingContractor_company_address_town")
        String delegationBuildingContractorCompanyAddressTown,
    @JsonProperty("projectDescription") String projectDescription,
    @JsonProperty("projectLocation_address_index") Integer projectLocationAddressIndex,
    @JsonProperty("projectLocation_address_street") String projectLocationAddressStreet,
    @JsonProperty("projectLocation_address_houseNumber") String projectLocationAddressHouseNumber,
    @JsonProperty("projectLocation_address_swissZipCode")
        Integer projectLocationAddressSwissZipCode,
    @JsonProperty("projectLocation_address_town") String projectLocationAddressTown,
    @JsonProperty("districtCadastre_relation_cadastre") String districtCadastreRelationCadastre,
    @JsonProperty("districtCadastre_relation_cadastre_raw")
        String districtCadastreRelationCadastreRaw,
    @JsonProperty("districtCadastre_relation_buildingZone")
        String districtCadastreRelationBuildingZone,
    @JsonProperty("districtCadastre_relation_district") String districtCadastreRelationDistrict,
    @JsonProperty("last_updated") LocalDate lastUpdated) {
  public String externalId() {
    return id + ":" + publicationNumber;
  }
}
