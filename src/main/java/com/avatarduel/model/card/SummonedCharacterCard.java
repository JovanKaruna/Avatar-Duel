package com.avatarduel.model.card;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;



public class SummonedCharacterCard extends SummonedCard {
    CardController card;
    ArrayList<SummonedSkillCard> supportCards;
    private boolean isAttackStance;

    public boolean canAttack(){
        return this.isAttackStance;
    }

    private Character getCard(){
        return (Character) this.card.getCard();
    }

    public Integer getAttackValue() {
        return getCard().getAttack() + this.supportCards.stream().mapToInt(SummonedSkillCard::getAttack).sum();
    }

    public Integer getDefendValue(){
        return getCard().getDefend() + this.supportCards.stream().mapToInt(SummonedSkillCard::getDefend).sum();
    }

    private void changeStance() {
        if(this.isAttackStance){
            this.card.getRoot().setRotate(90);
        }else{
            this.card.getRoot().setRotate(0);
        }
    }
}
