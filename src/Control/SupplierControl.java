package Control;

import Model.Item;
import Model.Supplier;
import View.SupplierView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class SupplierControl {
    SupplierView view;

    public SupplierControl(SupplierView view) {
        this.view = view;
        setEditListeners();
        setButtonActions();
    }

    private void setEditListeners() {
        this.view.getSupplierId().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setSupplierId(e.getNewValue());

        });

        this.view.getCompanyName().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setCompanyName(e.getNewValue());

        });
        this.view.getEmail().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setEmail(e.getNewValue());

        });
        this.view.getPhoneNumber().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setPhoneNumber(e.getNewValue());

        });
        this.view.getAddress().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setAddress(e.getNewValue());

        });


    }
    private void setButtonActions() {
        this.view.getDelete().setOnAction(event -> productDelete(event));
        this.view.getAdd().setOnAction(event -> addProduct(event));
    }

    private void productDelete(ActionEvent event) {
        ObservableList<Supplier> selectedItems = this.view.getTable().getSelectionModel().getSelectedItems();

        if (selectedItems.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No item selected for deletion.");
            alert.setTitle("Delete Warning");
            alert.show();
            return;
        }

        this.view.getTable().getItems().removeAll(selectedItems);


        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted successfully.");
        alert.setTitle("Delete Result");
        alert.show();
    }

    private void addProduct(ActionEvent event) {
        // Get the values from the text fields
        String supplierId = this.view.getsId().getText();
        String companyName = this.view.getcName().getText();
        String email = this.view.getEmail().getText();
        String phoneNumber = this.view.getPhone().getText();
        String address = this.view.getAddress().getText();

        // Validate that none of the fields are empty
        if (supplierId.isEmpty() || companyName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled out.");
            alert.setTitle("Input Error");
            alert.show();
            return;
        }

        // Create a new Supplier object with the entered data
        try {
            int supplierIdInt = Integer.parseInt(supplierId); // Convert supplierId to an integer
            Supplier newSupplier = new Supplier(supplierIdInt, companyName, email, phoneNumber, address);

            // Add the new supplier to the table's ObservableList
            ObservableList<Supplier> currentSuppliers = this.view.getTable().getItems();
            currentSuppliers.add(newSupplier);

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Supplier added successfully.");
            alert.setTitle("Add Supplier");
            alert.show();

            this.view.getsId().clear();
            this.view.getcName().clear();
            this.view.getEmail().setText("");
            this.view.getPhone().clear();
            this.view.getAddress().setText("");

        } catch (NumberFormatException e) {
            // Handle invalid supplierId input (not an integer)
            Alert alert = new Alert(Alert.AlertType.ERROR, "Supplier ID must be a valid number.");
            alert.setTitle("Input Error");
            alert.show();
        }
    }

}
