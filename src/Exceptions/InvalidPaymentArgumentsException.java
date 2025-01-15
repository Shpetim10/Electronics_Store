package Exceptions;

public class InvalidPaymentArgumentsException extends Exception {
    public InvalidPaymentArgumentsException() {
        super("Payment was declined! Please fill fields correctly! ");
    }
    public InvalidPaymentArgumentsException(double amount){
        super(amount+" are not enough to process payment!");
    }
}
