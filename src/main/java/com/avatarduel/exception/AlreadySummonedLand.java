package com.avatarduel.exception;

public class AlreadySummonedLand extends CannotSummonCardException {
    public AlreadySummonedLand() {
        super("Already summoned land this turn");
    }
}
