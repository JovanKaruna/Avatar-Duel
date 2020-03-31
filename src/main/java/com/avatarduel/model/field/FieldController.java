package com.avatarduel.model.field;

import com.avatarduel.model.player.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        for(Node node : root.getChildren()){
            node.getStyleClass().add(color+"-field-cell");
        }
    }
}
