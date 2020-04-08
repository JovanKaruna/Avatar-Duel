package com.avatarduel.model.card;

import com.avatarduel.model.Location;

public class SelectedCard <T extends Card> {
    private T card;
    private Integer ownerId;
    private Location location;
    private static SelectedCard<Card> empty;


    private SelectedCard(){
        this((T) EmptyCard.getInstance(), 0, Location.UNKNOWN);
    }

    public SelectedCard(T card, Integer ownerId, Location location) {
        this.card = card;
        this.ownerId = ownerId;
        this.location = location;
    }

    public static SelectedCard<Card> getEmpty(){
        if(SelectedCard.empty == null){
            SelectedCard.empty = new SelectedCard();
        }

        return SelectedCard.empty;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isAt(Location location){
        return this.getLocation().equals(location);
    }

    public boolean isType(CardType cardType){
        return this.getCard().getCardType().equals(cardType);
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
