package View.BillingSystemView;

import View.Design;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

//Sh
public class BillingSystemView extends HBox implements Design {
    private ProductCartView productCartBox=new ProductCartView();
    private CheckoutView checkOutPane=new CheckoutView();

    public BillingSystemView(){
        setupView();
    }

    public void setupView(){
        productCartBox.prefWidthProperty().bind(this.widthProperty().divide(2));
        productCartBox.prefHeightProperty().bind(this.heightProperty());
        //Right part for checkout
        checkOutPane.prefWidthProperty().bind(this.widthProperty().divide(2));
        checkOutPane.prefHeightProperty().bind(this.heightProperty());
        //Arranging view
        this.setPadding(new Insets(10,10,10,10));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        this.setSpacing(30);
        this.getChildren().addAll(productCartBox,checkOutPane);
    }

    public ProductCartView getProductCartBox() {
        return productCartBox;
    }

    public CheckoutView getCheckOutPane() {
        return checkOutPane;
    }

}