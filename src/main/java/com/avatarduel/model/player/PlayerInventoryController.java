package com.avatarduel.model.player;

import com.avatarduel.Settings;
import com.avatarduel.event.DiscardFieldEvent;
import com.avatarduel.event.DiscardHandEvent;
import com.avatarduel.event.EventType;
import com.avatarduel.event.GameEventHandler;
import com.avatarduel.exception.NotEnoughPowerException;
import com.avatarduel.model.GameInfo;
import com.avatarduel.model.HasCardController;
import com.avatarduel.model.Location;
import com.avatarduel.model.card.*;
import com.avatarduel.model.card.summonable.SummonableCard;
import com.avatarduel.model.element.*;

import com.avatarduel.util.CardDAO;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

public class PlayerInventoryController implements HasCardController, DiscardHandEvent, DiscardFieldEvent {

    private PlayerController parent;

    private HashMap<Element, PowerController> powerMap;

    @FXML private HBox air;
    @FXML private HBox water;
    @FXML private HBox fire;
    @FXML private HBox earth;
    @FXML private PowerController airController;
    @FXML private PowerController waterController;
    @FXML private PowerController fireController;
    @FXML private PowerController earthController;
    @FXML private Text currentDeck;
    @FXML private Text maxDeck;
    @FXML private CardController deckController;
    @FXML private CardController graveyardController;

    private Integer currentDeckAmount;
    private Integer maxDeckAmount;
    private ArrayList<Card> cards;
    private final Integer characterWeight = 6;
    private final Integer landWeight = 9;
    private final Integer auraWeight = 3;
    private final Integer destroyWeight = 1;
    private final Integer powerupWeight = 1;
    private final Integer totalWeight = 20;

    public void init(PlayerController playerController) {
        this.parent = playerController;
        this.cards = new ArrayList<>();

        this.cards.addAll(CardDAO.get(Settings.startingDeckAmount * auraWeight / totalWeight, CardDAO.Type.AURA));
        this.cards.addAll(CardDAO.get(Settings.startingDeckAmount * characterWeight / totalWeight, CardDAO.Type.CHARACTER));
        this.cards.addAll(CardDAO.get(Settings.startingDeckAmount * landWeight / totalWeight, CardDAO.Type.LAND));
        this.cards.addAll(CardDAO.get(Settings.startingDeckAmount * destroyWeight / totalWeight, CardDAO.Type.DESTROY));
        this.cards.addAll(CardDAO.get(Settings.startingDeckAmount * powerupWeight / totalWeight, CardDAO.Type.POWERUP));

        Collections.shuffle(this.cards);

        this.graveyardController.setCard(EmptyCard.getInstance());
        this.maxDeckAmount = this.cards.size();
        this.currentDeckAmount = this.maxDeckAmount;

        this.powerMap = new HashMap<>();
        this.powerMap.put(Element.valueOf("AIR"), airController);
        this.powerMap.put(Element.valueOf("WATER"), waterController);
        this.powerMap.put(Element.valueOf("FIRE"), fireController);
        this.powerMap.put(Element.valueOf("EARTH"), earthController);

        this.airController.init(this, Air.getInstance());
        this.fireController.init(this, Fire.getInstance());
        this.earthController.init(this, Earth.getInstance());
        this.waterController.init(this, Water.getInstance());
        this.graveyardController.init(this);
        this.deckController.init(this);

        this.getGameEventHandler().subscribe(this, EventType.DISCARDHAND);
    }

    void update() {
        this.currentDeck.setText(this.currentDeckAmount.toString());
        this.maxDeck.setText(this.maxDeckAmount.toString());
    }

    @FXML
    public void discard(MouseEvent event) {
        if (this.isActivePlayer() && GameInfo.isMainPhase()) {
            this.getParent().getGameEventHandler().getSelectedCard().selectCard(EmptyCard.getInstance(), this.getParent().getId(), Location.GRAVEYARD);
        }
    }

    public void addPowerCapacity(Element element) {
        this.powerMap.get(element).addCapacity();
        this.powerMap.get(element).addCurrentPower(1);
    }

    public void usePower(SummonableCard c) throws NotEnoughPowerException {
        this.powerMap.get(c.getElement()).usePower(c.getPower());
    }

    public void addCurrentPower(Element e, Integer n) {
        this.powerMap.get(e).addCurrentPower(n);
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

    @Override
    public void setActiveCard(Card c) {
        c.setNotSelected();
        this.graveyardController.setCard(c);
    }

    @Override
    public CardController getCardController() {
        return this.graveyardController;
    }

    public void startTurn() {
        this.powerMap.forEach((k, v) -> v.resetCurrentPower());
    }

    private boolean isActivePlayer() {
        return this.getParent().isActivePlayer();
    }

    public PlayerController getParent() {
        return parent;
    }

    private GameEventHandler getGameEventHandler() {
        return this.getParent().getGameEventHandler();
    }

    @Override
    public void onDiscardFieldEvent(SelectedCard firstCard, SelectedCard secondCard) {
        this.setActiveCard(firstCard.getCard());
    }

    @Override
    public void onDiscardHandEvent(SelectedCard firstCard, SelectedCard secondCard) {
        this.setActiveCard(firstCard.getCard());
    }

    @Override
    public void onEvent(EventType type, SelectedCard firstCard, SelectedCard secondCard) {
        if (type.equals(EventType.DISCARDFIELD)) {
            this.onDiscardFieldEvent(firstCard, secondCard);
        } else if (type.equals(EventType.DISCARDHAND)) {
            this.onDiscardHandEvent(firstCard, secondCard);
        } else {
            assert false;
        }
    }
}
