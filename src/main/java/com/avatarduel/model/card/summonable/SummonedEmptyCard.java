package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.EmptyCard;

// Singleton Pattern
public final class SummonedEmptyCard extends SummonedCard {
    private static SummonedEmptyCard singleton;
    private static final EmptyCard card = EmptyCard.getInstance();

    private SummonedEmptyCard() {
        super(EmptyCard.getInstance(), CardType.EMPTY);
    }

    public static SummonedEmptyCard getInstance() {
        if (SummonedEmptyCard.singleton == null) {
            SummonedEmptyCard.singleton = new SummonedEmptyCard();
        }

        return SummonedEmptyCard.singleton;
    }
}
