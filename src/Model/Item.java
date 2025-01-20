package Model;

import Exceptions.OutOfStockException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.*;
import java.io.*;
import java.time.LocalDate;

public class Item implements Serializable {


    @Serial
    private static final long serialVersionUID = -7535246308595776385L;
    private transient SimpleIntegerProperty productId;
    private transient SimpleStringProperty productName;
    private transient SimpleStringProperty sector;
    private transient SimpleDoubleProperty sellingPrice;
    private transient SimpleDoubleProperty priceBought;
    private transient SimpleStringProperty supplierName;
    private transient SimpleIntegerProperty stockQuantity;
    private transient SimpleStringProperty image;
    private String brand;
    private LocalDate lastRestockDate;
    private transient SimpleIntegerProperty barcode;

    // Constructors
    public Item() {
        this.productId = new SimpleIntegerProperty();
        this.productName = new SimpleStringProperty();
        this.sellingPrice = new SimpleDoubleProperty();
        this.priceBought = new SimpleDoubleProperty();
        this.supplierName = new SimpleStringProperty();
        this.stockQuantity = new SimpleIntegerProperty();
        this.barcode = new SimpleIntegerProperty();
        this.image=new SimpleStringProperty();
    }

    public Item(int productId, String productName, String sector, double sellingPrice, double priceBought,
                String supplierName, int stockQuantity, String brand, LocalDate lastRestockDate, int barcode,String image) {
        this();
        setProductId(productId);
        setProductName(productName);
        setSector(sector);
        setSellingPrice(sellingPrice);
        setPriceBought(priceBought);
        setSupplier(supplierName);
        setStockQuantity(stockQuantity);
        setBrand(brand);
        setLastRestockDate(lastRestockDate);
        setBarcode(barcode);
        setImage(image);
    }

    // Serialization methods
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(productId.get());
        out.writeUTF(productName.getValueSafe());
        out.writeUTF(sector.getValueSafe());
        out.writeDouble(sellingPrice.get());
        out.writeDouble(priceBought.get());
        out.writeUTF(supplierName.getValueSafe());
        out.writeInt(stockQuantity.get());
        out.writeUTF(brand);
        out.writeObject(lastRestockDate);
        out.writeInt(barcode.get());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        productId = new SimpleIntegerProperty(in.readInt());
        productName = new SimpleStringProperty(in.readUTF());
        sector = new SimpleStringProperty(in.readUTF()) ;
        sellingPrice = new SimpleDoubleProperty(in.readDouble());
        priceBought = new SimpleDoubleProperty(in.readDouble());
        supplierName = new SimpleStringProperty(in.readUTF());
        stockQuantity = new SimpleIntegerProperty(in.readInt());
        brand = in.readUTF();
        lastRestockDate = (LocalDate) in.readObject();
        barcode = new SimpleIntegerProperty(in.readInt());
    }

    // Properties and Getters/Setters
    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getSector() {
        return sector.getValueSafe();
    }

    public void setSector(String sector) {
        this.sector = new SimpleStringProperty(sector);
    }

    public double getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice.set(sellingPrice);
    }

    public double getPriceBought() {
        return priceBought.get();
    }

    public void setPriceBought(double priceBought) {
        this.priceBought.set(priceBought);
    }

    public String getSupplier() {
        return supplierName.get();
    }

    public void setSupplier(String supplierName) {
        this.supplierName.set(supplierName);
    }

    public int getStockQuantity() {
        return stockQuantity.get();
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
        this.stockQuantity.set(stockQuantity);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be null or blank.");
        }
        this.brand = brand;
    }

    public LocalDate getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(LocalDate lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }

    public int getBarcode() {
        return barcode.get();
    }

    public void setBarcode(int barcode) {
        this.barcode.set(barcode);
    }

    // Inventory Management
    public void incrementStock(int quantity) {
        setStockQuantity(getStockQuantity() + quantity);
    }

    public void decrementStock(int quantity) {
        if (quantity > getStockQuantity()) {
            throw new IllegalArgumentException("Insufficient stock to decrement.");
        }
        setStockQuantity(getStockQuantity() - quantity);
    }

    public void checkInventoryStockAvailable() throws OutOfStockException {
        if (getStockQuantity() == 0) {
            throw new OutOfStockException();
        }
    }

    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    // Utility
    @Override
    public String toString() {
        return String.format(
                "Item [productId=%d, productName=%s, sector=%s, sellingPrice=%.2f, stockQuantity=%d, brand=%s, barcode=%d]",
                getProductId(), getProductName(), sector, getSellingPrice(), getStockQuantity(), getBrand(), getBarcode());
}
}