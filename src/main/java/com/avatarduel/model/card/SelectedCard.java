package com.avatarduel.model.card;

import com.avatarduel.model.GameInfo;
import com.avatarduel.model.Location;
import com.avatarduel.model.card.summonable.EmptyCard;

public class SelectedCard<T extends Card> {
    private T card;
    private Integer ownerId;
    private Location location;
    private static SelectedCard<Card> empty;

    private SelectedCard() {
        this((T) EmptyCard.getInstance(), 0, Location.UNKNOWN);
    }

    public SelectedCard(T card, Integer ownerId, Location location) {
        this.card = card;
        this.ownerId = ownerId;
        this.location = location;
    }

    public static SelectedCard<Card> getEmpty() {
        if (SelectedCard.empty == null) {
            SelectedCard.empty = new SelectedCard<>();
        }

        return SelectedCard.empty;
    }

    public boolean isAt(Location location) {
        return this.location.equals(location);
    }

    public boolean isType(CardType cardType) {
        return this.getCard().getCardType().equals(cardType);
    }

    public boolean isSkill() {
        return this.isType(CardType.AURA) || this.isType(CardType.POWERUP) || this.isType(CardType.DESTROY);
    }

    public boolean isOurCard(){
        return this.ownerId.equals(GameInfo.getPlayerTurn());
    }

    public T getCard() {
        return card;
    }

    public boolean equals(SelectedCard other) {
        return this.card.equals(other.card) && this.ownerId.equals(other.ownerId) && this.location == other.location;
    }

    @Override
    public String toString() {
        return "SelectedCard{" +
                "card=" + card +
                ", ownerId=" + ownerId +
                ", location=" + location +
                '}';
    }
}
