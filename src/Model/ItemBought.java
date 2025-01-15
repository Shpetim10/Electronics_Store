package Model;

import javafx.beans.property.*;

public class ItemBought  implements InventoryManagement{
    //Are separated for creating table view
    private SimpleIntegerProperty productId;
    private SimpleStringProperty productName;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty sellingPrice;
    private SimpleDoubleProperty totalTax;
    private SimpleDoubleProperty totalPrice;

    //Used for Inventory Management
    private Item itemBought;

    public ItemBought(SimpleIntegerProperty productId, SimpleStringProperty productName,
                      SimpleIntegerProperty quantity, SimpleDoubleProperty sellingPrice) {
        this.productId = productId;
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        setTotalPrice();
        setTotalTax();
        setItem();
    }
    public ItemBought(int productId, String productName,
                      int quantity, double sellingPrice) {
        this.setProductId(productId);
        this.setProductName(productName);
        this.setSellingPrice(sellingPrice);
        this.setQuantity(quantity);
        setTotalPrice();
        setTotalTax();
        setItem();
    }
    public ItemBought(Item itemBought) {
        this.productId = new SimpleIntegerProperty(itemBought.getProductId());
        this.productName = new SimpleStringProperty(itemBought.getProductName());
        this.sellingPrice = new SimpleDoubleProperty(itemBought.getSellingPrice());
        this.quantity = new SimpleIntegerProperty(0);
        this.itemBought = itemBought;
        setTotalPrice();
        setTotalTax();
    }
    public int getProductId() {
        return productId.getValue();
    }

    public void setProductId(int productId) {
        this.productId=new SimpleIntegerProperty(productId);
        this.setItem();
    }

    public String getProductName() {
        return productName.getValue();
    }

    public void setProductName(String productName) {
        this.productName=new SimpleStringProperty(productName);
    }

    public int getQuantity() {
        return quantity.getValue();
    }

    public void setQuantity(int quantity) {
        this.quantity=new SimpleIntegerProperty(quantity);
        setTotalPrice();
        setTotalTax();
    }

    public double getSellingPrice() {
        return sellingPrice.getValue();
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice=new SimpleDoubleProperty(sellingPrice);
    }

    public double getTotalPrice(){
        return this.totalPrice.getValue();
    }
    public double getTotalTax(){
        return this.totalTax.getValue();
    }

    public void setTotalTax() {
        this.totalTax=new SimpleDoubleProperty(0.2*getTotalPrice());
    }

    public void setTotalPrice() {
        this.totalPrice=new SimpleDoubleProperty(getSellingPrice()*getQuantity());
    }
    public void setItem(){
        for(Item item:items){
            if(item.getProductId()==getProductId()){
                this.itemBought=item;
            }
        }
    }
    public Item getItem(){
        return this.itemBought;
    }
}
