package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.List;

public class DeckController extends HasCardController{

    private PlayerController parent;

    @FXML
    private GridPane container;

    private List<Card> cards;

    @FXML
    public void initialize(){

    }

    public void init(PlayerController playerController) {
        this.parent = playerController;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @FXML
    void removeDetail(MouseEvent event) {

    }

    @FXML
    void showDetail(MouseEvent event) {
//        this.parent.setActiveCard(this.parent.);
    }

}
