package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface ChangeStanceEvent extends Subscriber {
    void onChangeStanceEvent(SelectedCard firstCard, SelectedCard secondCard);
}
