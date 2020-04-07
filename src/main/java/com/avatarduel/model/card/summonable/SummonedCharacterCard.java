package com.avatarduel.model.card.summonable;

import java.util.ArrayList;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.card.EmptyCard;
import com.avatarduel.model.card.summonable.character.Character;
import com.avatarduel.model.card.summonable.skill.Skill;
import com.avatarduel.model.element.Element;


public class SummonedCharacterCard extends SummonedCard{
    ArrayList<SummonedSkillCard> supportCards;
    private boolean isAttackStance;
    private Character card;

    public SummonedCharacterCard(Character summonableCard) {
        this.card = summonableCard;
    }

    public boolean canAttack(){
        return this.isAttackStance;
    }

    private SummonableCard getCard(){
        return this.card;
    }

    public Integer getAttackValue() {
        return this.card.getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getAttack).sum();
    }

    public Integer getDefendValue(){
        return this.card.getDefend() + this.supportCards.stream().mapToInt(SummonedSkillCard::getDefend).sum();
    }

    public void changeStance() {
        this.isAttackStance ^= true;
        this.card.changeOrientation();
    }
}
