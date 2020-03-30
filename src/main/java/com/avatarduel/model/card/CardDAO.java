package com.avatarduel.model.card;

import java.util.ArrayList;

public class CardDAO {
    private static ArrayList<Card> cards = new ArrayList<>();

    private CardDAO() {
        throw new AssertionError("This is a utility class.");
    }

    public static ArrayList<Card> getCards() {
        return CardDAO.cards;
    }

    public static void add(Card c) {
        CardDAO.cards.add(c);
    }

    public static Card get(Integer i) {
        return CardDAO.cards.get(i);
    }
}
