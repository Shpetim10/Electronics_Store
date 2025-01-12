package Model;

import Exceptions.ItemNotFoundException;

public class ItemBought  implements InventoryManagement{
    //Are separated for creating table view
    private int productId;
    private String productName;
    private int quantity;
    private double sellingPrice;
    private double totalTax;
    private double totalPrice;

    //Used for Inventory Management
    private Item item;
    public ItemBought(int productId, String productName, int quantity, double sellingPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        setTotalPrice();
        setTotalTax();
        setItem();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setTotalPrice();
        setTotalTax();
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }
    public double getTotalTax(){
        return this.totalTax;
    }

    public void setTotalTax() {
        this.totalTax=0.2*getTotalPrice();
    }

    public void setTotalPrice() {
        this.totalPrice = sellingPrice*quantity;
    }
    public void setItem(){
        for(Item item:items){
            if(item.getProductId()==this.productId){
                this.item=item;
            }
        }
    }
    public Item getItem(){
        return this.item;
    }
}
