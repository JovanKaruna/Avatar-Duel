package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;

import javafx.fxml.FXML;


public abstract class HasCardController {
    @FXML
    protected CardController cardController;

    public void setActiveCard(Card c){
        this.cardController.setAttributes(c);
    }

    public CardController getCardController() {
        return cardController;
    }
}
