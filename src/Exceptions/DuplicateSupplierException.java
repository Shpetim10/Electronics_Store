package Exceptions;

public class DuplicateSupplierException extends Exception {
    public DuplicateSupplierException() {
        super("Supplier already exists! ");
    }
}
