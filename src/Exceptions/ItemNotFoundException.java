package Exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("This item is out of stock! ");
    }
}
