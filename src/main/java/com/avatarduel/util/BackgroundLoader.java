package com.avatarduel.util;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

// Utility Pattern
public class BackgroundLoader {

    private BackgroundLoader() {
        throw new AssertionError("This is a utility class.");
    }

    public static void setBackground(Region target, Color c){
        target.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
