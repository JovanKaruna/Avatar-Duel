package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.summonable.skill.Destroy;

public class SummonedDestroyCard extends SummonedSkillCard {
    public SummonedDestroyCard(Destroy card) {
        super(card, card.getClass());
    }
}
