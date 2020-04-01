package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class Destroy extends Skill{
    private static final String TYPE_NAME = "DESTROY";

    public Destroy(String name, String description, Element element, String imgPath, Integer power) {
        super(name, description, element, imgPath, power, Destroy.TYPE_NAME);
    }
}
