package com.avatarduel.model;

import com.avatarduel.model.card.CardController;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PlayerInventoryController {

    private PlayerController parent;

    @FXML
    private PowerController windController;

    @FXML
    private HBox wind;

    @FXML
    private PowerController waterController;

    @FXML
    private HBox water;

    @FXML
    private PowerController fireController;

    @FXML
    private HBox fire;

    @FXML
    private PowerController earthController;

    @FXML
    private HBox earth;

    @FXML
    private Text currentDeck;

    @FXML
    private Text maxDeck;

    @FXML
    private CardController graveyardController;

    @FXML
    public void initialize(){
        this.waterController.init(this);
        this.fireController.init(this);
        this.earthController.init(this);
        this.windController.init(this);
    }

    public void init(PlayerController playerController){
        this.parent = playerController;
    }
}
