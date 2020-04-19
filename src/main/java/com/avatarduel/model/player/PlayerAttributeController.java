package com.avatarduel.model.player;

import com.avatarduel.Settings;
import com.avatarduel.event.EventType;
import com.avatarduel.event.Subscriber;
import com.avatarduel.model.GameEventHandler;
import com.avatarduel.model.GameInfo;
import com.avatarduel.model.card.SelectedCard;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PlayerAttributeController implements Subscriber {

    private PlayerController parent;

    @FXML private ProgressBar healthBar;
    @FXML private Text hp;
    @FXML private Text name;

    private Integer hpValue;
    private String nameValue;

    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.hpValue = Settings.startingHealthAmount;
        this.update();

        this.getGameEventHandler().subscribe(this, EventType.ATTACKHPSUCCESS);
    }

    private GameEventHandler getGameEventHandler() {
        return this.parent.getGameEventHandler();
    }

    void update() {
        this.hp.setText(this.hpValue.toString());
        this.healthBar.setProgress(this.hpValue.floatValue() / Settings.startingHealthAmount);
    }

    public void setName(String nameValue) {
        this.nameValue = nameValue;
        this.name.setText(this.nameValue);
    }

    @Override
    public void onEvent(MouseEvent event, EventType type, SelectedCard firstCard, SelectedCard secondCard) {
        if (!this.isActivePlayer() && type.equals(EventType.ATTACKHPSUCCESS)) {
            this.onAttackHpSuccessEvent(firstCard, secondCard);
            this.update();
        }
    }

    private void onAttackHpSuccessEvent(SelectedCard firstCard, SelectedCard secondCard) {
        Integer attack = this.parent.getParent().getActivePlayer().getFieldController().getSummonedCard(firstCard.getCard()).getAttackValue();
        Integer defend = secondCard.getCard().isEmpty() ? 0 : this.parent.getParent().getOtherPlayer().getFieldController().getSummonedCard(secondCard.getCard()).getDefendValue();

        this.hpValue -= (attack - defend);
        this.update();
        if (this.hpValue < 0) {
            this.parent.getParent().gameOver(GameInfo.getPlayerTurn());
            this.parent.getParent().setMessage("YOU WIN");
        }
    }

    private boolean isActivePlayer() {
        return GameInfo.getPlayerTurn().equals(this.parent.getId());
    }
}
