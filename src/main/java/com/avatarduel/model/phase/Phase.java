package com.avatarduel.model.phase;

public enum Phase {
    DRAW, MAIN1, BATTLE, MAIN2, END;

    private static Phase[] phases = values();
    public Phase next(){
        return phases[(this.ordinal()+1) % phases.length];
    }
}
