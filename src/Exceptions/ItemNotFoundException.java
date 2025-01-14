package Exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String name) {
        super("Item "+name+" does not exist! ");
    }

}
