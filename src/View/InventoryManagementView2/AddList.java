//package View.InventoryManagementView2;
//
//import View.Design;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.Label;
//
//public class AddList implements Design {
//
//    private Label sellingPrice;
//    private Label brand;
//    private CheckBox select;
//    private Label productCode;
//    private Label productName;
//    private Label sector;
//    private Label description;
//    private Label priceBought;
//    private Label supplier;
//    private Label discountRate;
//    private Label stockQuantity;
//    private Label weight;
//    private Label volume;
//    private Label color;
//    private Label lastRestockDate;
//    private Label barcode;
//    private Label nrOfReturns;
//    private CheckBox isDiscounted;
//    private CheckBox isDiscontinued;
//    private CheckBox isAvailable;
//
//    public AddList(  double sellingPrice, String brand,
//                             String productCode, String productName, String sector, String description, double priceBought,
//                             String supplier, boolean isDiscounted, double discountRate, int stockQuantity, double weight,
//                             double volume, String color, boolean isDiscontinued, boolean isAvailable,
//                             String lastRestockDate, String barcode, int nrOfReturns) {
//
//        //Ktu eshte mire te fusesh si input (Item item) si objekt
//        //athere ta marre si parameter ktu item
//        //Lere njeher kshu derisa te marrim eventetet
//        this.select=createCheckBox();
//        this.sellingPrice = createAlignedBlackLabel(String.valueOf(sellingPrice), 180);
//        this.brand = createAlignedBlackLabel(brand, 150);
//        this.select = createCheckBox();
//        this.productCode = createAlignedBlackLabel(productCode, 150);
//        this.productName = createAlignedBlackLabel(productName, 150);
//        this.sector = createAlignedBlackLabel(sector, 150);
//        this.description = createAlignedBlackLabel(description, 250);
//        this.priceBought = createAlignedBlackLabel(String.valueOf(priceBought), 150);
//        this.supplier = createAlignedBlackLabel(supplier, 150);
//        this.discountRate = createAlignedBlackLabel(String.valueOf(discountRate), 150);
//        this.stockQuantity = createAlignedBlackLabel(String.valueOf(stockQuantity), 150);
//        this.weight = createAlignedBlackLabel(String.valueOf(weight), 150);
//        this.volume = createAlignedBlackLabel(String.valueOf(volume), 150);
//        this.color = createAlignedBlackLabel(color, 150);
//        this.lastRestockDate = createAlignedBlackLabel(lastRestockDate, 150);
//        this.barcode = createAlignedBlackLabel(barcode, 150);
//        this.nrOfReturns = createAlignedBlackLabel(String.valueOf(nrOfReturns), 150);
//
//        this.isDiscounted = createCheckBox();
//        //this.isDiscontinued.setAllowIndeterminate(false);
//        this.isDiscounted.setSelected(isDiscounted);
////Duhet ber qe te mos ndryshohet
//        this.isDiscontinued = createCheckBox();
//        this.isDiscontinued.setSelected(isDiscontinued);
//        this.isAvailable = createCheckBox();
//        this.isAvailable.setSelected(isAvailable);
//    }
//
//
//
//
//    public Label getSellingPrice() { return sellingPrice; }
//    public void setSellingPrice(Label sellingPrice) { this.sellingPrice = sellingPrice; }
//
//    public Label getBrand() { return brand; }
//    public void setBrand(Label brand) { this.brand = brand; }
//
//    public CheckBox getSelect() { return select; }
//    public void setSelect(CheckBox select) { this.select = select; }
//
//    public Label getProductCode() { return productCode; }
//    public void setProductCode(Label productCode) { this.productCode = productCode; }
//
//    public Label getProductName() { return productName; }
//    public void setProductName(Label productName) { this.productName = productName; }
//
//    public Label getSector() { return sector; }
//    public void setSector(Label sector) { this.sector = sector; }
//
//    public Label getDescription() { return description; }
//    public void setDescription(Label description) { this.description = description; }
//
//    public Label getPriceBought() { return priceBought; }
//    public void setPriceBought(Label priceBought) { this.priceBought = priceBought; }
//
//    public Label getSupplier() { return supplier; }
//    public void setSupplier(Label supplier) { this.supplier = supplier; }
//
//    public Label getDiscountRate() { return discountRate; }
//    public void setDiscountRate(Label discountRate) { this.discountRate = discountRate; }
//
//    public Label getStockQuantity() { return stockQuantity; }
//    public void setStockQuantity(Label stockQuantity) { this.stockQuantity = stockQuantity; }
//
//    public Label getWeight() { return weight; }
//    public void setWeight(Label weight) { this.weight = weight; }
//
//    public Label getVolume() { return volume; }
//    public void setVolume(Label volume) { this.volume = volume; }
//
//    public Label getColor() { return color; }
//    public void setColor(Label color) { this.color = color; }
//
//    public Label getLastRestockDate() { return lastRestockDate; }
//    public void setLastRestockDate(Label lastRestockDate) { this.lastRestockDate = lastRestockDate; }
//
//    public Label getBarcode() { return barcode; }
//    public void setBarcode(Label barcode) { this.barcode = barcode; }
//
//    public Label getNrOfReturns() { return nrOfReturns; }
//    public void setNrOfReturns(Label nrOfReturns) { this.nrOfReturns = nrOfReturns; }
//
//    public CheckBox getIsDiscounted() { return isDiscounted; }
//    public void setIsDiscounted(CheckBox isDiscounted) { this.isDiscounted = isDiscounted; }
//
//    public CheckBox getIsDiscontinued() { return isDiscontinued; }
//    public void setIsDiscontinued(CheckBox isDiscontinued) { this.isDiscontinued = isDiscontinued; }
//
//    public CheckBox getIsAvailable() { return isAvailable; }
//    public void setIsAvailable(CheckBox isAvailable) { this.isAvailable = isAvailable; }
//}
