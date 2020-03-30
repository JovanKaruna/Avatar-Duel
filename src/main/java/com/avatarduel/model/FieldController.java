package com.avatarduel.model;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class FieldController {

    private PlayerController parent;

    @FXML
    private GridPane root;

    @FXML
    public void init(PlayerController playerController){
        this.parent = playerController;
    }
}
