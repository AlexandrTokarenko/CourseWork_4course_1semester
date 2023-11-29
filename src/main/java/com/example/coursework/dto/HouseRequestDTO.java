package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequestDTO {

    private Float landArea;
    private Integer realtyId;
    private Integer numberOfFloors;
}
