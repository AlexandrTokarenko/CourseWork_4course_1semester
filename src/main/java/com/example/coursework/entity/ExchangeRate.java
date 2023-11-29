package com.example.coursework.entity;

import com.example.coursework.model.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

/*    @Size(max = 3)
    @NotNull
    @Column(name = "currency_from", nullable = false, length = 3)
    private String currencyFrom;*/

    @Column(name = "currency_from", nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private Currency currencyFrom;

/*    @Size(max = 3)
    @NotNull
    @Column(name = "currency_to", nullable = false, length = 3)
    private String currencyTo;*/

    @Column(name = "currency_to", nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private Currency currencyTo;

    @NotNull
    @Column(name = "buy_rate", nullable = false, precision = 10, scale = 2)
    private double buyRate;

    @NotNull
    @Column(name = "sale_rate", nullable = false, precision = 10, scale = 2)
    private double saleRate;

    @NotNull
    @Column(name = "effective_date", nullable = false)
    private Date effectiveDate;

}