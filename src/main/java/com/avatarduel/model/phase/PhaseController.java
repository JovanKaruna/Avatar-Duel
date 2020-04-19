package com.avatarduel.model.phase;

import com.avatarduel.Settings;
import com.avatarduel.model.BoardController;
import com.avatarduel.model.GameInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PhaseController {
    private BoardController parent;

    @FXML private Text playerTurn;
    @FXML private Text turn;
    @FXML private Text phase;

    public void init(BoardController boardController) {
        this.parent = boardController;
        this.update();
    }

    // on Mouse Click
    @FXML
    public void nextPhase(ActionEvent event) {
        if ((GameInfo.isMainPhase()) && (this.getParent().getActivePlayer().getHandController().getCards().size() == Settings.maximumHandCard)) {
            this.getParent().setMessage("You must discard 1 card to proceed");
        } else {
            if (GameInfo.isBattlePhase()) {
                this.getParent().getActivePlayer().getFieldController().setAllHasNotAttacked();
            }
            this.nextPhase();
        }
    }

    /**
     * Change to next phase
     */
    public void nextPhase() {
        this.getParent().endPhase();
        GameInfo.nextPhase();
        this.getParent().getGameEventHandler().resetCards();
        if (GameInfo.isDrawPhase()) {
            this.nextTurn();
        }
        this.update();
    }

    private void nextTurn() {
        GameInfo.nextTurn();
        this.getParent().nextPlayer();
    }

    /**
     * Update layout (of Phase) at Pane (at top left down corner)
     */
    private void update() {
        this.playerTurn.setText("Player " + GameInfo.getPlayerTurn().toString());
        this.turn.setText(GameInfo.getTurn().toString());
        this.phase.setText(GameInfo.getPhase().toString() + " PHASE");
    }

    public BoardController getParent() {
        return parent;
    }
}

