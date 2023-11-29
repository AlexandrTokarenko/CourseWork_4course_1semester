package com.example.coursework.model;

public enum Property {

    FLAT("Квартира"),
    HOUSE("Приватний будинок");

    private final String title;

    Property(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
