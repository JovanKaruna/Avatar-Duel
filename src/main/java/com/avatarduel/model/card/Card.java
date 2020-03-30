package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public abstract class Card {
    public static String CSV_FILE_PATH;
    private String name;
    private String description;
    private Element element;
    private String imgPath;

    public Card(String name, String description, Element element, String imgPath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.imgPath = imgPath;
    }

    abstract public String getAttributeDescription();

    abstract public String getEffectDescription();

    abstract public String getTypeDescription();

    public String getName() {
        return name;
    }

    public Element getElement() {
        return element;
    }

    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }
}

