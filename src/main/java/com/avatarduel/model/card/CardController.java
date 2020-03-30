package com.avatarduel.model.card;

import com.avatarduel.Paths;
import com.avatarduel.Settings;
import com.avatarduel.model.HasCardController;
import com.avatarduel.model.element.Element;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class CardController {
    public static final String FXML_PATH = "fxml/Card.fxml";
    private static final Double imageToCardWidthRatio = 0.8;
    private static final HashMap<Element, Color> bgMap = new HashMap<Element, Color>() {
        {
            put(Element.AIR, Color.LIGHTBLUE);
            put(Element.FIRE, Color.INDIANRED);
            put(Element.EARTH, Color.SANDYBROWN);
            put(Element.WATER, Color.DEEPSKYBLUE);
        }
    };
    private static final HashMap<Element, String> elementImageMap = new HashMap<Element, String>() {
        {
            put(Element.AIR, Settings.airElementImagePath);
            put(Element.FIRE, Settings.fireElementImagePath);
            put(Element.EARTH, Settings.earthElementImagePath);
            put(Element.WATER, Settings.waterElementImagePath);
        }
    };

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

    public void init(HasCardController hasCardController) {
        this.parent = hasCardController;
    }

    public <T extends Card> void setAttributes(T c) {
        // fit image container size
        this.image.setFitWidth(this.root.getWidth() * CardController.imageToCardWidthRatio);
        this.elementImage.setFitHeight(this.title.getHeight() * 1.1);

        // set text
        this.name.setText(c.getName());
        this.descriptionText.setText(c.getDescription());
        this.attribute.setText(c.getAttributeDescription());
        this.type.setText(c.getTypeDescription());
        this.effect.setText(c.getEffectDescription());

        // set image and background
        this.setBackground(CardController.bgMap.get(c.getElement()));
        this.setImage(c.getImgPath());
        this.setElementImage(CardController.elementImageMap.get(c.getElement()));
    }

    public String getDescription() {
        return this.descriptionText.getText();
    }

    private void setImage(String path) {
        try {
            this.image.setImage(this.loadImageFromPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setElementImage(String path) {
        try {
            this.elementImage.setImage(this.loadImageFromPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBackground(Color c) {
        root.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private Image loadImageFromPath(String path) throws FileNotFoundException {
        return new Image(new FileInputStream(Paths.RESOURCE_PATH + Paths.IMAGE_PATH + path));
    }
}

