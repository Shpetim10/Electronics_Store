package Exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("This user does not exist! ");
    }
}
