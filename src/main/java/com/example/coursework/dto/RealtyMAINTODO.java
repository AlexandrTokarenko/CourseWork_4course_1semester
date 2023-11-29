package com.example.coursework.dto;

import com.example.coursework.entity.Address;
import com.example.coursework.model.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class RealtyMAINTODO {

    private Integer id;
    private String header;
    private Integer numberOfRooms;
    private Float housingArea;
    /*private String regionTitle;
    private String localityTitle;
    private String districtTitle;*/
    private Address address;
    private Integer priceInFirstCurrency;
    private Currency firstCurrency;
    private int priceInSecondCurrency;
    private Currency lastCurrency;
    private String firstImage;
    public RealtyMAINTODO(Integer id, String header, Integer numberOfRooms, Float housingArea, Address address, Integer priceInFirstCurrency, Currency firstCurrency) {
        this.id = id;
        this.header = header;
        this.numberOfRooms = numberOfRooms;
        this.housingArea = housingArea;
        this.address = address;
        this.priceInFirstCurrency = priceInFirstCurrency;
        this.firstCurrency = firstCurrency;
    }
}
