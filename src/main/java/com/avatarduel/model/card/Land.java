package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class Land extends Card {
    public static final String CSV_FILE_PATH = "card/data/land.csv";
    private static final String TYPE_NAME = "LAND";

    public Land(String name, String description, Element element, String imgPath) {
        super(name, description, element, imgPath);
    }

    @Override
    public String getAttributeDescription() {
        return "";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }

    @Override
    public String getTypeDescription() {
        return Land.TYPE_NAME;
    }


}