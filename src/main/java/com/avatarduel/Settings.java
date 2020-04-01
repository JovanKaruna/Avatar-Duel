package com.avatarduel;

import com.avatarduel.model.element.Element;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Settings {
    public static final Integer screenWidth = 1280;
    public static final Integer screenHeight = 720;
    public static final Integer startingDeckAmount = 60;
    public static final Integer startingCardAmount = 7;
    public static final Integer startingHealthAmount = 80;

    public static final String title = "Avatar Duel K03 G07";

    private Settings() {
        throw new AssertionError("This is a utility class.");
    }
}
