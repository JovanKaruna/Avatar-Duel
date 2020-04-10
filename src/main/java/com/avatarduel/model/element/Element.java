package com.avatarduel.model.element;

import javafx.scene.paint.Color;

import java.util.EnumMap;

public abstract class Element {
    enum Type {
        WATER, FIRE, AIR, EARTH, ENERGY, NOELEMENT
    }

    private static EnumMap<Type, Element> classMap = new EnumMap<Type, Element>(Type.class) {
        {
            put(Type.FIRE, Fire.getInstance());
            put(Type.WATER, Water.getInstance());
            put(Type.AIR, Air.getInstance());
            put(Type.EARTH, Earth.getInstance());
            put(Type.ENERGY, Energy.getInstance());
            put(Type.NOELEMENT, NoElement.getInstance());
        }
    };

    private Color color;
    private Type type;
    private String imagePath;

    Element(Color color, Type type, String imagePath) {
        this.color = color;
        this.type = type;
        this.imagePath = imagePath;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static Element valueOf(String type) {
        return Element.classMap.get(Element.Type.valueOf(type));
    }
}

