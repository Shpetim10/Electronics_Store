package View;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class InventoryListView implements Design {
    private Label id;
    private Label name;
    private Label quantity;
    private Label CostPrice;
    private Label sellingPrice;
    private Label Brand;
    private CheckBox select;
    public InventoryListView(int id,String name,int quantity,double CostPrice ,double sellingPrice,String Brand) {
        this.id = createAlignedBlackLabel(String.valueOf(id), 40);
        this.name = createAlignedBlackLabel(name, 200);
        this.quantity = createAlignedBlackLabel(String.valueOf(quantity), 80);
        this.CostPrice = createAlignedBlackLabel(String.valueOf(CostPrice), 80);
        this.sellingPrice = createAlignedBlackLabel(String.valueOf(sellingPrice), 100);
        this.Brand = createAlignedBlackLabel(Brand, 80);
        this.select=createCheckBox();
    }
    public Label getId() {
        return id;
    }

    public void setId(Label id) {
        this.id = id;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getQuantity() {
        return quantity;
    }

    public void setQuantity(Label quantity) {
        this.quantity = quantity;
    }

    public Label getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(Label costPrice) {
        this.CostPrice = costPrice;
    }

    public Label getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Label sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Label getBrand() {
        return Brand;
    }

    public void setBrand(Label brand) {
        this.Brand = brand;
    }
    public CheckBox getSelect() {
        return select;
    }
    public void setSelect(CheckBox select) {
        this.select = select;
    }


}
