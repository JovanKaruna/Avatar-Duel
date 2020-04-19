package com.avatarduel.model;

import com.avatarduel.event.EventType;
import com.avatarduel.event.Subscriber;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.SelectedCard;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

// Singleton Pattern
public final class GameEventHandler {
    private static GameEventHandler singleton;
    private HashMap<EventType, ArrayList<Subscriber>> subscriber;

    private final SelectedCard empty = SelectedCard.getEmpty();
    private SelectedCard firstCard = this.empty;
    private SelectedCard secondCard = this.empty;

    /**
     * @param inputCard : card that wanted to be selected
     * @param ownerid   : ownership of inputCard
     * @param location  : location of inputCard
     */
    public void selectCard(MouseEvent event, Card inputCard, Integer ownerid, Location location) {
        SelectedCard card = new SelectedCard<>(inputCard, ownerid, location);

        // kartu sama persis, mau unselect kartu
        if (this.firstCard.equals(card)) {
            this.firstCard = this.setCard(this.firstCard, this.empty);
            return;
        }

        if (GameInfo.isMainPhase()) {
            // card = kartu pertama, harus kartu milik sendiri, tidak boleh di graveyard, tidak boleh kosong
            if (this.firstCard == this.empty) {
                if (card.isOurCard() && !card.isAt(Location.GRAVEYARD) && !card.isType(CardType.EMPTY)) {
                    // di tangan boleh apapun, di field kalo karakter trigger change stance
                    this.firstCard = this.setCard(this.firstCard, card);
                    if (card.isType(CardType.CHARACTER) && card.isAt(Location.FIELD)) {
                        this.triggerEvent(event, EventType.CHANGESTANCE);
                    }
                }
                return;
            }

            // prekondisi : kartu pertama di hand (apapun) / field (skill)
            // card = kartu kedua, != kartu pertama
            if (card.isOurCard()) {
                if (card.isAt(Location.HAND)) {
                    this.firstCard = this.setCard(this.firstCard, card);

                } else if (card.isAt(Location.FIELD)) {
                    if (this.firstCard.isAt(Location.HAND)) {
                        // kartu 1 di tangan, kartu 2 di field
                        // bisa kalo kartu 2 kosong, atau kartu 1 land
                        if (this.firstCard.isType(CardType.LAND) || card.isType(CardType.EMPTY)) {
                            this.secondCard = this.setCard(this.secondCard, card);
                            this.triggerEvent(event, EventType.SUMMON);
                            return;
                        }

                    } else {
                        // card at field (pasti skill}
                        if (card.isSkill()) {
                            this.firstCard = this.setCard(this.firstCard, card);
                        } else if (card.isType(CardType.CHARACTER)) {
                            this.secondCard = this.setCard(this.secondCard, card);
                            this.triggerEvent(event, EventType.ATTACHSKILL);
                        }
                    }

                } else if (card.isAt(Location.GRAVEYARD)) {
                    if (this.firstCard.isSkill()) {
                        this.secondCard = this.setCard(this.secondCard, card);
                        if (this.firstCard.isAt(Location.FIELD)) {
                            this.triggerEvent(event, EventType.DISCARDFIELD);
                        } else if (this.firstCard.isAt(Location.HAND)) {
                            this.triggerEvent(event, EventType.DISCARDHAND);
                        }
                    } else {
                        this.resetCards();
                        System.out.println("Cannot discard other than skill");
                    }
                }
                return;
            }

            // card = kartu musuh, cuma bisa attach skill
            if (card.isAt(Location.FIELD) && card.isType(CardType.CHARACTER) && this.firstCard.isAt(Location.FIELD) && this.firstCard.isSkill()) {
                this.secondCard = this.setCard(this.secondCard, card);
                this.triggerEvent(event, EventType.ATTACHSKILL);
            }
            return;
        }

        if (GameInfo.isBattlePhase()) {
            // cuma bisa attack,
            // kartu 1 harus kartu field karakter sendiri, prekondisi : kartu sendiri kondisi meneyerang
            if (this.firstCard == this.empty) {
                if (card.isOurCard() && card.isAt(Location.FIELD) && card.isType(CardType.CHARACTER)) {
                    this.firstCard = this.setCard(this.firstCard, card);
                    System.out.println("Card is ready to attack:" + card);
                }
                return;
            }

            // punya sendiri, mau ganti kartu
            if (card.isOurCard()) {
                if (card.isAt(Location.FIELD) && card.isType(CardType.CHARACTER)) {
                    this.firstCard = this.setCard(this.firstCard, card);
                }
                return;
            }

            // punya musuh, mau serang, boleh karakter boleh kosong (prekondisi: ga ada karakter lain di field)
            if (card.isAt(Location.FIELD) && !card.isSkill()) {
                this.secondCard = this.setCard(this.secondCard, card);
                this.triggerEvent(event, EventType.ATTACK);
            }
            return;
        }
        this.resetCards();
    }

    private SelectedCard setCard(SelectedCard cardFrom, SelectedCard cardTo) {
        if (!(cardFrom == this.empty || cardFrom.isType(CardType.EMPTY))) {
            cardFrom.getCard().setNotSelected();
        }
        if (!(cardTo == this.empty || cardTo.isType(CardType.EMPTY))) {
            cardTo.getCard().setSelected();
        }
        cardFrom = cardTo;
        return cardFrom;
    }

    public void setFirstCard(SelectedCard firstCard) {
        this.firstCard = firstCard;
    }

    public void setSecondCard(SelectedCard secondCard) {
        this.secondCard = secondCard;
    }

    public void resetCards() {
        this.firstCard = this.setCard(this.firstCard, this.empty);
        this.secondCard = this.setCard(this.secondCard, this.empty);
    }

    /**
     * @param event TODO
     * @param type
     */
    private void triggerEvent(MouseEvent event, EventType type) {
        this.firstCard.getCard().setNotSelected();
        this.secondCard.getCard().setNotSelected();
        GameEventHandler.getInstance().publish(event, type);
        this.resetCards();
    }

    private GameEventHandler() {
        this.subscriber = new HashMap<>();
    }

    static GameEventHandler getInstance() {
        if (singleton == null) {
            singleton = new GameEventHandler();
        }
        return singleton;
    }

    /**
     * @param subscriber TODO
     * @param type
     */
    public void subscribe(Subscriber subscriber, EventType type) {
        if (!this.subscriber.containsKey(type)) {
            this.subscriber.put(type, new ArrayList<>());
        }
        this.subscriber.get(type).add(subscriber);
    }

    /**
     * @param event TODO
     * @param type
     */
    public void publish(MouseEvent event, EventType type) {
        System.out.println("Event " + type + " triggered:");
        if(this.subscriber != null){
            this.subscriber.get(type).forEach(s -> s.onEvent(event, type, this.firstCard, this.secondCard));
        } else {
            System.out.println("No subscribers :(");
        }
    }
}
