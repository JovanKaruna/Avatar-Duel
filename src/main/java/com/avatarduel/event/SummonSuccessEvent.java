package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface SummonSuccessEvent extends Subscriber{
    void onSummonSuccessEvent(SelectedCard firstCard, SelectedCard secondCard);
}
