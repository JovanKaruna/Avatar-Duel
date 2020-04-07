package com.avatarduel.model.player;

import com.avatarduel.model.BoardController;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.player.hand.HandController;
import com.avatarduel.model.player.field.FieldController;

import com.avatarduel.model.phase.Phase;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class PlayerController {

    public BoardController parent;

    private List<Card> handCard;
    private List<Card> deckCard;

    public boolean isPlayerOne;

    @FXML private GridPane field;
    @FXML private FieldController fieldController;
    @FXML private HBox hand;
    @FXML private HandController handController;
    @FXML private BorderPane inventory;
    @FXML private PlayerInventoryController inventoryController;
    @FXML private BorderPane attribute;
    @FXML private PlayerAttributeController attributeController;

    @FXML
    public void initialize() {
        this.handController.init(this);
        this.inventoryController.init(this);
        this.attributeController.init(this);
        this.fieldController.init(this);
    }

    public void init(BoardController boardController, String name, String color) {
        this.parent = boardController;
        this.fieldController.setColor(color);
        this.attributeController.setName(name);
        this.handCard = this.handController.getCards();
        this.deckCard = this.inventoryController.getCards();
        this.isPlayerOne = this.equals(this.getParent().getActivePlayer());
    }

    public void update() {
        this.handController.update();
        this.attributeController.update();
        this.inventoryController.update();
        this.fieldController.update();
    }

    public void startTurn() {
        this.drawNCards(1);
        this.handController.startTurn();
        this.inventoryController.startTurn();

        this.getParent().getPhaseController().nextPhase();
    }

    public void endTurn() {
        this.handController.endTurn();
    }

    public Phase getCurrentPhase(){
        return this.getParent().getPhaseController().getPhaseValue();
    }

    public void setName(String name) {
        this.attributeController.setName(name);
    }

    public void drawNCards(Integer n) {
        this.handController.addNCards(this.inventoryController.takeNCards(n));
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public HandController getHandController(){
        return this.handController;
    }

    public PlayerInventoryController getInventory(){
        return this.inventoryController;
    }

    public BoardController getParent() {
        return parent;
    }

    public boolean isActivePlayer(){
        return this.getParent().getActivePlayer() == this;
    }

    public void endPhase() {
        this.handController.endPhase();
    }
}
