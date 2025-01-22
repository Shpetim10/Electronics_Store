package Exceptions;

public class DifferentSectorForItemCashier extends Exception{
    public DifferentSectorForItemCashier(){
        super("This item does not correspond to this sector!");
    }
}
