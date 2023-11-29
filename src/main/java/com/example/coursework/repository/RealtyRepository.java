package com.example.coursework.repository;

import com.example.coursework.dto.RealtyMAINTODO;
import com.example.coursework.dto.RealtyRequestDTO;
import com.example.coursework.dto.RealtyUpdateDTO;
import com.example.coursework.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RealtyRepository extends JpaRepository<Realty, Integer> {

    @Transactional
    @Modifying
    @Query(value = """
            update Realty set additionalInformation = :#{#realty.additionalInformation}, furnishing = :#{#realty.furnishing}, header = :#{#realty.header}, 
            housingArea = :#{#realty.housingArea}, numberOfRooms = :#{#realty.numberOfRooms}, price = :#{#realty.price},construction.id = :#{#realty.constructionId}, 
            purpose.title = :#{#realty.purposeTitle}, repair.title = :#{#realty.repairTitle}, heating.id = :#{#realty.heatingId}, address.id = :#{#realty.addressId}, 
            currency = :#{#realty.currency} where id = :#{#realty.id}""")
    void update(RealtyUpdateDTO realty);


    @Query("select new com.example.coursework.dto.RealtyMAINTODO(r.id,r.header,r.numberOfRooms,r.housingArea," +
                        "r.address,r.price,r.currency) from Realty r where r.id = ?1 and r.advertisement.open=true order by r.advertisement.publicationDateAndTime desc")
    RealtyMAINTODO findRealtyMainTODOByIdAndAdvertisement_Open(int id);

    /*r.price between ?1 and ?2 and */
/*    @Query(value = """
            select r from Realty r
            where CASE
                   WHEN r.currency = ?15 THEN r.price
                   WHEN r.currency = 'USD' THEN r.price * (select buyRate FROM ExchangeRate WHERE currencyFrom = 'USD' AND currencyTo = 'UAH')
                   WHEN r.currency = 'UAH' THEN r.price / (select saleRate FROM ExchangeRate WHERE currencyFrom = 'USD' AND currencyTo = 'UAH')
                   ELSE r.price
                   END BETWEEN ?1 AND ?2
            and r.housingArea between ?3 and ?4 and r.numberOfRooms between ?5 and ?6
            and r.house.floors between ?7 and ?8 and r.advertisement.open = true and r.house.landArea between ?9
            and ?10 and (r.address.region.title = ?11 OR ?11 = 'Не вибрано') and (r.address.locality.id = ?12 OR ?12 = 0)
            and (r.address.district.id = ?13 OR ?13 = 0) and r.property.title = ?14 order by r.advertisement.publicationDateAndTime desc""")
    List<Realty> findByFilterOrderByPublicationDateAndTime(Integer priceStart, Integer priceEnd, Double housingAreaStart,
                                                           Double housingAreaEnd, Integer numberOfRoomsStart, Integer numberOfRoomsEnd , Integer floorsStart, Integer floorsEnd,
                                                           Double landAreaStart, Double landAreaEnd, String regionTitle,
                                                           Integer localityId, Integer districtId, String propertyTitle,String currency);*/

    @Query(value = """

            select r.* from realty r
                inner join public.house h on r.id = h.realty_id
                inner join public.advertisement a on r.id = a.realty_id
                inner join public.address a2 on a2.id = r.address_id
                inner join public.region r2 on r2.title = a2.region
                inner join public.locality l on l.id = a2.locality_id
                left join public.district d on d.id = a2.district_id
                    where CASE
                        WHEN r.currency = ?15 THEN r.price
                        WHEN r.currency = 'USD' THEN r.price * (select buy_rate FROM exchange_rate WHERE currency_from = 'USD' AND currency_to = 'UAH')
                        WHEN r.currency = 'UAH' THEN r.price / (select sale_rate FROM exchange_rate WHERE currency_from = 'USD' AND currency_to = 'UAH')
                        ELSE r.price
                        END BETWEEN ?1 AND ?2
                and r.housing_area between ?3 and ?4 and r.number_of_rooms between ?5 and ?6
                and h.floors between ?7 and ?8 and a.open = true and h.land_area between ?9
                and ?10 and (r2.title = ?11 OR ?11 = 'Не вибрано') and (l.id = ?12 OR ?12 = 0)
                and (d.id = ?13 OR ?13 = 0) and r.property = ?14 order by a.publication_date_and_time desc""", nativeQuery = true)
    List<Realty> findByFilterOrderByPublicationDateAndTime(Integer priceStart, Integer priceEnd, Double housingAreaStart,
                                                           Double housingAreaEnd, Integer numberOfRoomsStart, Integer numberOfRoomsEnd , Integer floorsStart, Integer floorsEnd,
                                                           Double landAreaStart, Double landAreaEnd, String regionTitle,
                                                           Integer localityId, Integer districtId, String propertyTitle,String currency);


    /*r.price between ?1 and ?2 and*/
/*    @Query("""
            select r from Realty r
            where r.housingArea between ?3 and ?4 and r.numberOfRooms between ?5 and ?6
            and r.flat.floor between ?7 and ?8 and r.advertisement.open = true 
            and (r.address.region.title = ?9 OR ?9 = 'Не вибрано') and (r.address.locality.id = ?10 OR ?10 = 0) 
            and (r.address.district.id = ?11 OR ?11 = 0) and r.property.title = ?12 order by r.advertisement.publicationDateAndTime desc""")
    List<Realty> findByFilterOrderByPublicationDateAndTime(Integer priceStart, Integer priceEnd, Double housingAreaStart,
                                                           Double housingAreaEnd, Integer numberOfRoomsStart, Integer numberOfRoomsEnd , Integer floorsStart, Integer floorsEnd,
                                                           String regionTitle, Integer localityId, Integer districtId, String propertyTitle);*/

    @Query(value = """
            select r.* from realty r
               inner join public.flat f on r.id = f.realty_id
                inner join public.advertisement a on r.id = a.realty_id
                inner join public.address a2 on a2.id = r.address_id
                inner join public.region r2 on r2.title = a2.region
                inner join public.locality l on l.id = a2.locality_id
                left join public.district d on d.id = a2.district_id
                where CASE
                    WHEN r.currency = ?13 THEN r.price
                    WHEN r.currency = 'USD' THEN r.price * (select buy_rate FROM exchange_rate WHERE currency_from = 'USD' AND currency_to = 'UAH')
                    WHEN r.currency = 'UAH' THEN r.price / (select sale_rate FROM exchange_rate WHERE currency_from = 'USD' AND currency_to = 'UAH')
                    ELSE r.price
                    END BETWEEN ?1 AND ?2
                and r.housing_area between ?3 and ?4 and r.number_of_rooms between ?5 and ?6
                and f.floor between ?7 and ?8 and a.open = true
                and (r2.title = ?9 OR ?9 = 'Не вибрано') and (l.id = ?10 OR ?10 = 0)
                and (d.id = ?11 OR ?11 = 0) and r.property = ?12 order by a.publication_date_and_time desc""",nativeQuery = true)
    List<Realty> findByFilterOrderByPublicationDateAndTime(Integer priceStart, Integer priceEnd, Double housingAreaStart,
                                                           Double housingAreaEnd, Integer numberOfRoomsStart, Integer numberOfRoomsEnd , Integer floorsStart, Integer floorsEnd,
                                                           String regionTitle, Integer localityId, Integer districtId, String propertyTitle,String currency);

    @Query("select r from Realty r where r.advertisement.userEmail.email = ?1")
    List<Realty> findByAdvertisement_UserEmail_Email(String email);
    @Query("""
            select r from Realty r inner join r.advertisement advertisement
            where r.id = ?1 and advertisement.open = true""")
    Optional<Realty> findByIdAndAdvertisements_OpenTrue(Integer id);
    @Query("select r from Realty r inner join r.advertisement advertisement where advertisement.open = true")
    List<Realty> findByAdvertisements_Open();

    @Override
    void deleteById(Integer integer);

    @Transactional
    @Query(value = "insert into Realty(additional_information, furnishing, header, housing_area, number_of_rooms," +
            " price, address_id, construction_id, heating_id, property, purpose, repair,currency) values(:#{#realty.additionalInformation}," +
            ":#{#realty.furnishing},:#{#realty.header},:#{#realty.housingArea}," +
            ":#{#realty.numberOfRooms},:#{#realty.price},:addressId,:#{#realty.constructionId},:#{#realty.heatingId}," +
            ":#{#realty.propertyTitle}," +
            ":#{#realty.purposeTitle},:#{#realty.repairTitle},:#{#realty.currency.name()}) RETURNING *",nativeQuery = true)
    Realty saveRealtyRequest(@Param("realty") RealtyRequestDTO realty, @Param("addressId") Integer addressId);

    @Query("select r from Realty r where r.advertisement.userEmail.email = ?1 order by r.id asc ")
    List<Realty> findByAdvertisement_UserEmail_EmailOrderById(String userEmail);

/*    @Query("select new com.example.realestateads.dto.RealtyMAINTODO(r.id,r.header,r.numberOfRooms,r.housingArea," +
            "r.address.region.title,r.address.locality.title,r.address.district.title,r.price) from Realty r where r.advertisement.open=true ")
    List<RealtyMAINTODO> findRealtyMAINTODOByOpenTrue();*/

    @Query("select new com.example.coursework.dto.RealtyMAINTODO(r.id,r.header,r.numberOfRooms,r.housingArea," +
            "r.address,r.price,r.currency) from Realty r where r.advertisement.open=true order by r.advertisement.publicationDateAndTime desc")
    List<RealtyMAINTODO> findRealtyMAINTODOByOpenTrueOrderByAdvertisementDesc();

    /*@Query("select new com.example.realestateads.dto.RealtyMAINTODO(r.id,r.header,r.numberOfRooms,r.housingArea," +
    "r.address,r.price) from Realty r where r.id = ?1 and r.advertisement.open = true ")
    List<RealtyMAINTODO> findAllByIdAndAdvertisement_Open(List<Integer> ids);*/
}