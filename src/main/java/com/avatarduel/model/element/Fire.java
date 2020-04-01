package com.avatarduel.model.element;

import javafx.scene.paint.Color;

public class Fire extends Element {
    private static Fire singleton;

    private Fire() {
        this.color = Color.INDIANRED;
        this.type = Type.FIRE;
        this.imagePath = "element/Fire.png";
    }

    public static Fire getInstance() {
        if (Fire.singleton == null) {
            Fire.singleton = new Fire();
        }
        return Fire.singleton;
    }
}