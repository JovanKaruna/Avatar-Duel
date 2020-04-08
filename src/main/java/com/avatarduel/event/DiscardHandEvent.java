package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public class DiscardHandEvent implements Subscriber {
    @Override
    public void onEvent(EventType type, SelectedCard firstCard, SelectedCard secondCard) {
        // TODO
        System.out.println("Discard hand!");
    }
}
