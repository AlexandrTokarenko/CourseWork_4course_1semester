package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseFilterDto {

    private String property;
    private String purpose;
    private Integer firstPrice;
    private Double firstHouseArea;
    private Double lastHouseArea;
    private Integer lastPrice;
    private Double firstLandArea;
    private Double lastLandArea;
    private Integer firstNumberRooms;
    private Integer lastNumberRooms;
    private Integer constructionId;
    private Integer heatingId;
    private Integer furnishing;
    private Integer firstFloors;
    private Integer lastFloors;
    private Integer districtId;
    private Integer localityId;
    private String regionTitle;
    private String repairTitle;
    private String currency;
}
