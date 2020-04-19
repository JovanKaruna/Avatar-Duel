package com.avatarduel.model.player;

import com.avatarduel.exception.NotEnoughPowerException;
import com.avatarduel.model.element.Element;
import com.avatarduel.util.ImageLoader;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PowerController {

    private PlayerInventoryController parent;

    @FXML private ImageView image;
    @FXML private Text cur;
    @FXML private Text max;

    private Integer curVal;
    private Integer maxVal;

    @FXML
    public void initialize() {
        this.curVal = 5;
        this.maxVal = 5;
        this.updateText();
    }

    public void init(PlayerInventoryController playerInventoryController, Element element) {
        this.parent = playerInventoryController;
        ImageLoader.setImage(this.image, element.getImagePath());
    }

    private void updateText() {
        this.cur.setText(this.curVal.toString());
        this.max.setText(this.maxVal.toString());
    }

    public void resetCurrentPower() {
        this.curVal = this.maxVal;
        this.updateText();
    }

    void addCapacity() {
        this.maxVal++;
        this.updateText();
    }

    void addCurrentPower(Integer n) {
        this.curVal += n;
        this.updateText();
    }

    void usePower(Integer n) throws NotEnoughPowerException {
        if (this.curVal < n) {
            throw new NotEnoughPowerException();
        } else {
            this.curVal -= n;
            this.updateText();
        }
    }

    public PlayerInventoryController getParent() {
        return parent;
    }
}
