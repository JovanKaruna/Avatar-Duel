package com.avatarduel.model;

public abstract class Card {
    String name;
    String description;
    Element element;
    String imgPath;

    public Card(String name, String description, Element element, String imgPath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.imgPath = imgPath;
    }
}

