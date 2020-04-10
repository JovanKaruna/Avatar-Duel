package com.avatarduel;

public class Paths {
    public static final String resourceFolder = "src/main/resources/com/avatarduel/";
    public static final String srcFolder = "src/main/java/com/avatarduel/";
    public static final String imageFolder = "card/image/";
    public static final String csvFolder = "card/data/";
    public static final String fxmlFolder = "fxml/";
    public static final String elementFolder = "element/";
    public static final String cssFolder = "css/";

    public static final String appFXML = fxmlFolder + "Board.fxml";
    public static final String deckViewerFXML = fxmlFolder + "DeckViewer.fxml";
    public static final String cardFXML = fxmlFolder + "Card.fxml";
    public static final String boardCSS = cssFolder + "root.css";

    public static final String landCSV = csvFolder + "land.csv";
    public static final String characterCSV = csvFolder + "character.csv";
    public static final String auraCSV = csvFolder + "aura.csv";
    public static final String destroyCSV = csvFolder + "destroy.csv";
    public static final String powerupCSV = csvFolder + "powerup.csv";

    public static final String emptyImagePath = "Empty.png";
    public static final String earthElementImage = elementFolder + "Earth.png";
    public static final String airElementImage = elementFolder + "Air.png";
    public static final String waterElementImage = elementFolder + "Water.png";
    public static final String fireElementImage = elementFolder + "Fire.png";
    public static final String energyElementImage = elementFolder + "Energy.png";

    private Paths() {
        throw new AssertionError("This is a utility class.");
    }
}

