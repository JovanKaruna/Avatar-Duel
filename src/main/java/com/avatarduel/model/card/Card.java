package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public abstract class Card {
    private String typeName;
    private String name;
    private String description;
    private Element element;
    private String imgPath;
    private boolean isOpen;

    public Card(String name, String description, Element element, String imgPath, String typeName) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.imgPath = imgPath;
        this.isOpen = true;
        this.typeName = typeName;
    }

    public String getAttributeDescription() {
        return "";
    }

    public String getEffectDescription() {
        return "";
    }

    public String getTypeDescription(){
        return this.typeName;
    }

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

    public boolean isEmpty() {
        return element.equals(Element.valueOf("NOELEMENT"));
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void flip() {
        if (this.isEmpty()) {
            return;
        }
        if (this.isOpen) {
            this.close();
        } else {
            this.open();
        }
    }

    public void close() {
        this.isOpen = false;
    }

    public void open() {
        this.isOpen = true;
    }
}

