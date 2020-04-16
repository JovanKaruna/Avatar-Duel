package com.avatarduel.exception;

public class NotEnoughStrongException extends CannotAttackException {
    public NotEnoughStrongException(){
        super("Not strong enough to use this card");
    }
}
