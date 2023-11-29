package com.example.coursework.repository;

import com.example.coursework.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Transactional
    List<Image> deleteImageByRealty_Id(Integer realtyId);

    @Transactional
    @Modifying
    @Query("delete from Image i where i.realty.id = ?1")
    void deleteByRealty_Id(Integer id);

    @Query("select i from Image i where i.realty.id = ?1 order by i.imageOrder")
    List<Image> findByRealty_IdOrderByImageOrderAsc(Integer id);

    @Query("select i from Image i where i.realty.id = ?1 and i.imageOrder = ?2")
    Optional<Image> findImageByRealtyIdAndImageOrder(int realtyId, int imageOrder);
}