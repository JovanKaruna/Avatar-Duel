package com.avatarduel;

import com.avatarduel.model.element.Element;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Settings {
    public static final Integer minHeight = 850;
    public static final Integer minWidth = 550;
    public static final Integer startingCardAmount = 7;
    public static final String airElementImagePath = "element/Air.png";
    public static final String fireElementImagePath = "element/Fire.png";
    public static final String earthElementImagePath = "element/Earth.png";
    public static final String waterElementImagePath = "element/Water.png";

    public static final HashMap<Element, Color> elementColorMap = new HashMap<Element, Color>() {
        {
            put(Element.AIR, Color.LIGHTBLUE);
            put(Element.FIRE, Color.INDIANRED);
            put(Element.EARTH, Color.SANDYBROWN);
            put(Element.WATER, Color.DEEPSKYBLUE);
        }
    };

    private Settings(){
        throw new AssertionError("This is a utility class.");
    }
}
