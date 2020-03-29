package com.avatarduel.model.card;

import com.avatarduel.model.Element;

public class Land extends Card {
    public static final String CSV_FILE_PATH = "card/data/land.csv";

    public Land(String name, String description, Element element, String imgPath) {
        super(name, description, element, imgPath);
    }
}