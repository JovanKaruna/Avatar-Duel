package com.avatarduel.exception;

public class NotEnoughPowerException extends Exception {
    public NotEnoughPowerException(){
        super("Not enough power to use this card");
    }
}
