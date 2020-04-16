package com.avatarduel.exception;

public class CannotAttackEmptyException extends CannotAttackException {
    public CannotAttackEmptyException() {
        super("There are still character cards!");
    }
}
