package Exceptions;

public class InvalidCredentialsExceptions extends Exception {
    public InvalidCredentialsExceptions() {
        super("The username or password you have entered is invalid.Please try again! ");
    }
}
