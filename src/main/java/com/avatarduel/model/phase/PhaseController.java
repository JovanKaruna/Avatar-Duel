package com.avatarduel.model.phase;

import com.avatarduel.Settings;
import com.avatarduel.model.BoardController;
import com.avatarduel.model.GameInfo;

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

    // on Mouse Click
    @FXML
    public void nextPhase(ActionEvent event) {
        if ((GameInfo.isMainPhase()) && (this.getParent().getActivePlayer().getHandController().getCards().size() == Settings.maximumHandCard)) {
            System.out.println("You must discard 1 card from your hand!");
        }
        else {
            this.nextPhase();
        }
    }

    public void nextPhase() {
        this.getParent().endPhase();
        GameInfo.nextPhase();
        this.getParent().getGameEventHandler().resetCards();
        if (GameInfo.isDrawPhase()) {
            this.nextTurn();
        }
        this.update();
    }

    private void nextTurn(){
        GameInfo.nextTurn();
        this.getParent().nextPlayer();
    }

    public void init(BoardController boardController) {
        this.parent = boardController;
        this.update();
    }

    public BoardController getParent() {
        return parent;
    }

    public void update() {
        this.playerTurn.setText("Player " + GameInfo.getPlayerTurn().toString());
        this.turn.setText(GameInfo.getTurn().toString());
        this.phase.setText(GameInfo.getPhase().toString() + " PHASE");
    }
}

