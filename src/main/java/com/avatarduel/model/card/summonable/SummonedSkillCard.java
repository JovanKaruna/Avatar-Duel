package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.summonable.skill.Skill;

public class SummonedSkillCard <T extends Skill> extends SummonedCard {
    boolean isAttached;

    public SummonedSkillCard(Skill card, CardType type) {
        super(card, type);
        this.isAttached = false;
    }

    public void setAttached() {
        this.isAttached = true;
    }

    public boolean isAttached() {
        return isAttached;
    }
}
