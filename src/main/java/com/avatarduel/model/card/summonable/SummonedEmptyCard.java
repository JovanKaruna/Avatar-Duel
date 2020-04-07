package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.EmptyCard;

public class SummonedEmptyCard extends SummonedCard {
    private static SummonedEmptyCard singleton;
    private static final EmptyCard card = EmptyCard.getInstance();

    private SummonedEmptyCard() {
    }

    public static SummonedEmptyCard getInstance() {
        if (SummonedEmptyCard.singleton == null) {
            SummonedEmptyCard.singleton = new SummonedEmptyCard();
        }

        return SummonedEmptyCard.singleton;
    }
}
