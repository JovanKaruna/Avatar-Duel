package com.avatarduel.model.player.field;

import com.avatarduel.event.EventType;
import com.avatarduel.event.Subscriber;
import com.avatarduel.exception.*;
import com.avatarduel.model.GameEventHandler;
import com.avatarduel.model.GameInfo;
import com.avatarduel.model.Location;
import com.avatarduel.model.card.*;
import com.avatarduel.model.card.land.Land;
import com.avatarduel.model.card.summonable.*;
import com.avatarduel.model.card.summonable.character.Character;
import com.avatarduel.model.card.summonable.skill.Destroy;
import com.avatarduel.model.card.summonable.skill.Skill;
import com.avatarduel.model.player.PlayerController;
import com.avatarduel.util.Pair;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class FieldController implements Subscriber {

    private PlayerController parent;

    @FXML private GridPane root;

    private static final Integer nrow = 2;
    private static final Integer ncol = 6;
    private ArrayList<ArrayList<SummonedCardController>> cardControllers;
    private boolean summonedLandThisTurn;

    @FXML
    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cardControllers = new ArrayList<>();
        for (Integer i = 0; i < FieldController.nrow; i++) {
            this.cardControllers.add(new ArrayList<>());
            for (Integer j = 0; j < FieldController.ncol; j++) {
                this.cardControllers.get(i).add(new SummonedCardController());
                this.cardControllers.get(i).get(j).setRoot((StackPane) ((Pane) this.root.getChildren().get(i * ncol + j)).getChildren().get(0));
                this.cardControllers.get(i).get(j).setSummonedCard(SummonedEmptyCard.getInstance());
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
                System.out.println(this.getSummonedCard(i, j).getCard());
                this.getCardController(i, j).show(Location.FIELD);
            }
        }
    }

    @FXML
    public void onHoverEnter(MouseEvent event) {
        SummonedCard c = this.getCardAtCursor(event);
        if (c != null) {
            this.getParent().getParent().setActiveCard(c.getCard());
            if (c.getType().equals(CardType.CHARACTER)) {
                this.getParent().getParent().getCardDescController().setSkills(c);
            }
        }

    }

    @FXML
    public void onHoverExit(MouseEvent event) {
        this.getParent().getParent().setActiveCard(EmptyCard.getInstance());
        this.getParent().getParent().getCardDescController().clearSkill();
    }

    @FXML
    public void onClick(MouseEvent event) {
        if (!GameInfo.isEndPhase()) {
            if (this.isActivePlayer()) {
                if (GameInfo.isMainPhase()) {
                    if (!(this.getCardAtCursor(event).getCard() instanceof Skill && ((SummonedSkillCard) this.getSummonedCard(this.getCardAtCursor(event).getCard())).isAttached())) {
                        this.getGameEventHandler().selectCard(event, this.getCardAtCursor(event).getCard(), this.getParent().getId(), Location.FIELD);
                    }
                } else if (!this.getCardAtCursor(event).getCard().isEmpty()) {
                    if (((SummonedCharacterCard) this.getCardAtCursor(event)).isAttackStance()) {
                        this.getGameEventHandler().selectCard(event, this.getCardAtCursor(event).getCard(), this.getParent().getId(), Location.FIELD);
                    }
                }
            } else {
                // enemy's card
                // no character = can attack directly
                if (!this.thereIsCharacter()) {
                    this.getGameEventHandler().selectCard(event, this.getCardAtCursor(event).getCard(), this.getParent().getId(), Location.FIELD);
                } else {
                    // must attack character
                    Integer i = this.getIndexFromCursor(event).first();
                    if (i == 0 && !this.getCardAtCursor(event).getCard().isEmpty()) {
                        this.getGameEventHandler().selectCard(event, this.getCardAtCursor(event).getCard(), this.getParent().getId(), Location.FIELD);
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

    private void summonCard(MouseEvent event, Card selectedCard) throws NotEnoughPowerException, CannotSummonCardException {
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

            Integer i = this.correctRow(GridPane.getRowIndex(this.getNodeAtCursor(event)));
            Integer j = GridPane.getColumnIndex(this.getNodeAtCursor(event));

            SummonedCard summonedCard = new CardSummoner<>(summonCard).summon(this);
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

    private void attachSkill(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard) {
        FieldController field = secondCard.isOurCard() ? this : this.getParent().getParent().getOtherPlayer().getFieldController();
        ((SummonedCharacterCard) field.getSummonedCard(secondCard.getCard())).attachSkill((SummonedSkillCard) this.getSummonedCard(firstCard.getCard()));

        if (firstCard.getCard() instanceof Destroy) {
            this.getParent().getParent().getActivePlayer().getFieldController().onDiscardFieldSudden(firstCard, secondCard);
            this.getParent().getParent().getActivePlayer().getInventory().onDiscardFieldSudden(firstCard, secondCard);
            this.getParent().getParent().getOtherPlayer().getFieldController().onDiscardFieldSudden(secondCard, secondCard);
            this.getParent().getParent().getOtherPlayer().getInventory().onDiscardFieldSudden(secondCard, secondCard);
        }
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

    private Pair<Integer, Integer> getIndexFromCursor(MouseEvent event) {
        Integer i = this.correctRow(GridPane.getRowIndex(this.getNodeAtCursor(event)));
        Integer j = GridPane.getColumnIndex(this.getNodeAtCursor(event));
        return new Pair<>(i, j);
    }

    private SummonedCardController getCardController(Integer i, Integer j) {
        return this.cardControllers.get(this.correctRow(i)).get(j);
    }

    public SummonedCard getSummonedCard(Card card) {
        for (ArrayList<SummonedCardController> cardController : this.cardControllers) {
            for (SummonedCardController summonedCardController : cardController) {
                if (summonedCardController.getSummonedCard().getCard().equals(card)) {
                    return summonedCardController.getSummonedCard();
                }
            }
        }
        return SummonedEmptyCard.getInstance();
    }

    private SummonedCard getSummonedCard(Integer i, Integer j) {
        return this.getCardController(i, j).getSummonedCard();
    }

    private Node getNodeAtCursor(MouseEvent event) {
        return (Node) event.getSource();
    }

    private SummonedCard getCardAtCursor(MouseEvent event) {
        Pair<Integer, Integer> idx = this.getIndexFromCursor(event);
        return this.getSummonedCard(idx.first(), idx.second());
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
        for (ArrayList<SummonedCardController> card : this.cardControllers) {
            for (SummonedCardController summonedCard : card) {
                summonedCard.getCard().setNotSelected();
            }
        }
        this.update();
    }

    public void setColor(String color) {
        root.getChildren().forEach(node -> node.getStyleClass().add(color + "-field-cell"));
    }

    public PlayerController getParent() {
        return parent;
    }

    private GameEventHandler getGameEventHandler() {
        return this.getParent().getGameEventHandler();
    }

    private void removeCard(Card card) {
        for (ArrayList<SummonedCardController> cardController : this.cardControllers) {
            for (SummonedCardController summonedCardController : cardController) {
                if (summonedCardController.getSummonedCard().getCard().equals(card)) {
                    summonedCardController.setEmpty(Location.FIELD);
                    return;
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
            case DISCARDFIELD:
                this.onDiscardFieldEvent(firstCard, secondCard);
                break;
            case CHANGESTANCE:
                this.onChangeStanceEvent(firstCard);
                break;
            case ATTACK:
                this.onAttackEvent(event, firstCard, secondCard);
                break;
            case ATTACHSKILL:
                this.onAttachSkillEvent(event, firstCard, secondCard);
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

            } catch (CannotSummonCardException e) {
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
        if (GameInfo.isMainPhase() && this.isActivePlayer()) {
            this.removeCard(firstCard.getCard());
        } else if (GameInfo.isBattlePhase() && !this.isActivePlayer()) {
            this.removeCard(firstCard.getCard());
        }
    }

    public void onDiscardFieldSudden(SelectedCard firstCard, SelectedCard secondCard) {
        this.removeCard(firstCard.getCard());
    }

    private void onAttachSkillEvent(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard) {
        if (this.isActivePlayer()) {
            this.attachSkill(event, firstCard, secondCard);
        }
    }

    public void setAllHasNotAttacked() {
        for (ArrayList<SummonedCardController> card : this.cardControllers) {
            for (SummonedCardController summonedCard : card) {
                if (summonedCard.getSummonedCard() instanceof SummonedCharacterCard) {
                    ((SummonedCharacterCard) summonedCard.getSummonedCard()).changeToHasNotAttacked();
                }
            }
        }
    }

    private void onAttack(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard) throws JustSummonedException, JustAttackedException, NotStrongEnoughException {
        SummonedCharacterCard summonedFirstCard = (SummonedCharacterCard) this.getParent().getParent().getActivePlayer().getFieldController().getSummonedCard(firstCard.getCard());

        if (!summonedFirstCard.isSummonedThisTurn()) {
            if (!summonedFirstCard.isHasAttacked()) {
                if (this.thereIsCharacter()) { //In enemy's field
                    Integer attack = summonedFirstCard.getAttackValue();
                    SummonedCharacterCard summonedSecondCard = (SummonedCharacterCard) this.getSummonedCard(secondCard.getCard());

                    Integer defense = summonedSecondCard.getDefendValue();
                    if (attack > defense) {
                        summonedFirstCard.changeToHasAttacked();
                        // if first card dont have power up and second card defends -> no attack hp
                        if (summonedFirstCard.hasPowerUp() || summonedSecondCard.isAttackStance()) {
                            this.getGameEventHandler().publish(event, EventType.ATTACKHPSUCCESS);
                        }
                        this.getGameEventHandler().setFirstCard(secondCard);
                        this.getGameEventHandler().publish(event, EventType.DISCARDFIELD);
                        for (SummonedSkillCard summonedSkillCard : summonedSecondCard.getSupportCard()) {
                            this.getGameEventHandler().selectCard(event, summonedSkillCard.getCard(), this.getParent().getParent().getOtherPlayer().getId(), Location.FIELD);
                            this.removeCard(summonedSkillCard.getCard());
                            this.getGameEventHandler().publish(event, EventType.DISCARDFIELD);
                        }
                        this.removeCard(secondCard.getCard());
                    } else {
                        System.out.println(attack);
                        System.out.println(defense);
                        throw new NotStrongEnoughException();
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
    }

    private void onAttackEvent(MouseEvent event, SelectedCard firstCard, SelectedCard secondCard) {
        firstCard.getCard().setNotSelected();
        if (!this.isActivePlayer()) {
            try {
                this.onAttack(event, firstCard, secondCard);
                this.getParent().getParent().setMessage("HAHAHA");

            } catch (JustSummonedException | JustAttackedException | NotStrongEnoughException e) {
                this.getParent().getParent().setMessage(e.getMessage());
            }
        }
    }
}
