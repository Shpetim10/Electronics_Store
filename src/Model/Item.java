package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Item implements Serializable {


    @Serial
    private static final long serialVersionUID = 6910665039320934429L;
    private SimpleIntegerProperty productId;
    private SimpleStringProperty productName;
    private SectorType sector;
    private SimpleStringProperty sectorType;
    private String description;
    private SimpleDoubleProperty sellingPrice;
    private SimpleDoubleProperty priceBought;
    private SimpleStringProperty supplierName;

    private IntegerProperty stockQuantity;
//    private boolean isLowStock;

    private String brand;
    //private String isDiscontinued;
    private LocalDate lastRestockDate;
  private SimpleStringProperty image;
    private SimpleIntegerProperty barcode;
    //private int nrOfReturns;
    

    public Item() {
    }

    public Item(int productId, String productName, SectorType sector,  double sellingPrice, double priceBought, String supplierName, int stockQuantity, String brand, LocalDate lastRestockDate,  int barcode) {
        this.setProductId(productId);
        this.setProductName(productName);
        this.sector=sector;
        this.setSellingPrice(sellingPrice);
        this.setPriceBought(priceBought);
        this.setSupplier(supplierName);
        this.setStockQuantity(stockQuantity);
        this.brand = brand;
        this.lastRestockDate = lastRestockDate;
        this.setBrand(brand);
        this.setBarcode(barcode);
    }



    public void incrementStock(int quantity){
        setStockQuantity(getStockQuantity()+quantity);
    } //Sh

    public void decrementStock(int quantity){
        setStockQuantity(getStockQuantity()-quantity);
    } //Sh



    public String getProductName() {
        return productName.getValue();
    }

    public void setProductName(String productName) {
        this.productName=new SimpleStringProperty(productName);

    }
    public int getProductId() {
        return productId.getValue();
    }

    public void setProductId(int productid) {
        this.productId = new SimpleIntegerProperty(productid);
    }



    public SectorType getSectorType() {
        return sector;
    }

    public void setSector(SectorType sector) {
        this.sector = sector;
    }

    public String getSector(){
        return sectorType.getValue();
    }
    public void setSector(String sector){
        this.sectorType=new SimpleStringProperty(sector);

    }


    public double getSellingPrice() {
        return sellingPrice.getValue();
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice =new  SimpleDoubleProperty(sellingPrice);
    }

    public double getPriceBought() {
        return priceBought.getValue();
    }

    public void setPriceBought(double priceBought) {
        this.priceBought = new  SimpleDoubleProperty(priceBought);
    }

    public String getSupplier() {
        return supplierName.getValue();
    }

    public void setSupplier(String supplier) {
        this.supplierName = new  SimpleStringProperty(supplier);
    }

//    public String isDiscounted() {
//        return isDiscounted;
//    }
//
//    public void setIsDiscounted(String isDiscounted) {
//        this.isDiscounted=isDiscounted;
//    }
//    public void setIsAvailable(String available) {
//        this.isAvailable=available;
//    }
//
//
//    public void setDiscounted(String discounted) {
//        this.isDiscounted = discounted;
//    }
//    public void setDiscountRate(Double discount){
//        this.discountRate=discount;
//    }



    public int getStockQuantity() {
        return stockQuantity.getValue();
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = new  SimpleIntegerProperty(stockQuantity);
    }

//    public boolean isLowStock() {
//        return isLowStock;
//    }
//
//    public void setLowStock(boolean lowStock) {
//        isLowStock = lowStock;
//    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }




//
//    public String isAvailable() {
//        return available;
//    }
//
//    public void setAvailable(String available) {
//        this.available = available;
//    }





    public LocalDate getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(LocalDate lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }



//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public int getBarcode() {
        return barcode.getValue();
    }

    public void setBarcode(Integer barcode) {
        this.barcode = new SimpleIntegerProperty(barcode);}
    }






