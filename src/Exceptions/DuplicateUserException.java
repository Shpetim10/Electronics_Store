package Exceptions;

public class DuplicateUserException extends Exception {
    public DuplicateUserException() {
        super("This username already exist!");
    }
}
