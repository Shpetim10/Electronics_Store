package Exceptions;

public class LowInventoryException extends RuntimeException {
    public LowInventoryException() {
        super("This item is almost out of stock");
    }
}
