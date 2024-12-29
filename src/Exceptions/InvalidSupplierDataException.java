package Exceptions;

public class InvalidSupplierDataException extends Exception {
    public InvalidSupplierDataException() {
        super("Supplier data does not exist! ");
    }
}
