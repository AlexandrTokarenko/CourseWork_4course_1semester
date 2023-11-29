package com.example.coursework.service.dataServices;

import com.example.coursework.dto.HouseRequestDTO;
import com.example.coursework.dto.HouseUpdateDTO;
import com.example.coursework.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;

    public void save(HouseRequestDTO houseRequestDTO,Integer realtyId) {

        houseRepository.saveHouseRequestDTO(houseRequestDTO,realtyId);
    }

    public void update(HouseUpdateDTO house) {

        houseRepository.update(house.getLandArea(),house.getNumberOfFloors(),house.getRealtyId());
    }
}
