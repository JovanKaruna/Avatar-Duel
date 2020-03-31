package com.avatarduel;

import com.avatarduel.model.element.Element;

import java.util.HashMap;

public class Paths {
    public static final String RESOURCE_PATH = "src/main/resources/com/avatarduel/";
    public static final String IMAGE_PATH = "card/image/";
    public static final String APP_FXML_PATH = "fxml/Board.fxml";
    public static final String DECK_VIEWER_FXML_PATH = "fxml/DeckViewer.fxml";
    public static final String CARD_FXML_PATH =  "fxml/Card.fxml";

    public static final HashMap<Element, String> elementImageMap = new HashMap<Element, String>() {
        {
            put(Element.AIR, Settings.airElementImagePath);
            put(Element.FIRE, Settings.fireElementImagePath);
            put(Element.EARTH, Settings.earthElementImagePath);
            put(Element.WATER, Settings.waterElementImagePath);
        }
    };

    private Paths(){
        throw new AssertionError("This is a utility class.");
    }
}
