package Exceptions;

public class InvalidItemDataException extends Exception {
    public InvalidItemDataException() {
        super("Invalid item data detected! ");
    }
}
