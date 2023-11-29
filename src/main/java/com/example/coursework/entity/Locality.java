package com.example.coursework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locality")
@NoArgsConstructor
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 80)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region", nullable = false)
    private Region region;

   /* @OneToMany(mappedBy = "locality")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "locality")
    private Set<District> districts = new LinkedHashSet<>();*/

    public Locality(Integer id) {
        this.id = id;
    }

    public Locality(String title, Region region) {
        this.title = title;
        this.region = region;
    }

    public Locality(Integer id, String title, Region region) {
        this.id = id;
        this.title = title;
        this.region = region;
    }
}