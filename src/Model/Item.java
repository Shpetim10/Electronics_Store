package Model;

import java.util.Date;

public class Item {
    private int productId;
    private String productName;
    private SectorType sector;
    private String description;
    private double sellingPrice;
    private double priceBrought;
    private Supplier supplier;
    private boolean isDiscounted;
    private double discountRate;
    private int stockQuality;
    private boolean isLowStock;
    private double weight;
    private double volume;
    private int[] dimensions=new int[3];
    private String color;
    private String brand;
    private int warrantyPeriod;
    private boolean isDiscontinued;
    private boolean isAvailable;
    private Date lastRestockDate;
    private double AverageRating;
    private String image;
    private String barcode;
    private int nrOfReturns;



    public Item() {
    }

    public Item(int productId, String productName, SectorType sector, String description, double sellingPrice,
                double priceBrought, Supplier supplier, boolean isDiscounted, double discountRate, int stockQuality,
                boolean isLowStock, double weight, double volume, int[] dimensions, String color, String brand,
                int warrantyPeriod, boolean isDiscontinued, boolean isAvailable, Date lastRestockDate,
                double averageRating, String image, String barcode, int nrOfReturns) {
        this.productId = productId;
        this.productName = productName;
        this.sector = sector;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.priceBrought = priceBrought;
        this.supplier = supplier;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
        this.stockQuality = stockQuality;
        this.isLowStock = isLowStock;
        this.weight = weight;
        this.volume = volume;
        this.dimensions = dimensions;
        this.color = color;
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
        this.isDiscontinued = isDiscontinued;
        this.isAvailable = isAvailable;
        this.lastRestockDate = lastRestockDate;
        this.averageRating = averageRating;
        this.image = image;
        this.barcode = barcode;
        this.nrOfReturns = nrOfReturns;
    }

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

    public double getPriceBrought() {
        return priceBrought;
    }

    public void setPriceBrought(double priceBrought) {
        this.priceBrought = priceBrought;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean discounted) {
        isDiscounted = discounted;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getStockQuality() {
        return stockQuality;
    }

    public void setStockQuality(int stockQuality) {
        this.stockQuality = stockQuality;
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

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public boolean isDiscontinued() {
        return isDiscontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        isDiscontinued = discontinued;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(Date lastRestockDate) {
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




