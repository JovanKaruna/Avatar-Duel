package com.avatarduel.model.phase;

public enum Phase {
    DRAW, MAIN, BATTLE, END;

    private static Phase[] phases = values();
    public Phase next(){
        return phases[(this.ordinal()+1) % phases.length];
    }
}
