package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;
import javafx.scene.input.MouseEvent;

public interface ChangeStanceEvent extends Subscriber {
    void onChangeStanceEvent(MouseEvent event);
}
