package com.example.coursework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "region")
@NoArgsConstructor
public class Region {
    @Id
    @Column(name = "title", nullable = false, length = 80)
    private String title;

 /*   @OneToMany(mappedBy = "region")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "region")
    private Set<Locality> localities = new LinkedHashSet<>();*/

    public Region(String title) {
        this.title = title;
    }
}