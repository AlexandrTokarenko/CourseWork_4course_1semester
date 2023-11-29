package com.example.coursework.repository;

import com.example.coursework.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query("select count(d) from District d where d.locality.id = ?1")
     int countByLocalityId(int localityId);
}