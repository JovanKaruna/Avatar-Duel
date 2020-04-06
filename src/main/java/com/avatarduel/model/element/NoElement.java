package com.avatarduel.model.element;

import com.avatarduel.Paths;

import javafx.scene.paint.Color;

public final class NoElement extends Element {
    private static NoElement singleton;

    private NoElement() {
        super(Color.TRANSPARENT, Type.NOELEMENT, Paths.emptyImagePath);
    }

    public static NoElement getInstance() {
        if (NoElement.singleton == null) {
            NoElement.singleton = new NoElement();
        }
        return NoElement.singleton;
    }
}