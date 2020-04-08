package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface SummonEvent extends Subscriber{
    void onSummonEvent(SelectedCard firstCard, SelectedCard secondCard);
}
