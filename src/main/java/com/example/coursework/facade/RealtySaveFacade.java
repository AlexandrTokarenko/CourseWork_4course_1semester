package com.example.coursework.facade;

import com.example.coursework.dto.*;
import com.example.coursework.model.Currency;
import com.example.coursework.service.*;
import com.example.coursework.service.dataServices.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class RealtySaveFacade {

    private final AddressService addressService;
    private final RealtyService realtyService;
    private final AdvertisementService advertisementService;
    private final FlatService flatService;
    private final FileService fileService;
    private final HouseService houseService;
    private final ModelMapper modelMapper;

    public void save(RealtyFlatDto realtyFlatDto, String userName, String property) {

        int addressId = addressService.save(realtyFlatDto.getDistrictId(), realtyFlatDto.getLocalityId(), realtyFlatDto.getRegionTitle());
        realtyFlatDto.setPropertyTitle(property);

        RealtyRequestDTO realtyRequestDTO = modelMapper.map(realtyFlatDto, RealtyRequestDTO.class);
        realtyRequestDTO.setCurrency(Currency.valueOf(realtyFlatDto.getCurrency()));

        int realtyId = realtyService.save(realtyRequestDTO,addressId);

        advertisementService.save(new AdvertisementRequestDTO(new Timestamp(System.currentTimeMillis()),
                realtyId,userName));

        FlatRequestDTO flatRequestDto = modelMapper.map(realtyFlatDto, FlatRequestDTO.class);

        flatService.saveFlatRequestDTO(flatRequestDto,realtyId);

        fileService.save(realtyId,realtyFlatDto.getImageFiles());
    }

    public void save(RealtyHouseDTO realtyHouseDTO, String userName, String property) {

        int addressId = addressService.save(realtyHouseDTO.getDistrictId(), realtyHouseDTO.getLocalityId(), realtyHouseDTO.getRegionTitle());
        realtyHouseDTO.setPropertyTitle(property);

        RealtyRequestDTO realtyRequestDTO = modelMapper.map(realtyHouseDTO, RealtyRequestDTO.class);

        realtyRequestDTO.setCurrency(Currency.valueOf(realtyHouseDTO.getCurrency()));

        System.out.println("realtyRequestDTO = " + realtyRequestDTO);

        int realtyId = realtyService.save(realtyRequestDTO,addressId);

        advertisementService.save(new AdvertisementRequestDTO(new Timestamp(System.currentTimeMillis()),
                realtyId,userName));

        HouseRequestDTO houseRequestDTO = modelMapper.map(realtyHouseDTO, HouseRequestDTO.class);

        houseService.save(houseRequestDTO,realtyId);

        fileService.save(realtyId,realtyHouseDTO.getImageFiles());
    }
}
