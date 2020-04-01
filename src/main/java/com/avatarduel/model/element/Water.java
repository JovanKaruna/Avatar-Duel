package com.avatarduel.model.element;

import com.avatarduel.Paths;
import javafx.scene.paint.Color;

public class Water extends Element {
    private static Water singleton;

    private Water() {
        super(Color.DEEPSKYBLUE, Type.WATER, Paths.waterElementImage);
    }

    public static Water getInstance() {
        if (Water.singleton == null) {
            Water.singleton = new Water();
        }
        return Water.singleton;
    }
}