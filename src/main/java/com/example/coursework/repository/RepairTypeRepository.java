package com.example.coursework.repository;

import com.example.coursework.entity.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairType, String> {
}