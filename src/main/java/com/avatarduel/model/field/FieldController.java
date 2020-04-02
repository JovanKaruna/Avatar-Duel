package com.avatarduel.model.field;

import com.avatarduel.model.player.PlayerController;

import com.avatarduel.util.CSSLoader;
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

    public void setColor(String color){
        root.getChildren().forEach(node -> CSSLoader.addClass(node, color+"-field-cell"));
    }

    public PlayerController getParent() {
        return parent;
    }
}
