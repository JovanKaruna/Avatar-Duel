package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;
import javafx.scene.input.MouseEvent;

public interface SummonFailEvent extends Subscriber{
    void onSummonFailEvent(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard);
}
