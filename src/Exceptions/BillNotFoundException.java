package Exceptions;

public class BillNotFoundException extends Exception {
    public BillNotFoundException() {
        super("Billing document not found! ");
    }
}
