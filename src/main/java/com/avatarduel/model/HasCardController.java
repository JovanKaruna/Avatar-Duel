package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;

public interface HasCardController {
    void setActiveCard(Card c);

    CardController getCardController();
}
