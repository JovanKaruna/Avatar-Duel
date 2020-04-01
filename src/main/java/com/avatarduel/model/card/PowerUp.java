package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class PowerUp extends Skill{
    private static final String TYPE_NAME = "POWERUP";

    public PowerUp(String name, String description, Element element, String imgPath, Integer power) {
        super(name, description, element, imgPath, power, PowerUp.TYPE_NAME);
    }
}
