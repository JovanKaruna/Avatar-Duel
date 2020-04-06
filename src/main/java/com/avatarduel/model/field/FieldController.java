package com.avatarduel.model.field;

import com.avatarduel.model.card.*;
import com.avatarduel.model.player.PlayerController;

import com.avatarduel.util.CSSLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class FieldController {

    private PlayerController parent;

    @FXML private GridPane root;
    private ArrayList<SummonableCard> cards;
    private static final Integer nrow = 2;
    private static final Integer ncol = 8;
    private CardController[][] cardControllers;

    @FXML
    public void init(PlayerController playerController){
        this.parent = playerController;
        this.cards = new ArrayList<>();
        this.cardControllers = new CardController[nrow][ncol];
        for (Integer i = 0; i < FieldController.nrow; i++) {
            for (Integer j = 0; j < FieldController.ncol; j++) {
                CardController cc = new CardController();
                cc.setRoot((StackPane) ((Pane) this.root.getChildren().get(i * ncol + j)).getChildren().get(0));
                cc.setCard(EmptyCard.getInstance());
                this.cardControllers[i][j] = cc;
            }
        }
    }

    public void update(){
        this.cardControllers[0][1].setCard(CardDAO.get(2));
        for (int i = 0; i < FieldController.nrow; i++) {
            for (int j = 0; j < FieldController.ncol; j++) {
                this.cardControllers[i][j].setCard(this.getCard(i));
            }
        }
    }

    private Card getCard(Integer index) {
        try {
            return this.cards.get(index);
        } catch (IndexOutOfBoundsException e) {
            return EmptyCard.getInstance();
        }
    }

    public void setColor(String color){
        root.getChildren().forEach(node -> CSSLoader.addClass(node, color+"-field-cell"));
    }

    public PlayerController getParent() {
        return parent;
    }
}
