package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.model.*;
import com.avatarduel.model.card.*;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.element.Element;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {

    public static HasCardController rootController;

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
        scene.getStylesheets().add(getClass().getResource(Paths.BOARD_CSS_FILE).toExternalForm());

        stage.setScene(scene);
        stage.setTitle(Settings.title);
        stage.setResizable(false);
        stage.show();

        this.rootController = rootLoader.getController();

        // deck viewer
//        DeckViewerController rootController = rootLoader.getController();
//        rootController.setCards(AvatarDuel.getActivePlayer().deck);

        // board
        ((BoardController) rootController).startGame();
    }

    public AvatarDuel() {
        try {
            for (String[] row : this.loadCards(Land.CSV_FILE_PATH)) {
                CardDAO.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
                CardDAO.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
            }
            for (String[] row : this.loadCards(Character.CSV_FILE_PATH))
                CardDAO.add(new Character(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
            for (String[] row : this.loadCards(Aura.CSV_FILE_PATH))
                CardDAO.add(new Aura(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
            for (String[] row : this.loadCards(Destroy.CSV_FILE_PATH))
                CardDAO.add(new Destroy(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private List<String[]> loadCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader landReader = new CSVReader(csvFile, "\t");
        landReader.setSkipHeader(true);
        return landReader.read();
    }
}
