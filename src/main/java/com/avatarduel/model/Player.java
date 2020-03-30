package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardDAO;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    public ArrayList<Card> deck = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<>();
    private PlayerController playerController;

    public Player(PlayerController playerController) {
        this.playerController = playerController;
        this.initDeck();
    }

    private void initDeck() {
        Collections.shuffle(CardDAO.getCards());
        for (int i = 0; i < 60; i++) {
            this.deck.add(CardDAO.get(i));
        }
        this.playerController.deckController.setCards(this.hand);
    }

    public void startTurn() {
        this.drawNCards(1);
        this.playerController.update();
    }

    public void drawNCards(int n) {
        for (int i = 0; i < n; i++) {
            this.hand.add(this.deck.get(0));
            this.deck.remove(0);
        }
    }
}
