package com.example.coursework.service.dataServices;

import com.example.coursework.entity.District;
import com.example.coursework.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;

    public List<District> findAll() {
        return districtRepository.findAll();
    }

    public District findById(Integer districtId) {
        if (districtId == null) return null;
        return districtRepository.findById(districtId).orElse(null);
    }

    public int countByLocalityId(int localityId) {

        return districtRepository.countByLocalityId(localityId);
    }
}
