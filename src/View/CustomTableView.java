package View;



import Model.FileHandler;
import Model.Item;
import Model.SectorType;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Function;

public class CustomTableView {
    private TableView<Item> table;

    public CustomTableView() {
        this.table = new TableView<>();
        initializeTableView();
    }

    private void initializeTableView() {
        table.setPrefHeight(1000);
        table.setPrefWidth(3000);
        table.setStyle("-fx-background-color:white ;" +
                "-fx-border-radius:10;" +
                "-fx-border-color:yellowgreen;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Define columns
        addColumn("Id", "productId", 100);
        addColumn("Barcode", "barcode", 80);
        addColumn("Name", "productName", 100);
        addColumn("Sector", "sector", 80);
        addColumn("Selling Price", "sellingPrice", 100);
        addColumn("Cost Price", "priceBought", 100);
        addColumn("Supplier", "supplier", 80);
        addColumn("Quantity", "stockQuantity", 80);
        addColumn("Brand", "brand", 80);
        addColumn("Last Restock Date", "lastRestockDate", 100);

        ObservableList<Item> items = FXCollections.observableArrayList(FileHandler.getItemsOfInventory());
        table.setItems(items);
    }

    private void addColumn(String title, String property, int width) {
        TableColumn<Item, Object> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setMaxWidth(width);
        table.getColumns().add(column);
    }


    public TableView<Item> getTable() {
        return table;
    }
}
