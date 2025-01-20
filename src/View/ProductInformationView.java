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


public class ProductInformationView extends VBox implements Design {

    private ImageView photo = new ImageView();
    private Label name = createAlignedBlackLabel("");
    private Label productId = createAlignedBlackLabel("");
    private Label quantity = createAlignedBlackLabel("");
    private Label price = createAlignedBlackLabel("");
    private Label sector = createAlignedBlackLabel("");
    private Label supplier = createAlignedBlackLabel("");
    private Label brand = createAlignedBlackLabel("");
    private Label lastRestock = createAlignedBlackLabel("");
    private Label sellingPrice = createAlignedBlackLabel("");
    private Label priceBought = createAlignedBlackLabel("");
    private final SearchBoxPane searchBox=new SearchBoxPane("Search Product...");
    private GridPane infoPane=createInfoPane();
    private CustomTableView inventoryTable=new CustomTableView();
    private StackPane displayPane=new StackPane();

    public ImageView getPhoto() {
        return photo;
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

    public Label getPriceBought() {
        return priceBought;
    }
    public SearchBoxPane getSearchBox() {
        return searchBox;
    }

    public GridPane getInfoPane() {
        return infoPane;
    }

    public CustomTableView getInventoryTable() {
        return inventoryTable;
    }

    public StackPane getDisplayPane() {
        return displayPane;
    }
    public ProductInformationView() {

        setUpView();

    }
    public void setUpView() {
        this.setSpacing(30);
        this.setPadding(new Insets(20,20,20,20));
        searchBox.getSearchButton().setText("View");
        this.getChildren().addAll(searchBox,displayPane);
    }
    public GridPane createInfoPane(){
        GridPane pane=new GridPane();
        pane.setHgap(50);
        pane.setVgap(30);
        pane.setPadding(new Insets(50, 50, 50, 50));
        pane.setStyle("-fx-background-color: rgba(167,246,8,0.15)");
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

        Label sellingPriceLabel = new Label("Selling Price:");
        sellingPriceLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label sellingPriceField = new Label();
        sellingPriceField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        Label priceBoughtLabel = new Label("Price Bought:");
        priceBoughtLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        Label priceBoughtField = new Label();
        priceBoughtField.setStyle("-fx-pref-width: 200;-fx-background-color: white;-fx-border-width:3 ; -fx-alignment: center; -fx-background-radius: 40; -fx-border-radius: 40; -fx-border-width: 2; -fx-border-color: yellowgreen;");

        pane.add(photoLabel, 4, 5,5,4);
        pane.add(productIdLabel, 1, 1);
        pane.add(productIdField, 2, 1);
        pane.add(nameLabel, 1, 2);
        pane.add(nameField, 2, 2);

        pane.add(quantityLabel, 1, 4);
        pane.add(quantityField, 2, 4);

        pane.add(supplierLabel, 1, 5);
        pane.add(supplierField, 2, 5);

        pane.add(lastRestockLabel, 1, 7);
        pane.add(lastRestockPicker, 2, 7);

        pane.add(priceLabel, 1, 8);
        pane.add(priceField, 2, 8);

        pane.add(brandLabel, 1, 11);
        pane.add(brandField, 2, 11);

        pane.add(sectorLabel, 1, 10);
        pane.add(sectorField, 2, 10);

        pane.add(sellingPriceLabel, 3, 2);
        pane.add(sellingPriceField, 4, 2);

        pane.add(priceBoughtLabel, 3, 1);
        pane.add(priceBoughtField, 4, 1);


        return pane;
    }


}
