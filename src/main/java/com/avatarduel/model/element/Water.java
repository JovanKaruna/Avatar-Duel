package com.avatarduel.model.element;

import javafx.scene.paint.Color;

public class Water extends Element {
    private static Water singleton;

    private Water() {
        this.color = Color.DEEPSKYBLUE;
        this.type = Type.WATER;
        this.imagePath = "element/Water.png";
    }

    public static Water getInstance() {
        if (Water.singleton == null) {
            Water.singleton = new Water();
        }
        return Water.singleton;
    }
}