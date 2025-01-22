package Model;

import Database.Database;
import javafx.beans.property.*;

import java.io.*;

public class ItemBought implements InventoryManagement, Serializable {


    @Serial
    private static final long serialVersionUID = 742948372676824199L;
    private transient SimpleIntegerProperty productId;
    private transient SimpleStringProperty productName;
    private transient SimpleIntegerProperty quantity;
    private transient SimpleDoubleProperty sellingPrice;
    private transient SimpleDoubleProperty totalTax;
    private transient SimpleDoubleProperty totalPrice;

    private Item itemBought;

    public ItemBought(int productId, String productName, int quantity, double sellingPrice) {
        this.productId = new SimpleIntegerProperty(productId);
        this.productName = new SimpleStringProperty(productName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.sellingPrice = new SimpleDoubleProperty(sellingPrice);
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
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(productId.getValue());
        out.writeUTF(productName.getValueSafe());
        out.writeInt(quantity.getValue());
        out.writeDouble(sellingPrice.getValue());
        out.writeDouble(totalTax.getValue());
        out.writeDouble(totalPrice.getValue());
        out.writeObject(itemBought);
    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        productId = new SimpleIntegerProperty(in.readInt());
        productName = new SimpleStringProperty((String) in.readUTF());
        quantity = new SimpleIntegerProperty(in.readInt());
        sellingPrice = new SimpleDoubleProperty(in.readDouble());
        totalTax = new SimpleDoubleProperty(in.readDouble());
        totalPrice = new SimpleDoubleProperty(in.readDouble());
        setItem();
    }

    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
        setItem();
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
        setTotalPrice();
        setTotalTax();
    }

    public double getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice.set(sellingPrice);
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public double getTotalTax() {
        return totalTax.get();
    }

    private void setTotalTax() {
        this.totalTax = new SimpleDoubleProperty(0.2 * getTotalPrice());
    }

    private void setTotalPrice() {
        this.totalPrice = new SimpleDoubleProperty(getSellingPrice() * getQuantity());
    }

    private void setItem() {
        if (Database.getDatabase() != null) {
            for (Item item : Database.getDatabase().getInventory()) {
                if (item.getProductId() == getProductId()) {
                    this.itemBought = item;
                    return;
                }
            }
        }
        this.itemBought = null;
    }

    public Item getItem() {
        setItem();
        return this.itemBought;
    }
}
