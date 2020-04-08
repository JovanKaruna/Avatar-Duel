package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface DiscardFieldEvent extends Subscriber {
    void onDiscardFieldEvent(SelectedCard firstCard, SelectedCard secondCard);
}
