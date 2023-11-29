package com.example.coursework.dto;

public record RealtyDTO(Integer id,
        String header,
        Integer numberOfRooms,
        Float housingArea,
        String regionTitle,
        String localityTitle,
        String districtTitle,
        Integer price) {
    public RealtyDTO(Integer id, String header, Integer numberOfRooms, Float housingArea, String regionTitle, String localityTitle, String districtTitle, Integer price) {
        this.id = id;
        this.header = header;
        this.numberOfRooms = numberOfRooms;
        this.housingArea = housingArea;
        this.regionTitle = regionTitle;
        this.localityTitle = localityTitle;
        this.districtTitle = districtTitle;
        this.price = price;
    }
}
