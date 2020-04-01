package com.avatarduel.model.player;

import com.avatarduel.exception.NotEnoughPowerException;
import com.avatarduel.util.ImageLoader;
import com.avatarduel.model.element.Element;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PowerController {

    private PlayerInventoryController parent;

    @FXML
    private ImageView image;

    @FXML
    private Text cur;

    private Integer curVal;

    @FXML
    private Text max;

    private Integer maxVal;

    @FXML
    public void initialize(){
        this.curVal = 0;
        this.maxVal = 0;
        this.updateText();
    }

    public void init(PlayerInventoryController playerInventoryController, Element element){
        this.parent = playerInventoryController;
        ImageLoader.setImage(this.image, element.getImagePath());
    }

    private void updateText(){
        this.cur.setText(this.curVal.toString());
        this.max.setText(this.maxVal.toString());
    }

    public void resetCurrentPower(){
        this.curVal = this.maxVal;
    }

    public void addCapacity() {
        this.maxVal++;
    }

    public void usePower(Integer n) throws NotEnoughPowerException {
        if(this.curVal < n) {
            throw new NotEnoughPowerException();
        } else {
            this.curVal -= n;
        }
    }
}
