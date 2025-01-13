package View.BillingSystemView;

import Model.ItemBought;
import View.Design;
import View.SearchBoxPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import View.SearchBoxPane;

public class ProductCardView extends VBox implements Design {
    private final SearchBoxPane searchBox=new SearchBoxPane("Search Product...");
    private Label totalBillNumber =createAlignedBlackLabel("0");
    private Label moneyCollected=createAlignedBlackLabel("0");
    private Label taxCollected=createAlignedBlackLabel("0");
    private Label customerId=createAlignedBlackLabel("00000000");
    private Label loyaltyPoints=createAlignedBlackLabel("0");
    private Button clearCart=createGeneralButton("Clear Cart");
    private Label errorMessage=createAlignedBlackBoldLabel("");
    private TableView productCartTable=createTableView();

    public ProductCardView() {
        setUpView();
    }

    public void setUpView(){
        //Product Cart
        this.setSpacing(10);
        this.setStyle("-fx-background-color: transparent;");

        Label title=createAlignedGreenBoldLabel("Billing System",150);

        //Total Sales Info Pane and Customer Info
        HBox infoBox=new HBox(10);
        Label todaySaleTitle=createAlignedGreenBoldLabel("Today's Sales");
        infoBox.getChildren().addAll(createTodaySalesInfoPane(),createCustomerInfoPane());

        //Header of product cart
        HBox headerBox=new HBox(20);
        Label productCartTitle=createAlignedGreenBoldLabel("Product Cart");
        searchBox.getSearchButton().setText("Add");
        headerBox.getChildren().addAll(productCartTitle,searchBox,clearCart);
        errorMessage.setTextFill(Color.RED);
        errorMessage.setAlignment(Pos.CENTER);

        this.getChildren().addAll(title,todaySaleTitle,infoBox,headerBox,errorMessage,productCartTable);
    }

    public GridPane createTodaySalesInfoPane(){
        GridPane todaysaleInfo=new GridPane();

        todaysaleInfo.setHgap(100);
        todaysaleInfo.setVgap(15);

        todaysaleInfo.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;");
        todaysaleInfo.setPadding(new Insets(10,10,10,10));
        todaysaleInfo.setPrefWidth(350);
        Label billNrTitle=createAlignedBlackBoldLabel("Total Bills");
        Label moneyTitle=createAlignedBlackBoldLabel("Collected Money");
        Label taxTitle=createAlignedBlackBoldLabel("Collected Tax");


        todaysaleInfo.add(billNrTitle,0,0);
        todaysaleInfo.add(totalBillNumber,1,0);
        todaysaleInfo.add(moneyTitle,0,1);
        todaysaleInfo.add(moneyCollected,1,1);
        todaysaleInfo.add(taxTitle,0,2);
        todaysaleInfo.add(taxCollected,1,2);
        return todaysaleInfo;
    }

    public GridPane createCustomerInfoPane(){
        GridPane customerInfo=new GridPane();
        customerInfo.setHgap(100);
        customerInfo.setVgap(15);

        customerInfo.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;");
        customerInfo.setPadding(new Insets(10,10,10,10));
        customerInfo.setPrefWidth(350);

        Label customerIdTitle=createAlignedBlackBoldLabel("Customer Personal Id");
        Label loyaltyPointsTitle=createAlignedBlackBoldLabel("Loyalty Points");

        customerInfo.add(customerIdTitle,0,0);
        customerInfo.add(customerId,0,1);
        customerInfo.add(loyaltyPointsTitle,1,0);
        customerInfo.add(loyaltyPoints,1,1);

        return customerInfo;
    }

    public TableView createTableView(){
        TableView<ItemBought> table = new TableView<>();
        table.setStyle("-fx-background-color: white;" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;" +
                "-fx-border-width: 1;" +
                "-fx-border-color: yellowgreen;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create columns of product cart
        TableColumn<ItemBought, Integer> idColumn = new TableColumn<>("Product ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        idColumn.setMaxWidth(100);

        // Product Name column
        TableColumn<ItemBought, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        nameColumn.setMinWidth(150);

        // Quantity column
        TableColumn<ItemBought, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setMaxWidth(80);

        // Selling Price column
        TableColumn<ItemBought, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellingPriceColumn.setMaxWidth(120);

        // Total Tax column
        TableColumn<ItemBought, Double> totalTaxColumn = new TableColumn<>("Total Tax");
        totalTaxColumn.setCellValueFactory(new PropertyValueFactory<>("totalTax"));
        totalTaxColumn.setMaxWidth(100);

        // Total Price column
        TableColumn<ItemBought, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPriceColumn.setMinWidth(120);

        // Action Button column
        TableColumn<ItemBought, Void> deleteColumn = new TableColumn<>("");
        deleteColumn.setCellFactory(param -> new TableCell<ItemBought, Void>() {
            private final Button deleteButton = createDeleteRowButton();
            {

                deleteButton.setOnAction(event -> {
                    // Get the item for this row
                    ItemBought item = getTableView().getItems().get(getIndex());

                    // Remove the item from the table's data source
                    getTableView().getItems().remove(item);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                // If the row is not empty, show the button
                if (empty) {
                    setGraphic(null); // No button for empty rows
                } else {
                    setGraphic(deleteButton); // Add the delete button for this row
                }
            }
        });


        // Add all columns to the table
        table.getColumns().addAll(idColumn, nameColumn, quantityColumn, sellingPriceColumn, totalTaxColumn, totalPriceColumn, deleteColumn);


        return table;
    }

    public Button createDeleteRowButton(){
        Button button=new Button();

        ImageView xIcon=new ImageView(new Image("Images/xIcon.png"));
        xIcon.setFitHeight(10);
        xIcon.setFitWidth(10);
        button.setGraphic(xIcon);
        button.setStyle("-fx-background-color: red;" +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5;");
        button.setAlignment(Pos.CENTER);

        return button;
    }

    public SearchBoxPane getSearchBox() {
        return searchBox;
    }

    public Label getTotalBillNumber() {
        return totalBillNumber;
    }

    public Label getMoneyCollected() {
        return moneyCollected;
    }

    public Label getTaxCollected() {
        return taxCollected;
    }

    public Label getCustomerId() {
        return customerId;
    }

    public Label getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public Button getClearCart() {
        return clearCart;
    }

    public Label getErrorMessage() {
        return errorMessage;
    }

    public TableView getProductCartTable() {
        return productCartTable;
    }
}
