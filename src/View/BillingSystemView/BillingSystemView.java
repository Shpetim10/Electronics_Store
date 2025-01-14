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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//Sh
public class BillingSystemView extends HBox implements Design {
    private ProductCardView productCartBox=new ProductCardView();
    private CheckoutView checkOutPane=new CheckoutView();
//    private BorderPane temporaryPane=new BorderPane();
//    private final SearchBoxPane searchBox=new SearchBoxPane("Search Product..."); //
//    private Label totalBillNumber =createAlignedBlackLabel("0");
//    private Label moneyCollected=createAlignedBlackLabel("0");
//    private Label taxCollected=createAlignedBlackLabel("0");
//    private Label customerId=createAlignedBlackLabel("00000000");
//    private Label loyaltyPoints=createAlignedBlackLabel("0");
//    private Button clearCart=createGeneralButton("Clear Cart");
//    private Label generatedDateTime=createAlignedBlackLabel("0");
//    private Label billId=createAlignedBlackLabel("0");
//    private Label collectedMoney=createAlignedBlackLabel("0");
//    private Label changeMoney=createAlignedBlackLabel("0");
//    private Button calculateCashButton=createCustomizedButtonForVBox("/Images/cashIcon.png","Cash");
//    private Button creditCardButton=createCustomizedButtonForVBox("/Images/creditCardIcon.png","Card");
//    private Button customerInfoButton=createCustomizedButtonForVBox("/Images/clientIcon.png","Customer");
//    private Label noTaxTotal=createAlignedBlackLabel("0");
//    private Label taxAmount=createAlignedBlackLabel("0");
//    private Label totalAmount=createAlignedBlackLabel("0");
//    private Button generateBillButton=createGeneralButton("Generate Bill");
//    private Button newBillButton=createGeneralButton("New Bill");
//    private TextField collectedMoneyTf=createTextField("Collected Money...");
//    private TextField changeMoneyTf=createTextField("Give Change...");
//    private TextField creditCardName=createTextField("Full Name...");
//    private TextField creditCardNumber=createTextField("Card Number...");
//    private TextField creditCardExpDate=createTextField("Expiration day...");
//    private PasswordField creditCardCVV=createPasswordField();
//    private TextField customerIdTf=createTextField("Customer ID...");
//    private Label billLoyalyPoints=createAlignedBlackLabel("");
    //private TableView productCartTable=createTableView();
//    private RadioButton payCashRb=new RadioButton("Cash");
//    private RadioButton payByCreditCardRb=new RadioButton("Credit Card");
//    private Label errorMessage=createAlignedBlackBoldLabel("");
    //private Button deleteButton = createDeleteRowButton();
    public BillingSystemView(){
        setupView();
    }

    public void setupView(){
        //Scene will be divided into 2 parts, left will be product card and right checkout
        //Left part is ProductCart
        //setUpProductCartView();
        productCartBox.prefWidthProperty().bind(this.widthProperty().divide(2));
        productCartBox.prefHeightProperty().bind(this.heightProperty());
        //Right part for checkout
        //setUpCheckoutView();
        checkOutPane.prefWidthProperty().bind(this.widthProperty().divide(2));
        checkOutPane.prefHeightProperty().bind(this.heightProperty());
        //Arranging view
        this.setPadding(new Insets(10,10,10,10));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        this.setSpacing(30);
        this.getChildren().addAll(productCartBox,checkOutPane);
    }

//    public void setUpProductCartView(){
//        //Product Cart
//        this.productCartBox=new VBox(10);
//        productCartBox.setStyle("-fx-background-color: transparent;");
//        productCartBox.prefWidthProperty().bind(this.widthProperty().divide(2));
//        productCartBox.prefHeightProperty().bind(this.heightProperty());
//
//        Label title=createAlignedGreenBoldLabel("Billing System",150);
//
//        //Total Sales Info Pane and Customer Info
//        HBox infoBox=new HBox(10);
//        Label todaySaleTitle=createAlignedGreenBoldLabel("Today's Sales");
//        infoBox.getChildren().addAll(createTodaySalesInfoPane(),createCustomerInfoPane());
//
//        //Header of product cart
//        HBox headerBox=new HBox(20);
//        Label productCartTitle=createAlignedGreenBoldLabel("Product Cart");
//        headerBox.getChildren().addAll(productCartTitle,searchBox,clearCart);
//        errorMessage.setTextFill(Color.RED);
//        errorMessage.setAlignment(Pos.CENTER);
//
//        productCartBox.getChildren().addAll(title,todaySaleTitle,infoBox,headerBox,errorMessage,productCartTable);
//    }

//    public void setUpCheckoutView(){
//        checkOutPane=new GridPane();
//        checkOutPane.setHgap(20);
//        checkOutPane.setVgap(10);
//        checkOutPane.setStyle("-fx-background-color: white;" +
//                "-fx-border-radius: 10;" +
//                "-fx-background-radius: 10;" +
//                "-fx-border-color: yellowgreen;" +
//                "-fx-border-width: 1;");
//        checkOutPane.setAlignment(Pos.CENTER);
//        checkOutPane.setPadding(new Insets(20,20,20,20));
//
//        //Titles
//        Label checkoutTitle=createAlignedGreenBoldLabel("Checkout\n");
//        Label dateTitle=createAlignedGreenBoldLabel("Date");
//        Label billIdTitle=createAlignedGreenBoldLabel("Bill Id");
//
//        //For displaying money that customer give and how much cashier will return
//        VBox collectAmountBox=createStyledVBoxForInfo("Collected Money", collectedMoney);
//        VBox changeAmountBox=createStyledVBoxForInfo("Change Money", changeMoney);
//
//        //Toggle Group
//        ToggleGroup paymentMethod=new ToggleGroup();
//        payCashRb.setToggleGroup(paymentMethod);
//        payCashRb.setFont(Font.font("bahnschrift", FontWeight.BOLD,13));
//        payByCreditCardRb.setToggleGroup(paymentMethod);
//        payByCreditCardRb.setFont(Font.font("bahnschrift", FontWeight.BOLD,13));
//
//        Label paymentMethodTitle=createAlignedGreenBoldLabel("Payment Method");
//        HBox paymentBox=new HBox(10);
//        paymentBox.getChildren().addAll(payCashRb,payByCreditCardRb);
//
//        //VBox for buttons to change
//        VBox buttonBox=new VBox(0);
//        buttonBox.getChildren().addAll(calculateCashButton,creditCardButton,customerInfoButton);
//        temporaryPane.setPrefSize(350,300);
//        //Footer Part
//        Label noTaxTitle=createAlignedBlackBoldLabel("No-Tax Total");
//        Label taxesTitle=createAlignedBlackBoldLabel("Taxes");
//        Label totalTitle=createAlignedGreenBoldLabel("Total");
//
//        //Adding to checkout gridpane
//        checkOutPane.add(checkoutTitle,0,0);
//        checkOutPane.add(dateTitle,0,1);
//        checkOutPane.add(generatedDateTime,1,1);
//        checkOutPane.add(billIdTitle,0,2);
//        checkOutPane.add(billId,1,2);
//        checkOutPane.add(collectAmountBox,0,3);
//        checkOutPane.add(changeAmountBox,1,3);
//        checkOutPane.add(paymentMethodTitle,0,4);
//        checkOutPane.add(paymentBox,1,4);
//        checkOutPane.add(buttonBox,0,5);
//        checkOutPane.add(temporaryPane,1,5);
//        checkOutPane.add(noTaxTitle,0,6);
//        checkOutPane.add(noTaxTotal,1,6);
//        checkOutPane.add(taxesTitle,0,7);
//        checkOutPane.add(taxAmount,1,7);
//        checkOutPane.add(totalTitle,0,8);
//        checkOutPane.add(totalAmount,1,8);
//        checkOutPane.add(newBillButton,0,9);
//        checkOutPane.add(generateBillButton,1,9);
//    }

//    public GridPane createTodaySalesInfoPane(){
//        GridPane todaysaleInfo=new GridPane();
//
//        todaysaleInfo.setHgap(100);
//        todaysaleInfo.setVgap(15);
//
//        todaysaleInfo.setStyle("-fx-background-color: white;" +
//                "-fx-background-radius: 10;" +
//                "-fx-border-radius: 10;" +
//                "-fx-border-color: yellowgreen;" +
//                "-fx-border-width: 1;");
//        todaysaleInfo.setPadding(new Insets(10,10,10,10));
//        todaysaleInfo.setPrefWidth(350);
//        Label billNrTitle=createAlignedBlackBoldLabel("Total Bills");
//        Label moneyTitle=createAlignedBlackBoldLabel("Collected Money");
//        Label taxTitle=createAlignedBlackBoldLabel("Collected Tax");
//
//
//        todaysaleInfo.add(billNrTitle,0,0);
//        todaysaleInfo.add(totalBillNumber,1,0);
//        todaysaleInfo.add(moneyTitle,0,1);
//        todaysaleInfo.add(moneyCollected,1,1);
//        todaysaleInfo.add(taxTitle,0,2);
//        todaysaleInfo.add(taxCollected,1,2);
//        return todaysaleInfo;
//    }

//    public GridPane createCustomerInfoPane(){
//        GridPane customerInfo=new GridPane();
//        customerInfo.setHgap(100);
//        customerInfo.setVgap(15);
//
//        customerInfo.setStyle("-fx-background-color: white;" +
//                "-fx-background-radius: 10;" +
//                "-fx-border-radius: 10;" +
//                "-fx-border-color: yellowgreen;" +
//                "-fx-border-width: 1;");
//        customerInfo.setPadding(new Insets(10,10,10,10));
//        customerInfo.setPrefWidth(350);
//
//        Label customerIdTitle=createAlignedBlackBoldLabel("Customer Personal Id");
//        Label loyaltyPointsTitle=createAlignedBlackBoldLabel("Loyalty Points");
//
//        customerInfo.add(customerIdTitle,0,0);
//        customerInfo.add(customerId,0,1);
//        customerInfo.add(loyaltyPointsTitle,1,0);
//        customerInfo.add(loyaltyPoints,1,1);
//
//        return customerInfo;
//        }

//    public VBox createStyledVBoxForInfo(String titleText, Label content) {
//        VBox box = new VBox(5);
//        Label title = createAlignedBlackBoldLabel(titleText);
//        title.setTextFill(Color.WHITE);
//        content.setTextFill(Color.WHITE);
//        box.getChildren().addAll(title, content);
//        box.setStyle("-fx-background-color: rgba(38,141,17,0.9);" +
//                "-fx-background-radius: 10;" +
//                "-fx-border-radius: 10;");
//        box.setPrefWidth(150);
//        box.setPrefHeight(50);
//        box.setPadding(new Insets(20, 20, 20, 20));
//        return box;
//    }
//
//    public Button createCustomizedButtonForVBox(String imagePath,String text){
//        Button button=new Button();
//        button.setPrefWidth(150);
//        button.setPrefHeight(100);
//        button.setStyle("-fx-background-color: rgba(167,246,8,0.1);" +
//                "-fx-font-weight: bold;" +
//                "-fx-font-size: 15;" +
//                "-fx-font-family: Bahnschrift;");
//        ImageView icon=new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
//        icon.setFitHeight(40);
//        icon.setFitWidth(40);
//        button.setGraphic(icon);
//        button.setText(text);
//        return button;
//    }

//    public VBox createCashAndChangeBox(){
//        VBox box=new VBox(10);
//        box.setPadding(new Insets(20,20,20,20));
//        box.setAlignment(Pos.CENTER);
//
//        Label collectedMoneyTitle=createAlignedGreenBoldLabel("Collected Money");
//        Label changeMoneyTitle=createAlignedGreenBoldLabel("Give Change");
//        changeMoneyTf.setEditable(false);
//        box.getChildren().addAll(collectedMoneyTitle,collectedMoneyTf,changeMoneyTitle,changeMoneyTf);
//        return box;
//    }
//
//    public GridPane createCreditCardBox(){
//        GridPane grid=new GridPane();
//        grid.setHgap(20);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(10,10,10,10));
//        grid.setAlignment(Pos.CENTER);
//
//        Label nameTitle=createAlignedGreenBoldLabel("Full Name");
//        Label cardNumberTitle=createAlignedGreenBoldLabel("Card Number");
//        Label expDateTitle=createAlignedGreenBoldLabel("Expiration Date");
//        Label cvvTitle= createAlignedGreenBoldLabel("CVV");
//
//        grid.add(nameTitle,0,0);
//        grid.add(creditCardName,1,0);
//        grid.add(cardNumberTitle,0,1);
//        grid.add(creditCardNumber,1,1);
//        grid.add(expDateTitle,0,2);
//        grid.add(cvvTitle,1,2);
//        grid.add(creditCardExpDate,0,3);
//        grid.add(creditCardCVV,1,3);
//        return grid;
//    }
//
//    public GridPane createCustomerBox(){
//        GridPane grid=new GridPane();
//        grid.setHgap(20);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(10,10,10,10));
//        grid.setAlignment(Pos.CENTER);
//
//        Label customerIdTitle=createAlignedGreenBoldLabel("Customer Id");
//
//        grid.add(customerIdTitle,0,0);
//        grid.add(customerIdTf,1,0);
//        grid.add(createStyledVBoxForInfo("Bill points",billLoyalyPoints),0,1);
//        grid.add(createStyledVBoxForInfo("Customer Points",loyaltyPoints),1,1);
//
//        return grid;
//    }

//    public TableView createTableView(){
//        TableView<ItemBought> table = new TableView<>();
//        table.setStyle("-fx-background-color: white;" +
//                "-fx-border-radius: 15;" +
//                "-fx-background-radius: 15;" +
//                "-fx-border-width: 1;" +
//                "-fx-border-color: yellowgreen;");
//        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//        // Create data source
//        ObservableList<ItemBought> itemBought= FXCollections.observableArrayList(
//                new ItemBought(1, "Product A", 2, 10.5),
//                new ItemBought(2, "Product B", 1, 20.0)
//        );
//
//        table.setItems(itemBought);
//
//        // Create columns of product cart
//        TableColumn<ItemBought, Integer> idColumn = new TableColumn<>("Product ID");
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
//        idColumn.setMaxWidth(100);
//
//        // Product Name column
//        TableColumn<ItemBought, String> nameColumn = new TableColumn<>("Product Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
//        nameColumn.setMinWidth(150);
//
//        // Quantity column
//        TableColumn<ItemBought, Integer> quantityColumn = new TableColumn<>("Quantity");
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        quantityColumn.setMaxWidth(80);
//
//        // Selling Price column
//        TableColumn<ItemBought, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
//        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
//        sellingPriceColumn.setMaxWidth(120);
//
//        // Total Tax column
//        TableColumn<ItemBought, Double> totalTaxColumn = new TableColumn<>("Total Tax");
//        totalTaxColumn.setCellValueFactory(new PropertyValueFactory<>("totalTax"));
//        totalTaxColumn.setMaxWidth(100);
//
//        // Total Price column
//        TableColumn<ItemBought, Double> totalPriceColumn = new TableColumn<>("Total Price");
//        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
//        totalPriceColumn.setMinWidth(120);
//
//        // Action Button column
//        TableColumn<ItemBought, Void> deleteColumn = new TableColumn<>("");
//        deleteColumn.setCellFactory(param -> new TableCell<ItemBought, Void>() {
//            private final Button deleteButton = createDeleteRowButton();
//            {
//
//                deleteButton.setOnAction(event -> {
//                    // Get the item for this row
//                    ItemBought item = getTableView().getItems().get(getIndex());
//
//                    // Remove the item from the table's data source
//                    getTableView().getItems().remove(item);
//                });
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//
//                // If the row is not empty, show the button
//                if (empty) {
//                    setGraphic(null); // No button for empty rows
//                } else {
//                    setGraphic(deleteButton); // Add the delete button for this row
//                }
//            }
//        });
//
//
//        // Add all columns to the table
//        table.getColumns().addAll(idColumn, nameColumn, quantityColumn, sellingPriceColumn, totalTaxColumn, totalPriceColumn, deleteColumn);
//
//
//        return table;
//    }
//
//    public Button createDeleteRowButton(){
//        Button button=new Button();
//
//        ImageView xIcon=new ImageView(new Image("Images/xIcon.png"));
//        xIcon.setFitHeight(10);
//        xIcon.setFitWidth(10);
//        button.setGraphic(xIcon);
//        button.setStyle("-fx-background-color: red;" +
//                "-fx-border-radius: 5; " +
//                "-fx-background-radius: 5;");
//        button.setAlignment(Pos.CENTER);
//
//        return button;
//    }
//
    public ProductCardView getProductCartBox() {
        return productCartBox;
    }

    public CheckoutView getCheckOutPane() {
        return checkOutPane;
    }

//    public BorderPane getTemporaryPane() {
//        return temporaryPane;
//    }
//
//    public SearchBoxPane getSearchBox() {
//        return searchBox;
//    }
//
//    public Label getTotalBillNumber() {
//        return totalBillNumber;
//    }
//
//    public Label getMoneyCollected() {
//        return moneyCollected;
//    }
//
//    public Label getTaxCollected() {
//        return taxCollected;
//    }
//
//    public Label getCustomerId() {
//        return customerId;
//    }
//
//    public Label getLoyaltyPoints() {
//        return loyaltyPoints;
//    }
//
//    public Button getClearCart() {
//        return clearCart;
//    }
//
//    public Label getGeneratedDateTime() {
//        return generatedDateTime;
//    }
//
//    public Label getBillId() {
//        return billId;
//    }
//
//    public Label getCollectedMoney() {
//        return collectedMoney;
//    }
//
//    public Label getChangeMoney() {
//        return changeMoney;
//    }
//
//    public Button getCalculateCashButton() {
//        return calculateCashButton;
//    }
//
//    public Button getCreditCardButton() {
//        return creditCardButton;
//    }
//
//    public Button getCustomerInfoButton() {
//        return customerInfoButton;
//    }
//
//    public Label getNoTaxTotal() {
//        return noTaxTotal;
//    }
//
//    public Label getTaxAmount() {
//        return taxAmount;
//    }
//
//    public Label getTotalAmount() {
//        return totalAmount;
//    }
//
//    public Button getGenerateBillButton() {
//        return generateBillButton;
//    }
//
//    public Button getNewBillButton() {
//        return newBillButton;
//    }
//
//    public TextField getCollectedMoneyTf() {
//        return collectedMoneyTf;
//    }
//
//    public TextField getChangeMoneyTf() {
//        return changeMoneyTf;
//    }
//
//    public TextField getCreditCardName() {
//        return creditCardName;
//    }
//
//    public TextField getCreditCardNumber() {
//        return creditCardNumber;
//    }
//
//    public TextField getCreditCardExpDate() {
//        return creditCardExpDate;
//    }
//
//    public PasswordField getCreditCardCVV() {
//        return creditCardCVV;
//    }
//
//    public TextField getCustomerIdTf() {
//        return customerIdTf;
//    }
//
//    public Label getBillLoyalyPoints() {
//        return billLoyalyPoints;
//    }
//
//    public TableView getProductCartTable() {
//        return productCartTable;
//    }
//
//    public RadioButton getPayCashRb() {
//        return payCashRb;
//    }
//
//    public RadioButton getPayByCreditCardRb() {
//        return payByCreditCardRb;
//    }
//
//    public Label getErrorMessage() {
//        return errorMessage;
//    }

//    public Button getDeleteButton() {
//        return deleteButton;
//    }
}