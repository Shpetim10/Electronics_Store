package Control;


import Database.Database;
import Database.FileHandler;
import Model.RestockTransaction;
import Model.Validator;
import View.InventoryManagementView;
import Model.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class InventoryManagementController {

    private InventoryManagementView view=new InventoryManagementView();
    private ArrayList<Item> inventory= Database.getDatabase().getInventory();

    public InventoryManagementController( ) {

        setEditListeners();
        setButtonActions();
        searchBoxListener();

    }
    private void setupSearchBox() {
        // Search button
        view.getSearch().getSearchButton().setOnAction(e -> filterInventory());
        // When enter is pressed
        view.getSearch().getSearchField().setOnAction(e -> filterInventory());
    }

    private void filterInventory() {
        String searchText = view.getSearch().getSearchField().getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            view.getTable().setItems(FXCollections.observableArrayList(inventory)); // Reset table
            return;
        }

        ObservableList<Item> filteredItems = FXCollections.observableArrayList(
                inventory.stream()
                        .filter(item -> item.getProductName().toLowerCase().contains(searchText)
                                || String.valueOf(item.getProductId()).contains(searchText)
                                || item.getBrand().toLowerCase().contains(searchText)
                                || item.getSector().toLowerCase().contains(searchText))
                        .toList()
        );

        view.getTable().setItems(filteredItems);
    }


    private void setEditListeners() {
        this.view.getProductId().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newProductId=(String.valueOf(e.getNewValue()));
            if (!Validator.validatePositiveInteger(newProductId)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Quantity", "Quantity must be a non-negative integer.");
                return;
            }
            Database.getDatabase().updateInventory(inventory);
        });

        this.view.getProductName().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newProductName = e.getNewValue();


            // Validate Product Name
            if (!Validator.validateProductName(newProductName)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Product Name", "Product name must contain only letters and numbers.");
                return;
            }
            item.setProductName(e.getNewValue());
            Database.getDatabase().updateInventory(inventory);

        });
        this.view.getSector().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newSector = e.getNewValue();

            if (newSector == null || newSector.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Sector", "Sector cannot be empty.");
                return;
            }
            Database.getDatabase().updateInventory(inventory);

        });
        this.view.getCostPrice().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newCostPrice = String.valueOf(e.getNewValue());
            if (newCostPrice == null || newCostPrice.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Cost Price", "Cost Price cannot be empty.");
                return;
            }

            // Validate Cost Price (positive decimal)
            if (!Validator.validatePositiveDouble(newCostPrice)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Cost Price", "Cost Price must be a positive number.");
                return;
            }
            Database.getDatabase().updateInventory(inventory);

        });
        this.view.getSellingPrice().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newSellingPrice = String.valueOf(e.getNewValue());
            if (newSellingPrice == null || newSellingPrice.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Selling Price", "Selling Price cannot be empty.");
                return;
            }

            // Validate Selling Price (positive decimal)
            if (!Validator.validatePositiveDouble(newSellingPrice)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Selling Price", "Selling price must be a positive number.");
                return;
            }
            Database.getDatabase().updateInventory(inventory);

        });
        this.view.getBrand().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newBrand = e.getNewValue();

            // Validate Brand (non-empty string)
            if (newBrand == null || newBrand.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Brand", "Brand cannot be empty.");
                return;
            }
            Database.getDatabase().updateInventory(inventory);
        });

        this.view.getSupplier().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newSupplier = e.getNewValue();

            if (!Validator.doesSupplierExist(inventory,newSupplier)) {
                showAlert(Alert.AlertType.WARNING, "Supplier Already Exists", "The supplier already exists in the inventory.");
                return; // Prevent update if supplier already exists
            }
            if (newSupplier == null || newSupplier.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Barcode", "Barcode cannot be empty.");
                return;
            }

            // Validate Supplier (non-empty string)
            if (!Validator.validateSupplierName(newSupplier)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Supplier", "Supplier name cannot be empty.");
                return;
            }
            Database.getDatabase().updateInventory(inventory);
        });

        this.view.getQuantity().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newQuantity = String.valueOf(e.getNewValue());

            // Validate Quantity (non-negative integer)
            if (!Validator.validatePositiveInteger(newQuantity)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Quantity", "Quantity must be a non-negative integer.");
                return;
            }
            if (newQuantity == null || newQuantity.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Barcode", "Barcode cannot be empty.");
                return;
            }
            int oldQuantity=item.getStockQuantity();
            item.setStockQuantity(e.getNewValue());
            RestockTransaction restockTransaction=new RestockTransaction(item,e.getNewValue()-oldQuantity);
            FileHandler.writeTransactionToFile(restockTransaction);
            item.setLastRestockDate(LocalDate.now());
            Database.getDatabase().updateInventory(inventory);
        });
//        this.view.getLastrestockDate().setOnEditCommit(e -> {
//            Item item = e.getRowValue();
//            item.setLastRestockDate(e.getNewValue());
//            updateFile(item);
//        });
        this.view.getBarcode().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newBarcode = String.valueOf(e.getNewValue());
            if (!Validator.validatePositiveInteger(newBarcode)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Barcode must be a positive integer.");
                return;
            }

            // Validate Barcode (not empty)
            if (newBarcode == null || newBarcode.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Barcode", "Barcode cannot be empty.");
                return;
            }
            Database.getDatabase().updateInventory(inventory);
        });
//        this.view.getSupplier().setOnEditCommit(e -> {
//            Item item = e.getRowValue();
//            item.setSupplier(e.getNewValue());
//            Database.getDatabase().updateInventory(inventory);
//        });

        }


    private void setButtonActions() {
        this.view.getDelete().setOnAction(event -> productDelete());// Set delete action once
    }

    private void productDelete() {
        Item selectedItem = this.view.getTable().getSelectionModel().getSelectedItem();

        if (selectedItem==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No item selected for deletion.");
            alert.setTitle("Delete Warning");
            alert.show();
            return;
        }

        this.view.getTable().getItems().remove(selectedItem);
        inventory.remove(selectedItem); // Ensure to remove from the ObservableList as well
        Database.getDatabase().updateInventory(inventory);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted successfully.");
        alert.setTitle("Delete Result");
        alert.show();
    }
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void searchBoxListener(){
        view.getSearch().getSearchField().setOnAction(
                e->{

                    String searchQuery=this.view.getSearch().getSearchField().getText().toLowerCase();
                    ObservableList<Item> filteredItems=FXCollections.observableArrayList();
                    for(Item item: view.getTable().getItems()){
                        if(item.getProductName().toLowerCase().contains(searchQuery)){
                            filteredItems.add(item);
                        }
                    }
                    this.view.getTable().setItems(filteredItems);
                }
        );
    }

    public InventoryManagementView getView() {
        return view;
    }
}