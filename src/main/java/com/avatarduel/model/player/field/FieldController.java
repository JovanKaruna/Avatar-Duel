package com.avatarduel.model.player.field;

import com.avatarduel.exception.*;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.card.EmptyCard;
import com.avatarduel.model.card.summonable.*;
import com.avatarduel.model.card.summonable.character.Character;
import com.avatarduel.model.card.summonable.skill.Destroy;
import com.avatarduel.model.card.summonable.skill.Skill;
import com.avatarduel.model.phase.Phase;
import com.avatarduel.model.player.PlayerController;

import com.avatarduel.model.card.Land;
import com.avatarduel.util.CSSLoader;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FieldController {

    private PlayerController parent;

    @FXML private GridPane root;
    private static final Integer nrow = 2;
    private static final Integer ncol = 8;
    private SummonedCard[][] cards;
    private CardController[] characterCardControllers;
    private CardController[] skillCardControllers;
    private SummonableCard selectedCard;

    @FXML
    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cards = new SummonedCard[nrow][ncol];
        this.characterCardControllers = new CardController[ncol];
        this.skillCardControllers = new CardController[ncol];
        int i = 0;
        for (Integer j = 0; j < FieldController.ncol; j++) {
            CardController cc = new CardController();
            cc.setRoot((StackPane) ((Pane) this.root.getChildren().get(i * ncol + j)).getChildren().get(0));
            this.characterCardControllers[j] = cc;
            try {
                this.cards[i][j] = new SummonedCharacterCard((Character) cc.getCard());
            } catch (ClassCastException e) {
                this.cards[i][j] = SummonedEmptyCard.getInstance();
            }
            cc.setCard(EmptyCard.getInstance());
        }
        i++;
        i %= 2;
        for (Integer j = 0; j < FieldController.ncol; j++) {
            CardController cc = new CardController();
            cc.setRoot((StackPane) ((Pane) this.root.getChildren().get(i * ncol + j)).getChildren().get(0));
            this.skillCardControllers[j] = cc;
            try {
                this.cards[i][j] = new SummonedSkillCard((Skill) cc.getCard());
            } catch (ClassCastException e) {
                this.cards[i][j] = SummonedEmptyCard.getInstance();
            }
            cc.setCard(EmptyCard.getInstance());
        }

    }

    @FXML
    public void onHoverEnter(MouseEvent event) {
        Card c = this.cursorAtCard(event);
        if (c != null) {
            this.getParent().getParent().setActiveCard(c);
        }
    }

    @FXML
    public void onClick(MouseEvent event) {
        if (this.isActivePlayer()) {
            Phase currentPhase = this.getCurrentPhase();
            Card selectedCard = this.getParent().getHandController().getSelectedCard();

            if ((currentPhase.equals(Phase.MAIN1)) || currentPhase.equals(Phase.MAIN2)) {
                try {
                    this.summonCard(event, selectedCard);
                    this.getParent().getHandController().removeCard(selectedCard);
                    this.update();
                    System.out.println("Success summon card" + selectedCard);

                } catch (FieldCellIsOccupiedException e) {
                    try{
                        SummonableCard summonableCard = (SummonableCard) selectedCard;
                        this.getParent().getInventory().addCurrentPower(summonableCard.getElement(), summonableCard.getPower());
                    } catch (Exception a) {
                        //do nothing
                    }
                    this.changeStance(event);
                    System.out.println("Success to change card stance");

                } catch (CannotSummonCardException e){
                    SummonableCard summonableCard = (SummonableCard) selectedCard;
                    this.getParent().getInventory().addCurrentPower(summonableCard.getElement(), summonableCard.getPower());
                    System.out.println(e.getLocalizedMessage());

                } catch (NotImplementedException | NotEnoughPowerException | NullPointerException e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println(e.getClass());
                }
            } else if (currentPhase.equals(Phase.BATTLE)) {
                this.selectedCard = (SummonableCard) selectedCard;
                System.out.println("Card selected for attacking");
            }
        }
    }

    private void changeStance(MouseEvent event) throws ClassCastException {
        ((SummonedCharacterCard) this.getCardAtCursor(event)).changeStance();
        this.update();
    }

    public void update() {
        for (int i = 0; i < FieldController.nrow; i++) {
            for (int j = 0; j < FieldController.ncol; j++) {
                this.getCardController(i, j).update();
            }
        }
    }

    private void summonCard(MouseEvent event, Card selectedCard) throws CannotSummonCardException, NotEnoughPowerException, NotImplementedException {
        if (selectedCard instanceof Land) {
            if (!this.getParent().getInventory().summonedLandThisTurn()) {
                this.getParent().getInventory().addPowerCapacity(selectedCard.getElement());
            } else {
                throw new AlreadySummonedLand();
            }
        } else {
            this.getParent().getInventory().usePower((SummonableCard) selectedCard);

            Integer i = (GridPane.getRowIndex(this.cursorAtNode(event)));
            Integer j = (GridPane.getColumnIndex(this.cursorAtNode(event)));
            i = correctRow(i);
            if (this.isEmpty(i, j)) {
                if (this.isRightRow(i, selectedCard)) {
                    try {
                        // mau summon destroy
                        Destroy skill = (Destroy) selectedCard;
                        throw new NotImplementedException();
                    } catch (ClassCastException e) {
                        // mau summon skill
                        try {
                            Skill skill = (Skill) selectedCard;
                            if (!this.thereIsCharacter()) {
                                throw new NoCharaterOnFieldException();
                            }
                            this.setCard(i, j, (SummonableCard) selectedCard);
                        } catch (ClassCastException f) {
                            // mau summon character
                            this.setCard(i, j, (SummonableCard) selectedCard);
                        }
                    }
                } else {
                    throw new WrongRowException();
                }
            } else {
                throw new FieldCellIsOccupiedException();
            }
        }
    }

    private boolean isRightRow(Integer i, Card c) {
        try {
            Character ch = (Character) c;
            return i == 0;
        } catch (ClassCastException ignored) {
            try {
                Skill skill = (Skill) c;
                return i == 1;
            } catch (ClassCastException ignored2) {
                return false;
            }
        }
    }

    private Integer correctRow(Integer i) {
        i += this.getParent().isPlayerOne ? 0 : 1;
        i %= 2;
        return i;
    }


    private boolean isEmpty(Integer i, Integer j) {
        return this.getCardController(i, j).isEmpty();
    }

    private void setCard(Integer i, Integer j, SummonableCard summonedCard) {
        SummonableCard tmp = summonedCard;
        try {
            this.cards[i][j] = new SummonedCharacterCard((Character) tmp);
        } catch (ClassCastException e) {
            this.cards[i][j] = new SummonedSkillCard((Skill) tmp);
        }
        this.getCardController(i, j).setCard(tmp);
    }

    private Phase getCurrentPhase() {
        return this.getParent().getCurrentPhase();
    }

    private boolean isActivePlayer() {
        return this.getParent().isActivePlayer();
    }

    private CardController getControllerAtCursor(MouseEvent event) {
        Integer j = (GridPane.getColumnIndex(this.cursorAtNode(event)));
        Integer i = (GridPane.getRowIndex(this.cursorAtNode(event)));
        i = this.correctRow(i);
        return this.getCardController(i, j);
    }

    private SummonedCard getCardAtCursor(MouseEvent event) {
        Integer j = (GridPane.getColumnIndex(this.cursorAtNode(event)));
        Integer i = (GridPane.getRowIndex(this.cursorAtNode(event)));
        i = this.correctRow(i);
        return this.cards[i][j];
    }

    private CardController getCardController(Integer i, Integer j) {
        i = this.correctRow(i);
        return i == 0 ? this.characterCardControllers[j] : this.skillCardControllers[j];
    }

    private Node cursorAtNode(MouseEvent event) {
        return (Node) event.getSource();
    }

    private Card cursorAtCard(MouseEvent event) {
        int i = GridPane.getRowIndex(this.cursorAtNode(event));
        int j = GridPane.getColumnIndex(this.cursorAtNode(event));
        i = correctRow(i);
        return this.getCardController(i, j).getCard();
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

    public void endTurn(){
        for (int i = 0; i < ncol; i++) {
            ((SummonedCharacterCard)this.cards[0][i]).setPlacedOnThisTurn(false);
        }
    }

    public void setColor(String color) {
        root.getChildren().forEach(node -> CSSLoader.addClass(node, color + "-field-cell"));
    }

    public PlayerController getParent() {
        return parent;
    }

    public Card getSelectedCard() {
        return this.selectedCard;
    }

//    public void removeCard(Card c) {
//        this.getController(this.selectedCard).unlift();
//        this.selectedCard = null;
//        this.cards.remove(c);
//        this.update();
//    }
//
//    private CardController getController(Card c) {
//        for (int i = 0; i < this.cards.length; i++) {
//            for (int j = 0; j < this.cards[i].length; j++) {
//                if (this.getCard(i, j) == c) {
//                    return this.cardControllers.get(i);
//                }
//            }
//        }
//        return null;
//    }
//
//    private Card getCard(Integer i, Integer j) {
//        try {
//            return this.cards[i][j];
//        } catch (IndexOutOfBoundsException e) {
//            return EmptyCard.getInstance();
//        }
//    }
}
