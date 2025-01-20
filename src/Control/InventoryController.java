package Control;


import Database.Database;
import Database.FileHandler;
import Model.RestockTransaction;
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
import java.util.ArrayList;

public class InventoryController {

    private InventoryManagementView view=new InventoryManagementView();
    private ArrayList<Item> inventory= Database.getDatabase().getInventory();

    public InventoryController() {
        setEditListeners();
        setButtonActions();
    }


    private void setEditListeners() {
        this.view.getProductId().setOnEditCommit(e -> {
            Item item = e.getRowValue();//Kto jan ne rregull vetem ndrysho ate te file.. ja bjm nje prove
            item.setProductId(e.getNewValue()); //Kjo e ndryshon elementet e arrayt kto tjerat ti le ksh u?
            Database.getDatabase().updateInventory(inventory);//Ne vend te ksaj thir metoden qe i shkruan filet nga e para
        });
        this.view.getProductName().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setProductName(e.getNewValue());
            updateFile(item);

        });
        this.view.getSector().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSector(e.getNewValue());
            updateFile(item);

        });
        this.view.getCostPrice().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setPriceBought(e.getNewValue());
            updateFile(item);

        });
        this.view.getSellingPrice().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSellingPrice(e.getNewValue());
            updateFile(item);

        });
        this.view.getBrand().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setBrand(e.getNewValue());
            updateFile(item);
        });

        this.view.getSupplier().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSupplier(e.getNewValue());
            updateFile(item);//a mblidhemi neser te shkolla punojme pak ok
        });

        this.view.getQuantity().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            int oldQuantity=item.getStockQuantity();
            item.setStockQuantity(e.getNewValue());
            updateFile(item);
            RestockTransaction restockTransaction=new RestockTransaction(item,e.getNewValue()-oldQuantity);
            FileHandler.writeTransactionToFile(restockTransaction);
            item.setLastRestockDate(LocalDate.now()); //Ta besh automatikisht
        });
//        this.view.getLastrestockDate().setOnEditCommit(e -> {
//            Item item = e.getRowValue();
//            item.setLastRestockDate(e.getNewValue());
//            updateFile(item);
//        });
        this.view.getBarcode().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setBarcode(e.getNewValue());
            updateFile(item);
        });
        this.view.getSupplier().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setSupplier(e.getNewValue());
            updateFile(item);
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

       // FileHandler.deleteFromFile(selectedItems);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted successfully.");
        alert.setTitle("Delete Result");
        alert.show();
    }
    private void updateFile(Item updatedItem) {
        FileHandler.updateItemInFile(updatedItem);  // Call to update the inventory file
    }

    public InventoryManagementView getView() {
        return view;
    }
}