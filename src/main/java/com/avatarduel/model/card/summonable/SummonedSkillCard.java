package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.summonable.skill.Skill;

public class SummonedSkillCard <T extends Skill> extends SummonedCard {
    public SummonedSkillCard(Skill card, Class<T> type) {
        super(card, type);
    }
}
