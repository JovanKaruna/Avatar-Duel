package com.avatarduel.model.player;

import com.avatarduel.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class PlayerAttributeController {

    private PlayerController parent;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private Text hp;

    @FXML
    private Integer hpValue;

    @FXML
    private Text name;

    public void init(PlayerController playerController){
        this.parent = playerController;
        this.hpValue = Settings.startingHealthAmount;
        this.update();
    }

    public void update(){
        this.hp.setText(this.hpValue.toString());
        this.healthBar.setProgress(this.hpValue/Settings.startingHealthAmount);
    }
}
