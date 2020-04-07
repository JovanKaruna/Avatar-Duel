package com.avatarduel.model.element;

import com.avatarduel.Paths;

import javafx.scene.paint.Color;

public final class Fire extends Element {
    private static Fire singleton;

    private Fire() {
        super(Color.INDIANRED, Type.FIRE, Paths.fireElementImage);
    }

    public static Fire getInstance() {
        if (Fire.singleton == null) {
            Fire.singleton = new Fire();
        }
        return Fire.singleton;
    }
}