package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseUpdateDTO{

    private Float landArea;
    private Integer numberOfFloors;
    private Integer realtyId;

    public static HouseUpdateDTO of(RealtyHouseDTO realty) {
        return HouseUpdateDTO.builder()
                .numberOfFloors(realty.getNumberOfFloors())
                .landArea(realty.getLandArea()).build();
    }

}
