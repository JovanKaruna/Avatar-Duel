package com.avatarduel.model.deck;

import com.avatarduel.Settings;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.player.CanShowCard;
import com.avatarduel.model.player.PlayerController;

import com.avatarduel.util.CSSLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class HandController implements CanShowCard {

    private PlayerController parent;

    private ArrayList<CardController> cardControllers;

    @FXML
    private GridPane container;

    private List<Card> cards;

    public HandController() {
        this.cardControllers = new ArrayList<>();
    }

    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cards = new ArrayList<>();
        for (Integer i = 0; i < Settings.maximumHandCard; i++) {
            CardController cc = new CardController();
            cc.setRoot((VBox) ((Pane) this.container.getChildren().get(i)).getChildren().get(0));
            this.cardControllers.add(cc);
        }
    }

    @FXML // on Enter Hover
    public void showDetail(MouseEvent event) {
        this.getParent().getParent().setActiveCard(this.cursorAtCard(event));
    }

    @FXML // on Click
    public void useCard(MouseEvent event){
        Card c = this.cursorAtCard(event);
        this.container.getChildren().forEach(node -> CSSLoader.setClass(node, "border"));
        CSSLoader.setClass(this.cursorAtNode(event), "chosen-card-border");
    }

    @FXML // on Exit Hover
    public void removeDetail(MouseEvent event) {
        // set to empty card
    }

    public void update() {
        for (int i = 0; i < this.cards.size(); i++) {
            this.cardControllers.get(i).setAttributes(this.cards.get(i));
        }
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public void addNCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public PlayerController getParent() {
        return parent;
    }

    private Card cursorAtCard(MouseEvent event){
        return this.cards.get(GridPane.getColumnIndex(this.cursorAtNode(event)));
    }

    private Node cursorAtNode(MouseEvent event){
        return (Node) event.getSource();
    }
}
