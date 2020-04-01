package com.avatarduel.model.card;

import com.avatarduel.model.HasCardController;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class CardDescController {
    private HasCardController parent;

    @FXML
    private Text attribute;

    @FXML
    private Text description;

    @FXML
    private ListView<Text> skills;

    public void init(HasCardController hasCardController) {
        this.parent = hasCardController;
    }

    public <T extends Card> void setAttributes(T c) {
        this.description.setText(c.getDescription());
        this.attribute.setText(c.getAttributeDescription());
    }
}

