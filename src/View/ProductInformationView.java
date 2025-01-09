package View;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class ProductInformationView implements Design {

private Label name;
private Label productId;
private Label quantity;
private Label price;
private Label sector;
private Label description;
private Label supplier;
private Label brand;
private Label lastRestock;
private Label sellingPrice;
private Label weight;
private CheckBox isAvailable;
private CheckBox isDiscontinued;
private Label priceBought;

    public ProductInformationView(String name, long productId, int quantity, double price,
                                  String sector, String description, String supplier, String brand) {
    this.isAvailable=createCheckBox();
    this.isDiscontinued=createCheckBox();

    this.productId=createAlignedBlackLabel(String.valueOf(productId),40);
    this.name=createAlignedBlackLabel(name,200);

    this.quantity=createAlignedBlackLabel(String.valueOf(quantity),50);

    this.price=createAlignedBlackLabel(String.valueOf(price),50);
    this.sector=createAlignedBlackLabel(sector,300);
    this.description=createAlignedBlackLabel(description,500);
    this.supplier=createAlignedBlackLabel(supplier,300);
    this.brand=createAlignedBlackLabel(brand,300);
    this.lastRestock=createAlignedBlackLabel(String.valueOf(lastRestock),300);
    this.sellingPrice=createAlignedBlackLabel(String.valueOf(sellingPrice),200);
    this.weight=createAlignedGreenBoldLabel(String.valueOf(weight),200);
    this.priceBought=createAlignedBlackLabel(String.valueOf(priceBought),200);

}

    public CheckBox getIsAvailable() {
        return isAvailable;
    }
    public CheckBox getIsDiscontinued() {
        return isDiscontinued;
    }
    public Label getProductId() {
        return productId;
    }
    public Label getName() {
        return name;
    }
    public Label getQuantity() {
        return quantity;
    }
    public Label getPrice() {
        return price;
    }
    public Label getSector() {
        return sector;
    }
    public Label getDescription() {
        return description;
    }
    public Label getSupplier() {
        return supplier;
    }
    public Label getBrand() {
        return brand;
    }
    public Label getLastRestock() {
        return lastRestock;
    }
    public Label getSellingPrice() {
        return sellingPrice;
    }
    public Label getWeight() {
        return weight;
    }
    public Label getPriceBought() {
        return priceBought;
    }
}
