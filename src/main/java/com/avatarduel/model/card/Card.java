package com.avatarduel.model.card;

import com.avatarduel.model.Element;

public abstract class Card {
    public static String CSV_FILE_PATH;
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

