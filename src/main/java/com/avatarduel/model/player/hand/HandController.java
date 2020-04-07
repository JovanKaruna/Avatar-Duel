package com.avatarduel.model.player.hand;

import com.avatarduel.Settings;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.card.EmptyCard;
import com.avatarduel.model.phase.Phase;
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

    @FXML private GridPane container;

    private List<Card> cards;
    private ArrayList<CardController> cardControllers;
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

    @FXML // on Hover Enter
    public void showDetail(MouseEvent event) {
        if (this.isActivePlayer()) {
            this.getParent().getParent().setActiveCard(this.cursorAtCard(event));
        }
    }

    @FXML // on Click
    public void useCard(MouseEvent event) {
        Phase currentPhase = this.getCurrentPhase();
        if (this.isActivePlayer() && ((currentPhase.equals(Phase.MAIN1)) || currentPhase.equals(Phase.MAIN2))) {
            this.select(this.cursorAtCard(event));
        }
    }

    private void select(Card card) {
        if (this.selectedCard != null) {
            this.getController(this.selectedCard).unlift();
        }
        this.selectedCard = card;
        if (this.selectedCard != null) {
            this.getController(this.selectedCard).lift();
        }
    }

    @FXML // on Hover Exit
    public void removeDetail(MouseEvent event) {
        this.getParent().getParent().setActiveCard(EmptyCard.getInstance());
    }

    public void update() {
        for (int i = 0; i < Settings.maximumHandCard; i++) {
            this.cardControllers.get(i).setCard(this.getCard(i));
        }
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public void removeCard(Card c) {
        this.getController(this.selectedCard).unlift();
        this.selectedCard = null;
        this.cards.remove(c);
        this.update();
    }

    public void addNCards(List<Card> cards) {
        this.cards.addAll(cards);
        this.update();
    }

    public List<Card> getCards() {
        return this.cards;
    }

    private Card getCard(Integer index) {
        try {
            return this.cards.get(index);
        } catch (IndexOutOfBoundsException e) {
            return EmptyCard.getInstance();
        }
    }

    public PlayerController getParent() {
        return parent;
    }

    private boolean isActivePlayer() {
        return this.getParent().isActivePlayer();
    }

    private Card cursorAtCard(MouseEvent event) {
        return this.getCard(GridPane.getColumnIndex(this.cursorAtNode(event)));
    }

    private Node cursorAtNode(MouseEvent event) {
        return (Node) event.getSource();
    }

    public void startTurn() {
        this.cards.forEach(Card::open);
        this.update();
    }

    public void endTurn() {
        this.select(null);
        this.cards.forEach(Card::close);
        this.update();
    }

    public void endPhase() {
        for (Card card : this.cards) {
            if (card != null) {
                this.getController(card).unlift();
            }
        }
    }

    private CardController getController(Card c) {
        for (int i = 0; i < this.cards.size(); i++) {
            if (this.getCard(i) == c) {
                return this.cardControllers.get(i);
            }
        }
        return null;
    }

    public Card getSelectedCard() {
        return this.selectedCard;
    }

    private Phase getCurrentPhase() {
        return this.getParent().getCurrentPhase();
    }
}
