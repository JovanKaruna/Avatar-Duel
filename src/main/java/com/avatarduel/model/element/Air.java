package com.avatarduel.model.element;

import com.avatarduel.Paths;

import javafx.scene.paint.Color;

public final class Air extends Element {
    private static Air singleton;

    private Air() {
        super(Color.LIGHTBLUE, Type.AIR, Paths.airElementImage);
    }

    public static Air getInstance() {
        if (Air.singleton == null) {
            Air.singleton = new Air();
        }
        return Air.singleton;
    }
}