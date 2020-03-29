package com.avatarduel;

public class Settings {
    public static final Integer minHeight = 850;
    public static final Integer minWidth = 550;
    public static final Integer startingCardAmount = 7;

    private Settings(){
        throw new AssertionError("This is a utility class.");
    }
}
