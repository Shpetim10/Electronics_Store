package Control;

import Model.Item;
import View.ProductInformationView;

public class ProductInformationController {
    private ProductInformationView view=new ProductInformationView();

    public ProductInformationController(){
        setUpPaneSwitcher();
    }

public void setUpPaneSwitcher(){
    view.getSearchBox().getSearchField().setOnMouseClicked(
            e->{
        this.view.getDisplayPane().getChildren().clear();
        this.view.getDisplayPane().getChildren().add(this.view.getInventoryTable().getTable());
    });
    view.getSearchBox().getSearchButton().setOnAction(e-> {
        this.view.getDisplayPane().getChildren().clear();
        this.view.getDisplayPane().getChildren().add(this.view.getInfoPane());
        Item item=this.view.getInventoryTable().getTable().getSelectionModel().getSelectedItem();
        displayProductInfo(item);

    });
}
public void displayProductInfo(Item selectedItem)
{
    view.getName().setText(selectedItem.getProductName());
    view.getSector().setText(selectedItem.getSector());
    view.getSupplier().setText(selectedItem.getSupplier());
    view.getBrand().setText(selectedItem.getBrand());
    view.getLastRestock().setText(String.valueOf(selectedItem.getLastRestockDate()));
    view.getSellingPrice().setText(String.format("$%.2f", selectedItem.getSellingPrice()));
    view.getPriceBought().setText(String.format("$%.2f", selectedItem.getPriceBought()));
    view.getName().setText(selectedItem.getProductName());
    view.getProductId().setText(String.valueOf(selectedItem.getProductId()));
    view.getQuantity().setText(String.valueOf(selectedItem.getStockQuantity()));
    view.getSector().setText(selectedItem.getSector());
    view.getSupplier().setText(selectedItem.getSupplier());
    view.getBrand().setText(selectedItem.getBrand());
    view.getLastRestock().setText(String.valueOf(selectedItem.getLastRestockDate()));
    view.getSellingPrice().setText(String.format("$%.2f", selectedItem.getSellingPrice()));
    view.getPriceBought().setText(String.format("$%.2f", selectedItem.getPriceBought()));
    // view.getPhotoLabel().setText((selectedItem.getImage()));
}
public ProductInformationView getView() {
    return view;
}
}