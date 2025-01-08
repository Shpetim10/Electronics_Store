package Exceptions;

public class OutOfStockException extends Exception {
    public OutOfStockException() {
        super("This item is out of Stock");
    }
}
