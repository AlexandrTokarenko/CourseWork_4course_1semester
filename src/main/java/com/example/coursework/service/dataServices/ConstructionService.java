package com.example.coursework.service.dataServices;

import com.example.coursework.entity.Construction;
import com.example.coursework.repository.ConstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionService {

    private final ConstructionRepository constructionRepository;

    public List<Construction> findAllByPropertyTitle(String propertyTitle) {

        return constructionRepository.findAllByPropertyTitle(propertyTitle);
    }
}
