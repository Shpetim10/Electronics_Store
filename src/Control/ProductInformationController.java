package Control;

import Model.Item;
import View.ProductInformationView;
import javafx.scene.Node;

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
                }
        );
        view.getSearchBox().getSearchButton().setOnAction(
                e->{
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(this.view.getInfoPane());

                   // displayProductInfo(view.createTable().getItems());
                }
        );
    }
    public void displayProductInfo(Item selectedItem)
    {
        // Update the labels in the info pane with the selected product details
        view.getName().setText(selectedItem.getProductName());
       // view.getProductId().setText(String.valueOf(selectedItem.(getProductId());
        //view.getQuantity().setText(String.valueOf(String.valueOf(selectedItem.getQuantity()));
        //view.getPrice().setText(String.format("$%.2f",String.valueOf(selectedItem.getPrice());
        view.getSector().setText(selectedItem.getSector());
        view.getSupplier().setText(selectedItem.getSupplier());
        view.getBrand().setText(selectedItem.getBrand());
        view.getLastRestock().setText(String.valueOf(selectedItem.getLastRestockDate()));
        view.getSellingPrice().setText(String.format("$%.2f", selectedItem.getSellingPrice()));
        view.getPriceBought().setText(String.format("$%.2f", selectedItem.getPriceBought()));
    }
    public ProductInformationView getView() {
        return view;
    }
}
