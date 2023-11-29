package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressRequestDTO {

    private String regionTitle;

    private Integer localityId;

    private Integer districtId;
}
