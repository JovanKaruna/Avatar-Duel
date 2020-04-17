package com.avatarduel.model.card;

import com.avatarduel.model.HasCardController;

import com.avatarduel.model.card.summonable.SummonedCard;
import com.avatarduel.model.card.summonable.SummonedCharacterCard;
import com.avatarduel.model.card.summonable.SummonedSkillCard;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;

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

    public <T extends SummonedCard> void setSkills(T c){
        ArrayList<SummonedSkillCard> supportCard = ((SummonedCharacterCard)c).getSupportCard();
        for(int i=0 ; i< supportCard.size(); i++){
            Text skillName = new Text(supportCard.get(i).getCard().getName());
            this.skills.getItems().add(skillName);
        }
    }
    public void clearSkill(){
        this.skills.getItems().clear();
    }
}

