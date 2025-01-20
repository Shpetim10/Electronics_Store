package View;

import Model.Item;
import Model.SectorType;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddProductView extends GridPane implements Design {
    SearchBoxPane search=new SearchBoxPane();
    private CheckBox select;
    private TextField productCode;
    private TextField productName;
    private ComboBox<String> sector;
    private TextField sellingPrice;
    private TextField priceBought;
    private TextField supplier;
    private TextField stockQuantity;
    private TextField brand;
    private DatePicker lastRestockDate=createDatePicker("Restock date...");
    private TextField barcode;
    private TextField nrOfReturns;
    private Button add;
    private Button edit;
    private TableView<Item> table;
    private TextField image;


    public AddProductView() {
        this.select=createCheckBox();
        this.productCode = createTextField("Product Code");
        this.productName = createTextField("Product Name");
        this.sector = createComboBox("Sector");
        this.sector.setItems(FXCollections.observableArrayList(
                SectorType.ELECTRONICS.toString(),
                SectorType.ACCESSORIES.toString(),
                SectorType.HOME_APPLIANCES.toString(),
                SectorType.MOBILE_DEVICES.toString(),
                SectorType.CAMERAS.toString(),
                SectorType.COMPUTERS.toString(),
                SectorType.GAMING.toString(),
                SectorType.KITCHEN_ELECTRONICS.toString(),
                SectorType.SMART_HOME.toString()
        ));

        this.sellingPrice = createTextField("Selling Price");
        this.priceBought = createTextField("Price Bought");
        this.supplier = createTextField("Supplier");
        this.stockQuantity = createTextField("Quantity");
        this.brand = createTextField("Brand");
        this.barcode = createTextField("Barcode");
        this.nrOfReturns = createTextField("Number of Returns");
        this.add=createGeneralButton("Add Product");
        this.edit=createGeneralButton("Edit Product");
        this.table = new TableView<>();
        this.image=createTextField("Image URL");
        setUpView();

    }

    public void  setUpView() {

        this.setHgap(20);
        this.setVgap(10);
        this.setPadding(new Insets(50, 100, 50, 100));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15)");

        HBox buttons=new HBox(30);
        buttons.getChildren().addAll(add,edit);

       GridPane grid=new GridPane();
       grid.setVgap(25);
      grid.setHgap(25);

        grid.add(createAlignedGreenBoldLabel("Barcode: ", 100), 0, 0);
        grid.add(barcode, 1, 0);
        grid.add(createAlignedGreenBoldLabel("Product Code: ", 150), 2, 0);
        grid.add(productCode, 3, 0);
        grid.add(createAlignedGreenBoldLabel("Product Name: ", 150), 4, 0);
        grid.add(productName, 5, 0);
        grid.add(createAlignedGreenBoldLabel("Brand: ", 100), 0, 1);
        grid.add(brand, 1, 1);
        grid.add(createAlignedGreenBoldLabel("Sector: ", 100), 2, 1);
        grid.add(sector, 3, 1);
        grid.add(createAlignedGreenBoldLabel("Supplier: ", 100), 4, 1);
        grid.add(supplier, 5, 1);
        grid.add(createAlignedGreenBoldLabel("Price Bought: ", 150), 0, 2);
        grid.add(priceBought, 1, 2);
        grid.add(createAlignedGreenBoldLabel("Selling Price: ", 150), 2, 2);
        grid.add(sellingPrice, 3, 2);
        grid.add(createAlignedGreenBoldLabel(" Quantity: ", 100), 4, 2);
        grid.add(stockQuantity, 5, 2);
        grid.add(createAlignedGreenBoldLabel("Last Restock Date: ", 200), 0, 3);
        grid.add(lastRestockDate, 1, 3);
        grid.add(createAlignedGreenBoldLabel("Image URL ", 150), 2, 3);
       grid.add(image, 3, 3);
       grid.add(add, 5, 7);

        Label label=createAlignedGreenBoldLabel("Products Management",200);
        label.setPrefSize(200,100);

        HBox searchBox = new HBox(100);

            Image image = new Image("file:C:/Users/user/Downloads/logo.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);

        searchBox.getChildren().addAll( label);
       this.add(imageView,2,0);
        this.add(searchBox,1,0);
       this.add(grid,1,8);

    }
    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public TextField getProductCode() {
        return productCode;
    }

    public void setProductCode(TextField productCode) {
        this.productCode = productCode;
    }

    public TextField getProductName() {
        return productName;
    }

    public void setProductName(TextField productName) {
        this.productName = productName;
    }

    public ComboBox<String> getSector() {
        return sector;
    }

    public void setSector(ComboBox<String> sector) {
        this.sector = sector;
    }



    public TextField getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(TextField sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public TextField getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(TextField priceBought) {
        this.priceBought = priceBought;
    }

    public TextField getSupplier() {
        return supplier;
    }

    public void setSupplier(TextField supplier) {
        this.supplier = supplier;
    }


    public TextField getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(TextField stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public TextField getBrand() {
        return brand;
    }

    public void setBrand(TextField brand) {
        this.brand = brand;
    }


    public DatePicker getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(DatePicker lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }

    public TextField getBarcode() {
        return barcode;
    }

    public void setBarcode(TextField barcode) {
        this.barcode = barcode;
    }

    public TextField getNrOfReturns() {
        return nrOfReturns;
    }

    public void setNrOfReturns(TextField nrOfReturns) {
        this.nrOfReturns = nrOfReturns;
    }

    public Button getAdd() {
        return add;
    }

    public void setAdd(Button add) {
        this.add = add;
    }


    public TableView<Item> getTable() {
        return table;
    }

    public void setTable(TableView<Item> table) {
        this.table = table;
    }
    public TextField getImage(){
        return image;
    }




}
