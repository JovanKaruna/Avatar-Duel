package com.avatarduel.exception;

public class NoCharaterOnFieldException extends CannotSummonCardException {
    public NoCharaterOnFieldException(){
        super("No character on field, cannot summon skill");
    }
}
