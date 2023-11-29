package com.example.coursework.entity;

import com.example.coursework.model.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "realty")
@Data
@AllArgsConstructor
public class Realty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "additional_information", length = 350)
    private String additionalInformation;

    @Column(name = "furnishing")
    private Boolean furnishing;

    @Column(name = "header", length = 50)
    private String header;

    @Column(name = "housing_area")
    private Float housingArea;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

/*    @Size(max = 3)
    @NotNull
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;*/

    @Column(name = "currency", nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "construction_id")
    private Construction construction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heating_id")
    private TypeOfHeating heating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property")
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purpose", nullable = false)
    private Purpose purpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair")
    private RepairType repair;

    @OneToOne(mappedBy = "realty")
    private Advertisement advertisement;

    @OneToOne(mappedBy = "realty")
    private Flat flat;

    @OneToOne(mappedBy = "realty")
    private House house;

    public Realty(Integer id) {
        this.id = id;
    }

    public Realty() {


    }
}