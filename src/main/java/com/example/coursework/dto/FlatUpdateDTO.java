package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlatUpdateDTO {

    private int realtyId;
    private boolean parking;
    private int numberOfFloors;
    private int floor;


    public static FlatUpdateDTO of(RealtyFlatDto realty) {
        return FlatUpdateDTO.builder()
                .floor(realty.getFloor())
                .parking(realty.getParking())
                .numberOfFloors(realty.getNumberOfFloors()).build();
    }
}
