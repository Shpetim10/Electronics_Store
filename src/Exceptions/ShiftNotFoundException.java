package Exceptions;

public class ShiftNotFoundException extends Exception {
    public ShiftNotFoundException() {
        super("Shift not found! ");
    }
}
