package com.avatarduel.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PhaseController {

    @FXML
    private BoardController parent;

    @FXML
    private Text playerTurn;

    private Integer playerTurnValue;

    @FXML
    private Text turn;

    private Integer turnValue;

    @FXML
    private Text phase;

    private Phase phaseValue;

    @FXML
    private Button next;

    @FXML
    public void nextPhase(ActionEvent event){
        this.phaseValue = this.phaseValue.next();
        if(this.phaseValue.equals(Phase.DRAW)){
            this.playerTurnValue %= 2;
            this.playerTurnValue++;
            if(this.playerTurnValue == 1){
                this.turnValue++;
            }
            this.parent.nextPlayer();
        }
        this.update();
    }

    @FXML
    public void initialize(){
        this.playerTurnValue = 1;
        this.turnValue = 1;
        this.phaseValue = Phase.DRAW;
    }

    public void init(BoardController boardController){
        this.parent = boardController;
        this.update();
    }

    public BoardController getParent() {
        return parent;
    }

    public void update(){
        this.playerTurn.setText("Player " + this.playerTurnValue.toString());
        this.turn.setText(this.turnValue.toString());
        this.phase.setText(this.phaseValue.toString() + " PHASE");
    }


}

