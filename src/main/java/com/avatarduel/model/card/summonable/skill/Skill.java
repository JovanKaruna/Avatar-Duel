package com.avatarduel.model.card.summonable.skill;

import com.avatarduel.model.card.summonable.SummonableCard;
import com.avatarduel.model.element.Element;

public abstract class Skill extends SummonableCard {
    private String type;

    public Skill(String name, String description, Element element, String imgPath, Integer power, String type) {
        super(name, description, element, imgPath, power, "SKILL");
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getAttributeDescription() {
        return "POW / " + this.getPower().toString();
    }

    @Override
    public String getEffectDescription() {
        return this.getType();
    }
}
