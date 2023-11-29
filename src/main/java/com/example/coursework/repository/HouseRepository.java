package com.example.coursework.repository;

import com.example.coursework.dto.HouseRequestDTO;
import com.example.coursework.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
    @Transactional
    @Modifying
    @Query("update House h set h.landArea = ?1, h.floors = ?2 where h.realty.id = ?3")
    void update(Float landArea, Integer floors, int realty);

    @Modifying
    @Transactional
    @Query(value = "insert into House(floors,land_area,realty_id) values(:#{#house.numberOfFloors},:#{#house.landArea}," +
            ":realtyId)",nativeQuery = true)
    void saveHouseRequestDTO(@Param("house") HouseRequestDTO houseRequestDTO, @Param("realtyId") Integer realtyId);
}