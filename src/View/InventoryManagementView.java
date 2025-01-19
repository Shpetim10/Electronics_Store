package View;

import Model.Item;
import Model.SectorType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

public class InventoryManagementView extends GridPane implements Design {
    private final TableView<Item> table = new TableView<>();

    private final TableColumn<Item, Integer> productId;
    private final TableColumn<Item, String> productName;
    private final TableColumn<Item, String> sector;
    private final TableColumn<Item, Double> costPrice;
    private final TableColumn<Item, Double> sellingPrice;
    private final TableColumn<Item, String> supplier;
    private final TableColumn<Item, Integer> quantity;
    private final TableColumn<Item, String> brand;
    private final TableColumn<Item, LocalDate> lastrestockDate;
    private final TableColumn<Item, Integer> barcode;
    private final Button delete;

    SearchBoxPane search=new SearchBoxPane();
    Label label=createAlignedGreenBoldLabel("Inventory Management",200);

    public InventoryManagementView(){
        table.setEditable(true);
        table.setPrefHeight(800);
        table.setPrefWidth(3000);
        table.setStyle("-fx-background-color:white ;" +
                "-fx-border-radius:10;" +
                "-fx-border-color:yellowgreen;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        barcode = new TableColumn<>("Barcode");
        barcode.setMinWidth(100);
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        barcode.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        productId = new TableColumn<>("Product ID");
        productId.setMinWidth(100);
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        productName = new TableColumn<>("Product Name");
        productName.setMinWidth(100);
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productName.setCellFactory(TextFieldTableCell.forTableColumn());

        sector = new TableColumn<>(" Sector");
        sector.setMinWidth(100);
        sector.setCellValueFactory(new PropertyValueFactory<>("sector"));
        sector.setCellFactory(TextFieldTableCell.forTableColumn());

        costPrice = new TableColumn<>(" Price Bought");
        costPrice.setMinWidth(100);
        costPrice.setCellValueFactory(new PropertyValueFactory<>("priceBought"));
        costPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        sellingPrice = new TableColumn<>("Selling Price");
        sellingPrice.setMinWidth(100);
        sellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellingPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        supplier = new TableColumn<>("Supplier");
        supplier.setMinWidth(100);
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        supplier.setCellFactory(TextFieldTableCell.forTableColumn());

        quantity = new TableColumn<>("Quantity");
        quantity.setMinWidth(100);
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        brand = new TableColumn<>("Brand");
        brand.setMinWidth(100);
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brand.setCellFactory(TextFieldTableCell.forTableColumn());

        lastrestockDate = new TableColumn<>("Last Restock Date");
        lastrestockDate.setMinWidth(100);
        lastrestockDate.setCellValueFactory(new PropertyValueFactory<>("lastRestockDate"));
        lastrestockDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        delete=createGeneralButton("Delete");

        table.getColumns().addAll(barcode,productId, productName, sector, costPrice,sellingPrice,supplier,quantity,brand,lastrestockDate);

        table.setItems(getSampleData());
        setUpView();

    }
    private ObservableList<Item> getSampleData() {
        return FXCollections.observableArrayList();
    }

    public void setUpView(){
        this.setPadding(new Insets(10,10,10,10));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        HBox box=new HBox(10);
        box.getChildren().addAll(label,search);
        this.setHgap(20);
        this.setVgap(10);

        this.add(box,0,0);

        this.add(table,0,3);
        this.add(delete,0,4);
    }

    public Button getDelete() {
        return delete;
    }

    public TableColumn<Item, Integer> getProductId() {
        return productId;
    }

    public TableColumn<Item, String> getProductName() {
        return productName;
    }

    public TableColumn<Item, String> getSector() {
        return sector;
    }

    public TableColumn<Item, Double> getCostPrice() {
        return costPrice;
    }

    public TableColumn<Item, Double> getSellingPrice() {
        return sellingPrice;
    }

    public TableColumn<Item, String> getSupplier() {
        return supplier;
    }

    public TableColumn<Item, Integer> getQuantity() {
        return quantity;
    }

    public TableColumn<Item, String> getBrand() {
        return brand;
    }

    public TableColumn<Item, LocalDate> getLastrestockDate() {
        return lastrestockDate;
    }

    public TableColumn<Item, Integer> getBarcode() {
        return barcode;
    }



    public TableView<Item> getTable() {
        return table;
    }

    //    private void initializeTableView() {
//        table.setPrefHeight(600);
//        table.setPrefWidth(400);
//        table.setStyle("-fx-background-color:white ;"+ "-fx-border-radius:10;" + "-fx-border-color:yellowgreen;");
//        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        // Database.Files.Product Code Column
//        TableColumn<Item, Integer> idColumn = new TableColumn<>("Database.Files.Product Code");
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
//        idColumn.setMaxWidth(100);
//
//        // Database.Files.Product Name Column
//        TableColumn<Item, String> nameColumn = new TableColumn<>("Database.Files.Product Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
//        nameColumn.setMaxWidth(150);
//
//        // Quantity Column
//        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
//        quantityColumn.setMaxWidth(100);
//
//        // Cost Price Column
//        TableColumn<Item, Double> costPriceColumn = new TableColumn<>("Cost Price");
//        costPriceColumn.setCellValueFactory(new PropertyValueFactory<>("priceBought"));
//        costPriceColumn.setMaxWidth(100);
//
//        // Selling Price Column
//        TableColumn<Item, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
//        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
//        sellingPriceColumn.setMaxWidth(100);
//
//        // Brand Column
//        TableColumn<Item, String> brandColumn = new TableColumn<>("Brand");
//        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
//        brandColumn.setMaxWidth(100);




//    public void setTable(CustomTableView table) {
//        this.table = table;
//    }
//    public TableColumn<Item, Integer> getProductIdColumn() {
//        return table.getProductIdColumn();
//    }
//
//    public TableColumn<Item,String> getProductNameColumn() {
//        return table.getProductNameColumn();
//    }
//
//    public TableColumn<Item, String> getSectorColumn() {
//        return table.getSectorColumn();
//    }
//
//    public TableColumn<Item, Double> getSellingPriceColumn() {
//        return table.getSellingPriceColumn();
//    }
//
//    public TableColumn<Item, Double> getCostPriceColumn() {
//        return table.getCostPriceColumn();
//    }
//
//    public TableColumn<Item, String> getSupplierColumn() {
//        return table.getSupplierColumn();
//    }
//
//
//    public TableColumn<Item, Integer> getStockQuantityColumn() {
//        return table.getStockQuantityColumn();
//    }
//
//    public TableColumn<Item, String> getBrandColumn() {
//        return table.getBrandColumn();
//    }
//
//    public TableColumn<Item, LocalDate> getLastRestockDateColumn() {
//        return table.getLastRestockDateColumn();
//    }
//
//    public TableColumn<Item, String> getBarcodeColumn() {
//        return table.getBarcodeColumn();
//    }

    // Method to get the table itself//i paskam gati harrova ok mbarje kte pasaj ti bejm merge


    //    private ComboBox<String> search;
//    private Button refresh;
//    private Button restock;
//    private Button edit;
//    private Button delete;
//    private Button clear;
//    private Button add;
//    private TableView<Item> table;
//
//    public InventoryManagementView() {
//        this.refresh = createGeneralButton("Refresh");
//        this.restock = createGeneralButton("Restock");
//        this.edit = createGeneralButton("Edit");
//        this.add = createGeneralButton("Add Database.Files.Product");
//        this.delete = createGeneralButton("Delete");
//        this.clear = createGeneralButton("Clear");
//        this.search = createComboBox("Select");
//        this.table = new TableView<>();
//        initializeTableView();
//    }
//
//    private void initializeTableView() {
//        table.setPrefHeight(600);
//        table.setPrefWidth(400);
//        table.setStyle("-fx-background-color:white ;"+ "-fx-border-radius:10;" + "-fx-border-color:yellowgreen;");
//        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        // Database.Files.Product Code Column
//        TableColumn<Item, Integer> idColumn = new TableColumn<>("Database.Files.Product Code");
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
//        idColumn.setMaxWidth(100);
//
//        // Database.Files.Product Name Column
//        TableColumn<Item, String> nameColumn = new TableColumn<>("Database.Files.Product Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
//        nameColumn.setMaxWidth(150);
//
//        // Quantity Column
//        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
//        quantityColumn.setMaxWidth(100);
//
//        // Cost Price Column
//        TableColumn<Item, Double> costPriceColumn = new TableColumn<>("Cost Price");
//        costPriceColumn.setCellValueFactory(new PropertyValueFactory<>("priceBought"));
//        costPriceColumn.setMaxWidth(100);
//
//        // Selling Price Column
//        TableColumn<Item, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
//        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
//        sellingPriceColumn.setMaxWidth(100);
//
//        // Brand Column
//        TableColumn<Item, String> brandColumn = new TableColumn<>("Brand");
//        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
//        brandColumn.setMaxWidth(100);
//
//        // Select Column (CheckBox)

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
//
//
//        // Add columns to the table
//        table.getColumns().addAll(selectColumn,idColumn, nameColumn,brandColumn, quantityColumn, costPriceColumn, sellingPriceColumn);
//
//        // Sample data
//        ObservableList<Item> list = FXCollections.observableArrayList(
//                new Item(1, "Laptop","MacBook" ,50, 100.0, 200.0 ),
//                new Item(2, "Phone", "iPhone 16",75, 700.0, 860.0 )
//        );
//        table.setItems(list);
//
//
//
//    }
//

//    public Scene createScene() {
//        GridPane inventory = new GridPane();
//        inventory.setHgap(20);
//        inventory.setVgap(10);
//        inventory.setPadding(new Insets(50, 100, 50, 100));
//        inventory.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
//
//        Label label = createAlignedGreenBoldLabel("Inventory Management", 200);
//        HBox searchBox = new HBox(100);
//
//        searchBox.getChildren().addAll(search, refresh, label);
//
//        HBox buttons = new HBox(50);
//        buttons.getChildren().addAll(  delete, clear);
//
//
//        HBox buttons1 = new HBox(100);
//        buttons1.getChildren().addAll(add,edit);
//
//        GridPane control = new GridPane();
//        control.setHgap(10);
//        control.setVgap(10);
//        Label quantityLabel = createAlignedGreenBoldLabel("Quantity", 150);
//        Label title = createAlignedGreenBoldLabel("Restock", 200);
//
//        control.add(quantityLabel, 0, 6);
//        control.add(createTextField(""),1,6);
//        control.add(title, 1, 0);
//        control.add(restock, 1, 9);
//
//        inventory.add(searchBox, 0, 0);
//        inventory.add(table, 0, 1);
//        inventory.add(buttons, 1, 2);
//        inventory.add(buttons1, 0, 2);
//        inventory.add(control, 1, 1);
//
//        Scene scene = new Scene(inventory);
//        return scene;
//    }
}
