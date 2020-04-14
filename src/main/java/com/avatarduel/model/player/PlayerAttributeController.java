package com.avatarduel.model.player;

import com.avatarduel.Settings;
import com.avatarduel.event.EventType;
import com.avatarduel.event.Subscriber;
import com.avatarduel.model.GameEventHandler;
import com.avatarduel.model.GameInfo;
import com.avatarduel.model.card.SelectedCard;
import com.avatarduel.model.card.summonable.character.Character;

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

        this.getGameEventHandler().subscribe(this, EventType.ATTACK);
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
        if (!this.isActivePlayer() && type == EventType.ATTACK) {
            this.onAttackEvent(firstCard, secondCard);
            this.update();
        }
    }

    private void onAttackEvent(SelectedCard firstCard, SelectedCard secondCard) {
        Integer attack = ((Character) firstCard.getCard()).getAttack();
        Integer defend = secondCard.getCard().isEmpty() ? 0 : ((Character) firstCard.getCard()).getAttack();
        if (attack > defend) {
            this.hpValue -= (attack - defend);
            this.update();
            if (this.hpValue < 0) {
                // TODO trigger defeat
            }
        }
    }

    private boolean isActivePlayer() {
        return GameInfo.getPlayerTurn().equals(this.parent.getId());
    }
}
