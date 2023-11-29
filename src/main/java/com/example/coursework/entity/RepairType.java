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
@Table(name = "repair_type")
@NoArgsConstructor
@AllArgsConstructor
public class RepairType {
    @Id
    @Column(name = "title", nullable = false, length = 35)
    private String title;
/*
    @OneToMany(mappedBy = "repair")
    private Set<Realty> realties = new LinkedHashSet<>();*/

}