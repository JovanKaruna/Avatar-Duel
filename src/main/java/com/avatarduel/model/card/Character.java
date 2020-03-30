package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class Character extends Card implements Summonable {
    public static final String CSV_FILE_PATH = "card/data/character.csv";
    private static final String TYPE_NAME = "CHARACTER";
    private Integer attack;
    private Integer defend;
    private Integer power;

    public Character(String name, String description, Element element, String imgPath, Integer attack, Integer defend, Integer power) {
        super(name, description, element, imgPath);
        this.attack = attack;
        this.defend = defend;
        this.power = power;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefend() {
        return defend;
    }

    public Integer getPower() {
        return power;
    }

    @Override
    public String getAttributeDescription() {
        return "ATK / " + this.getAttack().toString() +
                " | DEF / " + this.getDefend().toString() +
                " | POW / " + this.getPower().toString();
    }

    @Override
    public String getEffectDescription() {
        return "";
    }

    @Override
    public String getTypeDescription() {
        return Character.TYPE_NAME;
    }
}
