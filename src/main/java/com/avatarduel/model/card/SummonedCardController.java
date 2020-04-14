package com.avatarduel.model.card;

import com.avatarduel.model.Location;
import com.avatarduel.model.card.summonable.SummonedCard;

public class SummonedCardController extends CardController {
    SummonedCard summonedCard;

    public void setSummonedCard(SummonedCard summonedCard) {
        this.summonedCard = summonedCard;
        this.setCard(summonedCard.getCard(), Location.FIELD);
    }

    public SummonedCard getSummonedCard() {
        return summonedCard;
    }

    @Override
    public void show(Location location) {
        this.getCard().setOrientation(summonedCard.isPortrait());
        super.show(location);
    }
}
