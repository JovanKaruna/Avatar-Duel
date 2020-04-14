package com.avatarduel.model.card.summonable;

import java.util.ArrayList;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.summonable.character.Character;

public class SummonedCharacterCard extends SummonedCard {
    ArrayList<SummonedSkillCard> supportCards;
    private boolean isAttackStance;

    public SummonedCharacterCard(Character summonableCard) {
        super(summonableCard, CardType.CHARACTER);
        this.supportCards = new ArrayList<>();
        this.isAttackStance = true;
    }

    @Override
    public boolean canAttack() {
        return this.isAttackStance;
    }

    @Override
    public Integer getAttackValue() {
        return ((Character) this.card).getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getAttackValue).sum();
    }

    @Override
    public Integer getDefendValue() {
        Integer value = this.supportCards.stream().mapToInt(SummonedSkillCard::getDefendValue).sum();
        if (this.isAttackStance) {
            return value + ((Character) this.card).getAttack();
        } else {
            return value + ((Character) this.card).getDefend();
        }
    }

    public void changeStance() {
        this.isAttackStance ^= true;
        this.isPortrait ^= true;
        this.card.changeOrientation();
    }

    public <T extends SummonedSkillCard> void attachSkill(T summonedCard) {
        this.supportCards.add(summonedCard);
        summonedCard.setAttached();
    }

    public boolean isAttackStance() {
        return isAttackStance;
    }
}
