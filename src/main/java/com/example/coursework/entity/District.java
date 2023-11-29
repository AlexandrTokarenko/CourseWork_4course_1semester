package com.example.coursework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "district")
@NoArgsConstructor
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 80)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "locality_id", nullable = false)
    private Locality locality;

  /*  @OneToMany(mappedBy = "district")
    private Set<Address> addresses = new LinkedHashSet<>();*/

    public District(Integer id) {
        this.id = id;
    }

    public District(Integer id, String title, Locality locality) {
        this.id = id;
        this.title = title;
        this.locality = locality;
    }

    public District(String title, Locality locality) {
        this.title = title;
        this.locality = locality;
    }
}