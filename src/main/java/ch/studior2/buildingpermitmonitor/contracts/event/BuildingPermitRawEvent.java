package ch.studior2.buildingpermitmonitor.contracts.event;

import java.time.LocalDate;

public record BuildingPermitRawEvent(
    String id,
    String publicationNumber,
    LocalDate publicationDate,
    LocalDate entryDeadline,
    LocalDate expirationDate,
    Integer bfsNr,
    String municipalityName,
    String buildingContractorLegalEntitySelectType,
    Boolean buildingContractorNoUid,
    Integer buildingContractorIndex,
    Integer buildingContractorCompanyLegalForm,
    String buildingContractorCompanyLegalFormDe,
    Integer buildingContractorCompanyAddressSwissZipCode,
    String buildingContractorCompanyAddressTown,
    String projectFramerSelectType,
    String projectFramerLegalEntitySelectType,
    Boolean projectFramerNoUid,
    Integer projectFramerIndex,
    Integer projectFramerCompanyLegalForm,
    String projectFramerCompanyLegalFormDe,
    Integer projectFramerCompanyAddressSwissZipCode,
    String projectFramerCompanyAddressTown,
    String delegationSelectType,
    String delegationBuildingContractorLegalEntitySelectType,
    Boolean delegationBuildingContractorNoUid,
    Integer delegationBuildingContractorIndex,
    Integer delegationBuildingContractorCompanyLegalForm,
    String delegationBuildingContractorCompanyLegalFormDe,
    Integer delegationBuildingContractorCompanyAddressSwissZipCode,
    String delegationBuildingContractorCompanyAddressTown,
    String projectDescription,
    Integer projectLocationAddressIndex,
    String projectLocationAddressStreet,
    String projectLocationAddressHouseNumber,
    Integer projectLocationAddressSwissZipCode,
    String projectLocationAddressTown,
    String districtCadastreRelationCadastre,
    String districtCadastreRelationCadastreRaw,
    String districtCadastreRelationBuildingZone,
    String districtCadastreRelationDistrict,
    LocalDate lastUpdated) {
  public String externalId() {
    return id + ":" + publicationNumber;
  }
}
