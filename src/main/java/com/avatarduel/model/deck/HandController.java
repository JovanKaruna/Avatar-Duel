package com.avatarduel.model.deck;

import com.avatarduel.Settings;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.card.EmptyCard;
import com.avatarduel.model.player.CanShowCard;
import com.avatarduel.model.player.PlayerController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class HandController implements CanShowCard {

    private PlayerController parent;

    private ArrayList<CardController> cardControllers;

    @FXML
    private GridPane container;

    private List<Card> cards;

    private Card selectedCard;

    public HandController() {
        this.cardControllers = new ArrayList<>();
    }

    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cards = new ArrayList<>();
        for (Integer i = 0; i < Settings.maximumHandCard; i++) {
            CardController cc = new CardController();
            cc.setRoot((StackPane) ((Pane) this.container.getChildren().get(i)).getChildren().get(0));
            this.cardControllers.add(cc);
        }
    }

    @FXML // on Enter Hover
    public void showDetail(MouseEvent event) {
        try {
            this.getParent().getParent().setActiveCard(this.cursorAtCard(event));
        } catch (IndexOutOfBoundsException e) {
            this.getParent().getParent().setActiveCard(EmptyCard.getInstance());
        }
    }

    @FXML // on Click
    public void useCard(MouseEvent event) {
        this.select(this.cursorAtCard(event));
    }

    private void select(Card card) {
        if (this.selectedCard != null) this.getController(this.selectedCard).unlift();
        this.selectedCard = card;
        if (this.selectedCard != null) this.getController(this.selectedCard).lift();
    }

    @FXML // on Exit Hover
    public void removeDetail(MouseEvent event) {
        this.getParent().getParent().setActiveCard(EmptyCard.getInstance());
    }

    public void update() {
        for (int i = 0; i < Settings.maximumHandCard; i++) {
            try {
                this.cardControllers.get(i).setAttributes(this.cards.get(i));
            } catch (IndexOutOfBoundsException e) {
                this.cardControllers.get(i).setAttributes(EmptyCard.getInstance());
            }
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

    private Card cursorAtCard(MouseEvent event) {
        return this.cards.get(GridPane.getColumnIndex(this.cursorAtNode(event)));
    }

    private Node cursorAtNode(MouseEvent event) {
        return (Node) event.getSource();
    }

    public void endTurn() {
        this.select(null);
        this.cards.forEach(c -> c.close());
        this.update();
    }

    private CardController getController(Card c) {
        for (int i = 0; i < this.cards.size(); i++) {
            if (this.cards.get(i) == c) {
                return this.cardControllers.get(i);
            }
        }
        return null;
    }
}
