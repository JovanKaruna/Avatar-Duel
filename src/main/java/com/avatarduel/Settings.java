package com.avatarduel;

public class Settings {
    public static final Integer minHeight = 850;
    public static final Integer minWidth = 550;
    public static final Integer startingCardAmount = 7;
    public static final String airElementImagePath = "element/Air.png";
    public static final String fireElementImagePath = "element/Fire.png";
    public static final String earthElementImagePath = "element/Earth.png";
    public static final String waterElementImagePath = "element/Water.png";

    private Settings(){
        throw new AssertionError("This is a utility class.");
    }
}
