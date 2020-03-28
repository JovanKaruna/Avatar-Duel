package com.avatarduel.model;

public class Land extends Card{
  public static final String CSV_FILE_PATH = "card/data/land.csv";
  public Land(String name, String description, Element element, String imgPath) {
    super(name, description, element, imgPath);
  }
}