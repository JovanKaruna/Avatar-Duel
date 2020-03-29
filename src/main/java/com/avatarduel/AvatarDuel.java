package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avatarduel.model.*;
import com.avatarduel.model.Character;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
    public static final String RESOURCE_PATH = "src/main/resources/com/avatarduel/";
    public static final String IMAGE_PATH = "card/image/";
    private static final String APP_FXML_PATH = "fxml/Board.fxml";
    private static final String DECK_VIEWER_FXML_PATH = "fxml/DeckViewer.fxml";
    public static ArrayList<Card> cards = new ArrayList<>();
    private static Player activePlayer, otherPlayer;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource(AvatarDuel.APP_FXML_PATH));
        // kalo mau deck viewer uncomment 3 baris di bwh:
        //        rootLoader = new FXMLLoader(getClass().getResource(AvatarDuel.DECK_VIEWER_FXML_PATH));
        Parent root = rootLoader.load();
//        DeckViewerController rootController = rootLoader.getController();
//        rootController.setCards(AvatarDuel.getActivePlayer().deck);
        stage.setTitle("Avatar Duel K03 G07");

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public AvatarDuel() {
        try {
            for (String[] row : this.loadCards(Land.CSV_FILE_PATH))
                AvatarDuel.cards.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
            for (String[] row : this.loadCards(Character.CSV_FILE_PATH))
                AvatarDuel.cards.add(new Character(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
            for (String[] row : this.loadCards(Skill.CSV_FILE_PATH))
                AvatarDuel.cards.add(new Skill(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AvatarDuel.activePlayer = new Player();
        AvatarDuel.otherPlayer = new Player();
        this.startGame();
    }

    public static void main(String[] args) {
        launch();
    }

    private void startGame() {
        AvatarDuel.activePlayer.drawNCards(7);
        AvatarDuel.otherPlayer.drawNCards(7);
        AvatarDuel.activePlayer.startTurn();
    }

    private List<String[]> loadCards(String path) throws IOException, URISyntaxException {
        File landCSVFile = new File(getClass().getResource(path).toURI());
        CSVReader landReader = new CSVReader(landCSVFile, "\t");
        landReader.setSkipHeader(true);
        return landReader.read();
    }

    public void nextPlayer() {
        Player tmp = AvatarDuel.activePlayer;
        AvatarDuel.activePlayer = AvatarDuel.otherPlayer;
        AvatarDuel.otherPlayer = tmp;
        AvatarDuel.activePlayer.startTurn();
    }

    public static Player getActivePlayer() {
        return activePlayer;
    }

    public static Player getOtherPlayer() {
        return otherPlayer;
    }
}
