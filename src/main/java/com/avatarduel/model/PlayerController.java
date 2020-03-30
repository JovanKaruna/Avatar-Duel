package com.avatarduel.model;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PlayerController {

    private BoardController parent;

    @FXML
    private GridPane field;

    @FXML
    private FieldController fieldController;

    @FXML
    private HBox deck;

    @FXML
    private DeckController deckController;

    @FXML
    private BorderPane inventory;

    @FXML
    private PlayerInventoryController inventoryController;

    @FXML
    private BorderPane attribute;

    @FXML
    private PlayerAttributeController attributeController;

    public void init(BoardController boardController){
        this.parent = boardController;
    }

    @FXML
    public void initialize(){
        this.fieldController.init(this);
        this.deckController.init(this);
        this.inventoryController.init(this);
        this.attributeController.init(this);
    }
}
