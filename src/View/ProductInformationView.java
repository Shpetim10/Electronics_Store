package View;
import javafx.*;


public class ProductInformationView implements Design {

private String name;
private long productId;
private int quantity;
private double price;

public ProductInformationView(String name,long productId,int quantity,double price)
{
    Label titleLabel=new Label("Product Information");
    titleLabel.setStyle("-fx-font-size:25px");

}



}
