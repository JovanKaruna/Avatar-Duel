package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avatarduel.model.DeckViewerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.model.Character;
import com.avatarduel.model.Skill;
import com.avatarduel.util.CSVReader;
import com.avatarduel.model.Card;

public class AvatarDuel extends Application {
  public static final String RESOURCE_PATH = "src/main/resources/com/avatarduel/";
  public static final String IMAGE_PATH = "card/image/";
  public static final String APP_FXML_PATH = "fxml/DeckViewer.fxml";
  public static ArrayList<Card> cards = new ArrayList<>();

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(APP_FXML_PATH));
    Parent root = loader.load();
    stage.setTitle("Avatar Duel K03 G07");

    DeckViewerController mainController = loader.getController();

    // load cards
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

    stage.setScene(new Scene(root));
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

  public List<String[]> loadCards(String path) throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(path).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    landReader.setSkipHeader(true);
    return landReader.read();
  }
}
