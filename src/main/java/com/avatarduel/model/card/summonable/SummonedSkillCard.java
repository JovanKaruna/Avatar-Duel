package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.summonable.skill.Skill;

public class SummonedSkillCard<T extends Skill> extends SummonedCard {
    private boolean isAttached;

    SummonedSkillCard(Skill card, CardType type) {
        super(card, type);
        this.isAttached = false;
    }

    void setAttached() {
        this.isAttached = true;
    }

    public boolean isAttached() {
        return isAttached;
    }
}
