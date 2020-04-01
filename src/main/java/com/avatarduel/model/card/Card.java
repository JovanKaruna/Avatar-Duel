package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public abstract class Card {
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

    public String getAttributeDescription() {
        return "";
    }

    public String getEffectDescription() {
        return "";
    }

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

    public void flip(){
        // flip
    }
}

