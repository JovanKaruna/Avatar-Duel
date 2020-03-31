package com.avatarduel.model;

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
        this.player1Controller.init(this);
        this.player2Controller.init(this);
    }

    @Override
    public void setActiveCard(Card c) {
        super.setActiveCard(c);
        this.cardDescController.setAttributes(c);
    }
}
