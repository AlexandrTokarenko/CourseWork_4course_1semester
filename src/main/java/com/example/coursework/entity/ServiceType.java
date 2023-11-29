package com.example.coursework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "service_type")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceType {
    @Id
    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "price_per_day")
    private Integer pricePerDay;

}