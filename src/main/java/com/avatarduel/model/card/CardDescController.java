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

    public void init(HasCardController hasCardController){
        this.parent = hasCardController;
    }

    public <T extends Card> void setAttribute(T c){
        this.description.setText(c.description);
        String tmp = "";
        if(c instanceof Character){
            tmp += "ATK / " + ((Character) c).getAttack();
            tmp += " | DEF / " + ((Character) c).getDefend();
            tmp += " | POW / " + ((Character) c).getPower();
        } else if (c instanceof Skill) {
            tmp += "ATK / " + ((Skill) c).getAttack();
            tmp += " | DEF / " + ((Skill) c).getDefend();
            tmp += " | POW / " + ((Skill) c).getPower();
        }
        this.attribute.setText(tmp);
    }
}

