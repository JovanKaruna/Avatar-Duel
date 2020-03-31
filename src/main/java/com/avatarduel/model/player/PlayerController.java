package com.avatarduel.model.player;

import com.avatarduel.model.BoardController;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.deck.HandController;
import com.avatarduel.model.field.FieldController;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class PlayerController {

    public BoardController parent;

    public List<Card> handCard;
    public List<Card> deckCard;

    @FXML
    private GridPane field;

    @FXML
    private FieldController fieldController;

    @FXML
    private HBox hand;

    @FXML
    protected HandController handController;

    @FXML
    private BorderPane inventory;

    @FXML
    PlayerInventoryController inventoryController;

    @FXML
    private BorderPane attribute;

    @FXML
    private PlayerAttributeController attributeController;

    public void init(BoardController boardController) {
        this.parent = boardController;
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    @FXML
    public void initialize() {
        this.fieldController.init(this);
        this.handController.init(this);
        this.inventoryController.init(this);
        this.attributeController.init(this);
        this.handCard = this.handController.getCards();
        this.deckCard = this.inventoryController.getCards();
    }

    public void update() {
        this.handController.update();
        this.inventoryController.update();
    }

    public void startTurn() {
        this.drawNCards(1);
        this.update();
    }

    public void drawNCards(Integer n) {
        this.handController.addNCards(this.inventoryController.takeNCards(n));
    }

}
