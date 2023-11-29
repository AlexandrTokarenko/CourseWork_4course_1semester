package com.example.coursework.service.dataServices;

import com.example.coursework.entity.Purpose;
import com.example.coursework.repository.PurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurposeService {

    private final PurposeRepository purposeRepository;

    public List<Purpose> findAll() {

        return purposeRepository.findAll();
    }
}
