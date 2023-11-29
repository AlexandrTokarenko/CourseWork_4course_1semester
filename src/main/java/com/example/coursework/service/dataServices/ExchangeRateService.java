package com.example.coursework.service.dataServices;

import com.example.coursework.entity.ExchangeRate;
import com.example.coursework.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public void updateBuyRateAndSaleRateAndEffectiveDateByCurrencyFromAndCurrencyTo(ExchangeRate exchangeRate) {

        exchangeRateRepository.updateBuyRateAndSaleRateAndEffectiveDateByCurrencyFromAndCurrencyTo(
                exchangeRate.getBuyRate(),exchangeRate.getSaleRate(),exchangeRate.getEffectiveDate(),exchangeRate.getCurrencyFrom(),exchangeRate.getCurrencyTo()
        );
        log.info("Updating currencies in table Exchange_Rate");
    }

    public void updateBuyRateAndSaleRateAndEffectiveDateByCurrencyFromAndCurrencyTo(List<ExchangeRate> exchangeRates) {

        for (ExchangeRate exchangeRate : exchangeRates) {
            updateBuyRateAndSaleRateAndEffectiveDateByCurrencyFromAndCurrencyTo(exchangeRate);
        }
    }

    public List<ExchangeRate> findAll() {

        return exchangeRateRepository.findAll();
    }

    public int fromUAHToUSD(double fromUAH) {
        Optional<ExchangeRate> currencyOpt = exchangeRateRepository.findFirstByCurrencyFromAndCurrencyTo("USD", "UAH");

        return currencyOpt.map(exchangeRate -> (int) (fromUAH / exchangeRate.getSaleRate())).orElse(0);
    }

    public int fromUSDToUAH(double fromUSD) {
        Optional<ExchangeRate> currencyOpt = exchangeRateRepository.findFirstByCurrencyFromAndCurrencyTo("USD", "UAH");

        return currencyOpt.map(exchangeRate -> (int) (fromUSD * exchangeRate.getBuyRate())).orElse(0);
    }
}
