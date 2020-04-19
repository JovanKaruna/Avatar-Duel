package com.avatarduel;

import javafx.scene.paint.Color;

// Utility Pattern
public class Settings {
    public static final String titleName = "Avatar Duel K03 G07";
    public static final Integer screenWidth = 1280;
    public static final Integer screenHeight = 720;
    public static final Integer startingCardAmount = 7;


    public static final Color cardBackColor = new Color(0.5, 0.63, 1, 1);

    public static final String player1Color = "blue";
    public static final String player2Color = "red";

    public static final String player1Name = "Player 1 Name";
    public static final String player2Name = "Player 2 Name"; // nanti diganti bisa input

    public static final Integer startingHealthAmount = 80;
    public static final Integer startingDeckAmount = 60;
    public static final Integer maximumHandCard = 9;
    public static final Integer numberOfPlayers = 2;

    private Settings() {
        throw new AssertionError("This is a utility class.");
    }
}
