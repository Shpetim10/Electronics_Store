package View;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ItemBoughtView implements Design{
    private CheckBox select;
    private Label id;
    private Label name;
    private TextField quantity; //Will change value
    private Label sellingPrice;
    private Label totalProductPrice; //Will change value

    public ItemBoughtView(int id,String name, double sellingPrice){
        this.select=createCheckBox();
        this.id=createAlignedBlackLabel(String.valueOf(id),40);
        this.name=createAlignedBlackLabel(name,200);

        this.quantity=createTransparentTextField();
        this.quantity.setPrefWidth(80);
        this.quantity.setText("0");

        this.sellingPrice=createAlignedBlackLabel(String.valueOf(sellingPrice),50);

        this.totalProductPrice=createAlignedBlackLabel("0.0",80);
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public Label getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(Label totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public Label getId() {
        return id;
    }

    public Label getName() {
        return name;
    }

    public Label getSellingPrice() {
        return sellingPrice;
    }
}
