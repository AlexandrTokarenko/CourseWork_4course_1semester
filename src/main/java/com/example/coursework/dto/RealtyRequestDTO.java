package com.example.coursework.dto;

import com.example.coursework.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtyRequestDTO {

    private String additionalInformation;

    private Boolean furnishing;

    private String header;

    private Float housingArea;

    private String regionTitle;

    private Integer localityId;

    private Integer districtId;

    private Integer numberOfRooms;

    private Integer price;

    private Integer constructionId;

    private Integer heatingId;

    private String propertyTitle;

    private String purposeTitle;

    private String repairTitle;

    private Currency currency;
}
