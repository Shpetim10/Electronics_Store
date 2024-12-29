package Exceptions;

public class InvalidRefundRequestException extends Exception {
    public InvalidRefundRequestException() {
        super("Your refund was declined!");
    }
}
