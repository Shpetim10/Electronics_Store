package Exceptions;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
        super("We do not have this item! ");
    }
}
