package com.avatarduel.model;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PhaseController {

    @FXML
    private BoardController parent;

    @FXML
    private Text playerTurn;

    @FXML
    private Text turn;

    @FXML
    private Text phase;

    @FXML
    private Button next;

    @FXML
    public void initialize(){
        this.playerTurn.setText("Player 1");
        this.turn.setText("1");
        this.phase.setText("DRAW PHASE");
    }

    public void init(BoardController boardController){
        this.parent = boardController;
    }

    public BoardController getParent() {
        return parent;
    }
}

