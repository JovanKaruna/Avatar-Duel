package com.avatarduel.model;

import com.avatarduel.PlayerInfo;
import com.avatarduel.Settings;
import com.avatarduel.model.phase.Phase;

import java.util.TreeMap;

public final class GameInfo {
    private GameInfo(){
        throw new AssertionError("This is a utility class");
    }

    private static Integer playerTurn = 1;
    private static Integer turn = 1;
    private static Phase phase = Phase.DRAW;
    private static TreeMap<Integer, PlayerInfo> players = new TreeMap<>();

    public static void addPlayer(Integer id, PlayerInfo info){
        players.put(id, info);
    }

    public static PlayerInfo getPlayer(Integer id){
        return players.get(id);
    }

    public static Integer getPlayerTurn() {
        return playerTurn;
    }

    public static Integer getTurn() {
        return turn;
    }

    public static Phase getPhase() {
        return phase;
    }

    public static void nextPhase() {
        GameInfo.phase = GameInfo.phase.next();
    }

    public static void nextTurn() {
        GameInfo.playerTurn %= Settings.numberOfPlayers;
        GameInfo.playerTurn++;
        if (GameInfo.playerTurn == 1) {
            GameInfo.turn++;
        }
    }

    public static boolean isMainPhase(){
        return GameInfo.phase.equals(Phase.MAIN1) || GameInfo.phase.equals(Phase.MAIN2);
    }

    public static boolean isBattlePhase(){
        return GameInfo.phase.equals(Phase.BATTLE);
    }

    public static boolean isEndPhase(){
        return GameInfo.phase.equals(Phase.END);
    }
}
