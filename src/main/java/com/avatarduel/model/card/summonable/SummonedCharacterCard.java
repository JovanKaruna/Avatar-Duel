package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.summonable.character.Character;

import java.util.ArrayList;

public class SummonedCharacterCard extends SummonedCard {
    private ArrayList<SummonedSkillCard> supportCards;
    private boolean isAttackStance;
    private boolean hasAttacked;

    SummonedCharacterCard(Character summonableCard) {
        super(summonableCard, CardType.CHARACTER);
        this.supportCards = new ArrayList<>();
        this.isAttackStance = true;
        this.hasAttacked = false;
    }

    public ArrayList<SummonedSkillCard> getSupportCard() {
        return supportCards;
    }

    @Override
    public Integer getAttackValue() {
        return ((Character) this.card).getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getAttackValue).sum();
    }

    @Override
    public Integer getDefendValue() {
        if (this.isAttackStance) {
            return ((Character) this.card).getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getAttackValue).sum();
        } else {
            return ((Character) this.card).getDefend() + this.supportCards.stream().mapToInt(SummonedSkillCard::getDefendValue).sum();
        }
    }

    /**
     * Change stance of card in field arena (attack or defense)
     */
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

    public void changeToHasAttacked() {
        hasAttacked = true;
    }

    public void changeToHasNotAttacked() {
        hasAttacked = false;
    }

    public boolean isHasAttacked() {
        return this.hasAttacked;
    }

    /**
     * Check that this card is attached Power Up Card or not
     * @return true if this card is attached Power Up Card
     */
    public boolean hasPowerUp() {
        return this.supportCards.stream().map(SummonedCard::getType).anyMatch(cardType -> cardType.equals(CardType.POWERUP));
    }
}
