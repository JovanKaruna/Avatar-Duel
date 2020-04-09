package com.avatarduel.model;

import com.avatarduel.Settings;
import com.avatarduel.model.card.*;
import com.avatarduel.model.phase.PhaseController;
import com.avatarduel.model.player.PlayerController;

import com.avatarduel.util.CardDAO;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

public class BoardController implements HasCardController {
    @FXML private CardController cardController;
    @FXML private PlayerController player1Controller;
    @FXML private PlayerController player2Controller;
    @FXML private CardDescController cardDescController;
    @FXML private PhaseController phaseController;

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

    @Override
    public void setActiveCard(Card c) {
        boolean tmp = c.isPortrait();
        boolean tmp2 = c.isSelected();
        c.setOrientation(true);
        c.setNotSelected();
        this.cardController.setCard(c);
        this.cardDescController.setAttributes(c);
        c.setOrientation(tmp);
        c.setSelection(tmp2);
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

    public PhaseController getPhaseController(){
        return this.phaseController;
    }

    public void endPhase() {
        this.getActivePlayer().endPhase();
    }

    public GameEventHandler getGameEventHandler() {
        return gameEventHandler;
    }
}
