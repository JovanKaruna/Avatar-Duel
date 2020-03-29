package com.avatarduel.model.card;

import java.util.ArrayList;

public class CardContainer {
    private static ArrayList<Card> cards = new ArrayList<>();

    private CardContainer(){
        throw new AssertionError("This is a utility class.");
    }

    public static ArrayList<Card> getCards(){
        return CardContainer.cards;
    }

    public static void add(Card c){
        CardContainer.cards.add(c);
    }

    public static Card get(Integer i){
        return CardContainer.cards.get(i);
    }
}
