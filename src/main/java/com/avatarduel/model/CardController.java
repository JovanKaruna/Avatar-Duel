package com.avatarduel.model;

import com.avatarduel.AvatarDuel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class CardController{

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

    public <T extends Card> void setAttributes(T c){
        this.name.setText(c.name);
        this.descriptionText.setText(c.description);
        try {
            this.image.setImage(new Image(new FileInputStream(AvatarDuel.RESOURCE_PATH + AvatarDuel.IMAGE_PATH + c.imgPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if(c instanceof Land){
            this.type.setText("[LAND]");
            this.attack.setText("");
            this.defend.setText("");
            this.power.setText("");
            this.effect.setText("");
        } else if (c instanceof Character){
            this.type.setText("[CHARACTER]");
            this.attack.setText(((Character) c).getAttack().toString());
            this.defend.setText(((Character) c).getDefend().toString());
            this.power.setText(((Character) c).getPower().toString());
            this.effect.setText("");
        } else if (c instanceof  Skill){
            this.type.setText("[SKILL]");
            this.attack.setText(((Skill) c).getAttack().toString());
            this.defend.setText(((Skill) c).getDefend().toString());
            this.power.setText(((Skill) c).getPower().toString());
            this.effect.setText(((Skill) c).getType());
        }
    }
}

