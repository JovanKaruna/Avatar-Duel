package com.avatarduel.event;

import com.avatarduel.model.GameInfo;
import com.avatarduel.model.Location;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.SelectedCard;
import com.avatarduel.model.card.summonable.skill.Skill;

public final class SelectedCardDAO {
    private final SelectedCard empty = SelectedCard.getEmpty();
    private SelectedCard firstCard = this.empty;
    private SelectedCard secondCard = this.empty;

    /**
     * @param inputCard : card that wanted to be selected
     * @param ownerid   : ownership of inputCard
     * @param location  : location of inputCard
     */
    public void selectCard(Card inputCard, Integer ownerid, Location location) {
        SelectedCard card = new SelectedCard(inputCard, ownerid, location);

        if (GameInfo.isMainPhase()) {
            if (this.firstCard == this.empty) {
                if (GameInfo.getPlayerTurn() == card.getOwnerId()) {
                    if (card.isAt(Location.HAND)) {        // HAND
                        this.firstCard = this.setCard(this.firstCard, card);

                    } else if (card.isAt(Location.FIELD)) { // FIELD
                        if (card.isType(CardType.AURA) || card.isType(CardType.DESTROY) || card.isType(CardType.POWERUP)) {
                            this.firstCard = this.setCard(this.firstCard, card);

                        } else if (card.isType(CardType.CHARACTER)) {
                            this.firstCard = this.setCard(this.firstCard, card);
                            this.triggerEvent();
                            this.resetCards();                // ubah stance card
                        }
                    }
                }

            } else /* this.firstCard != this.empty */ {
                if (this.firstCard.equals(card)) {               // kartunya sama
                    this.firstCard = this.setCard(this.firstCard, this.empty);

                } else /* !this.firstCard.equals(card) */ {
                    if (GameInfo.getPlayerTurn() == card.getOwnerId()) {
                        if (card.isAt(Location.HAND)) {              // HAND
                            this.firstCard = this.setCard(this.firstCard, card);

                        } else if (card.isAt(Location.FIELD)) {      // FIELD
                            if (this.firstCard.isAt(Location.HAND)) {
                                if(this.firstCard.isType(CardType.CHARACTER) && card.isType(CardType.EMPTY)){
                                    this.secondCard = this.setCard(this.secondCard, card);

                                }else if ((this.firstCard.isType(CardType.AURA) || this.firstCard.isType(CardType.DESTROY) || this.firstCard.isType(CardType.POWERUP)) && card.isType(CardType.EMPTY)) {
                                    this.secondCard = this.setCard(this.secondCard, card);

                                }else if (this.firstCard.isType(CardType.LAND)) {
                                    this.secondCard = this.setCard(this.secondCard, card);
                                }

                            } else /* !this.firstCard.isAt(HAND) atau arti lain FIELD */ {
                                // firstCard sudah pasti kartu SKILL
                                if ((card.isType(CardType.AURA) || card.isType(CardType.DESTROY) || card.isType(CardType.POWERUP))) {
                                    this.firstCard = this.setCard(this.firstCard, card);
                                } else if (card.isType(CardType.CHARACTER)) {
                                    this.secondCard = this.setCard(this.secondCard, card);
                                }
                            }

                        } else if (card.isAt(Location.GRAVEYARD)) {        // GRAVEYARD
                            if (this.firstCard.isType(CardType.AURA) || this.firstCard.isType(CardType.DESTROY) || this.firstCard.isType(CardType.POWERUP)) {
                                this.secondCard = this.setCard(this.secondCard, card);

                            } else if (card.isType(CardType.CHARACTER)) {
                                System.out.println("Cannot discard other than skill");
                            }
                        }
                    } else /* GameInfo.getPlayerTurn() != card.getOwnerId() */ {
                        if (card.isAt(Location.HAND)) {
                            System.out.println("Ini kartu lawan"); //asal-asalan comment

                        } else if (card.isAt(Location.FIELD)) {
                            if (this.firstCard.isAt(Location.HAND)) {
                                System.out.println("Belum ditaruh ke field");

                            } else if (this.firstCard.isAt(Location.FIELD)) {
                                if (this.firstCard.isType(CardType.AURA) || this.firstCard.isType(CardType.DESTROY) || this.firstCard.isType(CardType.POWERUP)) {
                                    this.secondCard = this.setCard(this.secondCard, card);

                                } else if (this.firstCard.isType(CardType.CHARACTER)) {
                                    System.out.println("Cannot be used other than skill");
                                }
                            }
                        }
                        //GRAVEYARD nothing happens
                    }
                }
            }

        } else if (GameInfo.isBattlePhase()) { /* BATTLE */
            if (this.firstCard == this.empty) {
                if (GameInfo.getPlayerTurn() == card.getOwnerId()) {
                    if (card.isAt(Location.FIELD) && card.isType(CardType.CHARACTER)) { // FIELD
                        this.firstCard = this.setCard(this.firstCard, card);
                    }
                } // di tempat player lain tidak ngapa-ngapain

            } else /* this.firstCard != this.empty */ {
                if (GameInfo.getPlayerTurn() == card.getOwnerId()){
                    if(this.firstCard.equals(card)){ // kartunya sama
                        this.firstCard = this.setCard(this.firstCard, this.empty);
                    }else if (card.isAt(Location.FIELD) && card.isType(CardType.CHARACTER)) { // FIELD
                        this.firstCard = this.setCard(this.firstCard, card);
                    }
                } else /*if (GameInfo.getPlayerTurn() != card.getOwnerId())*/ { //Card punya player lain
                    if (card.isAt(Location.FIELD) && card.isType(CardType.CHARACTER)) { // FIELD
                        this.secondCard = this.setCard(this.secondCard, card);
                    }else if(card.isAt(Location.FIELD) && card.isType(CardType.EMPTY))/* && FIELD PREKONDISI: musuh gk boleh punya kartu char) */ {
                        this.secondCard = this.setCard(this.secondCard, card);
                    }
                }
            }
        }

        if (this.secondCard != this.empty) {
            this.triggerEvent();
            this.resetCards();
        }
    }

    private SelectedCard setCard(SelectedCard cardFrom, SelectedCard cardTo) {
        System.out.println("From:\n" + cardFrom + "\n");
        System.out.println("To:\n" + cardTo + "\n");
        if (cardFrom == this.empty && cardTo != this.empty) {
            cardFrom = cardTo;
            cardFrom.getCard().setSelected();
        } else if (cardFrom != this.empty && cardTo == this.empty) {
            cardFrom.getCard().setNotSelected();
            cardFrom = cardTo;
        } else if (cardFrom != this.empty && cardTo != this.empty) {
            cardFrom.getCard().setNotSelected();
            cardFrom = cardTo;
            cardFrom.getCard().setSelected();
        }
        return cardFrom;
    }

    public SelectedCard getFirstCard() {
        return firstCard;
    }

    public SelectedCard getSecondCard() {
        return secondCard;
    }

    public void resetCards() {
        this.firstCard = this.empty;
        this.secondCard = this.empty;
    }

    private void triggerEvent() {
        if (GameInfo.isMainPhase()) {
            if (this.firstCard.isAt(Location.HAND) && this.secondCard.isAt(Location.FIELD)) {
                // summon cards
                GameEventHandler.getInstance().publish(EventType.SUMMON);
            } else if (this.firstCard.isAt(Location.FIELD) && this.secondCard.isAt(Location.FIELD)) {
                // attach skill
                GameEventHandler.getInstance().publish(EventType.ATTACHSKILL);
            } else if (this.firstCard.isAt(Location.HAND) && this.secondCard.isAt(Location.GRAVEYARD)) {
                // discard hand
                GameEventHandler.getInstance().publish(EventType.DISCARDHAND);
            } else if (this.firstCard.isAt(Location.FIELD) && this.secondCard.isAt(Location.GRAVEYARD)){
                // discard field
                GameEventHandler.getInstance().publish(EventType.DISCARDFIELD);
            } else if (this.firstCard.isAt(Location.FIELD) && this.secondCard.isType(CardType.EMPTY)) {
                // change stance
                GameEventHandler.getInstance().publish(EventType.CHANGESTANCE);
            }

        } else if (GameInfo.isBattlePhase()) {
            if (this.secondCard.isType(CardType.CHARACTER)) {
                // attack card
                GameEventHandler.getInstance().publish(EventType.ATTACKCARD);
            } else if (this.secondCard.isType(CardType.EMPTY)) {
                // attack enemy
                GameEventHandler.getInstance().publish(EventType.ATTACKENEMY);
            }
        }
    }
}
