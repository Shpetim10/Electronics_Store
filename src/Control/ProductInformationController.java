package Control;

import Model.Item;
import View.ProductInformationView;

public class ProductInformationController {
    private ProductInformationView view=new ProductInformationView();

    public ProductInformationController(){
        setUpPaneSwitcher();    }

    public void setUpPaneSwitcher(){
        view.getSearchBox().getSearchField().setOnMouseClicked(
                e->{
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(this.view.getInventoryTable().getTable());
                }
        );
        view.getSearchBox().getSearchButton().setOnAction(
                e->{
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(this.view.getInfoPane());
                    displayProductInfo(view.getInventoryTable().getTable().getSelectionModel().getSelectedItem());
                }
        );
    }
    public void displayProductInfo(Item selectedItem)
    {
        // Update the labels in the info pane with the selected product details
        view.getNameField().setText(selectedItem.getProductName());
        view.getProductIdField().setText(String.valueOf(selectedItem.getProductId()));
        view.getQuantityField().setText(String.valueOf(selectedItem.getStockQuantity()));
        //view.getPriceField().setText(String.format("$%.2f",String.valueOf(selectedItem.getS());
        view.getSectorField().setText(selectedItem.getSector());
        view.getSupplierField().setText(selectedItem.getSupplier());
        view.getBrandField().setText(selectedItem.getBrand());
        view.getLastRestockPicker().setText(String.valueOf(selectedItem.getLastRestockDate()));
        view.getSellingPriceField().setText(String.format("$%.2f", selectedItem.getSellingPrice()));
        view.getPriceBoughtField().setText(String.format("$%.2f", selectedItem.getPriceBought()));
        // view.getPhotoLabel().setText((selectedItem.getImage()));
    }
    public ProductInformationView getView() {
        return view;
    }
}
