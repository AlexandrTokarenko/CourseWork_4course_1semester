package com.example.coursework.dto;

import com.example.coursework.entity.Realty;
import com.example.coursework.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RealtyUpdateDTO {

    private Integer id;
    private String additionalInformation;

    private Boolean furnishing;

    private String header;

    private Float housingArea;

    private Integer addressId;

    private Integer numberOfRooms;

    private Integer price;

    private Integer constructionId;

    private Integer heatingId;

    private String propertyTitle;

    private String purposeTitle;

    private String repairTitle;
    private Currency currency;
    /*public static RealtyUpdateDTO of(RealtyHouseDTO realty) {

        return RealtyUpdateDTO.builder()
                .id(realty.getId())
                .additionalInformation(realty.getAdditionalInformation())
                .furnishing(realty.getFurnishing())
                .header(realty.getHeader())
                .housingArea(realty.getHousingArea())
                .numberOfRooms(realty.getNumberOfRooms())
                .price(realty.getPrice())
                .constructionId(realty.getConstructionId())
                .heatingId(realty.getHeatingId())
                .propertyTitle(realty.getPropertyTitle())
                .purposeTitle(realty.getPurposeTitle())
                .repairTitle(realty.getRepairTitle())
                .currency(realty.getCurrency()).build();
    }

    public static RealtyUpdateDTO of(RealtyFlatDto realty) {

        return RealtyUpdateDTO.builder()
                .id(realty.getId())
                .additionalInformation(realty.getAdditionalInformation())
                .furnishing(realty.getFurnishing())
                .header(realty.getHeader())
                .housingArea(realty.getHousingArea())
                .numberOfRooms(realty.getNumberOfRooms())
                .price(realty.getPrice())
                .constructionId(realty.getConstructionId())
                .heatingId(realty.getHeatingId())
                .propertyTitle(realty.getPropertyTitle())
                .purposeTitle(realty.getPurposeTitle())
                .repairTitle(realty.getRepairTitle()).build();
    }*/

    public static RealtyUpdateDTO of(Realty realty) {
        return RealtyUpdateDTO.builder()
                .id(realty.getId())
                .additionalInformation(realty.getAdditionalInformation())
                .furnishing(realty.getFurnishing())
                .header(realty.getHeader())
                .housingArea(realty.getHousingArea())
                .numberOfRooms(realty.getNumberOfRooms())
                .price(realty.getPrice())
//                .addressRegionTitle(realty.getAddress().getRegion().getTitle())
//                .addressLocalityId(realty.getAddress().getLocality().getId())
//                .addressDistrictId(realty.getAddress().getDistrict().getId())
                .constructionId(realty.getConstruction().getId())
                .heatingId(realty.getHeating().getId())
                .propertyTitle(realty.getProperty().getTitle())
                .purposeTitle(realty.getPurpose().getTitle())
                .repairTitle(realty.getRepair().getTitle()).build();
    }
}
