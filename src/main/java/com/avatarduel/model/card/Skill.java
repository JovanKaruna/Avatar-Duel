package com.avatarduel.model.card;

import com.avatarduel.model.Element;

public class Skill extends Card implements Summonable {
    public static final String CSV_FILE_PATH = "card/data/skill.csv";
    private Integer attack;
    private Integer defend;
    private Integer power;
    private String type;

    public Skill(String name, String description, Element element, String imgPath, Integer attack, Integer defend, Integer power) {
        super(name, description, element, imgPath);
        this.attack = attack;
        this.defend = defend;
        this.power = power;
        this.type = "AURA";
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

    public String getType() {
        return type;
    }
}
