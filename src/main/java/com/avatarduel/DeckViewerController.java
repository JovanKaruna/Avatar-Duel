package com.avatarduel;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.HasCardController;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.util.List;

public class DeckViewerController extends HasCardController {
    @FXML
    private Text currentIdx;

    @FXML
    private Text maxIdx;

    @FXML
    private Button nextBtn;

    private Integer i = 0;
    
    private List<Card> cards;

    @FXML
    public void initialize() {
        this.cardController.init(this);
    }

    public void setCards(List<Card> cards) {
        this.i = 0;
        this.cards = cards;
        this.setActiveCard(this.cards.get(i));
        this.currentIdx.setText(i.toString());
        this.maxIdx.setText(((Integer)this.cards.size()).toString());
    }

    @FXML
    void nextCard(ActionEvent event) {
        this.i++;
        this.i %= this.cards.size();
        this.cardController.setAttributes(this.cards.get(i));
        this.currentIdx.setText(i.toString());
    }

    @FXML
    void prevCard(ActionEvent event) {
        this.i--;
        this.i %= this.cards.size();
        this.cardController.setAttributes(this.cards.get(i));
        this.currentIdx.setText(i.toString());
    }

    @FXML
    public void viewDeckPlayer1(ActionEvent event){
        this.setCards(AvatarDuel.getActivePlayer().deck);
    }

    @FXML
    public void viewDeckPlayer2(ActionEvent event){
        this.setCards(AvatarDuel.getOtherPlayer().deck);
    }

    @FXML
    public void viewHandPlayer1(ActionEvent event){
        this.setCards(AvatarDuel.getActivePlayer().hand);
    }

    @FXML
    public void viewHandPlayer2(ActionEvent event){
        this.setCards(AvatarDuel.getOtherPlayer().hand);
    }

    public void setCardController(CardController cardController) {
        this.cardController = cardController;
    }
}
