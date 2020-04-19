package com.avatarduel.model.player;

import com.avatarduel.model.BoardController;
import com.avatarduel.model.GameEventHandler;
import com.avatarduel.model.GameInfo;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.player.field.FieldController;
import com.avatarduel.model.player.hand.HandController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class PlayerController {

    public BoardController parent;
    private Integer id;

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

    public void init(BoardController boardController, Integer id) {
        this.id = id;
        this.parent = boardController;
        this.fieldController.setColor(GameInfo.getPlayer(id).getColor());
        this.attributeController.setName(GameInfo.getPlayer(id).getName());
        this.handCard = this.handController.getCards();
        this.deckCard = this.inventoryController.getCards();
        this.isPlayerOne = this.id.equals(1);

        this.handController.init(this);
        this.inventoryController.init(this);
        this.attributeController.init(this);
        this.fieldController.init(this);
    }

    public void update() {
        this.handController.update();
        this.attributeController.update();
        this.inventoryController.update();
        this.fieldController.update();
    }

    public void endPhase() {
        this.handController.endPhase();
    }

    public void startTurn() {
        this.drawNCards(1);
        this.handController.startTurn();
        this.inventoryController.startTurn();

//        GameInfo.nextPhase();
    }

    public void endTurn() {
        this.handController.endTurn();
        this.fieldController.endTurn();
        //        this.inventoryController.endTurn();
    }

    public void drawNCards(Integer n) {
        this.handController.addNCards(this.inventoryController.takeNCards(n));
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public HandController getHandController() {
        return this.handController;
    }

    public PlayerInventoryController getInventory() {
        return this.inventoryController;
    }

    public BoardController getParent() {
        return parent;
    }

    public boolean isActivePlayer() {
        return this.id.equals(GameInfo.getPlayerTurn());
    }

    public Integer getId() {
        return id;
    }

    public GameEventHandler getGameEventHandler() {
        return this.getParent().getGameEventHandler();
    }
}
