package com.avatarduel.exception;

public class NotInAttackStanceException extends CannotAttackException {
    public NotInAttackStanceException(){
        super("Card is not in attack position");
    }
}
