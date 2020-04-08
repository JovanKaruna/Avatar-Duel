package com.avatarduel.model.card.summonable;

import com.avatarduel.model.GameInfo;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardType;

public abstract class SummonedCard<T extends Card> {
    T card;
    CardType type;
    Integer summonedOn;

    SummonedCard(T card, CardType type) {
        this.card = card;
        this.type = type;
    }

    public T getCard() {
        return this.card;
    }

    public boolean canAttack() {
        return false;
    }

    public Integer getAttackValue() {
        return 0;
    }

    public Integer getDefendValue() {
        return 0;
    }

    public boolean isSummonedThisTurn(){
        return this.summonedOn.equals(GameInfo.getTurn());
    }
}
