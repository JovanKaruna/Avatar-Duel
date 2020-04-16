package com.avatarduel.exception;

public class JustSummonedException extends CannotAttackException {
    public JustSummonedException(){
        super("Just summoned this card");
    }
}
