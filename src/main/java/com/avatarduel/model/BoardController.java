package com.avatarduel.model;

import com.avatarduel.Settings;
import com.avatarduel.model.card.*;
import com.avatarduel.model.player.PlayerController;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

public class BoardController extends HasCardController {
    @FXML
    public PlayerController player1Controller;

    @FXML
    public PlayerController player2Controller;

    @FXML
    public CardDescController cardDescController;

    public BoardController() throws IOException, URISyntaxException {
        CardDAO.loadAllCards();
    }

    public void init(String player1name, String player2name){
        this.cardController.init(this);
        this.cardDescController.init(this);
        this.player1Controller.init(this, player1name, Settings.player1Color);
        this.player2Controller.init(this, player2name, Settings.player2Color);
    }

    @Override
    public void setActiveCard(Card c) {
        super.setActiveCard(c);
        this.cardDescController.setAttributes(c);
    }

    public void startGame() {
        this.player1Controller.drawNCards(Settings.startingCardAmount);
        this.player2Controller.drawNCards(Settings.startingCardAmount);
        this.player1Controller.startTurn();
    }

    public void nextPlayer() {
        PlayerController tmp = this.player1Controller;
        this.player1Controller = this.player2Controller;
        this.player2Controller = tmp;
        this.player1Controller.startTurn();
    }

    public PlayerController getActivePlayer(){
        return this.player1Controller;
    }
}
