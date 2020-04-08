package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.summonable.skill.Aura;

public class SummonedAuraCard extends SummonedCard{
    public SummonedAuraCard(Aura card){
        super(card, card.getClass());
    }

    @Override
    public Integer getAttackValue() {
        return ((Aura) this.getCard()).getAttack();
    }

    @Override
    public Integer getDefendValue() {
        return ((Aura) this.getCard()).getDefend();
    }
}
