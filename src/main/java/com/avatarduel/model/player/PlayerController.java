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

    private List<Card> handCard;
    private List<Card> deckCard;

    @FXML
    private GridPane field;

    @FXML
    private FieldController fieldController;

    @FXML
    private HBox hand;

    @FXML
    private HandController handController;

    @FXML
    private BorderPane inventory;

    @FXML
    private PlayerInventoryController inventoryController;

    @FXML
    private BorderPane attribute;

    @FXML
    private PlayerAttributeController attributeController;

    @FXML
    public void initialize() {
        this.fieldController.init(this);
        this.handController.init(this);
        this.inventoryController.init(this);
        this.attributeController.init(this);
    }

    public void init(BoardController boardController, String name, String color) {
        this.parent = boardController;
        this.fieldController.setColor(color);
        this.attributeController.setName(name);
        this.handCard = this.handController.getCards();
        this.deckCard = this.inventoryController.getCards();
    }

    public void update() {
        this.handController.update();
        this.attributeController.update();
        this.inventoryController.update();
    }

    public void startTurn() {
        this.drawNCards(1);
        this.update();
    }

    public void drawNCards(Integer n) {
        this.handController.addNCards(this.inventoryController.takeNCards(n));
    }

    public void setName(String name) {
        this.attributeController.setName(name);
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public BoardController getParent() {
        return parent;
    }
}
