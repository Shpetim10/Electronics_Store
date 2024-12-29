package Model;

public class ItemBought  {
    private Item item;
    private int quantity;

    public ItemBought(){};
    public ItemBought(Item item,int quantity)
    {
        this.item=item;
        this.quantity=quantity;
    }

    public Item getItem() {return item;}
    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalProductPrice(){};//GET TOTAL PRICE
    public double getTotalTaxRate(){};//GET TOTAL TAX
}
