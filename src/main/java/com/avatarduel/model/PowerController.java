package com.avatarduel.model;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PowerController {

    private PlayerInventoryController parent;

    @FXML
    private ImageView image;

    @FXML
    private Text cur;

    @FXML
    private Text max;

    public void init(PlayerInventoryController playerInventoryController){
        this.parent = playerInventoryController;
    }

}
