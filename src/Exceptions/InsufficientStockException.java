package Exceptions;

public class InsufficientStockException extends Exception {
    public InsufficientStockException() {
        super("There is an insuffitient stock for this item! ");
    }
}
