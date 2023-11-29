package com.example.coursework.repository;

import com.example.coursework.dto.AddressRequestDTO;
import com.example.coursework.entity.Address;
import com.example.coursework.entity.District;
import com.example.coursework.entity.Locality;
import com.example.coursework.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByRegionAndLocalityAndDistrict(Region region, Locality locality, District district);
    @Query("select a from Address a where a.region.title = ?1 and a.locality.id = ?2 and a.district.id = ?3")
    Optional<Address> findByRegion_TitleAndLocality_IdAndDistrict_Id(String regionTitle, Integer localityId, Integer districtId);

    @Transactional
    @Query(value = "insert into Address(district_id,locality_id,region) values(:#{#address.districtId}, :#{#address.localityId}, :#{#address.regionTitle}) RETURNING *",nativeQuery = true)
    Address saveAndFlushAddressDTO(@Param("address") AddressRequestDTO addressRequestDTO);
}