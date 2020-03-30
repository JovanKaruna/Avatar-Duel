package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;

import javafx.fxml.FXML;


public abstract class HasCardController {
    @FXML
    public CardController cardController;

    public void setActiveCard(Card c){
        this.cardController.setAttributes(c);
    }
}
