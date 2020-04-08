package com.avatarduel.event;

import com.avatarduel.model.Location;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.SelectedCard;

public final class SelectedCardDAO {
    private final SelectedCard empty = SelectedCard.getEmpty();
    private SelectedCard firstCard = this.empty;
    private SelectedCard secondCard = this.empty;

    /**
     * @param inputCard : card that wanted to be selected
     * @param ownerid : ownership of inputCard
     * @param location : location of inputCard
     */
    public void selectCard(Card inputCard, Integer ownerid, Location location) {
        SelectedCard card = new SelectedCard(inputCard, ownerid, location);
        if (this.firstCard.equals(card)) {
            // card = kartu 1
            this.firstCard = this.setCard(this.firstCard, this.empty);
            return;
        }
        if (this.firstCard == this.empty) {
            if (this.secondCard == empty) {
                // dua2nya kosong, masukin ke yang pertama
                this.firstCard = this.setCard(this.firstCard, card);
            } else {
                // no 1 kosong, geser no2 ke 1, trus isi no 2
                if (this.secondCard.getLocation().equals(Location.HAND) && card.getLocation().equals(Location.HAND)) {
                    // kecuali kalo sama2 kartu tangan
                    this.secondCard = this.setCard(this.secondCard, this.empty);
                    this.firstCard = this.setCard(this.firstCard, card);
                } else {
                    this.firstCard = this.setCard(this.firstCard, this.empty);
                    this.secondCard = this.setCard(this.secondCard, card);
                }
            }
        } else {
            if (this.secondCard.equals(card)) {
                // kartu 2 = card
                this.secondCard = this.setCard(this.secondCard, this.empty);
                return;
            } else if (this.secondCard == this.empty) {
                if (this.firstCard.getLocation().equals(Location.HAND) && card.getLocation().equals(Location.HAND)) {
                    // maks 1 kartu tangan
                    this.firstCard = this.setCard(this.firstCard, this.empty);
                    this.firstCard = this.setCard(this.firstCard, card);
                    return;
                }
            }
            // ganti kartu 2
            this.secondCard = this.setCard(this.secondCard, this.empty);
            this.secondCard = this.setCard(this.secondCard, card);
        }
        this.triggerEvent();
    }

    private SelectedCard setCard(SelectedCard cardFrom, SelectedCard cardTo) {
        if (cardFrom == this.empty && cardTo != this.empty) {
            cardFrom = cardTo;
            cardFrom.getCard().setSelected();
        } else if (cardFrom != this.empty && cardTo == this.empty) {
            cardFrom.getCard().setNotSelected();
            cardFrom = cardTo;
        } else if (cardFrom != this.empty && cardTo != this.empty) {
            cardFrom.getCard().setNotSelected();
            cardFrom = cardTo;
            cardFrom.getCard().setSelected();
        }
        return cardFrom;
    }

    public SelectedCard getFirstCard() {
        return firstCard;
    }

    public SelectedCard getSecondCard() {
        return secondCard;
    }

    public void resetCards() {
        this.firstCard = this.empty;
        this.secondCard = this.empty;
    }

    private void triggerEvent() {
        if (this.firstCard != this.empty) {
            // TODO
        } else {
            // TODO
        }
    }
}
