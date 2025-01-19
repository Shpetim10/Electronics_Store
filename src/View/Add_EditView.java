package View;

import Model.Item;
import Model.SectorType;
import Model.Supplier;
import View.Design;
import View.SearchBoxPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Add_EditView implements Design {
    SearchBoxPane search=new SearchBoxPane();

    private CheckBox select;
    private TextField productCode;
    private TextField productName;
    private TextField sector;
   // private TextField description;
    private TextField sellingPrice;
    private TextField priceBought;
    private TextField supplier;
   // private ComboBox<String> isDiscounted;
   // private TextField discountRate;
    private TextField stockQuantity;
//    private TextField weight;
//    private TextField volume;
//    private TextField color;
    private TextField brand;
//    private ComboBox<String> isDiscontinued;
//    private ComboBox<String> isAvailable;
    private DatePicker lastRestockDate=createDatePicker("Restock date...");
    private TextField barcode;
    private TextField nrOfReturns;
    private Button add;
    private Button edit;
    private TableView<Item> table;
    private TextField image;


    public Add_EditView() {
        this.select=createCheckBox();
        this.productCode = createTextField("Product Id");
        this.productName = createTextField("Product Name");
        this.sector = createTextField("Sector");
        //this.description = createTextField("Description");
        this.sellingPrice = createTextField("Selling Price");
        this.priceBought = createTextField("Price Bought");
        this.supplier = createTextField("Supplier");
//        this.isDiscounted = createComboBox("Is Discounted");
//        isDiscounted.getItems().addAll("Yes","No");
//        this.discountRate = createTextField("Discount Rate");
        this.stockQuantity = createTextField("Quantity");
//        this.weight = createTextField("Weight");
//        this.volume = createTextField("Volume");
//        this.color = createTextField("Color");
        this.brand = createTextField("Brand");
//        this.isDiscontinued = createComboBox("Is Discontinued");
//        isDiscontinued.getItems().addAll("Yes","No");
//        this.isAvailable = createComboBox("Is Available");
//        isAvailable.getItems().addAll("Yes","No");
        //this.lastRestockDate = createTextField("Last Restock Date");
        this.barcode = createTextField("Barcode");
        this.nrOfReturns = createTextField("Number of Returns");
        this.add=createGeneralButton("Add Product");
        this.edit=createGeneralButton("Edit Product");

        this.table = new TableView<>();
        this.image=createTextField("Image URL");

    }


    public Scene createScene() {
        GridPane inventory = new GridPane();
        inventory.setHgap(20);
        inventory.setVgap(10);
        inventory.setPadding(new Insets(50, 100, 50, 100));
        inventory.setStyle("-fx-background-color: rgba(167,246,8,0.15)");

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
        grid.add(createAlignedGreenBoldLabel("Quantity: ", 100), 4, 2);
        grid.add(stockQuantity, 5, 2);

        grid.add(createAlignedGreenBoldLabel("Last Restock Date: ", 200), 0, 3);
        grid.add(lastRestockDate, 1, 3);
        grid.add(createAlignedGreenBoldLabel("Image URL ", 150), 2, 3);
       grid.add(image, 3, 3);

//        grid.add(createAlignedGreenBoldLabel("Discount Rate: ", 150), 4, 3);
//        grid.add(discountRate, 5, 3);

      //grid.add(add, 4, 4);
       grid.add(add, 5, 7);



        Label label=createAlignedGreenBoldLabel("Products Management",200);
        label.setPrefSize(200,100);

        HBox searchBox = new HBox(100);

            Image image = new Image("file:C:/Users/user/Downloads/logo.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);


        searchBox.getChildren().addAll( label);
       inventory.add(imageView,2,0);
        inventory.add(searchBox,1,0);
        //inventory.add(table,1,1);
       inventory.add(grid,1,8);


        Scene scene = new Scene(inventory);
        return scene;

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

    public TextField getSector() {
        return sector;
    }

    public void setSector(TextField sector) {
        this.sector = sector;
    }

//    public TextField getDescription() {
//        return description;
//    }
//
//    public void setDescription(TextField description) {
//        this.description = description;
//    }

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

//    public ComboBox<String> getIsDiscounted() {
//        return isDiscounted;
//    }

//    public void setIsDiscounted(ComboBox<String> isDiscounted) {
//        this.isDiscounted = isDiscounted;
//    }
//
//    public TextField getDiscountRate() {
//        return discountRate;
//    }
//
//    public void setDiscountRate(TextField discountRate) {
//        this.discountRate = discountRate;
//    }

    public TextField getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(TextField stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

//    public TextField getWeight() {
//        return weight;
//    }
//
//    public void setWeight(TextField weight) {
//        this.weight = weight;
//    }
//
//    public TextField getVolume() {
//        return volume;
//    }
//
//    public void setVolume(TextField volume) {
//        this.volume = volume;
//    }
//
//    public TextField getColor() {
//        return color;
//    }
//
//    public void setColor(TextField color) {
//        this.color = color;
//    }

    public TextField getBrand() {
        return brand;
    }

    public void setBrand(TextField brand) {
        this.brand = brand;
    }

//    public ComboBox<String> getIsDiscontinued() {
//        return isDiscontinued;
//    }
//
//    public void setIsDiscontinued(ComboBox<String> isDiscontinued) {
//        this.isDiscontinued = isDiscontinued;
//    }
//
//    public ComboBox<String> getIsAvailable() {
//        return isAvailable;
//    }
//
//    public void setIsAvailable(ComboBox<String> isAvailable) {
//        this.isAvailable = isAvailable;
//    }

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

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
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
