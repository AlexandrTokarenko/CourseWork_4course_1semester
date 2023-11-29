package com.example.coursework.repository;

import com.example.coursework.entity.ExchangeRate;
import com.example.coursework.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    Optional<ExchangeRate> findFirstByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
    @Transactional
    @Modifying
    @Query("update ExchangeRate e set e.buyRate = ?1, e.saleRate = ?2, e.effectiveDate = ?3 where e.currencyFrom = ?4 and e.currencyTo = ?5")
    void updateBuyRateAndSaleRateAndEffectiveDateByCurrencyFromAndCurrencyTo(double buyRate, double saleRate, Date effectiveDate, Currency currencyFrom, Currency currencyTo);

  /*  @Transactional
    @Modifying
    @Query("update ExchangeRate e set e.rate = :#{#currency.}, e.effectiveDate = ?2 where e.currencyFrom = ?3 and e.currencyTo = ?4")
    void update(@Param("currency") Currency currency);*/

}