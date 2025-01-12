package Exceptions;

public class InsuffitientStockException extends Exception {
    public InsuffitientStockException() {
        super("There is an insuffitient stock for this item! ");
    }
}
