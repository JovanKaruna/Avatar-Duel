package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.summonable.skill.Aura;
import com.avatarduel.model.card.summonable.skill.Skill;
import com.avatarduel.model.element.Element;

public class SummonedSkillCard extends SummonedCard {
    private Skill card;

    public SummonedSkillCard(Skill card) {
        this.card = card;
    }

    public Skill getCard() {
        return card;
    }

    public Integer getAttack() {
        if (this.getCard() instanceof Aura) {
            return ((Aura) this.getCard()).getAttack();
        } else {
            return 0;
        }
    }

    public Integer getDefend() {
        if (this.getCard() instanceof Aura) {
            return ((Aura) this.getCard()).getDefend();
        } else {
            return 0;
        }
    }
}
