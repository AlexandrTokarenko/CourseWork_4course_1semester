package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class AdvertisementRequestDTO {

    private Timestamp publicationDateAndTime;

    private Integer realtyId;

    private String userEmail;
}
