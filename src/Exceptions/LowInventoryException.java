package Exceptions;

public class LowInventoryException extends Exception {
    public LowInventoryException() {
        super("This item is almost out of stock! ");
    }
}
