package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class Land extends Card {
    private static final String TYPE_NAME = "LAND";

    public Land(String name, String description, Element element, String imgPath) {
        super(name, description, element, imgPath);
    }

    @Override
    public String getTypeDescription() {
        return Land.TYPE_NAME;
    }
}