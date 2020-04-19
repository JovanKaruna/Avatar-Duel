package com.avatarduel.model.element;

import com.avatarduel.Paths;
import javafx.scene.paint.Color;

public final class Energy extends Element {
    private static Energy singleton;

    private Energy() {
        super(Color.LIGHTBLUE, Type.ENERGY, Paths.energyElementImage);
    }

    public static Energy getInstance() {
        if (Energy.singleton == null) {
            Energy.singleton = new Energy();
        }
        return Energy.singleton;
    }
}