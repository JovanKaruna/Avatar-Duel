package com.avatarduel;

public class Settings {
    public static final Integer screenWidth = 1280;
    public static final Integer screenHeight = 720;
    public static final Integer startingDeckAmount = 60;
    public static final Integer startingCardAmount = 7;
    public static final Integer startingHealthAmount = 80;

    public static final String titleName = "Avatar Duel K03 G07";

    public static final String player1Color = "blue";
    public static final String player2Color = "red";

    public static final String player1Name = "Jovan";
    public static final String player2Name = "Jojo"; // nanti diganti bisa input

    private Settings() {
        throw new AssertionError("This is a utility class.");
    }
}
