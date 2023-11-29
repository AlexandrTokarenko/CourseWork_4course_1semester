package com.example.coursework.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RealtyEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    /*public List<Realty> findByFilter(HouseFilterDto houseFilter) {

        return entityManager.createQuery("select r from realty r\n" +
                "            where CASE\n" +
                "                   WHEN currency = ?15 THEN price\n" +
                "                   WHEN currency = 'USD' THEN r.price * (select buyRate FROM ExchangeRate WHERE currencyFrom = 'USD' AND currencyTo = 'UAH')\n" +
                "                   WHEN currency = 'UAH' THEN r.price / (select saleRate FROM ExchangeRate WHERE currencyFrom = 'USD' AND currencyTo = 'UAH')                                \n" +
                "                   ELSE price\n" +
                "                   END BETWEEN ?1 AND ?2\n" +
                "            and housingArea between ?3 and ?4 and r.numberOfRooms between ?5 and ?6\n" +
                "            and floors between ?7 and ?8 and r.advertisement.open = true and r.house.landArea between ?9 \n" +
                "            and ?10 and (r.address.region.title = ?11 OR ?11 = 'Не вибрано') and (r.address.locality.id = ?12 OR ?12 = 0) \n" +
                "            and (r.address.district.id = ?13 OR ?13 = 0) and r.property.title = ?14 order by r.advertisement.publicationDateAndTime desc").
    }*/
}
