package com.avatarduel.model.card;

import com.avatarduel.model.Location;
import com.avatarduel.model.card.summonable.SummonedCard;
import com.avatarduel.model.card.summonable.SummonedEmptyCard;

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

    @Override
    public void setEmpty(Location location) {
        super.setEmpty(location);
        this.summonedCard = SummonedEmptyCard.getInstance();
    }
}
