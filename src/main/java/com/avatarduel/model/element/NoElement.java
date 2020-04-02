package com.avatarduel.model.element;

import com.avatarduel.Paths;

import javafx.scene.paint.Color;

public class NoElement extends Element {
    private static NoElement singleton;

    private NoElement() {
        super(Color.TRANSPARENT, Type.NOELEMENT, "Empty.png");
    }

    public static NoElement getInstance() {
        if (NoElement.singleton == null) {
            NoElement.singleton = new NoElement();
        }
        return NoElement.singleton;
    }
}