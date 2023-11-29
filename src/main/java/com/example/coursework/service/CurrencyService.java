package com.example.coursework.service;

import com.example.coursework.dto.RealtyMAINTODO;
import com.example.coursework.entity.ExchangeRate;
import com.example.coursework.model.Currency;
import com.example.coursework.service.dataServices.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final ExchangeRateService exchangeRateService;

    private List<ExchangeRate> currencies;
    public List<RealtyMAINTODO> firstPriceExchangeRate(List<RealtyMAINTODO> realties) {

        currencies = exchangeRateService.findAll();
        for (RealtyMAINTODO realty : realties) {
            System.out.println("realty = " + realty);
            switch (realty.getFirstCurrency()) {
                case UAH -> {
                    realty.setPriceInSecondCurrency(fromUAHToUSD(realty.getPriceInFirstCurrency()));
                    realty.setLastCurrency(Currency.USD);
                }
                case USD -> {
                    realty.setPriceInSecondCurrency(fromUSDToUAH(realty.getPriceInFirstCurrency()));
                    realty.setLastCurrency(Currency.UAH);
                }
            }
        }
        return realties;
    }

    public int fromUAHToUSD(double fromUAH) {
        ExchangeRate exchangeRateInUSD = currencies.stream().filter(x -> x.getCurrencyFrom().equals(Currency.USD)).findFirst().get();
        return (int) (fromUAH / exchangeRateInUSD.getSaleRate());
    }

    public int fromUSDToUAH(double fromUSD) {
        ExchangeRate exchangeRateInUSD = currencies.stream().filter(x -> x.getCurrencyFrom().equals(Currency.USD)).findFirst().get();
        return (int) (fromUSD * exchangeRateInUSD.getBuyRate());
       /* Optional<ExchangeRate> currencyOpt = exchangeRateRepository.findFirstByCurrencyFromAndCurrencyTo("USD", "UAH");

        return currencyOpt.map(exchangeRate -> (int) (fromUSD * exchangeRate.getBuyRate())).orElse(0);*/
    }
}

