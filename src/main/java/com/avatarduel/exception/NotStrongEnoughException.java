package com.avatarduel.exception;

public class NotStrongEnoughException extends CannotAttackException {
    public NotStrongEnoughException(){
        super("Not strong enough to use this card");
    }
}

