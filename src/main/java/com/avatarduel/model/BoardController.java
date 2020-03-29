package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardDescController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class BoardController extends HasCardController{
    @FXML
    private CardDescController cardDescController;

    @FXML
    private Text player1Name;

    @FXML
    public void initialize(){
        this.player1Name.setText("Jojo");
        this.cardController.init(this);
        this.cardDescController.init(this);
    }

    @Override
    public void setActiveCard(Card c) {
        super.setActiveCard(c);
        this.cardDescController.setAttribute(c);
    }
}
