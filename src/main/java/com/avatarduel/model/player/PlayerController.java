package com.avatarduel.model.player;

import com.avatarduel.model.BoardController;
import com.avatarduel.model.deck.DeckController;
import com.avatarduel.model.field.FieldController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PlayerController{

    public BoardController parent;

    @FXML
    private GridPane field;

    @FXML
    private FieldController fieldController;

    @FXML
    private HBox deck;

    @FXML
    protected DeckController deckController;

    @FXML
    private BorderPane inventory;

    @FXML
    PlayerInventoryController inventoryController;

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

    public void update(){
        this.deckController.update();
        this.inventoryController.update();
    }
}
