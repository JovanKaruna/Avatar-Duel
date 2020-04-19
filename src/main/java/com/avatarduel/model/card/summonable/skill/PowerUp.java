package com.avatarduel.model.card.summonable.skill;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.element.Element;

public class PowerUp extends Skill {
    private static final String TYPE_NAME = "POWERUP";

    public PowerUp(String name, String description, Element element, String imgPath, Integer power) {
        super(name, description, element, imgPath, power, PowerUp.TYPE_NAME);
        this.cardType = CardType.POWERUP;
    }
}
