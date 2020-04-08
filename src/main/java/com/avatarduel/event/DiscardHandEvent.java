package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface DiscardHandEvent extends Subscriber {
    void onDiscardHandEvent(SelectedCard firstCard, SelectedCard secondCard);
}
