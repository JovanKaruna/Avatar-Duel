package com.avatarduel.model.card;

import com.avatarduel.Settings;
import com.avatarduel.model.Location;
import com.avatarduel.model.card.summonable.EmptyCard;
import com.avatarduel.util.BackgroundLoader;
import com.avatarduel.model.HasCardController;
import com.avatarduel.util.ImageLoader;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class CardController {
    private static final Double imageToCardWidthRatio = 0.8;

    private boolean isImageFitted = false;

    private Card card;

    private HasCardController parent;

    @FXML private VBox root;
    @FXML private HBox title;
    @FXML private Text name;
    @FXML private ImageView elementImage;
    @FXML private HBox subtitle;
    @FXML private Text effect;
    @FXML private Text type;
    @FXML private ImageView image;
    @FXML private Text descriptionText;
    @FXML private Text attribute;
    @FXML private AnchorPane back;
    @FXML private Ellipse backLogo;

    public CardController() {
        this.card = EmptyCard.getInstance();
    }

    public void init(HasCardController hasCardController) {
        this.init(hasCardController, EmptyCard.getInstance());
    }

    public void init(HasCardController hasCardController, Card card) {
        this.parent = hasCardController;
        this.setCard(card, Location.UNKNOWN);
    }

    public void setCard(Card card, Location location) {
        this.card = card;
        this.show(location);
    }

    public void setEmpty(Location location){
        this.setCard(EmptyCard.getInstance(), location);
    }

    public Card getCard() {
        return this.card;
    }

    public VBox getRoot() {
        return root;
    }

    /**
     * Set the layout of Card (at top left corner in game)
     * @param root A parent pane that have many children
     */
    public void setRoot(StackPane root) {
        this.root = (VBox) root.getChildren().get(0);

        this.back = (AnchorPane) root.getChildren().get(1);
        this.backLogo = (Ellipse) this.back.getChildren().get(0);

        this.title = (HBox) this.root.getChildren().get(0);
        this.name = (Text) this.title.getChildren().get(0);
        this.elementImage = (ImageView) this.title.getChildren().get(2);

        this.subtitle = (HBox) this.root.getChildren().get(1);
        this.effect = (Text) this.subtitle.getChildren().get(0);
        this.type = (Text) this.subtitle.getChildren().get(3);

        this.image = (ImageView) ((VBox) ((HBox) this.root.getChildren().get(2)).getChildren().get(1)).getChildren().get(0);
        this.descriptionText = (Text) ((VBox) this.root.getChildren().get(3)).getChildren().get(0);
        this.attribute = (Text) ((HBox) ((VBox) this.root.getChildren().get(3)).getChildren().get(2)).getChildren().get(1);
    }

    public boolean isEmpty() {
        return this.card.isEmpty();
    }

    /**
     * Set the layout card in real (can be open or close; can be lifted or unlifted; set location of card)
     * @param location location of the card
     */
    public void show(Location location) {
        boolean tmp = this.getCard().isSelected();
        boolean tmp2 = this.getCard().isPortrait();
        boolean tmp3 = this.getCard().isOpen();
        if(location.equals(Location.GRAVEYARD)) {
            this.getCard().setOrientation(true);
            this.getCard().setNotSelected();
            this.getCard().open();
        } else if (location.equals(Location.HAND)){
            this.getCard().setOrientation(true);
        } else if (location.equals(Location.FIELD)){
            this.getCard().open();
        }

        if (this.card.isOpen()) {
            this.back.setBackground(Background.EMPTY);
            this.backLogo.setFill(new Color(0, 0, 0, 0));

            if (!this.isImageFitted) {
                // fit image container size
                this.image.setFitWidth(this.root.getWidth() * CardController.imageToCardWidthRatio);
                this.elementImage.setFitHeight(this.title.getHeight());
                this.isImageFitted = true;
            }

            // set text
            this.name.setText(this.card.getName());
            this.descriptionText.setText(this.card.getDescription());
            this.attribute.setText(this.card.getAttributeDescription());
            this.type.setText(this.card.getTypeDescription());
            this.effect.setText(this.card.getEffectDescription());

            // set image and background
            this.setBackground(this.card.getElement().getColor());
            ImageLoader.setImage(this.image, this.card.getImgPath());
            ImageLoader.setImage(this.elementImage, this.card.getElement().getImagePath());

            if (this.card.isPortrait()) {
                this.root.setRotate(0);
            } else {
                this.root.setRotate(90);
            }

            if(this.card.isSelected()){
                this.lift();
            } else {
                this.unlift();
            }
        } else {
            BackgroundLoader.setBackground(this.back, Settings.cardBackColor);
            this.backLogo.setFill(new Color(45 / 255.0, 184 / 255.0, 255 / 255.0, 1));
        }

        this.getCard().setSelection(tmp);
        this.getCard().setOrientation(tmp2);
        this.getCard().setOpen(tmp3);
    }

    /**
     * Lift the card (to show that the card is selected)
     */
    private void lift() {
        this.root.setTranslateY(-100);
        this.root.setScaleX(1.1);
        this.root.setScaleY(1.1);
    }

    /**
     * Unlift the card (to show that the card is not selected)
     */
    private void unlift() {
        this.root.setTranslateY(0);
        this.root.setScaleX(1);
        this.root.setScaleY(1);
    }

    public String getDescription() {
        return this.descriptionText.getText();
    }

    private void setBackground(Color c) {
        BackgroundLoader.setBackground(root, c);
    }
}

