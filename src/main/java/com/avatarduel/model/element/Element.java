package com.avatarduel.model.element;

import javafx.scene.paint.Color;

import java.util.HashMap;

abstract public class Element {
    enum Type {
        WATER,
        FIRE,
        AIR,
        EARTH
    }

    private static HashMap<Type, Element> classMap = new HashMap<Type, Element>() {
        {
            put(Type.FIRE, Fire.getInstance());
            put(Type.WATER, Water.getInstance());
            put(Type.AIR, Air.getInstance());
            put(Type.EARTH, Earth.getInstance());
        }
    };

    Color color;
    Type type;
    String imagePath;

    Element(Color color, Type type, String imagePath){
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

