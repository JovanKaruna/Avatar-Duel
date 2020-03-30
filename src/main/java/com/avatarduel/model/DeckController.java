package com.avatarduel.model;

import com.avatarduel.Paths;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class DeckController implements CanShowCard{

    private PlayerController parent;

    private ArrayList<CardController> cardControllers;

    @FXML
    private GridPane container;

    private List<Card> cards;

    @FXML
    public void initialize(){
        this.cardControllers = new ArrayList<>();
    }

    @FXML
    public void removeDetail(MouseEvent event) {
        // set to empty card
    }

    @FXML
    public void showDetail(MouseEvent event) {
        Pane p = (Pane) event.getSource();
        try {
            this.parent.parent.cardController.setAttributes(this.cards.get(GridPane.getColumnIndex(p)));
            this.parent.parent.cardDescController.setAttributes(this.cards.get(GridPane.getColumnIndex(p)));
        } catch (IndexOutOfBoundsException e){
            // remove detail
        }
    }

    public void init(PlayerController playerController) {
        this.parent = playerController;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        for(int i = 0; i < this.cards.size(); i++){
            this.addCardController(i);
        }
    }

    public void update(){
        for(int i = 0; i < this.cards.size(); i++) {
            try{
                this.cardControllers.get(i);
            } catch (IndexOutOfBoundsException e){
                this.addCardController(i);
            }
            this.cardControllers.get(i).setAttributes(this.cards.get(i));
        }
    }

    private void addCardController(Integer i){
        CardController cc = new CardController();
        cc.setRoot((VBox) ((Pane) this.container.getChildren().get(i)).getChildren().get(0));
        this.cardControllers.add(cc);
    }
}
