package Control;

import Database.Database;
import Database.FileHandler;
import Model.*;
import View.AddProductView;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;

import java.time.LocalDate;



public class AddProductController {
    private final AddProductView view=new AddProductView();

    public AddProductController() {
        initialize();
    }

    public void initialize() {
        // Attach event handler to the Add button
        view.getAdd().setOnAction(e -> handleAddProduct());
    }

    public void handleAddProduct() {
        try {
            String productCodeText = view.getProductCode().getText();
            if (!Validator.validatePositiveInteger(productCodeText)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Product Code must be a positive integer.");
                return;
            }
            String barcodeText = view.getBarcode().getText();
            if (!Validator.validatePositiveInteger(barcodeText)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Barcode must be a positive integer.");
                return;
            }
            int barcodeValue = Integer.parseInt(barcodeText);
            //Checking existing products
            int productCodeValue = Integer.parseInt(productCodeText);
            if (productExists(productCodeValue, barcodeValue)) {
                showAlert(Alert.AlertType.WARNING, "Duplicate Product", "A product with this Product Code or Barcode already exists!");
                return;
            }
            String productNameValue = view.getProductName().getText();
            if (!Validator.validateProductName(productNameValue)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Product Name must start with a capital letter and contain only letters.");
                return;
            }

            if (view.getSector().getSelectionModel().getSelectedItem() == null) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Please select a Sector.");
                return;
            }

            if (view.getSector().getSelectionModel().getSelectedItem() == null) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Please select a Sector.");
                return;
            }
            SectorType sectorValue = SectorType.valueOf(view.getSector().getSelectionModel().getSelectedItem());


            String sellingPriceText = view.getSellingPrice().getText();
            if (!Validator.validatePositiveDouble(sellingPriceText)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Selling Price must be a positive number.");
                return;
            }
            double sellingPriceValue = Double.parseDouble(sellingPriceText);


            String priceBoughtText = view.getPriceBought().getText();
            if (!Validator.validatePositiveDouble(priceBoughtText)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Price Bought must be a positive number.");
                return;
            }
            double priceBoughtValue = Double.parseDouble(priceBoughtText);

            String supplier = view.getSupplier().getText();
            if (!Validator.validateSupplierName(supplier)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Supplier field cannot be empty.");
                return;
            }


            String imagePath = view.getImage().getText();
            if (!Validator.validateImageFile(imagePath)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Image must be a valid file (jpg, jpeg, png) and exist on disk.");
                return;
            }

            String stockQuantityText = view.getStockQuantity().getText();
            if (!Validator.validatePositiveInteger(stockQuantityText)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Stock Quantity must be a non-negative integer.");
                return;
            }

            int stockQuantityValue = Integer.parseInt(stockQuantityText);

            String brandValue = view.getBrand().getText();
            if (!Validator.validateSupplierName(brandValue)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Brand field cannot be empty.");
                return;
            }

            LocalDate lastRestockDateValue = view.getLastRestockDate().getValue();
            if (!Validator.validateLastRestockDate(lastRestockDateValue)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Input", "Last Restock Date cannot be in the future.");
                return;
            }
            if (!Validator.isSupplierRegistered(supplier)) {
                showAlert(Alert.AlertType.WARNING, "Unregistered Supplier", "The supplier must be registered in the system before adding products.");
                return;
            }
            Item newItem = new Item(
                    productCodeValue,
                    productNameValue,
                    sectorValue.toString(),
                    sellingPriceValue,
                    priceBoughtValue,
                    supplier,
                    stockQuantityValue,
                    brandValue,
                    lastRestockDateValue,
                    barcodeValue,
                    imagePath

            );

            RestockTransaction transaction=new RestockTransaction(newItem,newItem.getStockQuantity());
            Database.getDatabase().saveRestockTransaction(transaction);
            Database.getDatabase().saveProduct(newItem);
            clearInputFields();

            for(Cashier user: Database.getDatabase().getCashiers()){
                if(user.getSector().toString().equals(newItem.getSector())){
                    user.getNotifications().add(new Notification(NotificationType.NEW_PRODUCT,"We want to notify you that "+newItem.getProductName()+" is added to inventory!"));
                }
            }
            Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Product Added", "The product was successfully added!");
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please ensure all numeric fields are correctly filled.");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please check the values for fields like Sector or Supplier.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add product. Please check your input values.");
        }
    }

    public void clearInputFields() {
        view.getProductCode().clear();
        view.getProductName().clear();
        view.getSellingPrice().clear();
        view.getPriceBought().clear();
        view.getSupplier().clear();
        view.getStockQuantity().clear();
        view.getBrand().clear();
        view.getBarcode().clear();
        view.getNrOfReturns().clear();
        view.getLastRestockDate();
    }

    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean productExists(int productCode, int barcode) {
        for (Item item : view.getTable().getItems()) {
            if (item.getProductId() == productCode || item.getBarcode() == barcode) {
                return true; // Product already exists
            }
        }
        return false;
    }

    public AddProductView getView() {
        return view;
}
}