package com.avatarduel.exception;

public class FieldCellIsOccupiedException extends CannotSummonCardException {
    public FieldCellIsOccupiedException() {
        super("Field cell is occupied by another card");
    }
}
