package com.avatarduel.model;

import com.avatarduel.AvatarDuel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileInputStream;

public class CardController {
    public static final String FXML_PATH = "fxml/Card.fxml";

    private DeckViewerController parent;

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

    public <T extends Card> void setAttributes(T c) {
        this.name.setText(c.name);
        this.descriptionText.setText(c.description);
        try {
            this.image.setImage(new Image(new FileInputStream(AvatarDuel.RESOURCE_PATH + AvatarDuel.IMAGE_PATH + c.imgPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (c.element) {
            case AIR:
                root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//                this.elementImage = new ImageView(new Image());
                break;
            case FIRE:
                root.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
                //                this.elementImage = new ImageView(new Image());
                break;
            case EARTH:
                root.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
                //                this.elementImage = new ImageView(new Image());
                break;
            case WATER:
                root.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                //                this.elementImage = new ImageView(new Image());
                break;
        }
        if (c instanceof Land) {
            this.type.setText("[LAND]");
            this.attack.setText("");
            this.defend.setText("");
            this.power.setText("");
            this.effect.setText("");
        } else if (c instanceof Character) {
            this.type.setText("[CHARACTER]");
            this.attack.setText("ATK / " + ((Character) c).getAttack().toString());
            this.defend.setText("DEF / " + ((Character) c).getDefend().toString());
            this.power.setText("POW / " + ((Character) c).getPower().toString());
            this.effect.setText("");
        } else if (c instanceof Skill) {
            this.type.setText("[SKILL]");
            this.attack.setText("ATK / " + ((Skill) c).getAttack().toString());
            this.defend.setText("DEF / " + ((Skill) c).getDefend().toString());
            this.power.setText("POW / " + ((Skill) c).getPower().toString());
            this.effect.setText(((Skill) c).getType());
        }
    }

    public void init(DeckViewerController deckViewerController) {
        this.parent = deckViewerController;
    }
}

