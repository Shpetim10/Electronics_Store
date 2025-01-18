package Control;

import Model.FileHandler;
import Model.SectorType;
import View.InventoryManagementView;
import Model.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class InventoryController {

    private InventoryManagementView view;


    public InventoryController(InventoryManagementView view) {
        this.view = view;
        setEditListeners();
        setButtonActions();

    }
    private void setEditListeners() {
        this.view.getProductId().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setProductId(e.getNewValue());

        });
        this.view.getProductName().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setProductName(e.getNewValue());

        });
        this.view.getSector().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSector(e.getNewValue());

        });
        this.view.getCostPrice().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setPriceBought(e.getNewValue());

        });
        this.view.getSellingPrice().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSellingPrice(e.getNewValue());


        });
        this.view.getBrand().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setBrand(e.getNewValue());
        });

        this.view.getSupplier().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSupplier(e.getNewValue());
        });

        this.view.getQuantity().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setStockQuantity(e.getNewValue());
        });
        this.view.getLastrestockDate().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setLastRestockDate(e.getNewValue());
        });
        this.view.getBarcode().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.getBarcode(e.getNewValue());
        });
        this.view.getSupplier().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSupplier(e.getNewValue());
        });

        }


    private void setButtonActions() {
        this.view.getDelete().setOnAction(event -> productDelete(event));// Set delete action once
    }

    private void productDelete(ActionEvent event) {
        ObservableList<Item> selectedItems = this.view.getTable().getSelectionModel().getSelectedItems();

        if (selectedItems.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No item selected for deletion.");
            alert.setTitle("Delete Warning");
            alert.show();
            return;
        }

        this.view.getTable().getItems().removeAll(selectedItems);
        // Call a method to delete from the file (if applicable)
        //deleteFromFile(selectedItems);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted successfully.");
        alert.setTitle("Delete Result");
        alert.show();
    }


}