package Exceptions;

public class UnauthorizedAccessException extends Exception {
    public UnauthorizedAccessException() {
        super("You are not authorized to access this system! ");
    }
}
