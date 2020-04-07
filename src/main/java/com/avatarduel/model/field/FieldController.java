package com.avatarduel.model.field;

import com.avatarduel.exception.FieldCellIsOccupiedException;
import com.avatarduel.exception.HasSummonedLandThisTurnException;
import com.avatarduel.exception.NotEnoughPowerException;
import com.avatarduel.model.card.*;
import com.avatarduel.model.element.NoElement;
import com.avatarduel.model.phase.Phase;
import com.avatarduel.model.player.PlayerController;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Land;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import com.avatarduel.util.CSSLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class FieldController {

    private PlayerController parent;

    @FXML private GridPane root;
    private ArrayList<SummonableCard> cards;
    private static final Integer nrow = 2;
    private static final Integer ncol = 8;
    private CardController[][] cardControllers;

    @FXML
    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cards = new ArrayList<>();
        this.cardControllers = new CardController[nrow][ncol];
        for (Integer i = 0; i < FieldController.nrow; i++) {
            for (Integer j = 0; j < FieldController.ncol; j++) {
                CardController cc = new CardController();
                cc.setRoot((StackPane) ((Pane) this.root.getChildren().get(i * ncol + j)).getChildren().get(0));
                cc.setCard(EmptyCard.getInstance());
                this.cardControllers[i][j] = cc;
            }
        }
    }

    @FXML
    public void onClick(MouseEvent event) {
        Phase currentPhase = this.getCurrentPhase();
        if (this.isActivePlayer() && ((currentPhase.equals(Phase.MAIN1)) || currentPhase.equals(Phase.MAIN2))) {
            Card selectedCard = this.getParent().getHandController().getSelectedCard();
            try{
                this.summonCard(event, selectedCard);
                this.getParent().getHandController().removeCard(selectedCard);
            } catch (FieldCellIsOccupiedException e) {
                SummonableCard summonableCard = (SummonableCard) selectedCard;
                this.getParent().getInventory().addCurrentPower(summonableCard.getElement(), summonableCard.getPower());
            } catch (NotEnoughPowerException | NullPointerException | HasSummonedLandThisTurnException ignored){

            }
        } else if (currentPhase.equals(Phase.BATTLE)) {
            // this.selectedCard = currentCard;
        }
    }

    private void summonCard(MouseEvent event, Card selectedCard) throws NotEnoughPowerException, FieldCellIsOccupiedException, HasSummonedLandThisTurnException {
        if (selectedCard instanceof Land) {
            if(!this.getParent().getInventory().summonedLandThisTurn()){
                this.getParent().getInventory().addPowerCapacity(selectedCard.getElement());
            } else {
                throw new HasSummonedLandThisTurnException();
            }
        } else {
            SummonableCard summonableCard = (SummonableCard) selectedCard;
            this.getParent().getInventory().usePower(summonableCard);
            if (!this.setCardAtCursor(event, summonableCard)) {
                throw new FieldCellIsOccupiedException();
            }
        }
    }

    private Phase getCurrentPhase() {
        return this.getParent().getCurrentPhase();
    }

    private boolean isActivePlayer() {
        return this.getParent().isActivePlayer();
    }

    private boolean setCardAtCursor(MouseEvent event, Card card) {
        Integer j = (GridPane.getColumnIndex(this.cursorAtNode(event)));
        Integer i = (GridPane.getRowIndex(this.cursorAtNode(event)));
        if (this.canSummonCard(card, i, j)) {
            this.cardControllers[i][j].setCard(card);
            return true;
        } else {
            return false;
        }
    }

    private boolean canSummonCard(Card card, Integer i, Integer j) {
        i += this.getParent().isPlayerOne ? 0 : 1;
        i %= 2;
        return this.cardControllers[i][j].isEmpty() && (card instanceof Character && (i == 0)) || (card instanceof Skill && (i == 1) &&this.thereIsCharacter());
    }

    private Node cursorAtNode(MouseEvent event) {
        return (Node) event.getSource();
    }

    public void update() {
        this.cardControllers[0][1].setCard(CardDAO.get(2));
        for (int i = 0; i < FieldController.nrow; i++) {
            for (int j = 0; j < FieldController.ncol; j++) {
                this.cardControllers[i][j].setCard(this.getCard(i));
            }
        }
    }

    public boolean thereIsCharacter(){
        int i=0;
        for(int j=0; j< FieldController.ncol; j++){
            if(!this.cardControllers[i][j].isEmpty()){
                return true;
            }
        }
        return false;
    }

    private Card getCard(Integer index) {
        try {
            return this.cards.get(index);
        } catch (IndexOutOfBoundsException e) {
            return EmptyCard.getInstance();
        }
    }

    public void setColor(String color) {
        root.getChildren().forEach(node -> CSSLoader.addClass(node, color + "-field-cell"));
    }

    public PlayerController getParent() {
        return parent;
    }
}
