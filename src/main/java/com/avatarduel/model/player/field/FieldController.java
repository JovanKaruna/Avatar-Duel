package com.avatarduel.model.player.field;

import com.avatarduel.model.GameEventHandler;
import com.avatarduel.event.*;
import com.avatarduel.exception.*;

import com.avatarduel.model.GameInfo;
import com.avatarduel.model.Location;
import com.avatarduel.model.card.*;
import com.avatarduel.model.card.land.Land;
import com.avatarduel.model.card.summonable.*;
import com.avatarduel.model.card.summonable.character.Character;
import com.avatarduel.model.card.summonable.skill.Skill;

import com.avatarduel.model.player.PlayerController;
import com.avatarduel.util.CSSLoader;
import com.avatarduel.util.Pair;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FieldController implements Subscriber {

    private PlayerController parent;

    @FXML private GridPane root;

    private static final Integer nrow = 2;
    private static final Integer ncol = 6;
    private SummonedCardController[][] cardControllers;
    private boolean summonedLandThisTurn;

    @FXML
    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cardControllers = new SummonedCardController[nrow][ncol];
        for (Integer i = 0; i < FieldController.nrow; i++) {
            for (Integer j = 0; j < FieldController.ncol; j++) {
                this.cardControllers[i][j] = new SummonedCardController();
                this.cardControllers[i][j].setRoot((StackPane) ((Pane) this.root.getChildren().get(i * ncol + j)).getChildren().get(0));
                this.cardControllers[i][j].setSummonedCard(SummonedEmptyCard.getInstance());
            }
        }
        this.summonedLandThisTurn = false;
        this.update();

        this.getGameEventHandler().subscribe(this, EventType.DISCARDFIELD);
        this.getGameEventHandler().subscribe(this, EventType.SUMMON);
        this.getGameEventHandler().subscribe(this, EventType.ATTACK);
        this.getGameEventHandler().subscribe(this, EventType.CHANGESTANCE);
        this.getGameEventHandler().subscribe(this, EventType.ATTACHSKILL);
    }

    public void update() {
        for (int i = 0; i < FieldController.nrow; i++) {
            for (int j = 0; j < FieldController.ncol; j++) {
                System.out.println(this.getCardController(i, j).getSummonedCard().getCard());
                this.getCardController(i, j).show(Location.FIELD);
            }
        }
    }

    @FXML
    public void onHoverEnter(MouseEvent event) {
        SummonedCard c = this.cursorAtCard(event);
        if (c != null) {
            this.getParent().getParent().setActiveCard(c.getCard());
            if(c.getType().equals(CardType.CHARACTER)){
                this.getParent().getParent().getCardDescController().setSkills(c);
            }
        }

    }
    @FXML
    public void onHoverExit(MouseEvent event){
        this.getParent().getParent().setActiveCard(EmptyCard.getInstance());
        this.getParent().getParent().getCardDescController().clearSkill();
    }

    @FXML
    public void onClick(MouseEvent event) {
        if (!GameInfo.isEndPhase()) {
            if (this.isActivePlayer()) {
                if (GameInfo.isMainPhase()) {
                    if (!(this.cursorAtCard(event).getCard() instanceof Skill && ((SummonedSkillCard) this.getSummonedCard(this.cursorAtCard(event).getCard())).isAttached())) {
                        this.getGameEventHandler().selectCard(event, this.cursorAtCard(event).getCard(), this.getParent().getId(), Location.FIELD);
                    }
                } else if (!this.cursorAtCard(event).getCard().isEmpty()) {
                    if (((SummonedCharacterCard) this.cursorAtCard(event)).isAttackStance()) {
                        this.getGameEventHandler().selectCard(event, this.cursorAtCard(event).getCard(), this.getParent().getId(), Location.FIELD);
                    }
                }
            } else {
                // enemy's card
                // no character = can attack directly
                if (!this.thereIsCharacter()) {
                    this.getGameEventHandler().selectCard(event, this.cursorAtCard(event).getCard(), this.getParent().getId(), Location.FIELD);
                } else {
                    // must attack character
                    Integer i = this.correctRow(this.getIndexFromCursor(event).first());
                    if (i == 0 && !this.cursorAtCard(event).getCard().isEmpty()) {
                        this.getGameEventHandler().selectCard(event, this.cursorAtCard(event).getCard(), this.getParent().getId(), Location.FIELD);
                    }
                }
            }
        }
        this.getControllerAtCursor(event).show(Location.FIELD);
    }

    private void changeStance(SelectedCard firstCard) throws ClassCastException {
        ((SummonedCharacterCard) this.getSummonedCard(firstCard.getCard())).changeStance();
        this.update();
    }

    private void summonCard(MouseEvent event, Card selectedCard) throws NotEnoughPowerException, NotImplementedException, CannotSummonCardException {
        if (selectedCard instanceof Land) {
            if (!this.summonedLandThisTurn) {
                this.getParent().getInventory().addPowerCapacity(selectedCard.getElement());
                this.summonedLandThisTurn = true;
            } else {
                throw new AlreadySummonedLand();
            }
        } else {
            SummonableCard summonCard = (SummonableCard) selectedCard;
            this.getParent().getInventory().usePower(summonCard);

            Integer i = this.correctRow(GridPane.getRowIndex(this.cursorAtNode(event)));
            Integer j = GridPane.getColumnIndex(this.cursorAtNode(event));

            SummonedCard summonedCard = new CardSummoner<>(summonCard).summon(this, i, j);
            if (this.isEmpty(i, j)) {
                if (this.isRightRow(i, summonedCard)) {
                    this.getCardController(i, j).setSummonedCard(summonedCard);
                } else {
                    throw new WrongRowException();
                }
            } else {
                throw new FieldCellIsOccupiedException();
            }
        }
    }

    private void attachSkill(SelectedCard firstCard, SelectedCard secondCard) {
        FieldController field = secondCard.isOurCard() ? this : this.getParent().getParent().getOtherPlayer().getFieldController();
        ((SummonedCharacterCard) field.getSummonedCard(secondCard.getCard())).attachSkill((SummonedSkillCard) this.getSummonedCard(firstCard.getCard()));
    }

    private SummonedCard getSummonedCard(Card card) {
        for (SummonedCardController[] summonedCards : this.cardControllers) {
            for (SummonedCardController summonedCard : summonedCards) {
                if (summonedCard.getCard().equals(card)) {
                    return summonedCard.getSummonedCard();
                }
            }
        }
        assert false;
        return null;
    }

    private boolean isRightRow(Integer i, SummonedCard card) {
        Card c = card.getCard();
        if (c instanceof Character) {
            return i == 0;
        } else if (c instanceof Skill) {
            return i == 1;
        } else {
            return true;
        }
    }

    private Integer correctRow(Integer i) {
        i += this.getParent().isPlayerOne ? 0 : 1;
        return i % 2;
    }


    private boolean isEmpty(Integer i, Integer j) {
        return this.getCardController(i, j).isEmpty();
    }

    private boolean isActivePlayer() {
        return this.getParent().isActivePlayer();
    }

    private CardController getControllerAtCursor(MouseEvent event) {
        Pair<Integer, Integer> idx = this.getIndexFromCursor(event);
        return this.getCardController(idx.first(), idx.second());
    }

    private SummonedCard getCardAtCursor(MouseEvent event) {
        Pair<Integer, Integer> idx = this.getIndexFromCursor(event);
        return this.getCardController(idx.first(), idx.second()).getSummonedCard();
    }

    private Pair<Integer, Integer> getIndexFromCursor(MouseEvent event) {
        Integer i = this.correctRow(GridPane.getRowIndex(this.cursorAtNode(event)));
        Integer j = GridPane.getColumnIndex(this.cursorAtNode(event));
        return new Pair<>(i, j);
    }

    private SummonedCardController getCardController(Integer i, Integer j) {
        return this.cardControllers[this.correctRow(i)][j];
    }

    private Node cursorAtNode(MouseEvent event) {
        return (Node) event.getSource();
    }

    private SummonedCard cursorAtCard(MouseEvent event) {
        int i = GridPane.getRowIndex(this.cursorAtNode(event));
        int j = GridPane.getColumnIndex(this.cursorAtNode(event));
        i = correctRow(i);
        return this.getCardController(i, j).getSummonedCard();
    }

    public boolean thereIsCharacter() {
        int i = 0;
        for (int j = 0; j < FieldController.ncol; j++) {
            if (!this.getCardController(i, j).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void endTurn() {
        this.summonedLandThisTurn = false;
        for (SummonedCardController[] card : this.cardControllers) {
            for (SummonedCardController summonedCard : card) {
                summonedCard.getCard().setNotSelected();
            }
        }
        this.update();
    }

    public void setColor(String color) {
        root.getChildren().forEach(node -> CSSLoader.addClass(node, color + "-field-cell"));
    }

    public PlayerController getParent() {
        return parent;
    }

    private GameEventHandler getGameEventHandler() {
        return this.getParent().getGameEventHandler();
    }

    private void removeCard(Card card) {
        for (int i = 0; i < this.cardControllers.length; i++) {
            CardController[] ccs = this.cardControllers[i];
            for (int j = 0; j < ccs.length; j++) {
                if (ccs[j].getCard() == card) {
                    ccs[j].setEmpty(Location.FIELD);
                    this.getCardController(i, j).setSummonedCard(SummonedEmptyCard.getInstance());
                }
            }
        }
    }

    @Override
    public void onEvent(MouseEvent event, EventType type, SelectedCard firstCard, SelectedCard secondCard) {
        switch (type) {
            case SUMMON:
                this.onSummonEvent(event, firstCard, secondCard);
                break;
            case DISCARDHAND:
                this.onDiscardFieldEvent(firstCard, secondCard);
                break;
            case CHANGESTANCE:
                this.onChangeStanceEvent(firstCard);
                break;
            case ATTACK:
                this.onAttackEvent(event, firstCard, secondCard);
                break;
            case ATTACHSKILL:
                this.onAttachSkillEvent(firstCard, secondCard);
                break;
        }
        firstCard.getCard().setNotSelected();
        secondCard.getCard().setNotSelected();
        this.update();
    }

    private void onSummonEvent(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard) {
        if (this.isActivePlayer()) {
            try {
                this.summonCard(event, firstCard.getCard());
                this.getGameEventHandler().publish(event, EventType.SUMMONSUCCESS);

            } catch (NotEnoughPowerException e) {
                this.getGameEventHandler().publish(event, EventType.SUMMONFAIL);
                this.getParent().getParent().setMessage(e.getMessage());

            } catch (AlreadySummonedLand e) {
                firstCard.getCard().setNotSelected();
                this.getGameEventHandler().publish(event, EventType.ALREADYSUMMONEDLAND);
                this.getParent().getParent().setMessage(e.getMessage());

            } catch (NotImplementedException | CannotSummonCardException e) {
                firstCard.getCard().setNotSelected();
                this.getGameEventHandler().publish(event, EventType.SUMMONFAILBUTENOUGHPOWER);
                this.getParent().getParent().setMessage(e.getMessage());
            }
        }
    }

    private void onChangeStanceEvent(SelectedCard firstCard) {
        if (this.isActivePlayer()) {
            this.changeStance(firstCard);
        }
    }

    private void onDiscardFieldEvent(SelectedCard firstCard, SelectedCard secondCard) {
        if (this.isActivePlayer()) {
            this.removeCard(firstCard.getCard());
        }
    }

    public void onAttachSkillEvent(SelectedCard firstCard, SelectedCard secondCard) {
        if (this.isActivePlayer()) {
            this.attachSkill(firstCard, secondCard);
        }
    }

    public void setAllHasNotAttacked() {
//        for (SummonedCardController[] card : this.cardControllers) {
//            for (SummonedCardController summonedCard : card) {
//                if (summonedCard.getSummonedCard() instanceof SummonedCharacterCard) {
//                    ((SummonedCharacterCard) summonedCard.getSummonedCard()).changeToHasNotAttacked();
//                }
//            }
//        }
        for (SummonedCardController[] card : this.cardControllers) {
            for (SummonedCardController summonedCard : card) {
                if (summonedCard.getSummonedCard() instanceof SummonedCharacterCard) {
                    ((SummonedCharacterCard) summonedCard.getSummonedCard()).changeToHasNotAttacked();
                }
            }
        }
    }

    private void onAttack(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard) throws NotInAttackStanceException, JustSummonedException, JustAttackedException, CannotAttackEmptyException, NotEnoughStrongException{
        SummonedCharacterCard summonedFirstCard = (SummonedCharacterCard) this.getParent().getParent().getActivePlayer().getFieldController().getSummonedCard(firstCard.getCard());

        if ((summonedFirstCard.isAttackStance())) {
            if (!summonedFirstCard.isSummonedThisTurn()) {
                if (!summonedFirstCard.isHasAttacked()) {
                    if (thereIsCharacter()) { //In enemy's field
                        if (!secondCard.getCard().isEmpty()) { //GameEventHandler has already set to be empty or character
                            Integer attack = summonedFirstCard.getAttackValue();
                            SummonedCharacterCard summonedSecondCard = (SummonedCharacterCard) this.getSummonedCard(secondCard.getCard());

                            if (summonedSecondCard.isAttackStance()) {
                                Integer defense = summonedSecondCard.getAttackValue();
                                if (attack >= defense) {

                                    summonedFirstCard.changeToHasAttacked();
                                    this.getGameEventHandler().setFirstCard(secondCard);
                                    this.getGameEventHandler().publish(event, EventType.DISCARDFIELD);
                                    this.getGameEventHandler().publish(event, EventType.ATTACKHPSUCCESS);
                                } else {
                                    throw new NotEnoughStrongException();
                                }
                            } else {
                                Integer defense = summonedSecondCard.getDefendValue();
                                if (attack >= defense) {
                                    summonedFirstCard.changeToHasAttacked();
                                    this.getGameEventHandler().setFirstCard(secondCard);
                                    this.getGameEventHandler().publish(event, EventType.DISCARDFIELD);
                                } else {
                                    throw new NotEnoughStrongException();
                                }
                            }
                        } else {
                            throw new CannotAttackEmptyException();
                        }
                    } else {
                        summonedFirstCard.changeToHasAttacked();
                        this.getGameEventHandler().publish(event, EventType.ATTACKHPSUCCESS);
                    }
                } else {
                    throw new JustAttackedException();
                }
            } else {
                throw new JustSummonedException();
            }
        } else {
            throw new NotInAttackStanceException();
        }
    }

    private void onAttackEvent(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard) {
        firstCard.getCard().setNotSelected();
        if (!this.isActivePlayer()) {
            try {
                this.onAttack(event, firstCard, secondCard);

            } catch (NotInAttackStanceException e) {
                this.getParent().getParent().setMessage(e.getMessage());

            } catch (JustSummonedException e) {
                this.getParent().getParent().setMessage(e.getMessage());

            } catch (JustAttackedException e) {
                this.getParent().getParent().setMessage(e.getMessage());

            } catch (CannotAttackEmptyException e) {
                this.getParent().getParent().setMessage(e.getMessage());

            } catch (NotEnoughStrongException e) {
                this.getParent().getParent().setMessage(e.getMessage());
            }
        }
    }
}
