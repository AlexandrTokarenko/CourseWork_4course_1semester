package com.example.coursework.repository;

import com.example.coursework.entity.TypeOfHeating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfHeatingRepository extends JpaRepository<TypeOfHeating, Integer> {
}