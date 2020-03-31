package com.avatarduel.model.card;

import com.avatarduel.Paths;
import com.avatarduel.Settings;
import com.avatarduel.model.HasCardController;
import com.avatarduel.model.ImageLoader;
import com.avatarduel.model.element.Element;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.HashMap;

public class CardController {
    public static final String FXML_PATH = "fxml/Card.fxml";
    private static final Double imageToCardWidthRatio = 0.8;

    private boolean isImageFitted = false;

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
    private Text descriptionText;

    @FXML
    private Text attribute;


    public void setRoot(VBox root) {
        this.root = root;

        this.title = (HBox) root.getChildren().get(0);
        this.name = (Text) this.title.getChildren().get(0);
        this.elementImage = (ImageView) this.title.getChildren().get(2);

        this.subtitle = (HBox) root.getChildren().get(1);
        this.effect = (Text) this.subtitle.getChildren().get(0);
        this.type = (Text) this.subtitle.getChildren().get(3);

        this.image = (ImageView) ((VBox)((HBox) root.getChildren().get(2)).getChildren().get(1)).getChildren().get(0);
        this.descriptionText = (Text) ((VBox) root.getChildren().get(3)).getChildren().get(0);
        this.attribute = (Text) ((HBox) ((VBox) root.getChildren().get(3)).getChildren().get(2)).getChildren().get(1);
    }

    @FXML
    public void init(HasCardController hasCardController) {
        this.parent = hasCardController;
    }

    public <T extends Card> void setAttributes(T c) {
        if(!this.isImageFitted){
            // fit image container size
            this.image.setFitWidth(this.root.getWidth() * CardController.imageToCardWidthRatio);
            this.elementImage.setFitHeight(this.title.getHeight());
            this.isImageFitted = true;
        }

        // set text
        this.name.setText(c.getName());
        this.descriptionText.setText(c.getDescription());
        this.attribute.setText(c.getAttributeDescription());
        this.type.setText(c.getTypeDescription());
        this.effect.setText(c.getEffectDescription());

        // set image and background
        this.setBackground(Settings.elementColorMap.get(c.getElement()));
        ImageLoader.setImage(this.image, c.getImgPath());
        ImageLoader.setImage(this.elementImage, Paths.elementImageMap.get(c.getElement()));
    }

    public String getDescription() {
        return this.descriptionText.getText();
    }

    private void setBackground(Color c) {
        root.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}

