package com.avatarduel.model.element;

import com.avatarduel.Paths;
import javafx.scene.paint.Color;

public final class Earth extends Element {
    private static Earth singleton;

    private Earth() {
        super(Color.SANDYBROWN, Type.EARTH, Paths.earthElementImage);
    }

    public static Earth getInstance() {
        if (Earth.singleton == null) {
            Earth.singleton = new Earth();
        }
        return Earth.singleton;
    }
}