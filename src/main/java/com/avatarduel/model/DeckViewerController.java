package com.avatarduel.model;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import com.avatarduel.AvatarDuel;

public class DeckViewerController extends CardController{
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
}
