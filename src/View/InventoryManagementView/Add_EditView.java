package View.InventoryManagementView;

import Model.Item;
import Model.SectorType;
import Model.Supplier;
import View.Design;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Add_EditView implements Design {
    private final ComboBox<String> search;
    private CheckBox select;
    private TextField productCode;
    private TextField productName;
    private TextField sector;
    private TextField description;
    private TextField sellingPrice;
    private TextField priceBought;
    private TextField supplier;
    private ComboBox<String> isDiscounted;
    private TextField discountRate;
    private TextField stockQuantity;
    private TextField weight;
    private TextField volume;
    private TextField color;
    private TextField brand;
    private ComboBox<String> isDiscontinued;
    private ComboBox<String> isAvailable;
    private TextField lastRestockDate;
    private TextField barcode;
    private TextField nrOfReturns;
    private Button add;
    private Button edit;
    private TableView<Item> table;


    public Add_EditView() {
        this.select=createCheckBox();
        this.productCode = createTextField("Product Code");
        this.productName = createTextField("Product Name");
        this.sector = createTextField("Sector");
        this.description = createTextField("Description");
        this.sellingPrice = createTextField("Selling Price");
        this.priceBought = createTextField("Price Bought");
        this.supplier = createTextField("Supplier");
        this.isDiscounted = createComboBox("Is Discounted");
        this.discountRate = createTextField("Discount Rate");
        this.stockQuantity = createTextField("Quantity");
        this.weight = createTextField("Weight");
        this.volume = createTextField("Volume");
        this.color = createTextField("Color");
        this.brand = createTextField("Brand");
        this.isDiscontinued = createComboBox("Is Discontinued");
        this.isAvailable = createComboBox("Is Available");
        this.lastRestockDate = createTextField("Last Restock Date");
        this.barcode = createTextField("Barcode");
        this.nrOfReturns = createTextField("Number of Returns");
        this.add=createGeneralButton("Add Product");
        this.edit=createGeneralButton("Edit Product");
        this.search = createComboBox("Select");


        this.table = new TableView<>();
        initializeTableView();
    }
    private void initializeTableView() {
        table.setPrefHeight(300);
        table.setPrefWidth(3000);
        table.setStyle("-fx-background-color:white ;"+ "-fx-border-radius:10;" + "-fx-border-color:yellowgreen;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Product Code Column
        TableColumn<Item, Integer> idColumn = new TableColumn<>("Product Code");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        idColumn.setMaxWidth(100);

        // Product Name Column
        TableColumn<Item, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        nameColumn.setMaxWidth(100);

        TableColumn<Item, String> sectorColumn = new TableColumn<>("Sector");
        sectorColumn.setCellValueFactory(new PropertyValueFactory<>("sector"));
        sectorColumn.setMaxWidth(80);



        TableColumn<Item, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellingPriceColumn.setMaxWidth(100);

        TableColumn<Item, Double> costPriceColumn = new TableColumn<>("Cost Price");
        costPriceColumn.setCellValueFactory(new PropertyValueFactory<>("priceBought"));
        costPriceColumn.setMaxWidth(100);

        TableColumn<Item, String> supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        supplierColumn.setMaxWidth(80);



        TableColumn<Item, String> discountColumn = new TableColumn<>("Discount Rate");
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discountRate"));
        discountColumn.setMaxWidth(100);

        // Quantity Column
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        quantityColumn.setMaxWidth(80);




        TableColumn<Item, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(80);

        // Column for 'Is Discounted'
        TableColumn<Item, Boolean> discountedColumn = new TableColumn<>("Is Discounted");
        discountedColumn.setCellValueFactory(cellData -> cellData.getValue().isDiscountedProperty());
        discountedColumn.setMaxWidth(80);
        discountedColumn.setCellFactory(param -> new TableCell<Item, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);  // No content for empty cells
                } else {
                    CheckBox checkBox = createCheckBox();
                    checkBox.setSelected(item != null && item);  // Set the check state based on the item value
                    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (getTableRow() != null && getTableRow().getItem() != null) {
                            Item currentItem = getTableRow().getItem();
                            currentItem.setDiscounted(newValue); // Update the 'isDiscounted' property
                        }
                    });
                    setGraphic(checkBox);  // Set the custom checkbox as the cell's graphic
                }
            }
        });

// Column for 'Is Discontinued'
        TableColumn<Item, Boolean> discontinuedColumn = new TableColumn<>("Is Discontinued");
        discontinuedColumn.setCellValueFactory(cellData -> cellData.getValue().isDiscontinuedProperty());
        discontinuedColumn.setMaxWidth(80);
        discontinuedColumn.setCellFactory(param -> new TableCell<Item, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);  // No content for empty cells
                } else {
                    CheckBox checkBox = createCheckBox();
                    checkBox.setSelected(item != null && item);  // Set the check state based on the item value
                    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (getTableRow() != null && getTableRow().getItem() != null) {
                            Item currentItem = getTableRow().getItem();
                            currentItem.setDiscontinued(newValue); // Update the 'isDiscontinued' property
                        }
                    });
                    setGraphic(checkBox);  // Set the custom checkbox as the cell's graphic
                }
            }
        });

// Column for 'Is Available'
        TableColumn<Item, Boolean> availableColumn = new TableColumn<>("Is Available");
        availableColumn.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty());
        availableColumn.setMaxWidth(80);
        availableColumn.setCellFactory(param -> new TableCell<Item, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);  // No content for empty cells
                } else {
                    CheckBox checkBox = createCheckBox();
                    checkBox.setSelected(item != null && item);  // Set the check state based on the item value
                    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (getTableRow() != null && getTableRow().getItem() != null) {
                            Item currentItem = getTableRow().getItem();
                            currentItem.setAvailable(newValue); // Update the 'isAvailable' property
                        }
                    });
                    setGraphic(checkBox);  // Set the custom checkbox as the cell's graphic
                }
            }
        });


        TableColumn<Item, String> restockColumn = new TableColumn<>("Last_Restock_Date");
        restockColumn.setCellValueFactory(new PropertyValueFactory<>("lastRestockDate"));
        restockColumn.setMaxWidth(100);

        TableColumn<Item, String> barcodeColumn = new TableColumn<>("Barcode");
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        barcodeColumn.setMaxWidth(80);



//        TableColumn<Item, Boolean> selectColumn = new TableColumn<>("Select");
//        selectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
//
//        // Set a custom cell factory to use your createCheckBox method
//        selectColumn.setCellFactory(param -> new TableCell<Item, Boolean>() {
//            @Override
//            protected void updateItem(Boolean item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);  // No content for empty cells
//                } else {
//                    CheckBox checkBox = createCheckBox();
//                    checkBox.setSelected(item != null && item);  // Set the check state based on the item value
//                    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
//                        if (getTableRow() != null && getTableRow().getItem() != null) {
//                            Item currentItem = getTableRow().getItem();
//                            currentItem.setSelected(newValue); // Update the item's selected property
//                        }
//                    });
//                    setGraphic(checkBox);  // Set the custom checkbox as the cell's graphic
//                }
//            }
//        });


        // Add columns to the table
        table.getColumns().addAll(barcodeColumn,idColumn, nameColumn,brandColumn,sectorColumn,supplierColumn,costPriceColumn,sellingPriceColumn,quantityColumn,restockColumn,discountedColumn,discountColumn,discontinuedColumn,availableColumn );

        // Sample data
        ObservableList<Item> items = FXCollections.observableArrayList(

                new Item(2, "Phone", SectorType.ELECTRONICS, " iPhone", 1000.0, 800.0,
                        new Supplier("Apple"), false, 5.0, 75, false, 0.3, 0.1,
                        new int[]{6, 3, 4}, "Black", "Apple", 1, true, true,
                        new Date(), 4.8, "image-phone.jpg", "0987654321", 1)
        );

// Set items to TableView
        table.setItems(items);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
       grid.setVgap(20);
      grid.setHgap(20);


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
        grid.add(createAlignedGreenBoldLabel("Is Discounted: ", 150), 2, 3);
        grid.add(isDiscounted, 3, 3);
        grid.add(createAlignedGreenBoldLabel("Discount Rate: ", 150), 4, 3);
        grid.add(discountRate, 5, 3);

      grid.add(add, 4, 4);
       grid.add(edit, 5, 4);



        Label label=createAlignedGreenBoldLabel("Products Management",200);
        HBox searchBox = new HBox(100);



            Image image = new Image("file:C:/Users/user/Downloads/logo.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);


        searchBox.getChildren().addAll( label,search);
       inventory.add(imageView,2,0);
        inventory.add(searchBox,1,0);
        inventory.add(table,1,1);
       inventory.add(grid,1,2);


        Scene scene = new Scene(inventory);
        return scene;
    }
}
