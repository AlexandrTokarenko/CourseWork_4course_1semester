package com.example.coursework.service;

import com.example.coursework.entity.ExchangeRate;
import com.example.coursework.model.CurrencyAPI;
import com.example.coursework.service.dataServices.ExchangeRateService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {

    private final Gson gson;
    private final ExchangeRateService exchangeRateService;
    private final ModelMapper modelMapper;

    @Scheduled(cron = "${schedule.cron.expression}")
    public void updateCurrency() {

        String json = readCurrenciesFromHTTP();

        List<ExchangeRate> exchangeRates = fromCurrencyToExchangeRate(fromJsonToCurrencies(json));

        exchangeRateService.updateBuyRateAndSaleRateAndEffectiveDateByCurrencyFromAndCurrencyTo(exchangeRates);
    }

    private List<ExchangeRate> fromCurrencyToExchangeRate(List<CurrencyAPI> currencies) {
        TypeMap<CurrencyAPI, ExchangeRate> propertyMapper = modelMapper.getTypeMap(CurrencyAPI.class, ExchangeRate.class);

        if (propertyMapper == null) {

            propertyMapper = modelMapper.createTypeMap(CurrencyAPI.class, ExchangeRate.class);

            propertyMapper.addMappings(
                    mapper -> {
                        mapper.map(CurrencyAPI::getCcy, ExchangeRate::setCurrencyFrom);
                        mapper.map(CurrencyAPI::getBase_ccy, ExchangeRate::setCurrencyTo);
                        mapper.map(CurrencyAPI::getBuy, ExchangeRate::setBuyRate);
                        mapper.map(CurrencyAPI::getSale, ExchangeRate::setSaleRate);

                        mapper.skip(ExchangeRate::setEffectiveDate);
                    }
            );
        }

        List<ExchangeRate> exchangeRates = new ArrayList<>();
        for (CurrencyAPI currencyAPI : currencies) {
            ExchangeRate exchangeRate = modelMapper.map(currencyAPI, ExchangeRate.class);
            exchangeRate.setEffectiveDate(new Date(System.currentTimeMillis()));
            exchangeRates.add(exchangeRate);
        }

        return exchangeRates;
    }

    private List<CurrencyAPI> fromJsonToCurrencies(String json) {

        return gson.fromJson(json,new TypeToken<List<CurrencyAPI>>(){}.getType());
    }

    private String readCurrenciesFromHTTP() {

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=11"))
                    .build();
        } catch (URISyntaxException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }

        log.info("Reading currencies from API");
        return response.body();
    }
}
