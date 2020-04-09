package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;
import javafx.scene.input.MouseEvent;

public interface SummonEvent extends Subscriber{
    void onSummonEvent(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard);
}
