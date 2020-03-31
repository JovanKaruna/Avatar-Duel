package com.avatarduel.model.player;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface CanShowCard {
    @FXML
    void removeDetail(MouseEvent event);

    @FXML
    void showDetail(MouseEvent event);
}
