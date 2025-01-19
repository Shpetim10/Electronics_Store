package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class RestockTransaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 7799257874562112655L;
    private Item itemRestocked;
    private int quantity;
    private LocalDate transactionDate;


    public RestockTransaction(Item itemRestocked,int quantity){
        this.itemRestocked=itemRestocked;
        this.quantity=quantity;
        this.transactionDate=LocalDate.now();
    }

//Useful Methods
    public double calculateTotalRestockPayment(){
        return this.itemRestocked.getPriceBought()*quantity;
    }

    public Item getItemRestocked() {
        return itemRestocked;
    }

    public void setItemRestocked(Item itemRestocked) {
        this.itemRestocked = itemRestocked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
