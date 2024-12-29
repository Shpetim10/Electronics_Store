package Exceptions;

public class LoyaltyCardNotFoundException extends Exception {
    public LoyaltyCardNotFoundException() {
        super("Loyalty card not found! ");
    }
}
