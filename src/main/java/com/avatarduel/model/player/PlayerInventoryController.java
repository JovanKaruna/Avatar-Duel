package com.avatarduel.model.player;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.element.Element;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerInventoryController {

    private PlayerController parent;

    private HashMap<Element, PowerController> powerMap;

    @FXML
    private PowerController airController;

    @FXML
    private HBox air;

    @FXML
    private PowerController waterController;

    @FXML
    private HBox water;

    @FXML
    private PowerController fireController;

    @FXML
    private HBox fire;

    @FXML
    private PowerController earthController;

    @FXML
    private HBox earth;

    @FXML
    private Text currentDeck;

    private Integer currentDeckAmount;

    @FXML
    private Text maxDeck;

    private Integer maxDeckAmount;

    @FXML
    private CardController graveyardController;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    @FXML
    public void initialize(){
        this.powerMap = new HashMap<Element, PowerController>() {
            {
                put(Element.AIR, airController);
                put(Element.WATER, waterController);
                put(Element.FIRE, fireController);
                put(Element.EARTH, earthController);
            }
        };
        this.powerMap.forEach((element, controller) -> controller.init(this, element));
    }

    public void init(PlayerController playerController){
        this.parent = playerController;
    }

    public void addPowerCapacity(Element element){
        this.powerMap.get(element).addCapacity();
    }

    void setCards(ArrayList<Card> hand, ArrayList<Card> deck) {
        this.hand = hand;
        this.deck = deck;
        this.currentDeckAmount = this.deck.size();
        this.maxDeckAmount = 60;
        this.update();
    }

    void update(){
        this.currentDeck.setText(this.currentDeckAmount.toString());
        this.maxDeck.setText(this.maxDeckAmount.toString());
    }

    public void drawNCards(Integer n){
        this.currentDeckAmount -= n;
    }
    
    @FXML
    public void drawOneCard(){
        this.drawNCards(1);
    }
}
