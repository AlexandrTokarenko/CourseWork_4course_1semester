package com.example.coursework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region", nullable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "locality_id", nullable = false)
    private Locality locality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

   /* @OneToMany(mappedBy = "address")
    private Set<Realty> realties = new LinkedHashSet<>();*/

    public Address(Integer id) {
        this.id = id;
    }

    public Address(Integer id, Region region, Locality locality, District district) {
        this.id = id;
        this.region = region;
        this.locality = locality;
        this.district = district;
    }

    public Address(Region region, Locality locality, District district) {
        this.region = region;
        this.locality = locality;
        this.district = district;
    }
}