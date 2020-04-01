package com.avatarduel.model.element;

import javafx.scene.paint.Color;

public class Earth extends Element {
    private static Earth singleton;

    private Earth() {
        this.color = Color.SANDYBROWN;
        this.type = Type.EARTH;
        this.imagePath = "element/Earth.png";
    }

    public static Earth getInstance() {
        if (Earth.singleton == null) {
            Earth.singleton = new Earth();
        }
        return Earth.singleton;
    }
}