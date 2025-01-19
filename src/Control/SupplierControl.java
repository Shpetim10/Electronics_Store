package Control;

import Model.FileHandler;
import Model.Item;
import Model.Supplier;
import View.SupplierView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import static Model.FileHandler.deleteSupplier;


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
            updateFile(supplier);

        });


        this.view.getCompanyName().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setCompanyName(e.getNewValue());
            updateFile(supplier);

        });
        this.view.getEmail().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setEmail(e.getNewValue());
            updateFile(supplier);

        });
        this.view.getPhoneNumber().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setPhoneNumber(e.getNewValue());
            updateFile(supplier);

        });
        this.view.getAddress().setOnEditCommit(e -> {
            Supplier supplier = e.getRowValue();
            supplier.setAddress(e.getNewValue());
            updateFile(supplier);
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
        deleteSupplier(selectedItems);



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


        try {
            int supplierIdInt = Integer.parseInt(supplierId); // Convert supplierId to an integer
            if (supplierExists(supplierIdInt)) {
                showAlert(Alert.AlertType.WARNING, "Duplicate Supplier", "A supplier with this ID already exists!");
                return;
            }

            Supplier newSupplier = new Supplier(supplierIdInt, companyName, email, phoneNumber, address);

            // Add the new supplier to the table's ObservableList
            ObservableList<Supplier> currentSuppliers = this.view.getTable().getItems();
            currentSuppliers.add(newSupplier);

            boolean success = FileHandler.writeSupplierToFile(newSupplier);
            if (!success) {
                showAlert(Alert.AlertType.ERROR, "File Error", "Failed to save the supplier to the file.");
                return;
            }

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
    private boolean supplierExists(int supplierId) {
        for (Supplier supplier : view.getTable().getItems()) {
            if (supplier.getSupplierId() == supplierId) {
                return true; // Supplier already exists
            }
        }
        return false;
    }
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void updateFile(Supplier updatedSupplier) {
        FileHandler.updateSupplierInFile(updatedSupplier);  // Call to update the suppliers file
    }


}
