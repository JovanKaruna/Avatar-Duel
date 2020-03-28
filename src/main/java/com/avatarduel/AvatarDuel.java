package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.util.CSVReader;
import com.avatarduel.model.Card;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String APP_FXML_PATH = "fxml/DeckViewer.fxml";
  public static ArrayList<Card> cards;

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    landReader.setSkipHeader(true);
    List<String[]> landRows = landReader.read();
    AvatarDuel.cards = new ArrayList<>();
    for (String[] row : landRows) {
      AvatarDuel.cards.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
    }
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource(APP_FXML_PATH));
    stage.setTitle("Avatar Duel K03 G07");

    try {
      this.loadCards();
    } catch (Exception e) {
    }

//    this.cards.get(0);

    stage.setScene(new Scene(root));
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
