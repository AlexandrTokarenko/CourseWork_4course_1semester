package com.example.coursework.service.dataServices;

import com.example.coursework.dto.AdvertisementRequestDTO;
import com.example.coursework.entity.Advertisement;
import com.example.coursework.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Advertisement findById(int id) {

        return advertisementRepository.findById(id).orElse(null);
    }

    public AdvertisementRequestDTO findAdvertisementRequestDTOById(int id) {

        Advertisement advertisement = advertisementRepository.findById(id).orElse(null);

        if (advertisement != null) return mapToDto(advertisement);
        return null;
    }

    private AdvertisementRequestDTO mapToDto(Advertisement advertisement) {

        return AdvertisementRequestDTO.builder()
                .userEmail(advertisement.getUserEmail().getEmail())
                .publicationDateAndTime(advertisement.getPublicationDateAndTime())
                .realtyId(advertisement.getRealty().getId()).build();
    }

    public void save(AdvertisementRequestDTO advertisementRequestDTO) {

        advertisementRepository.saveAdvertisementRequestDTO(advertisementRequestDTO);
    }

    public void changeStatusByRealtyId(int realtyId, boolean condition) {

        advertisementRepository.updateOpenStatusByRealtyId(condition,realtyId);
    }
}
