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
@Table(name = "purpose")
@NoArgsConstructor
@AllArgsConstructor
public class Purpose {
    @Id
    @Column(name = "title", nullable = false, length = 20)
    private String title;

    //TODO [JPA Buddy] generate columns from DB
}