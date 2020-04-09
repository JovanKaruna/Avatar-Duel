package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;
import javafx.scene.input.MouseEvent;


public interface Subscriber {
    void onEvent(MouseEvent event, EventType type, SelectedCard firstCard, SelectedCard secondCard);
}
