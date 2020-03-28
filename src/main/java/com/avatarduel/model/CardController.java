package com.avatarduel.model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable{

    @FXML
    private AnchorPane root;

    @FXML
    private HBox title;

    @FXML
    private Text name;

    @FXML
    private ImageView elementImage;

    @FXML
    private HBox subtitle;

    @FXML
    private Text effect;

    @FXML
    private Text type;

    @FXML
    private ImageView image;

    @FXML
    private VBox descriptionBox;

    @FXML
    private Text descriptionText;

    @FXML
    private Text attack;

    @FXML
    private Text defend;

    @FXML
    private Text power;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAttributes(Card c){
        this.name = new Text(c.name);
        this.descriptionText = new Text(c.description);
        switch (c.element){
            case AIR:
//                this.elementImage = new ImageView(new Image());
                break;
            case FIRE:
                //                this.elementImage = new ImageView(new Image());
                break;
            case EARTH:
                //                this.elementImage = new ImageView(new Image());
                break;
            case WATER:
                //                this.elementImage = new ImageView(new Image());
                break;
        }
    }
}

