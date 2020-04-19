package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public abstract class Card {
    private String typeName;
    private String name;
    private String description;
    private Element element;
    private String imgPath;
    private boolean isOpen;
    private boolean isPortrait;
    private boolean isSelected;
    protected CardType cardType;

    public Card(String name, String description, Element element, String imgPath, String typeName) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.imgPath = imgPath;
        this.isOpen = true;
        this.isPortrait = true;
        this.isSelected = false;
        this.typeName = typeName;
        this.cardType = CardType.EMPTY;
    }

    public String getAttributeDescription() {
        return "";
    }

    public String getEffectDescription() {
        return "";
    }

    String getTypeDescription() {
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

    String getImgPath() {
        return imgPath;
    }


    public boolean isEmpty() {
        return element.equals(Element.valueOf("NOELEMENT"));
    }

    boolean isOpen() {
        return isOpen;
    }

    public void close() {
        this.isOpen = false;
    }

    public void open() {
        this.isOpen = true;
    }

    void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }


    public void changeOrientation() {
        this.isPortrait ^= true;
    }

    void setOrientation(boolean isPortrait) {
        this.isPortrait = isPortrait;
    }


    boolean isPortrait() {
        return isPortrait;
    }

    public void setSelected() {
        this.isSelected = true;
    }

    public void setNotSelected() {
        this.isSelected = false;
    }

    void setSelection(boolean selection) {
        this.isSelected = selection;
    }

    boolean isSelected() {
        return isSelected;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                ", name='" + name + '\'' +
                ", isOpen=" + isOpen +
                ", isPortrait=" + isPortrait +
                ", isSelected=" + isSelected +
                '}';
    }
}

