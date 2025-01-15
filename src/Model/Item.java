package Model;

import Exceptions.OutOfStockException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Item implements Serializable {


    @Serial
    private static final long serialVersionUID = -183069447224098725L;
    private int productId;
    private String productName;
    private SectorType sector;
    private String description;
    private double sellingPrice;
    private double priceBought;
    private String supplierName;
    private String isDiscounted;
    private int stockQuantity;
    private boolean isLowStock;
    private double weight;
    private double volume;
    private int[] dimensions=new int[3];
    private String color;
    private String brand;
    private String isDiscontinued;
    private  String available;
    private LocalDate lastRestockDate;
    private double averageRating;
    private String image;
    private String barcode;
    private int nrOfReturns;
    

    public Item() {
    }
    public Item(int productCode, String productName, SectorType sector,
                double sellingPrice, double priceBought, String supplierName,
                 int stockQuantity,
                String brand, LocalDate lastRestockDate,
                String barcode,String image) {
        this.productId = productCode;
        this.productName = productName;
        this.sector = sector;
        this.sellingPrice = sellingPrice;
        this.priceBought = priceBought;
        this.supplierName = supplierName;

        this.stockQuantity = stockQuantity;
        this.brand = brand;
    this.image=image;
        this.lastRestockDate = lastRestockDate;
        this.barcode = barcode;
    }

    public Item(int productId, String productName, SectorType sector, String description, double sellingPrice,
                double priceBrought, String supplierName, String isDiscounted, double discountRate, int stockQuantity,
                boolean isLowStock, double weight, double volume, int[] dimensions, String color, String brand,
                int warrantyPeriod, String isDiscontinued, String isAvailable, LocalDate lastRestockDate,
                double averageRating, String image, String barcode, int nrOfReturns) {
        this.productId = productId;
        this.productName = productName;
        this.sector = sector;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.priceBought = priceBrought;
        this.supplierName = supplierName;
      this.isDiscontinued=isDiscontinued;
        this.stockQuantity = stockQuantity;
        this.isLowStock = isLowStock;
        this.weight = weight;
        this.volume = volume;
        this.dimensions = dimensions;
        this.color = color;
        this.brand = brand;
        this.lastRestockDate = lastRestockDate;
        this.averageRating = averageRating;
        this.image = image;
        this.barcode = barcode;
        this.nrOfReturns = nrOfReturns;
    }

    public Item(int productId, String productName, String brand, int stockQuantity, double priceBrought, double sellingPrice) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.stockQuantity = stockQuantity;
        this.priceBought = priceBrought;
        this.sellingPrice = sellingPrice;
    }

    //Useful Methods
    public void incrementStock(int quantity){
        setStockQuantity(getStockQuantity()+quantity);
    } //Sh

    public void decrementStock(int quantity){
        setStockQuantity(getStockQuantity()-quantity);
    } //Sh

    public void checkInventoryStockAvailable() throws OutOfStockException {
        if(this.getStockQuantity()==0){
            throw new OutOfStockException();
        }
    }//Sh

    // Getters and Setters
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

    public SectorType getSector() {
        return sector;
    }

    public void setSector(SectorType sector) {
        this.sector = sector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(double priceBought) {
        this.priceBought = priceBought;
    }

    public String getSupplier() {
        return supplierName;
    }

    public void setSupplier(String supplier) {
        this.supplierName = supplier;
    }

    public String isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(String discounted) {
        this.isDiscounted = discounted;
    }



    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuality) {
        this.stockQuantity = stockQuality;
    }

    public boolean isLowStock() {
        return isLowStock;
    }

    public void setLowStock(boolean lowStock) {
        isLowStock = lowStock;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getIsDiscontinued() {
        return isDiscontinued;
    }

    public void setIsDiscontinueded(String discontinueded) {
        this.isDiscontinued = discontinueded;
    }
    public String isAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }





    public LocalDate getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(LocalDate lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getNrOfReturns() {
        return nrOfReturns;
    }

    public void setNrOfReturns(int nrOfReturns) {
        this.nrOfReturns = nrOfReturns;
    }

}




