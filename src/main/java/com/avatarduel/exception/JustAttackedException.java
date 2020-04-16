package com.avatarduel.exception;

public class JustAttackedException extends CannotAttackException {
    public JustAttackedException(){
        super("This card just attacked!");
    }
}
