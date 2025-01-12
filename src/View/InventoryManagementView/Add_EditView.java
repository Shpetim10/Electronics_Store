package View.InventoryManagementView;

import View.Design;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Add_EditView implements Design {
    private CheckBox select;
    private TextField productCode;
    private TextField productName;
    private TextField sector;
    private TextField description;
    private TextField sellingPrice;
    private TextField priceBought;
    private TextField supplier;
    private ComboBox<String> isDiscounted;
    private TextField discountRate;
    private TextField stockQuantity;
    private TextField weight;
    private TextField volume;
    private TextField color;
    private TextField brand;
    private ComboBox<String> isDiscontinued;
    private ComboBox<String> isAvailable;
    private TextField lastRestockDate;
    private TextField barcode;
    private TextField nrOfReturns;
    private ArrayList<AddList> list;
    private Button add;
    private Button edit;

    public Add_EditView() {
        this.select=createCheckBox();
        this.productCode = createTextField("Product Code");
        this.productName = createTextField("Product Name");
        this.sector = createTextField("Sector");
        this.description = createTextField("Description");
        this.sellingPrice = createTextField("Selling Price");
        this.priceBought = createTextField("Price Bought");
        this.supplier = createTextField("Supplier");
        this.isDiscounted = createComboBox("Is Discounted");
        this.discountRate = createTextField("Discount Rate");
        this.stockQuantity = createTextField("Quantity");
        this.weight = createTextField("Weight");
        this.volume = createTextField("Volume");
        this.color = createTextField("Color");
        this.brand = createTextField("Brand");
        this.isDiscontinued = createComboBox("Is Discontinued");
        this.isAvailable = createComboBox("Is Available");
        this.lastRestockDate = createTextField("Last Restock Date");
        this.barcode = createTextField("Barcode");
        this.nrOfReturns = createTextField("Number of Returns");
        this.add=createGeneralButton("Add Product");
        this.edit=createGeneralButton("Edit Product");

        this.list = new ArrayList<>();
    }
    public HBox addMetadata() {
        HBox metadata = new HBox(20);
        metadata.setStyle("-fx-font-family: Bahnschrift;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;");
        metadata.setAlignment(Pos.TOP_LEFT);

        Label space=createAlignedBlackLabel("",60);
        Label sellingPrice = createAlignedBlackLabel("Selling Price", 150);
        Label productCode = createAlignedBlackLabel("Product Code", 150);
        Label productName = createAlignedBlackLabel("Product Name", 150);
        Label sector = createAlignedBlackLabel("Sector", 150);
        Label description = createAlignedBlackLabel("Description", 250);
        Label priceBought = createAlignedBlackLabel("Price Bought", 150);
        Label supplier = createAlignedBlackLabel("Supplier", 150);
        Label discountRate = createAlignedBlackLabel("Discount Rate", 150);
        Label stockQuantity = createAlignedBlackLabel("Quantity", 150);
        Label weight = createAlignedBlackLabel("Weight", 150);
        Label volume = createAlignedBlackLabel("Volume", 150);
        Label color = createAlignedBlackLabel("Color", 150);
        Label brand = createAlignedBlackLabel("Brand", 150);
        Label lastRestockDate = createAlignedBlackLabel("Last Restock Date", 200);
        Label barcode = createAlignedBlackLabel("Barcode", 150);
        Label nrOfReturns = createAlignedBlackLabel("Number of Returns", 150);

        Label isDiscounted = createAlignedBlackLabel("Is Discounted", 150); // Reduced from 180
        Label isDiscontinued = createAlignedBlackLabel("Is Discontinued", 150); // Reduced from 180
        Label isAvailable = createAlignedBlackLabel("Is Available", 150);

        metadata.getChildren().addAll(
                space,
                barcode,
                productCode,
                productName,
                brand,
                sector,
                supplier,
                priceBought,
                sellingPrice,
                discountRate,
                description,
                weight,
                volume,
                color,
                stockQuantity,
                lastRestockDate,
                nrOfReturns,
                isDiscounted,
                isDiscontinued,
                isAvailable
        );

        return metadata;
    }

    public HBox addRow(AddList item) {
        HBox pane = new HBox(20);
        pane.setStyle("-fx-font-family: Bahnschrift;" +
                "-fx-font-size: 15;");
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.getChildren().addAll(
                item.getSelect(),
                item.getBarcode(),
                item.getProductCode(),
                item.getProductName(),
                item.getBrand(),
                item.getSector(),
                item.getSupplier(),
                item.getPriceBought(),
                item.getSellingPrice(),
                item.getDiscountRate(),
                item.getDescription(),
                item.getWeight(),
                item.getVolume(),
                item.getColor(),
                item.getStockQuantity(),
                item.getLastRestockDate(),
                item.getNrOfReturns(),
                item.getIsDiscounted(),
                item.getIsDiscontinued(),
                item.getIsAvailable()
        );
        return pane;
    }


    public ScrollPane createProductLog() {
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefHeight(350);
        scroll.setPrefWidth(5000);
        scroll.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-font-family: Bahnschrift;" +
               "-fx-border-radius: 30;" +
                "-fx-background-radius: 30;" +
                "-fx-padding: 10;");
        scroll.setFitToWidth(true);
        scroll.setPannable(true);

        VBox box = new VBox(5);
        box.setMinHeight(600);
        box.setMinWidth(3000);
        box.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-font-family: Bahnschrift;" +
                "-fx-padding: 10;");
        box.setAlignment(Pos.TOP_CENTER);

        Label headText = createAlignedGreenBoldLabel("Products Full Info", 150);

        VBox listing = new VBox(10);
        listing.setStyle("-fx-padding: 10;");

        list.add(new AddList(
                200.0,
                "MacBook",
                "PROD12345",
                "Apple MacBook Pro",
                "Electronics",
                "High-performance laptop",
                95.0,
                "Apple Inc.",
                true,
                10.0,
                50,
                2.3,
                0.025,
                "Space Gray",
                false,
                true,
                "2025-01-01",
                "123456789012",
                5
        ));



        for (AddList item : list) {
            listing.getChildren().add(addRow(item));
        }
        box.getChildren().addAll(headText, addMetadata(),listing);
        scroll.setContent(box);
        return scroll;
    }
    public Scene createScene() {
        GridPane inventory = new GridPane();
        inventory.setHgap(20);
        inventory.setVgap(10);
        inventory.setPadding(new Insets(50, 100, 50, 100));
        inventory.setStyle("-fx-background-color: rgba(167,246,8,0.15)");
        HBox buttons=new HBox(30);
        buttons.getChildren().addAll(add,edit);
/*
        FlowPane control=new FlowPane();
        control.setVgap(15);
        control.setHgap(10);
        control.setPrefWrapLength(300);
        control.getChildren().addAll(barcode,productCode,productName,description,brand,sector,weight,volume,color,supplier,priceBought,sellingPrice,stockQuantity,isDiscounted,discountRate,lastRestockDate,nrOfReturns,isDiscontinued,isAvailable,buttons);


 */
        GridPane grid=new GridPane();
        grid.setVgap(20);
        grid.setHgap(20);
        grid.add(barcode,0,0);//kte mdk se e kemi lene qe te behet autogenerated
        grid.add(productCode,1,0);
        grid.add(productName,2,0);
        grid.add(description,3,0);
        grid.add(brand,0,1);
        grid.add(sector,1,1);
        grid.add(weight,2,1);
        grid.add(volume,3,1);
        grid.add(color,0,2);
        grid.add(supplier,1,2);
        grid.add(priceBought,2,2);
        grid.add(sellingPrice,3,2);
        grid.add(stockQuantity,0,3);
        grid.add(lastRestockDate,1,3);
        grid.add(nrOfReturns,2,3);
        grid.add(createAlignedGreenBoldLabel("Is Discounted",80),0,4);
        grid.add(isDiscounted,1,4);
        grid.add(discountRate,2,4);
        grid.add(createAlignedGreenBoldLabel("Is Available: ",100),0,5);
        grid.add(isAvailable,1,5);
        grid.add(createAlignedGreenBoldLabel("Is Discontinued ",100),0,6);
        grid.add(isDiscontinued,1,6);
        grid.add(add,1,7);
        grid.add(edit,2,7);
        Label label=createAlignedGreenBoldLabel("Products Management",200);

        ScrollPane productLog=createProductLog();
        productLog.setMinHeight(300);

        inventory.add(label,1,0);
        inventory.add(productLog,1,1);
        inventory.add(grid,1,2);
       // inventory.add(buttons,2,3);

        Scene scene = new Scene(inventory);
        return scene;
    }
}
