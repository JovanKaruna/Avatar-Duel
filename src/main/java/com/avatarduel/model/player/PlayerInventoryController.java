package com.avatarduel.model.player;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardController;
import com.avatarduel.model.card.CardDAO;
import com.avatarduel.model.element.Element;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

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

    private ArrayList<Card> cards;

    @FXML
    public void initialize() {
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

    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cards = new ArrayList<>();
        Collections.shuffle(CardDAO.getCards());
        for (int i = 0; i < 60; i++) {
            this.cards.add(CardDAO.get(i));
        }
        this.maxDeckAmount = this.cards.size();
        this.currentDeckAmount = this.maxDeckAmount;
    }

    public void addPowerCapacity(Element element) {
        this.powerMap.get(element).addCapacity();
    }

    void update() {
        this.currentDeck.setText(this.currentDeckAmount.toString());
        this.maxDeck.setText(this.maxDeckAmount.toString());
    }

    public List<Card> takeNCards(Integer n) {
        List<Card> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tmp.add(this.cards.get(0));
            this.cards.remove(0);
        }
        this.currentDeckAmount -= n;
        this.update();
        return tmp;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
