package com.avatarduel.model.card;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.element.Element;

public class Land extends Card {
    public Land(String name, String description, Element element, String imgPath) {
        super(name, description, element, imgPath, "LAND");
        this.cardType = CardType.LAND;
    }
}