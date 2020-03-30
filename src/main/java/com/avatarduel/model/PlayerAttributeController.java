package com.avatarduel.model;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class PlayerAttributeController {

    private PlayerController parent;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private Text hpValue;

    @FXML
    private Text name;

    public void init(PlayerController playerController){
        this.parent = playerController;
    }

}
