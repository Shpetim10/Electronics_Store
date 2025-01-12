package View;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class ProductInformationView implements Design {
//
//    private ImageView photo;
//    private Label name;
//    private Label productId;
//    private Label quantity;
//    private Label price;
//    private Label sector;
//    private Label description;
//    private Label supplier;
//    private Label brand;
//    private Label lastRestock;
//    private Label sellingPrice;
//    private Label weight;
//    private CheckBox isAvailable;
//    private CheckBox isDiscontinued;
//    private Label priceBought;
//
//    public ProductInformationView() {
//
//    this.photo=createAlignedBlackLabel("",100);
//    this.isAvailable=createCheckBox();
//    this.isDiscontinued=createCheckBox();
//
//    this.productId=createAlignedBlackLabel(String.valueOf(productId),40);
//    this.name=createAlignedBlackLabel(String.valueOf(name),200);
//
//    this.quantity=createAlignedBlackLabel(String.valueOf(quantity),50);
//
//    this.price=createAlignedBlackLabel(String.valueOf(price),50);
//    this.sector=createAlignedBlackLabel(String.valueOf(sector),300);
//    this.description=createAlignedBlackLabel(String.valueOf(description),500);
//    this.supplier=createAlignedBlackLabel(String.valueOf(supplier),300);
//    this.brand=createAlignedBlackLabel(String.valueOf(brand),300);
//    this.lastRestock=createAlignedBlackLabel(String.valueOf(lastRestock),300);
//    this.sellingPrice=createAlignedBlackLabel(String.valueOf(sellingPrice),200);
//    this.weight=createAlignedGreenBoldLabel(String.valueOf(weight),200);
//    this.priceBought=createAlignedBlackLabel(String.valueOf(priceBought),200);
//
//}
//
//    public Label getPhoto() {return photo;}
//
//    public CheckBox getIsAvailable() {return isAvailable;}
//
//    public CheckBox getIsDiscontinued() {return isDiscontinued;}
//
//    public Label getProductId() {return productId;}
//
//    public Label getName() {return name;}
//
//    public Label getQuantity() {return quantity;}
//
//    public Label getPrice() {return price;}
//
//    public Label getSector() {return sector;}
//
//    public Label getDescription() {return description;}
//
//    public Label getSupplier() {return supplier;}
//
//    public Label getBrand() {return brand;}
//
//    public Label getLastRestock() {return lastRestock;}
//
//    public Label getSellingPrice() {return sellingPrice;}
//
//    public Label getWeight() {return weight;}
//
//    public Label getPriceBought() {return priceBought;}
//
//
//    HBox topSection = new HBox(10);
//        topSection.setPadding(new Insets(10));
//
//    Button refreshButton = new Button("Refresh");
//        topSection.getChildren().addAll(categoryComboBox, productComboBox, refreshButton);
//
//    // Center Section: Product Details
//    GridPane detailsGrid = new GridPane();
//        detailsGrid.setPadding(new Insets(10));
//        detailsGrid.setHgap(10);
//        detailsGrid.setVgap(10);
//
//    // Photo Placeholder
//    Label photoLabel = new Label("Photo");
//        photoLabel.setPrefSize(100, 100);
//        photoLabel.setStyle("-fx-border-color: black; -fx-alignment: left;");
//
//    // Product Info Fields
//    Label productIdLabel = new Label("Barcode:");
//    TextField productIdField = new TextField();
//
//    Label nameLabel = new Label("Name:");
//    TextField nameField = new TextField();
//
//    Label sectorLabel = new Label("Sector:");
//    TextField sectorField = new TextField();
//
//    Label quantityLabel = new Label("Quantity:");
//    TextField quantityField = new TextField();
//
//    Label priceLabel = new Label("Price:");
//    TextField priceField = new TextField();
//
//    Label descriptionLabel = new Label("Description:");
//    TextArea descriptionField = new TextArea();
//        descriptionField.setPrefRowCount(3);
//
//    Label supplierLabel = new Label("Supplier:");
//    TextField supplierField = new TextField();
//
//    Label brandLabel = new Label("Brand:");
//    TextField brandField = new TextField();
//
//    Label lastRestockLabel = new Label("Last Restock:");
//    DatePicker lastRestockPicker = new DatePicker();
//
//    Label weightLabel = new Label("Weight:");
//    TextField weightField = new TextField();
//
//    Label sellingPriceLabel = new Label("Selling Price:");
//    TextField sellingPriceField = new TextField();
//
//    Label priceBoughtLabel = new Label("Price Bought:");
//    TextField priceBoughtField = new TextField();
//
//    CheckBox isAvailableCheckbox = new CheckBox("Is Available");
//    CheckBox isDiscontinuedCheckbox = new CheckBox("Is Discontinued");
//
//    // Adding elements to GridPane
//        detailsGrid.add(photoLabel, 0, 0, 1, 4);
//
//        detailsGrid.add(productIdLabel, 1, 0);
//        detailsGrid.add(productIdField, 2, 0);
//        detailsGrid.add(nameLabel, 1, 1);
//        detailsGrid.add(nameField, 2, 1);
//        detailsGrid.add(sectorLabel, 1, 2);
//        detailsGrid.add(sectorField, 2, 2);
//        detailsGrid.add(quantityLabel, 1, 3);
//        detailsGrid.add(quantityField, 3, 3);
//
//        detailsGrid.add(descriptionLabel, 1, 4);
//        detailsGrid.add(descriptionField, 2, 3, 2, 1);//Check here
//
//        detailsGrid.add(supplierLabel, 1, 5);
//        detailsGrid.add(supplierField, 2, 5);
//        detailsGrid.add(brandLabel, 1, 6);
//        detailsGrid.add(brandField, 2, 6);
//        detailsGrid.add(lastRestockLabel, 1, 7);
//        detailsGrid.add(lastRestockPicker, 2,7);
//
//        detailsGrid.add(priceLabel, 1, 8);
//        detailsGrid.add(priceField, 2, 8);
//        detailsGrid.add(volumeLabel, 1, 9);
//        detailsGrid.add(volumeField, 2, 9);
//        detailsGrid.add(weightLabel, 1, 10);
//        detailsGrid.add(weightField, 2, 10);
//
//        detailsGrid.add(sellingPriceLabel, 3, 0);
//        detailsGrid.add(sellingPriceField, 4, 0);
//
//        detailsGrid.add(priceBoughtLabel, 3, 1);
//        detailsGrid.add(priceBoughtField, 4, 1);
//
//        detailsGrid.add(isAvailableCheckbox, 3, 3);
//        detailsGrid.add(isDiscontinuedCheckbox, 4, 3);
//
//    // Main Layout
//    BorderPane mainLayout = new BorderPane();
//        mainLayout.setTop(topSection);
//        mainLayout.setCenter(detailsGrid);

}
