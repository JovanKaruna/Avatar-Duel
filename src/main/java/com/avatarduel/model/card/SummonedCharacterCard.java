package com.avatarduel.model.card;

import java.util.ArrayList;

public class SummonedCharacterCard extends SummonedCard {
    Character card;
    ArrayList<SummonedSkillCard> supportCards;
    private boolean isAttackStance;

    public boolean canAttack(){
        return this.isAttackStance;
    }

    public Integer getAttackValue() {
        return this.card.getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getAttack).sum();
    }

    public Integer getDefendValue(){
        return this.card.getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getDefend).sum();
    }
}
