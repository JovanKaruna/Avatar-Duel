package com.avatarduel.model.phase;

import com.avatarduel.model.BoardController;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PhaseController {
    private BoardController parent;

    @FXML private Text playerTurn;
    @FXML private Text turn;
    @FXML private Text phase;
    @FXML private Button next;

    private Integer playerTurnValue;
    private Integer turnValue;
    private Phase phaseValue;

    // on Mouse Click
    @FXML
    public void nextPhase(ActionEvent event) {
        this.nextPhase();
    }

    public void nextPhase() {
        this.getParent().endPhase();
        this.phaseValue = this.phaseValue.next();
        if (this.phaseValue.equals(Phase.DRAW)) {
            this.nextTurn();
        }
        this.update();
    }

    private void nextTurn(){
        this.playerTurnValue %= 2;
        this.playerTurnValue++;
        if (this.playerTurnValue == 1) {
            this.turnValue++;
        }
        this.getParent().nextPlayer();
    }

    @FXML
    public void initialize() {
        this.playerTurnValue = 1;
        this.turnValue = 1;
        this.phaseValue = Phase.DRAW;
    }

    public void init(BoardController boardController) {
        this.parent = boardController;
        this.update();
    }

    public BoardController getParent() {
        return parent;
    }

    public void update() {
        this.playerTurn.setText("Player " + this.playerTurnValue.toString());
        this.turn.setText(this.turnValue.toString());
        this.phase.setText(this.phaseValue.toString() + " PHASE");
    }

    public Phase getPhaseValue(){
        return this.phaseValue;
    }
}

