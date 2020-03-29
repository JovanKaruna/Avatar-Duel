package com.avatarduel.model.card;

import com.avatarduel.Paths;
import com.avatarduel.model.HasCardController;
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

    private HasCardController parent;

    @FXML
    private VBox root;

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
    private Text attribute;

    @FXML
    public void initialize() {
        this.image.prefWidth(this.root.getWidth()*0.8);
    }

    public void init(HasCardController hasCardController) {
        this.parent = hasCardController;
    }

    public <T extends Card> void setAttributes(T c) {
        this.name.setText(c.name);
        this.descriptionText.setText(c.description);
        try {
            this.image.setImage(new Image(new FileInputStream(Paths.RESOURCE_PATH + Paths.IMAGE_PATH + c.imgPath)));
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
        String tmp = "";
        if (c instanceof Land) {
            this.type.setText("LAND");
            this.effect.setText("");
        } else if (c instanceof Character) {
            this.type.setText("CHARACTER");
            tmp += "ATK / " + ((Character) c).getAttack().toString();
            tmp += " | DEF / " + ((Character) c).getDefend().toString();
            tmp += " | POW / " + ((Character) c).getPower().toString();
            this.effect.setText("");
        } else if (c instanceof Skill) {
            this.type.setText("SKILL");
            tmp += "ATK / " + ((Skill) c).getAttack().toString();
            tmp += " | DEF / " + ((Skill) c).getDefend().toString();
            tmp += " | POW / " + ((Skill) c).getPower().toString();
            this.effect.setText(((Skill) c).getType());
        }
        this.attribute.setText(tmp);
    }

    public String getDescription() {
        return this.descriptionText.getText();
    }
}

