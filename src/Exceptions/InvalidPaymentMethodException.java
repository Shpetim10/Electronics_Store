package Exceptions;

public class InvalidPaymentMethodException extends Exception {
    public InvalidPaymentMethodException() {
        super("Payment was declined! Please Try Again! ");
    }
}
