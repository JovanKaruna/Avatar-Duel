package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.model.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AvatarDuel extends Application {

    private static HasCardController rootController;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource(Paths.appFXML));

        Parent root = rootLoader.load();

        Scene scene = new Scene(root, Settings.screenWidth, Settings.screenHeight);
        scene.getStylesheets().add(getClass().getResource(Paths.boardCSS).toExternalForm());

        stage.setScene(scene);
        stage.setTitle(Settings.titleName);
        stage.setResizable(false);
        stage.show();

        this.rootController = rootLoader.getController();

        BoardController boardController = (BoardController) rootController;
        boardController.init(Settings.player1Name,Settings.player2Name);

        ((BoardController) rootController).startGame();
    }

    public static void main(String[] args) {
        launch();
    }
}
