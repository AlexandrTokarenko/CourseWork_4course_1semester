package com.example.coursework.service.dataServices;

import com.example.coursework.entity.RepairType;
import com.example.coursework.repository.RepairTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairService {

    private final RepairTypeRepository repairTypeRepository;

    public List<RepairType> findAll() {

        return repairTypeRepository.findAll();
    }
}
