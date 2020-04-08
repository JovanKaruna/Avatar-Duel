package com.avatarduel.model.player.hand;

import com.avatarduel.Settings;
import com.avatarduel.event.DiscardHandEvent;
import com.avatarduel.event.EventType;
import com.avatarduel.event.GameEventHandler;
import com.avatarduel.event.Subscriber;
import com.avatarduel.model.GameInfo;
import com.avatarduel.model.Location;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.card.EmptyCard;
import com.avatarduel.model.card.SelectedCard;
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

public class HandController implements CanShowCard, DiscardHandEvent {

    private PlayerController parent;

    @FXML private GridPane container;

    private List<Card> cards;
    private ArrayList<CardController> cardControllers;

    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cards = new ArrayList<>();
        this.cardControllers = new ArrayList<>();
        for (Integer i = 0; i < Settings.maximumHandCard; i++) {
            CardController cc = new CardController();
            cc.setRoot((StackPane) ((Pane) this.container.getChildren().get(i)).getChildren().get(0));
            this.cardControllers.add(cc);
        }

        this.getGameEventHandler().subscribe(this, EventType.DISCARDHAND);
    }

    @FXML // on Hover Enter
    public void showDetail(MouseEvent event) {
        if (this.isActivePlayer()) {
            this.getParent().getParent().setActiveCard(this.cursorAtCard(event));
        }
    }

    @FXML // on Click
    public void useCard(MouseEvent event) {
        if (this.isActivePlayer() && GameInfo.isMainPhase()) {
            this.getGameEventHandler().getSelectedCard().selectCard(this.cursorAtCard(event), this.getParent().getId(), Location.HAND);
            this.update();
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

    public void removeCard(Card c) {
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
        this.getGameEventHandler().getSelectedCard().resetCards();
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

    public GameEventHandler getGameEventHandler(){
        return this.getParent().getGameEventHandler();
    }

    @Override
    public void onEvent(EventType type, SelectedCard firstCard, SelectedCard secondCard) {
        if(type.equals(EventType.DISCARDHAND)){
            this.onDiscardHandEvent(firstCard, secondCard);
        } else {
            assert false;
        }
    }

    @Override
    public void onDiscardHandEvent(SelectedCard firstCard, SelectedCard secondCard) {
        this.removeCard(firstCard.getCard());
    }
}