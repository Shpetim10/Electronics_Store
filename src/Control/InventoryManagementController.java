package Control;


import Database.Database;
import Database.FileHandler;
import Model.*;
import View.InventoryManagementView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

import static Model.Validator.isSupplierRegistered;

public class InventoryManagementController {

    private InventoryManagementView view=new InventoryManagementView();
    private ArrayList<Item> inventory= Database.getDatabase().getInventory();

    public InventoryManagementController() {

        setEditListeners();
        setButtonActions();
        setupSearchBox();

    }
    private void setupSearchBox() {
        // Search button

            // Search button
            view.getSearch().getSearchButton().setOnAction(e -> filterInventory());
            // When enter is pressed
            view.getSearch().getSearchField().setOnKeyReleased(e -> filterInventory()); // Changed to onKeyReleased

    }

    private void filterInventory() {
        String searchText = view.getSearch().getSearchField().getText().trim().toLowerCase();

        // If search is empty, reset the table
        if (searchText.isEmpty()) {
            view.getTable().setItems(FXCollections.observableArrayList(inventory));
            return;
        }

        ObservableList<Item> filteredItems = FXCollections.observableArrayList(
                inventory.stream()
                        .filter(item -> item.getProductName().toLowerCase().contains(searchText)
                                || String.valueOf(item.getBarcode()).contains(searchText)
                                ||String.valueOf(item.getLastRestockDate()).contains(searchText)
                                || String.valueOf(item.getProductId()).contains(searchText)
                                || item.getBrand().toLowerCase().contains(searchText)
                                || String.valueOf(item.getStockQuantity()).contains(searchText)
                                ||item.getSupplier().contains(searchText)
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
            item.setProductId(Integer.parseInt(newProductId));
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
            item.setSector(newSector);
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
            item.setPriceBought(Double.parseDouble(newCostPrice));
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
            item.setSellingPrice(Double.parseDouble(newSellingPrice));
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
            item.setBrand(newBrand);
            Database.getDatabase().updateInventory(inventory);
        });

        this.view.getSupplier().setOnEditCommit(e -> {
            Item item = e.getRowValue();
            String newSupplier = e.getNewValue();

//            if (!Validator.doesSupplierExist(inventory,newSupplier)) {
//                showAlert(Alert.AlertType.WARNING, "Supplier Already Exists", "The supplier already exists in the inventory.");
//                return; // Prevent update if supplier already exists
//            }
            if (newSupplier == null || newSupplier.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Invalid Barcode", "Barcode cannot be empty.");
                return;
            }
            if (!Validator.isSupplierRegistered(newSupplier)) {
                showAlert(Alert.AlertType.WARNING, "Unregistered Supplier", "The supplier must be registered in the system before adding products.");
                return;
            }
            // Validate Supplier (non-empty string)
            if (!Validator.validateSupplierName(newSupplier)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Supplier", "Supplier name cannot be empty.");
                return;
            }
            item.setSupplier(newSupplier);
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

            Database.getDatabase().saveRestockTransaction(restockTransaction);
            item.setLastRestockDate(LocalDate.now());
            Database.getDatabase().updateInventory(inventory);

            //Notify Users
            for(Cashier user: Database.getDatabase().getCashiers()){
                if(user.getSector().toString().equals(item.getSector())){
                    user.getNotifications().add(new Notification(NotificationType.RESTOCKED,"Item "+item.getProductName()+" is restocked by "+(e.getNewValue()-oldQuantity)+" items."));

                }
            }
            Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
        });

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
            item.setBarcode(Integer.valueOf(newBarcode));
            Database.getDatabase().updateInventory(inventory);
        });
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

        //Notify cashiers
        for(Cashier user: Database.getDatabase().getCashiers()){
            if(user.getSector().toString().equals(selectedItem.getSector())){
                user.getNotifications().add(new Notification(NotificationType.DISCONTINUED,"Item "+selectedItem.getProductName()+" is discontinued!"));
            }
        }
        Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());

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