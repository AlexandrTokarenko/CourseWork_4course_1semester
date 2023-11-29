package com.example.coursework.service.dataServices;

import com.example.coursework.entity.Locality;
import com.example.coursework.repository.LocalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalityService {

    private final LocalityRepository localityRepository;
    public List<Locality> findAll() {
        return localityRepository.findAll();
    }

    public Locality findById(Integer localityId) {

        return localityRepository.findById(localityId).orElse(null);
    }
}
