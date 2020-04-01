package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

abstract public class Skill extends Card implements Summonable {
    private static final String TYPE_NAME = "SKILL";

    private Integer power;
    private String type;

    public Skill(String name, String description, Element element, String imgPath, Integer power, String type) {
        super(name, description, element, imgPath);
        this.power = power;
        this.type = type;
    }

    public Integer getPower() {
        return power;
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

    @Override
    public String getTypeDescription() {
        return Skill.TYPE_NAME;
    }
}
