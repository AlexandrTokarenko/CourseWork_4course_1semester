package com.example.coursework.service.dataServices;

import com.example.coursework.entity.TypeOfHeating;
import com.example.coursework.repository.TypeOfHeatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfHeatingService {

    private final TypeOfHeatingRepository typeOfHeatingRepository;

    public List<TypeOfHeating> findAll() {

        return typeOfHeatingRepository.findAll();
    }
}
