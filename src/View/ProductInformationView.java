package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class ProductInformationView implements Design {

    private Label photo;
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

    public ProductInformationView() {

        this.photo=createAlignedBlackLabel("",100);
        this.isAvailable=createCheckBox();
        this.isDiscontinued=createCheckBox();

        this.productId=createAlignedBlackLabel(String.valueOf(productId),40);
        this.name=createAlignedBlackLabel(String.valueOf(name),200);

        this.quantity=createAlignedBlackLabel(String.valueOf(quantity),50);

        this.price=createAlignedBlackLabel(String.valueOf(price),50);
        this.sector=createAlignedBlackLabel(String.valueOf(sector),300);
        this.description=createAlignedBlackLabel(String.valueOf(description),400);
        this.supplier=createAlignedBlackLabel(String.valueOf(supplier),300);
        this.brand=createAlignedBlackLabel(String.valueOf(brand),300);
        this.lastRestock=createAlignedBlackLabel(String.valueOf(lastRestock),300);
        this.sellingPrice=createAlignedBlackLabel(String.valueOf(sellingPrice),200);
        this.weight=createAlignedGreenBoldLabel(String.valueOf(weight),200);
        this.priceBought=createAlignedBlackLabel(String.valueOf(priceBought),200);

    }
    public Label getPhoto() {return photo;}

    public CheckBox getIsAvailable() {return isAvailable;}

    public CheckBox getIsDiscontinued() {return isDiscontinued;}

    public Label getProductId() {return productId;}

    public Label getName() {return name;}

    public Label getQuantity() {return quantity;}

    public Label getPrice() {return price;}

    public Label getSector() {return sector;}

    public Label getDescription() {return description;}

    public Label getSupplier() {return supplier;}

    public Label getBrand() {return brand;}

    public Label getLastRestock() {return lastRestock;}

    public Label getSellingPrice() {return sellingPrice;}

    public Label getWeight() {return weight;}

    public Label getPriceBought() {return priceBought;}

    public HBox topSection() {
        HBox topSection = new HBox(10);
        topSection.setPadding(new Insets(10));

        Button refreshButton = new Button("Refresh");
        topSection.getChildren().addAll(refreshButton);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topSection);
        //mainLayout.setCenter(detailsGrid);

        return  topSection;}

    // Adding elements to GridPane
    public Scene createScene()
    {
        GridPane productInfo=new GridPane();
        productInfo.setHgap(100);
        productInfo.setVgap(30);
        productInfo.setPadding(new Insets(50,230,50,200));
        productInfo.setStyle("-fx-background-color: rgba(167,246,8,0.15)");

        Label photoLabel = new Label(" Photo");
        photoLabel.setStyle("-fx-font-size: 12;  -fx-text-fill: darkgreen;");
        photoLabel.setPrefSize(150, 200);
        photoLabel.setStyle("-fx-border-color: black;-fx-border-width:3 ; -fx-alignment: center;");

        // Product Info Fields
        Label productIdLabel = new Label("Barcode:");
        productIdLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label productIdField = new Label("");
        productIdField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label sectorLabel = new Label("Sector:");
        sectorLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField sectorField = new TextField();
        sectorField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField quantityField = new TextField();
        quantityField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label priceLabel = new Label("Price:");
        priceLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField priceField = new TextField();
        priceField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextArea descriptionField = new TextArea();
        descriptionField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");
        descriptionField.setPrefRowCount(0);

        Label supplierLabel = new Label("Supplier:");
        supplierLabel.setStyle(" -fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField supplierField = new TextField();
        supplierField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label brandLabel = new Label("Brand:");
        brandLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField brandField = new TextField();
        brandField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label lastRestockLabel = new Label("Last Restock:");
        lastRestockLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        DatePicker lastRestockPicker = new DatePicker();
        lastRestockPicker.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label weightLabel = new Label("Weight:");
        weightLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField weightField = new TextField();
        weightField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label sellingPriceLabel = new Label("Selling Price:");
        sellingPriceLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField sellingPriceField = new TextField();
        sellingPriceField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        Label priceBoughtLabel = new Label("Price Bought:");
        priceBoughtLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        TextField priceBoughtField = new TextField();
        priceBoughtField.setStyle("-fx-padding: 3px;-fx-border-color: lightgreen; -fx-border-width: 2px");

        CheckBox isAvailableCheckbox = new CheckBox("Available");
        isAvailableCheckbox.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        CheckBox isDiscontinuedCheckbox = new CheckBox("Discontinued");
        isDiscontinuedCheckbox.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");

        productInfo.add(photoLabel, 0, 0);

        productInfo.add(productIdLabel, 1, 1);
        productInfo.add(productIdField, 2, 1);
        productInfo.add(nameLabel, 2, 0);
        productInfo.add(nameField, 3, 0);
        productInfo.add(sectorLabel, 1, 2);
        productInfo.add(sectorField, 2, 2);
        productInfo.add(quantityLabel, 1, 3);
        productInfo.add(quantityField, 2, 3);

        productInfo.add(descriptionLabel, 1, 4);
        productInfo.add(descriptionField, 2, 4, 2, 1);

        productInfo.add(supplierLabel, 1, 5);
        productInfo.add(supplierField, 2, 5);
        productInfo.add(brandLabel, 1, 6);
        productInfo.add(brandField, 2, 6);
        productInfo.add(lastRestockLabel, 1, 7);
        productInfo.add(lastRestockPicker, 2, 7);

        productInfo.add(priceLabel, 1, 8);
        productInfo.add(priceField, 2, 8);

        productInfo.add(weightLabel, 1, 9);
        productInfo.add(weightField, 2, 9);

        productInfo.add(sellingPriceLabel, 3, 2);
        productInfo.add(sellingPriceField, 4, 2);

        productInfo.add(priceBoughtLabel, 3, 1);
        productInfo.add(priceBoughtField, 4, 1);

        productInfo.add(isAvailableCheckbox, 3, 5);
        productInfo.add(isDiscontinuedCheckbox, 3, 6);

        Scene scene=new Scene(productInfo);

        return scene; }
}
