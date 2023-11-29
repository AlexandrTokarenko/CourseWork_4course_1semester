package com.example.coursework.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "type_of_heating")
@Data
@NoArgsConstructor
public class TypeOfHeating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @OneToMany(mappedBy = "heating")
    private Set<Realty> realties = new LinkedHashSet<>();

}