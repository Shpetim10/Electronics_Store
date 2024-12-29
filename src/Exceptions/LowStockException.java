package Exceptions;

public class LowStockException extends Exception {
    public LowStockException() {
        super("Low Stock, you need to restock! ");
    }
}
