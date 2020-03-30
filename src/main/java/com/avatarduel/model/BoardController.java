package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardDescController;
import javafx.fxml.FXML;

public class BoardController extends HasCardController{
    @FXML
    private PlayerController player1Controller;

    @FXML
    private PlayerController player2Controller;

    @FXML
    private CardDescController cardDescController;

    @FXML
    public void initialize(){
        this.cardController.init(this);
        this.cardDescController.init(this);
        this.player1Controller.init(this);
        this.player2Controller.init(this);
    }

    @Override
    public void setActiveCard(Card c) {
        super.setActiveCard(c);
        this.cardDescController.setAttribute(c);
    }

    public PlayerController getPlayer1(){
        return this.player1Controller;
    }
}
