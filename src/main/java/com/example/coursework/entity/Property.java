package com.example.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "property")
@AllArgsConstructor
public class Property {
    @Id
    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @OneToMany(mappedBy = "propertyTitle")
    private Set<Construction> constructions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "property")
    private Set<Realty> realties = new LinkedHashSet<>();

    public Property(String title) {
        this.title = title;
    }

    public Property() {

    }
}