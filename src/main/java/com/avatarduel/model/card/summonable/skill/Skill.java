package com.avatarduel.model.card.summonable.skill;

import com.avatarduel.model.card.summonable.SummonableCard;
import com.avatarduel.model.element.Element;

public abstract class Skill extends SummonableCard {
    private String type;
    private static final String typeName = "SKILL";

    public Skill(String name, String description, Element element, String imgPath, Integer power, String type) {
        super(name, description, element, imgPath, power, Skill.typeName);
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
