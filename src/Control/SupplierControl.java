package Control;

import Database.Database;
import Database.FileHandler;
import Model.Supplier;
import Model.Validator;
import View.SupplierView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.util.ArrayList;

public class SupplierControl {
    SupplierView view = new SupplierView();
    private ArrayList<Supplier> suppliers = Database.getDatabase().getSuppliers();
    private ObservableList<Supplier> tableContent = FXCollections.observableArrayList(suppliers);

    public SupplierControl() {
        setEditListeners();
        setButtonActions();
        this.view.getTable().setItems(tableContent);
    }

    private void setEditListeners() {
        this.view.getSupplierId().setOnEditCommit(e -> {
            e.getRowValue().setSupplierId(e.getNewValue());
            updateSupplierFile();
        });

        this.view.getCompanyName().setOnEditCommit(e -> {
            e.getRowValue().setCompanyName(e.getNewValue());
            updateSupplierFile();
        });

        this.view.getEmail().setOnEditCommit(e -> {
            e.getRowValue().setEmail(e.getNewValue());
            updateSupplierFile();
        });

        this.view.getPhoneNumber().setOnEditCommit(e -> {
            e.getRowValue().setPhoneNumber(e.getNewValue());
            updateSupplierFile();
        });

        this.view.getAddress().setOnEditCommit(e -> {
            e.getRowValue().setAddress(e.getNewValue());
            updateSupplierFile();
        });
    }

    private void setButtonActions() {
        this.view.getDelete().setOnAction(event -> productDelete());
        this.view.getAdd().setOnAction(event -> {
            String supplierId = this.view.getsId().getText();
            String companyName = this.view.getcName().getText();
            String email = view.getEmailField().getText();
            String address = view.getAddressField().getText();
            String phoneNumber = this.view.getPhone().getText();

            if (!validateSupplierInput(supplierId, companyName, email, phoneNumber, address)) {
                return;

            }


            try {
                int supplierIdInt = Integer.parseInt(supplierId);
                if (supplierExists(supplierIdInt)) {
                    showAlert(Alert.AlertType.WARNING, "Duplicate Supplier", "A supplier with this ID already exists!");
                    return;
                }

                Supplier newSupplier = new Supplier(supplierIdInt, companyName, email, phoneNumber, address);
                tableContent.add(newSupplier); // Update observable list
                suppliers.add(newSupplier); // Update database list

                if (!FileHandler.writeSupplierToFile(newSupplier)) {
                    showAlert(Alert.AlertType.ERROR, "File Error", "Failed to save the supplier to the file.");
                    return;
                }

                showAlert(Alert.AlertType.INFORMATION, "Add Supplier", "Supplier added successfully.");
                clearInputFields();

            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Supplier ID must be a valid number.");
            }
        });

    }
    private boolean validateSupplierInput(String supplierId, String companyName, String email, String phoneNumber, String address) {
        // Validate Supplier ID (must be a valid integer)
        if (supplierId.isEmpty() || !Validator.validatePositiveInteger(supplierId)) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Supplier ID must be a valid number.");
            return false;
        }


        if (!Validator.validateName(companyName)) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Company Name must start with a capital letter and contain only lowercase letters.");
            return false;
        }

        if (!Validator.isValidEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Email must be in a valid format.");
            return false;
        }

        if (!Validator.validatePositiveInteger(phoneNumber)) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Phone number must be in a valid format.");
            return false;
        }

        if (address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Address cannot be empty.");
            return false;
        }
        return true;
    }

    private void productDelete() {
        ObservableList<Supplier> selectedItems = this.view.getTable().getSelectionModel().getSelectedItems();

        if (selectedItems.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Delete Warning", "No item selected for deletion.");
            return;
        }

        suppliers.removeAll(selectedItems);
        tableContent.removeAll(selectedItems);
        updateSupplierFile();

        showAlert(Alert.AlertType.INFORMATION, "Delete Result", "Deleted successfully.");
    }

    private void addProduct() {
        String supplierId = this.view.getsId().getText();
        String companyName = this.view.getcName().getText();
        String email = view.getEmailField().getText();
        String address = view.getAddressField().getText();
        String phoneNumber = this.view.getPhone().getText();

        System.out.println("Test 1");
        if (supplierId.isEmpty() || companyName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled out.");
            return;
        }

        try {
            int supplierIdInt = Integer.parseInt(supplierId);
            if (supplierExists(supplierIdInt)) {
                showAlert(Alert.AlertType.WARNING, "Duplicate Supplier", "A supplier with this ID already exists!");
                return;
            }
            System.out.println("Test 2");
            Supplier newSupplier = new Supplier(supplierIdInt, companyName, email, phoneNumber, address);
            view.getSupplierList().add(newSupplier);
            suppliers.add(newSupplier);
            view.getTable().getItems().clear();
            this.tableContent.add(newSupplier);
            view.getTable().setItems(tableContent);
            FileHandler.writeSupplierToFile(newSupplier);

            System.out.println("Test 3");
            if (!FileHandler.writeSupplierToFile(newSupplier)) {
                showAlert(Alert.AlertType.ERROR, "File Error", "Failed to save the supplier to the file.");
                return;
            }
            System.out.println("Test 4");
            showAlert(Alert.AlertType.INFORMATION, "Add Supplier", "Supplier added successfully.");
            clearInputFields();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Supplier ID must be a valid number.");
        }
    }

    private void updateSupplierFile() {
        FileHandler.updateSupplierInFile(suppliers);
    }

    private void clearInputFields() {
        this.view.getsId().clear();
        this.view.getcName().clear();
        this.view.getEmail().setText("");
        this.view.getPhone().clear();
        this.view.getAddress().setText("");
    }

    private boolean supplierExists(int supplierId) {
        return suppliers.stream().anyMatch(supplier -> supplier.getSupplierId() == supplierId);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public SupplierView getView() {
        return view;
    }

    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }

    public ObservableList<Supplier> getTableContent() {
        return tableContent;
    }
}
