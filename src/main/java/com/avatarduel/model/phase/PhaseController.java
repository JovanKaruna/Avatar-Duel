package com.avatarduel.model.phase;

import com.avatarduel.Settings;
import com.avatarduel.event.EventType;
import com.avatarduel.event.Subscriber;
import com.avatarduel.model.BoardController;
import com.avatarduel.model.GameInfo;
import com.avatarduel.model.card.SelectedCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PhaseController implements Subscriber {
    private BoardController parent;

    @FXML private Text playerTurn;
    @FXML private Text turn;
    @FXML private Text phase;

    public void init(BoardController boardController) {
        this.parent = boardController;
        this.parent.getGameEventHandler().subscribe(this, EventType.SUCCESSNEXTPHASE);
        this.update();
    }

    // on Mouse Click
    @FXML
    public void nextPhase(ActionEvent event) {
        if ((GameInfo.isMainPhase()) && (this.getParent().getActivePlayer().getHandController().getCards().size() == Settings.maximumHandCard)) {
            this.getParent().setMessage("You must discard 1 card to proceed");
        } else {
            this.parent.getGameEventHandler().publish(null, EventType.NEXTPHASE);
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

    @Override
    public void onEvent(MouseEvent event, EventType type, SelectedCard firstCard, SelectedCard secondCard) {
        if(type.equals(EventType.SUCCESSNEXTPHASE)){
            this.nextPhase();
        }
    }
}

