package com.example.coursework.service.dataServices;

import com.example.coursework.entity.Region;
import com.example.coursework.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public Region findById(String title) {
        return regionRepository.findById(title).orElse(null);
    }
    public List<Region> findAll() {

        return regionRepository.findAll();
    }
}
