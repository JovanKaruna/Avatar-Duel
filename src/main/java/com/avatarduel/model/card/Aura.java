package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public class Aura extends Skill{
    public static final String CSV_FILE_PATH = "card/data/aura.csv";
    private Integer attack;
    private Integer defend;

    public Aura(String name, String description, Element element, String imgPath, Integer power, Integer attack, Integer defend) {
        super(name, description, element, imgPath, power, "AURA");
        this.attack = attack;
        this.defend = defend;
    }

    private Integer getAttack() {
        return attack;
    }

    private Integer getDefend() {
        return defend;
    }

    @Override
    public String getAttributeDescription() {
        return "ATK / " + this.getAttack().toString() +
                " | DEF / " + this.getDefend().toString() +
                " | POW / " + this.getPower().toString();
    }
}
