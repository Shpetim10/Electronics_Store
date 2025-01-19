package Control;

import Model.Item;
import Model.SectorType;
import Model.Validator;
import View.Add_EditView;
import javafx.scene.control.Alert;

import java.time.LocalDate;

import static Model.FileHandler.writeProductToFile;

public class AddController {
    private final Add_EditView view;

    public AddController(Add_EditView view) {
        this.view = view;
        initialize();
    }

    public void initialize() {
        // Attach event handler to the Add button
        view.getAdd().setOnAction(e -> handleAddProduct());
    }

    public void handleAddProduct() {
        try {
            int productCodeValue = Integer.parseInt(view.getProductCode().getText());
            String productNameValue="";
            if(Validator.validateProductName(view.getProductName().getText())){
                productNameValue = view.getProductName().getText();
            }
            else{
                showAlert(Alert.AlertType.WARNING,"Invalid Input","Files.Product name does not conform format!\n Must start with capital leter and all letters!");
            }

            SectorType sectorValue = SectorType.valueOf(view.getSector().getText().toUpperCase());
            double sellingPriceValue = Double.parseDouble(view.getSellingPrice().getText());
            double priceBoughtValue = Double.parseDouble(view.getPriceBought().getText());
            String supplier = view.getSupplier().getText();
            String image=view.getImage().getText();
//            String isDiscountedValue = String.valueOf(view.getIsDiscounted().getValue().equalsIgnoreCase("Yes"));
//            double discountRateValue = (isDiscountedValue.equals("false"))? 0.0 : Double.parseDouble(view.getDiscountRate().getText());
            int stockQuantityValue = Integer.parseInt(view.getStockQuantity().getText());
            String brandValue = view.getBrand().getText();
            LocalDate lastRestockDateValue = view.getLastRestockDate().getValue();
            int barcodeValue = Integer.parseInt(view.getBarcode().getText());

            Item newItem = new Item(
                    productCodeValue,
                    productNameValue,
                    sectorValue.toString(), //Change
                    sellingPriceValue,
                    priceBoughtValue,
                    supplier,
                    stockQuantityValue,
                    brandValue,
                    lastRestockDateValue,
                    barcodeValue


            );


            view.getTable().getItems().add(newItem);
            writeProductToFile(newItem);

            // Clear input fields in the view
            clearInputFields();

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Files.Product Added", "The product was successfully added!");
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
        view.getSector().clear();
//        view.getDescription().clear();
        view.getSellingPrice().clear();
        view.getPriceBought().clear();
        view.getSupplier().clear();
//        view.getIsDiscounted().setValue("No");
//        view.getDiscountRate().clear();
        view.getStockQuantity().clear();
//        view.getWeight().clear();
//        view.getVolume().clear();
//        view.getColor().clear();
        view.getBrand().clear();
//        view.getIsDiscontinued().setValue("No");
//        view.getIsAvailable().setValue("Yes");
//        //
        view.getBarcode().clear();
        view.getNrOfReturns().clear();
        //Shtoi dhe ne file
    }

//    public Date parseDate(String dateString) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        return formatter.parse(dateString);
//    }

    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

