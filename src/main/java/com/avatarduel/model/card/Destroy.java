package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class Destroy extends Skill{
    public static final String CSV_FILE_PATH = "card/data/destroy.csv";

    public Destroy(String name, String description, Element element, String imgPath, Integer power) {
        super(name, description, element, imgPath, power, "DESTROY");
    }

    @Override
    public String getAttributeDescription() {
        return "POW / " + this.getPower().toString();
    }
}
