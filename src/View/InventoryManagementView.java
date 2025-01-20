package View;

import Database.Database;
import Database.FileHandler;
import Model.Item;
import Model.SectorType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.ArrayList;

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

    SearchBoxPane search = new SearchBoxPane();
    Label label = createAlignedGreenBoldLabel("Inventory Management", 200);

    public InventoryManagementView() {
        table.setEditable(true);
        table.setPrefHeight(800);
        table.setPrefWidth(3000);
        table.setStyle("-fx-background-color:white ;" +
                "-fx-border-radius:10;" +
                "-fx-border-color:yellowgreen;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

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
        quantity.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        brand = new TableColumn<>("Brand");
        brand.setMinWidth(100);
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brand.setCellFactory(TextFieldTableCell.forTableColumn());

        lastrestockDate = new TableColumn<>("Last Restock Date");
        lastrestockDate.setMinWidth(100);
        lastrestockDate.setCellValueFactory(new PropertyValueFactory<>("lastRestockDate"));
        lastrestockDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        delete = createGeneralButton("Delete");

        table.getColumns().addAll(barcode, productId, productName, sector, costPrice, sellingPrice, supplier, quantity, brand, lastrestockDate);

        table.setItems(getSampleData());
        setUpView();

    }

    private ObservableList<Item> getSampleData() {
        ArrayList<Item> inventory = Database.getDatabase().getInventory();
        return FXCollections.observableArrayList(inventory);
    }


    public void setUpView() {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        HBox box = new HBox(10);
        box.getChildren().addAll(label, search);
        this.setHgap(20);
        this.setVgap(10);

        this.add(box, 0, 0);

        this.add(table, 0, 3);
        this.add(delete, 0, 4);
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

    public SearchBoxPane getSearch() {
        return search;
    }
    public TableView<Item> getTable() {
        return table;
    }

}
