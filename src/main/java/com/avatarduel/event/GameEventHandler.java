package com.avatarduel.event;

import java.util.ArrayList;
import java.util.HashMap;

// Singleton
public final class GameEventHandler {
    private static GameEventHandler singleton;
    private SelectedCardDAO selectedCard;
    private final HashMap<EventType, ArrayList<Subscriber>> subscriber = new HashMap<EventType, ArrayList<Subscriber>>(){
        {
            put(EventType.SUMMON, new ArrayList<>());
            put(EventType.ATTACHSKILL, new ArrayList<>());
            put(EventType.ATTACKCARD, new ArrayList<>());
            put(EventType.ATTACKENEMY, new ArrayList<>());
            put(EventType.DISCARDFIELD, new ArrayList<>());
            put(EventType.DISCARDHAND, new ArrayList<>());
            put(EventType.CHANGESTANCE, new ArrayList<>());
        }
    };

    private GameEventHandler(){
        this.selectedCard = new SelectedCardDAO();
    }

    public static GameEventHandler getInstance() {
        if (singleton == null) {
            singleton = new GameEventHandler();
        }
        return singleton;
    }

    public void subscribe(Subscriber subscriber, EventType type){
        this.subscriber.get(type).add(subscriber);
    }

    public void publish(EventType type){
        System.out.println("Event " + type + " has subscribers:");
        this.subscriber.get(type).forEach(s -> s.onEvent(type, this.selectedCard.getFirstCard(), this.selectedCard.getSecondCard()));
        this.getSelectedCard().resetCards();
    }

    public SelectedCardDAO getSelectedCard() {
        return selectedCard;
    }
}
