package com.avatarduel.model.card.summonable;

import java.util.ArrayList;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.summonable.character.Character;

public class SummonedCharacterCard extends SummonedCard{
    ArrayList<SummonedSkillCard> supportCards;
    private boolean isAttackStance;

    public SummonedCharacterCard(Character summonableCard) {
        super(summonableCard, CardType.CHARACTER);
    }

    @Override
    public boolean canAttack(){
        return this.isAttackStance;
    }

    @Override
    public Integer getAttackValue() {
        return ((Character)this.card).getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getAttackValue).sum();
    }

    @Override
    public Integer getDefendValue(){
        return ((Character)this.card).getDefend() + this.supportCards.stream().mapToInt(SummonedSkillCard::getDefendValue).sum();
    }

    public void changeStance() {
        this.isAttackStance ^= true;
        this.card.changeOrientation();
    }
}
