package com.avatarduel.model.element;

import javafx.scene.paint.Color;

public class Air extends Element {
    private static Air singleton;

    private Air() {
        this.color = Color.LIGHTBLUE;
        this.type = Type.AIR;
        this.imagePath = "element/Air.png";
    }

    public static Air getInstance() {
        if (Air.singleton == null) {
            Air.singleton = new Air();
        }
        return Air.singleton;
    }
}