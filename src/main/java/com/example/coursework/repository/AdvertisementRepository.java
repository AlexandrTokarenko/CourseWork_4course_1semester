package com.example.coursework.repository;

import com.example.coursework.dto.AdvertisementRequestDTO;
import com.example.coursework.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    @Transactional
    @Modifying
    @Query("update Advertisement a set a.open = ?1 where a.realty.id = ?2")
    void updateOpenStatusByRealtyId(Boolean open, int realtyId);

    @Transactional
    @Modifying
    @Query(value = "insert into Advertisement(publication_date_and_time,realty_id,user_email) " +
            "values(:#{#ad.publicationDateAndTime},:#{#ad.realtyId},:#{#ad.userEmail})",nativeQuery = true)
    void saveAdvertisementRequestDTO(@Param("ad") AdvertisementRequestDTO advertisementRequestDTO);
}