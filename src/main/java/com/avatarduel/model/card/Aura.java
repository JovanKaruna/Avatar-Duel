package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class Aura extends Skill {
    private static final String TYPE_NAME = "AURA";
    private Integer attack;
    private Integer defend;

    public Aura(String name, String description, Element element, String imgPath, Integer power, Integer attack, Integer defend) {
        super(name, description, element, imgPath, power, Aura.TYPE_NAME);
        this.attack = attack;
        this.defend = defend;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefend() {
        return defend;
    }

    @Override
    public String getAttributeDescription() {
        return "ATK / " + this.getAttack().toString() +
                " | DEF / " + this.getDefend().toString() +
                " | " + super.getAttributeDescription();
    }
}
