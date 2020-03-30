package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardDAO;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    public ArrayList<Card> deck = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<>();

    public Player() {
        this.initDeck();
    }

    private void initDeck() {
        Collections.shuffle(CardDAO.getCards());
        for (int i = 0; i < 60; i++) {
            this.deck.add(CardDAO.get(i));
        }
    }

    public void startTurn() {
        this.drawNCards(1);
    }

    public void drawNCards(int n) {
        for (int i = 0; i < n; i++) {
            this.hand.add(this.deck.get(0));
            this.deck.remove(0);
        }
    }
}
