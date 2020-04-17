package com.avatarduel.model;

import com.avatarduel.Settings;
import com.avatarduel.model.card.*;
import com.avatarduel.model.phase.PhaseController;
import com.avatarduel.model.player.PlayerController;

import com.avatarduel.util.CardDAO;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;

public class BoardController implements HasCardController {
    @FXML private CardController cardController;
    @FXML private PlayerController player1Controller;
    @FXML private PlayerController player2Controller;
    @FXML private CardDescController cardDescController;
    @FXML private PhaseController phaseController;
    @FXML private Text message;

    private GameEventHandler gameEventHandler;

    public BoardController() throws IOException, URISyntaxException {
        CardDAO.init();
        this.gameEventHandler = GameEventHandler.getInstance();
    }

    public void init() {
        this.cardController.init(this);
        this.cardDescController.init(this);
        this.phaseController.init(this);
        this.player1Controller.init(this, 1);
        this.player2Controller.init(this, 2);
    }

    public CardDescController getCardDescController(){
        return this.cardDescController;
    }

    @Override
    public void setActiveCard(Card c){
        this.cardController.setCard(c, Location.GRAVEYARD);
        this.cardDescController.setAttributes(c);
    }

    @Override
    public CardController getCardController() {
        return this.cardController;
    }

    public void startGame() {
        this.player1Controller.drawNCards(Settings.startingCardAmount);
        this.player2Controller.drawNCards(Settings.startingCardAmount);

        this.player2Controller.endTurn();
        this.player1Controller.startTurn();
    }

    public void nextPlayer() {
        this.player1Controller.endTurn();
        PlayerController tmp = this.player1Controller;
        this.player1Controller = this.player2Controller;
        this.player2Controller = tmp;
        this.player1Controller.startTurn();
    }

    public PlayerController getActivePlayer() {
        return this.player1Controller;
    }

    public PlayerController getOtherPlayer() {
        return this.player2Controller;
    }

    public PhaseController getPhaseController(){
        return this.phaseController;
    }

    public void endPhase() {
        this.getActivePlayer().endPhase();
    }

    public GameEventHandler getGameEventHandler() {
        return gameEventHandler;
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
