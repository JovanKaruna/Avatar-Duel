package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface Subscriber {
    void onEvent(EventType type, SelectedCard firstCard, SelectedCard secondCard);
}
