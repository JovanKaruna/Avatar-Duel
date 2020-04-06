package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

public abstract class SummonableCard extends Card implements Summonable{
    private Integer power;

    public SummonableCard(String name, String description, Element element, String imgPath, Integer power, String typeName) {
        super(name, description, element, imgPath, typeName);
        this.power = power;
    }

    @Override
    public Integer getPower() {
        return this.power;
    }
}
