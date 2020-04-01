package com.avatarduel.model;

import com.avatarduel.AvatarDuel;
import com.avatarduel.Settings;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardDescController;
import com.avatarduel.model.player.PlayerController;

import javafx.fxml.FXML;

public class BoardController extends HasCardController {
    @FXML
    public PlayerController player1Controller;

    @FXML
    public PlayerController player2Controller;

    @FXML
    public CardDescController cardDescController;

    @FXML
    public void initialize() {
        this.cardController.init(this);
        this.cardDescController.init(this);
        this.player1Controller.init(this, "Jojo", "blue");
        this.player2Controller.init(this, "Jovan", "red");
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
