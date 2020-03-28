package com.avatarduel.model;

import com.avatarduel.AvatarDuel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DeckViewerController {
    private final static String IMAGE_PATH = "src/main/resources/com/avatarduel/card/image/";
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

    @FXML
    private Button nextBtn;

    private int i = 0;

    @FXML
    void nextCard(ActionEvent event) {
        this.setAttributes(AvatarDuel.cards.get(i));
        this.i++;
        this.i %= AvatarDuel.cards.size();
    }

    public void setAttributes(Card c){
        this.name.setText(c.name);
        this.descriptionText.setText(c.description);
        try {
            this.image.setImage(new Image(new FileInputStream(IMAGE_PATH + c.imgPath)));
        } catch (FileNotFoundException e) {
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
    }

}
