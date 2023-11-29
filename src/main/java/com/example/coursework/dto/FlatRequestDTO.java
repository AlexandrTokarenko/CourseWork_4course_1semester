package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlatRequestDTO {

    private Integer numberOfFloors;

    private Integer floor;

    private Boolean parking;
}
