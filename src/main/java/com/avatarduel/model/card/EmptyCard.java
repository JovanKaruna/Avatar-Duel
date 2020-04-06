package com.avatarduel.model.card;

import com.avatarduel.model.element.Element;

// Null Object Pattern, Singleton Pattern
public final class EmptyCard extends Card{

    private static EmptyCard emptyCard;

    private EmptyCard() {
        super("", "", Element.valueOf("NOELEMENT"), "Empty.png", "");
    }

    public static EmptyCard getInstance(){
        if(EmptyCard.emptyCard == null)
            EmptyCard.emptyCard = new EmptyCard();

        return EmptyCard.emptyCard;
    }
}
