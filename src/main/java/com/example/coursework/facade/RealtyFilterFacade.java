package com.example.coursework.facade;

import com.example.coursework.dto.FlatFilterDto;
import com.example.coursework.dto.HouseFilterDto;
import com.example.coursework.entity.Realty;
import com.example.coursework.service.dataServices.ExchangeRateService;
import com.example.coursework.service.dataServices.RealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RealtyFilterFacade {

    @Autowired
    private RealtyService realtyService;

    @Autowired
    private ExchangeRateService exchangeRateService;


    public List<Realty> findByFilter(HouseFilterDto houseFilterDto) {

        if (houseFilterDto.getFirstPrice() == null) houseFilterDto.setFirstPrice(0);
        if (houseFilterDto.getFirstHouseArea() == null) houseFilterDto.setFirstHouseArea(0.0);
        if (houseFilterDto.getFirstLandArea() == null) houseFilterDto.setFirstLandArea(0.0);
        if (houseFilterDto.getFirstNumberRooms() == null) houseFilterDto.setFirstNumberRooms(0);
        if (houseFilterDto.getFirstFloors() == null) houseFilterDto.setFirstFloors(0);

        if (houseFilterDto.getFurnishing() == null) houseFilterDto.setFurnishing(-1);

        if (houseFilterDto.getLastPrice() == null) houseFilterDto.setLastPrice(Integer.MAX_VALUE);
        if (houseFilterDto.getLastHouseArea() == null) houseFilterDto.setLastHouseArea(Double.MAX_VALUE);
        if (houseFilterDto.getLastLandArea() == null) houseFilterDto.setLastLandArea(Double.MAX_VALUE);
        if (houseFilterDto.getLastNumberRooms() == null) houseFilterDto.setLastNumberRooms(10000);
        if (houseFilterDto.getLastFloors() == null) houseFilterDto.setLastFloors(50000);

        System.out.println("houseFilterFinish = " + houseFilterDto);

        List<Realty> realtyList = realtyService.findByFilterOrderByPublicationDateAndTime(houseFilterDto);

        System.out.println("realtyList from data = " + realtyList);


        if (houseFilterDto.getFurnishing() != -1) {
            realtyList = realtyList.stream().filter(x -> x.getFurnishing() == getBoolean(houseFilterDto.getFurnishing())).toList();
        }
        if (!houseFilterDto.getPurpose().equals("Всі оголошення")) {
            realtyList = realtyList.stream().filter(x -> x.getPurpose().getTitle().equals(houseFilterDto.getPurpose())).toList();
        }
        if (!houseFilterDto.getRepairTitle().equals("Всі оголошення")) {
            realtyList = realtyList.stream().filter(x -> x.getRepair().getTitle().equals(houseFilterDto.getRepairTitle())).toList();
        } if (houseFilterDto.getConstructionId() != 0) {
            realtyList = realtyList.stream().filter(x -> x.getConstruction().getId() == houseFilterDto.getConstructionId()).toList();
        } if (houseFilterDto.getHeatingId() != 0) {
            realtyList = realtyList.stream().filter(x -> x.getHeating().getId() == houseFilterDto.getHeatingId()).toList();
        }

        return realtyList;
    }

  /*  private List<Realty> filterByPrice(List<Realty> realties, String currencyStr) {

        List<ExchangeRate> currencies = exchangeRateService.findAll();

        Currency currency = Currency.valueOf(currencyStr);
        List<Realty> results = new ArrayList<>();
        for (Realty realty : realties) {

            if (currency == realty.getCurrency()) {
                realties.
            }
        }
    }*/

    private boolean getBoolean(int value)
    {
        return value != 0;
    }

    public List<Realty> findByFilter(FlatFilterDto flatFilter) {
        if (flatFilter.getFirstPrice() == null) flatFilter.setFirstPrice(0);
        if (flatFilter.getFirstHouseArea() == null) flatFilter.setFirstHouseArea(0.0);
        if (flatFilter.getFirstNumberRooms() == null) flatFilter.setFirstNumberRooms(0);
        if (flatFilter.getFirstFloors() == null) flatFilter.setFirstFloors(0);

        if (flatFilter.getFurnishing() == null) flatFilter.setFurnishing(-1);
        if (flatFilter.getParking() == null) flatFilter.setParking(-1);

        if (flatFilter.getLastPrice() == null) flatFilter.setLastPrice(Integer.MAX_VALUE);
        if (flatFilter.getLastHouseArea() == null) flatFilter.setLastHouseArea(Double.MAX_VALUE);
        if (flatFilter.getLastNumberRooms() == null) flatFilter.setLastNumberRooms(10000);
        if (flatFilter.getLastFloors() == null) flatFilter.setLastFloors(50000);

        System.out.println("flatFilterFinish = " + flatFilter);

        List<Realty> realtyList = realtyService.findByFilterOrderByPublicationDateAndTime(flatFilter);


        if (flatFilter.getFurnishing() != -1) {
            realtyList = realtyList.stream().filter(x -> x.getFurnishing() == getBoolean(flatFilter.getFurnishing())).toList();
        }
        if (flatFilter.getParking() != -1) {
            realtyList = realtyList.stream().filter(x -> x.getFurnishing() == getBoolean(flatFilter.getParking())).toList();
        }
        if (!flatFilter.getPurpose().equals("Всі оголошення")) {
            realtyList = realtyList.stream().filter(x -> x.getPurpose().getTitle().equals(flatFilter.getPurpose())).toList();
        }
        if (!flatFilter.getRepairTitle().equals("Всі оголошення")) {
            realtyList = realtyList.stream().filter(x -> x.getRepair().getTitle().equals(flatFilter.getRepairTitle())).toList();
        } if (flatFilter.getConstructionId() != 0) {
            realtyList = realtyList.stream().filter(x -> x.getConstruction().getId() == flatFilter.getConstructionId()).toList();
        } if (flatFilter.getHeatingId() != 0) {
            realtyList = realtyList.stream().filter(x -> x.getHeating().getId() == flatFilter.getHeatingId()).toList();
        }

        return realtyList;
    }
}
