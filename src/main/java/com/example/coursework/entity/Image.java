package com.example.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "realty_id", nullable = false)
    private Realty realty;

    @Column(name = "path", nullable = false, length = Integer.MAX_VALUE)
    private String path;

    @Column(name = "image_order")
    private Integer imageOrder;

    public Image(Integer id, String path, Integer imageOrder) {
        this.id = id;
        this.path = path;
        this.imageOrder = imageOrder;
    }

    public Image(Realty realty, String path, Integer imageOrder) {
        this.realty = realty;
        this.path = path;
        this.imageOrder = imageOrder;
    }

    public Image(String path, Integer imageOrder) {
        this.path = path;
        this.imageOrder = imageOrder;
    }
}