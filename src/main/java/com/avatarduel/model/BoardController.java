package com.avatarduel.model;

import com.avatarduel.Settings;
import com.avatarduel.model.card.*;
import com.avatarduel.model.phase.PhaseController;
import com.avatarduel.model.player.PlayerController;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

public class BoardController implements HasCardController {
    @FXML private CardController cardController;
    @FXML private PlayerController player1Controller;
    @FXML private PlayerController player2Controller;
    @FXML private CardDescController cardDescController;
    @FXML private PhaseController phaseController;

    public BoardController() throws IOException, URISyntaxException {
        CardDAO.init();
    }

    public void init(String player1name, String player2name) {
        this.cardController.init(this);
        this.cardDescController.init(this);
        this.phaseController.init(this);
        this.player1Controller.init(this, player1name, Settings.player1Color);
        this.player2Controller.init(this, player2name, Settings.player2Color);
    }

    @Override
    public void setActiveCard(Card c) {
        boolean tmp = c.isPortrait();
        c.setOrientation(true);
        this.cardController.setCard(c);
        this.cardDescController.setAttributes(c);
        c.setOrientation(tmp);
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
}
