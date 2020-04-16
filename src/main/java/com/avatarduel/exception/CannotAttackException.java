package com.avatarduel.exception;

public class CannotAttackException extends Exception {
    public CannotAttackException(String msg){
        super(msg);
    }

    public CannotAttackException(){
        super();
    }
}
