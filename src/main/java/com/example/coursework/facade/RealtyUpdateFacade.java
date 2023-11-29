package com.example.coursework.facade;

import com.example.coursework.dto.*;
import com.example.coursework.model.Currency;
import com.example.coursework.service.*;
import com.example.coursework.service.dataServices.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RealtyUpdateFacade {
    private final AddressService addressService;
    private final ModelMapper modelMapper;
    private final HouseService houseService;
    private final FlatService flatService;
    private final RealtyService realtyService;
    private final ImageService imageService;
    private final FileService fileService;

    public void update(RealtyHouseDTO realtyHouse, MultipartFile[] imageFiles, Integer realtyId) {

       // RealtyUpdateDTO realty = RealtyUpdateDTO.of(realtyHouse);

        TypeMap<RealtyHouseDTO, RealtyUpdateDTO> propertyMapper = modelMapper.getTypeMap(RealtyHouseDTO.class, RealtyUpdateDTO.class);

/*        if (propertyMapper == null) {

            propertyMapper = modelMapper.createTypeMap(RealtyHouseDTO.class, RealtyUpdateDTO.class);

            propertyMapper.addMappings(
                    mapper -> {
                        mapper.map(RealtyHouseDTO::getCurrency, (RealtyUpdateDTO realtyUpdateDTO, String currency) -> realtyUpdateDTO.setCurrency(Currency.valueOf(currency)));
                    }
            );
        }*/


        RealtyUpdateDTO realty = modelMapper.map(realtyHouse, RealtyUpdateDTO.class);

        realty.setCurrency(Currency.valueOf(realtyHouse.getCurrency()));

        System.out.println("realtyHouse = " + realtyHouse);

        Integer addressId = addressService.save(realtyHouse.getDistrictId(), realtyHouse.getLocalityId(), realtyHouse.getRegionTitle());

        realty.setAddressId(addressId);

        realty.setId(realtyId);

        System.out.println("realty = " + realty);

        //HouseUpdateDTO house = HouseUpdateDTO.of(realtyHouse);
        HouseUpdateDTO house = modelMapper.map(realtyHouse, HouseUpdateDTO.class);

        house.setRealtyId(realtyId);

        houseService.update(house);

        realtyService.update(realty);

        /*if (imageFiles.length == 1 && imageFiles[0].getContentType().contains("octet-stream")) {
            return "redirect:/nedvizhimost/my-ads";
        }*/

        if (imageFiles.length == 1 && imageFiles[0].getContentType().contains("octet-stream")) {

        } else {
            List<String> pathsDeletedImages = imageService.deleteByRealtyId(realtyId);

            fileService.delete(pathsDeletedImages);

            fileService.save(realtyId,imageFiles);
        }
    }

    public void update(RealtyFlatDto realtyFlatDto, MultipartFile[] imageFiles, Integer realtyId) {

        RealtyUpdateDTO realty = modelMapper.map(realtyFlatDto, RealtyUpdateDTO.class);

        Integer addressId = addressService.save(realtyFlatDto.getDistrictId(), realtyFlatDto.getLocalityId(), realtyFlatDto.getRegionTitle());

        realty.setAddressId(addressId);

        realty.setId(realtyId);

        realtyService.update(realty);


        FlatUpdateDTO flat = modelMapper.map(realtyFlatDto, FlatUpdateDTO.class);

        realty.setCurrency(Currency.valueOf(realtyFlatDto.getCurrency()));


        flat.setRealtyId(realtyId);
        flatService.update(flat);

       /* if (imageFiles.length == 1 && imageFiles[0].getContentType().contains("octet-stream")) {
            return "redirect:/nedvizhimost/my-ads";
        }*/

        if (imageFiles.length == 1 && imageFiles[0].getContentType().contains("octet-stream")) {

        } else {
            List<String> pathsDeletedImages = imageService.deleteByRealtyId(realtyId);

            fileService.delete(pathsDeletedImages);

            fileService.save(realtyId,imageFiles);
        }
    }
}
