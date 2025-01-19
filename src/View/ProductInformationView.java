package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import View.SearchBoxPane;


public class ProductInformationView extends GridPane implements Design {

    private ImageView photo = new ImageView();
    private Label name = createAlignedBlackLabel("");
    private Label productId = createAlignedBlackLabel("");
    private Label quantity = createAlignedBlackLabel("");
    private Label price = createAlignedBlackLabel("");
    private Label sector = createAlignedBlackLabel("");
    private Label description = createAlignedBlackLabel("");
    private Label supplier = createAlignedBlackLabel("");
    private Label brand = createAlignedBlackLabel("");
    private Label lastRestock = createAlignedBlackLabel("");
    private Label sellingPrice = createAlignedBlackLabel("");
    private Label weight = createAlignedBlackLabel("");
    private CheckBox isAvailable = createCheckBox();
    private CheckBox isDiscontinued = createCheckBox();
    private Label priceBought = createAlignedBlackLabel("");
    private final SearchBoxPane searchBox=new SearchBoxPane("Search Product...");


    public ImageView getPhoto() {
        return photo;
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

    public HBox topSection() {
        HBox topSection = new HBox(10);
        topSection.setPadding(new Insets(10));

        Button refreshButton = new Button("Refresh");
        topSection.getChildren().addAll(refreshButton);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topSection);

        return topSection;
    }

    // Adding elements to GridPane
    public ProductInformationView() {

        setUpView();

        Label photoLabel = new Label(" Photo");
        photoLabel.setStyle("-fx-font-size: 12;  -fx-text-fill: darkgreen;");
        photoLabel.setPrefSize(180, 180);
        photoLabel.setStyle("-fx-border-color: black;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        // Database.Files.Product Info Fields
        Label productIdLabel = new Label("Barcode:");
        productIdLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label productIdField = new Label("");
        productIdField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label nameField = new Label();
        nameField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label sectorLabel = new Label("Sector:");
        sectorLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label sectorField = new Label();
        sectorField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label quantityField = new Label();
        quantityField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label priceLabel = new Label("Price:");
        priceLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label priceField = new Label();
        priceField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label descriptionField = new Label();
        descriptionField.setStyle("-fx-pref-width: 300;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");


        Label supplierLabel = new Label("Supplier:");
        supplierLabel.setStyle(" -fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label supplierField = new Label();
        supplierField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label brandLabel = new Label("Brand:");
        brandLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label brandField = new Label();
        brandField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label lastRestockLabel = new Label("Last Restock:");
        lastRestockLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label lastRestockPicker = new Label();
        lastRestockPicker.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label weightLabel = new Label("Weight:");
        weightLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label weightField = new Label();
        weightField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label sellingPriceLabel = new Label("Selling Price:");
        sellingPriceLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label sellingPriceField = new Label();
        sellingPriceField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label priceBoughtLabel = new Label("Price Bought:");
        priceBoughtLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label priceBoughtField = new Label();
        priceBoughtField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        CheckBox isAvailableCheckbox = new CheckBox("Is Available?");
        isAvailableCheckbox.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        CheckBox isDiscontinuedCheckbox = new CheckBox("Is Discontinued?");
        isDiscontinuedCheckbox.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");


        this.add(photoLabel, 0, 0);

        this.add(searchBox,2,0);

        this.add(productIdLabel, 1, 1);
        this.add(productIdField, 2, 1);
        this.add(nameLabel, 1, 2);
        this.add(nameField, 2, 2);

        this.add(sectorLabel, 1, 10);
        this.add(sectorField, 2, 10);

        this.add(quantityLabel, 1, 3);
        this.add(quantityField, 2, 3);

        this.add(descriptionLabel, 1, 4);
        this.add(descriptionField, 2, 4, 2, 1);

        this.add(supplierLabel, 1, 5);
        this.add(supplierField, 2, 5);
        this.add(brandLabel, 1, 6);
        this.add(brandField, 2, 6);
        this.add(lastRestockLabel, 1, 7);
        this.add(lastRestockPicker, 2, 7);

        this.add(priceLabel, 1, 8);
        this.add(priceField, 2, 8);

        this.add(weightLabel, 1, 9);
        this.add(weightField, 2, 9);

        this.add(sellingPriceLabel, 3, 2);
        this.add(sellingPriceField, 4, 2);

        this.add(priceBoughtLabel, 3, 1);
        this.add(priceBoughtField, 4, 1);

        this.add(isAvailableCheckbox, 3, 5);
        this.add(isDiscontinuedCheckbox, 3, 6);



    }
    public void setUpView() {
        this.setHgap(50);
        this.setVgap(30);
        this.setPadding(new Insets(50, 50, 50, 50));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15)");

    }
}
