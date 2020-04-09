package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;
import com.avatarduel.model.card.CardType;

public abstract class Card {
    private String typeName;
    private String name;
    private String description;
    private Element element;
    private String imgPath;
    private boolean isOpen;
    private boolean isPortrait;
    private boolean isSelected;
    public CardType cardType;

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

    public void changeOrientation(){
        this.isPortrait ^= true;
    }

    public void setOrientation(boolean isPortrait){
        this.isPortrait = isPortrait;
    }

    public boolean isPortrait() {
        return isPortrait;
    }

    public void setSelected(){
        this.isSelected = true;
    }

    public void setNotSelected(){
        this.isSelected = false;
    }

    public void setSelection(boolean selection){
        this.isSelected = selection;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public CardType getCardType(){
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

