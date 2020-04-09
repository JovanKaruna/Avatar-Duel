package com.avatarduel;

import java.io.IOException;

import com.avatarduel.model.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AvatarDuel extends Application {

    private static HasCardController rootController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource(Paths.appFXML));

        Parent root = rootLoader.load();

        Scene scene = new Scene(root, Settings.screenWidth, Settings.screenHeight);
        scene.getStylesheets().add(getClass().getResource(Paths.boardCSS).toExternalForm());

        stage.setScene(scene);
        stage.setTitle(Settings.titleName);
        stage.setResizable(false);
        stage.show();

        AvatarDuel.rootController = rootLoader.getController();

        BoardController boardController = (BoardController) rootController;
        GameInfo.addPlayer(1, new PlayerInfo(Settings.player1Name, Settings.player1Color));
        GameInfo.addPlayer(2, new PlayerInfo(Settings.player2Name, Settings.player2Color));
        boardController.init();

        ((BoardController) rootController).startGame();
    }

    public static void main(String[] args) {
        launch();
    }
}
