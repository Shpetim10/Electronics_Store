package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class InventoryManagementView implements Design {
    private ComboBox<String> search;
    private Button refresh;
    private Button products;
    private Button customers;
    private Button sales;
    private Button suppliers;
    private Button users;
    private TextField productCode;
    private TextField productName;
    private TextField date;
    private TextField quantity;
    private TextField costPrice;
    private TextField sellingPrice;
    private TextField brand;
    private Button add;
    private Button edit;
    private Button delete;
    private Button clear;
    private Button signOut;
    private ArrayList<InventoryListView> list;

    public InventoryManagementView() {
        this.refresh = createGeneralButton("Refresh");
        this.products = createGeneralButton("Products");
        products.setPrefWidth(200);
        products.setPrefHeight(50);

        this.suppliers = createGeneralButton("Suppliers");
        this.customers = createGeneralButton("Customers");
        this.sales = createGeneralButton("Sales");
        this.users = createGeneralButton("Users");
        this.productCode = createTextField("Product Code");
        this.productName = createTextField("Product Name");
        this.date = createTextField("Date");
        this.quantity = createTextField("Quantity");
        this.costPrice = createTextField("Cost Price");
        this.sellingPrice = createTextField("Selling Price");
        this.brand = createTextField("Brand");
        this.add = createGeneralButton("Add");
        this.edit = createGeneralButton("Edit");
        this.delete = createGeneralButton("Delete");
        this.clear = createGeneralButton("Clear");
        this.signOut = createGeneralButton("Sign Out");
        this.search = createComboBox("Select");
        this.list = new ArrayList<>();
    }

    public HBox addMetadata() {
        HBox metadata = new HBox(20);
        metadata.setStyle("-fx-font-family: Bahnschrift;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;");


        //Label tab = createAlignedBlackLabel("", 2); // To align with x button
        Label id = createAlignedBlackLabel("Id", 40);
        Label name = createAlignedBlackLabel("Name", 200);
        Label quantity = createAlignedBlackLabel("Quantity", 80);
        Label costPrice = createAlignedBlackLabel("Cost Price", 80);
        Label sellingPrice = createAlignedBlackLabel("Selling Price", 100);
        Label brand = createAlignedBlackLabel("Brand", 80);
        //shto dhe supplier ktu(vetem name)

        metadata.getChildren().addAll( id, name, quantity, costPrice, sellingPrice, brand);
        return metadata;
    }

    public HBox addRow(InventoryListView item) {
        HBox pane = new HBox(20);
        pane.setStyle("-fx-font-family: Bahnschrift;" +
                "-fx-font-size: 15;");

        pane.getChildren().addAll(item.getId(), item.getName(), item.getQuantity(),
                item.getCostPrice(), item.getSellingPrice(), item.getBrand());
        return pane;
    }

    public VBox createProductLog() {
        VBox box = new VBox(10);
        box.setMinHeight(400);
        box.setMinWidth(600);
        box.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-font-family: Bahnschrift;" +
                "-fx-border-radius: 30;" +
                "-fx-background-radius: 30;" +
                "-fx-padding: 10;");
        box.setAlignment(Pos.TOP_CENTER);

        Label headText = createAlignedGreenBoldLabel("Products", 150);


        VBox productList = new VBox(10);
        productList.setStyle("-fx-padding: 10;");

        // Here is the section for products(ItemBought)
        list.add(new InventoryListView(1, "Laptop", 50, 100, 200, "MacBook"));
        list.add(new InventoryListView(2, "Phone", 75, 700, 860, "Iphone 16"));
        for (InventoryListView item : list) {
            productList.getChildren().add(addRow(item));
        }

        box.getChildren().addAll(headText, addMetadata(), productList);
        return box;
    }

    public Scene createScene() {
        GridPane inventory = new GridPane();
        inventory.setHgap(20);
        inventory.setVgap(10);
        inventory.setPadding(new Insets(50, 100, 50, 100));
        inventory.setStyle("-fx-background-color: rgba(167,246,8,0.15)");

       // Label label = createAlignedGreenBoldLabel("User: [Name Surname] (Administrator)", 50);
        HBox searchBox = new HBox(100);
        searchBox.getChildren().addAll(search, refresh);

        VBox productLog=createProductLog();

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(add, edit, delete, clear);

        GridPane control = new GridPane();
        control.setHgap(10);
        control.setVgap(10);

        Label productCodeLabel = createAlignedGreenBoldLabel("Product Code", 150);
        Label productNameLabel = createAlignedGreenBoldLabel("Product Name", 150);
        Label dateLabel = createAlignedGreenBoldLabel("Date", 150);
        Label quantityLabel = createAlignedGreenBoldLabel("Quantity", 150);
        Label costPriceLabel = createAlignedGreenBoldLabel("Cost Price", 150);
        Label sellingPriceLabel = createAlignedGreenBoldLabel("Selling Price", 150);
        Label brandLabel = createAlignedGreenBoldLabel("Brand", 150);

        control.add(productCodeLabel, 0, 0);
        control.add(this.productCode,1,0);
        control.add(productNameLabel, 0, 2);
        control.add(productName,1,2);
        control.add(dateLabel, 0, 4);
        control.add(date,1,4);
        control.add(quantityLabel, 0, 6);
        control.add(quantity,1,6);
        control.add(costPriceLabel, 0, 8);
        control.add(costPrice,1,8);
        control.add(sellingPriceLabel, 0, 10);
        control.add(sellingPrice,1,10);
        control.add(brandLabel, 0, 12);
        control.add(brand,1,12);

        GridPane extra = new GridPane();
        control.setHgap(10);
        control.setVgap(10);
        extra.add(products,0,0);
        extra.add(suppliers,0,1);
        extra.add(customers,0,2);
        extra.add(sales,0,3);
        extra.add(users,0,4);



        inventory.add(extra,0,1);
        inventory.add(searchBox, 1, 0);
        inventory.add(productLog,1,1);
        inventory.add(buttons, 2, 2);
        inventory.add(control, 1, 2);


        Scene scene = new Scene(inventory);
        return scene;
    }

}
