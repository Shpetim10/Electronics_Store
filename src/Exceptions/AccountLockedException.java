package Exceptions;

public class AccountLockedException extends Exception {
    public AccountLockedException() {
        super("Your account has been temporary disabled due to multiple attempts or failed login! ");
    }
}
