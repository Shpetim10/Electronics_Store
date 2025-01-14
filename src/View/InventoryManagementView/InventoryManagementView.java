package View.InventoryManagementView;

import Model.Item;
import View.Design;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.awt.*;

public class InventoryManagementView implements Design {
    private ComboBox<String> search;
    private Button refresh;
    private Button restock;
    private Button edit;
    private Button delete;
    private Button clear;
    private Button add;
    private TableView<Item> table;

    public InventoryManagementView() {
        this.refresh = createGeneralButton("Refresh");
        this.restock = createGeneralButton("Restock");
        this.edit = createGeneralButton("Edit");
        this.add = createGeneralButton("Add Product");
        this.delete = createGeneralButton("Delete");
        this.clear = createGeneralButton("Clear");
        this.search = createComboBox("Select");
        this.table = new TableView<>();
        initializeTableView();
    }

    private void initializeTableView() {
        table.setPrefHeight(600);
        table.setPrefWidth(400);
        table.setStyle("-fx-background-color:white ;"+ "-fx-border-radius:10;" + "-fx-border-color:yellowgreen;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Product Code Column
        TableColumn<Item, Integer> idColumn = new TableColumn<>("Product Code");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        idColumn.setMaxWidth(100);

        // Product Name Column
        TableColumn<Item, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        nameColumn.setMaxWidth(150);

        // Quantity Column
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        quantityColumn.setMaxWidth(100);

        // Cost Price Column
        TableColumn<Item, Double> costPriceColumn = new TableColumn<>("Cost Price");
        costPriceColumn.setCellValueFactory(new PropertyValueFactory<>("priceBought"));
        costPriceColumn.setMaxWidth(100);

        // Selling Price Column
        TableColumn<Item, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellingPriceColumn.setMaxWidth(100);

        // Brand Column
        TableColumn<Item, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(100);

        // Select Column (CheckBox)

        TableColumn<Item, Boolean> selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());

        // Set a custom cell factory to use your createCheckBox method
        selectColumn.setCellFactory(param -> new TableCell<Item, Boolean>() {
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
                            currentItem.setSelected(newValue); // Update the item's selected property
                        }
                    });
                    setGraphic(checkBox);  // Set the custom checkbox as the cell's graphic
                }
            }
        });


        // Add columns to the table
        table.getColumns().addAll(selectColumn,idColumn, nameColumn,brandColumn, quantityColumn, costPriceColumn, sellingPriceColumn);

        // Sample data
        ObservableList<Item> list = FXCollections.observableArrayList(
                new Item(1, "Laptop","MacBook" ,50, 100.0, 200.0 ),
                new Item(2, "Phone", "iPhone 16",75, 700.0, 860.0 )
        );
        table.setItems(list);



    }


    public Scene createScene() {
        GridPane inventory = new GridPane();
        inventory.setHgap(20);
        inventory.setVgap(10);
        inventory.setPadding(new Insets(50, 100, 50, 100));
        inventory.setStyle("-fx-background-color: rgba(167,246,8,0.15);");

        Label label = createAlignedGreenBoldLabel("Inventory Management", 200);
        HBox searchBox = new HBox(100);

        searchBox.getChildren().addAll(search, refresh, label);

        HBox buttons = new HBox(50);
        buttons.getChildren().addAll(  delete, clear);


        HBox buttons1 = new HBox(100);
        buttons1.getChildren().addAll(add,edit);

        GridPane control = new GridPane();
        control.setHgap(10);
        control.setVgap(10);
        Label quantityLabel = createAlignedGreenBoldLabel("Quantity", 150);
        Label title = createAlignedGreenBoldLabel("Restock", 200);

        control.add(quantityLabel, 0, 6);
        control.add(createTextField(""),1,6);
        control.add(title, 1, 0);
        control.add(restock, 1, 9);

        inventory.add(searchBox, 0, 0);
        inventory.add(table, 0, 1);
        inventory.add(buttons, 1, 2);
        inventory.add(buttons1, 0, 2);
        inventory.add(control, 1, 1);

        Scene scene = new Scene(inventory);
        return scene;
    }
}
