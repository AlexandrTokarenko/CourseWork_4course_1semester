package com.example.coursework.repository;

import com.example.coursework.entity.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, Integer> {
    @Query("select c from Construction c where c.propertyTitle.title = ?1")
    List<Construction> findAllByPropertyTitle(String title);
}