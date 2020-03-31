package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.model.*;
import com.avatarduel.model.card.CardDAO;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.Land;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.element.Element;
import com.avatarduel.model.player.PlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
    private static PlayerController activePlayer, otherPlayer;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader rootLoader;

        // uncomment sesuai kebutuhan
        // board
        rootLoader = new FXMLLoader(getClass().getResource(Paths.APP_FXML_PATH));
        // deck viewer
//        rootLoader = new FXMLLoader(getClass().getResource(Paths.DECK_VIEWER_FXML_PATH));
        // card only
//        rootLoader = new FXMLLoader(getClass().getResource(Paths.CARD_FXML_PATH));

        Parent root = rootLoader.load();

        Scene scene = new Scene(root, Settings.screenWidth, Settings.screenHeight);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Avatar Duel K03 G07");
        stage.setResizable(false);
        stage.show();

        // deck viewer
//        DeckViewerController rootController = rootLoader.getController();
//        rootController.setCards(AvatarDuel.getActivePlayer().deck);

        // board
        BoardController boardController = rootLoader.getController();
        AvatarDuel.activePlayer = boardController.player1Controller;
        AvatarDuel.otherPlayer = boardController.player2Controller;
        this.startGame();
    }

    public AvatarDuel() {
        try {
            for (String[] row : this.loadCards(Land.CSV_FILE_PATH))
                CardDAO.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
            for (String[] row : this.loadCards(Character.CSV_FILE_PATH))
                CardDAO.add(new Character(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
            for (String[] row : this.loadCards(Skill.CSV_FILE_PATH))
                CardDAO.add(new Skill(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void startGame() {
        AvatarDuel.activePlayer.drawNCards(Settings.startingCardAmount);
        AvatarDuel.otherPlayer.drawNCards(Settings.startingCardAmount);
        AvatarDuel.activePlayer.startTurn();
    }

    private List<String[]> loadCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader landReader = new CSVReader(csvFile, "\t");
        landReader.setSkipHeader(true);
        return landReader.read();
    }

    public void nextPlayer() {
        PlayerController tmp = AvatarDuel.activePlayer;
        AvatarDuel.activePlayer = AvatarDuel.otherPlayer;
        AvatarDuel.otherPlayer = tmp;
        AvatarDuel.activePlayer.startTurn();
    }

    public static PlayerController getActivePlayer() {
        return activePlayer;
    }

    public static PlayerController getOtherPlayer() {
        return otherPlayer;
    }
}
