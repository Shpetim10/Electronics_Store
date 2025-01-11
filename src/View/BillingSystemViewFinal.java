package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BillingSystemViewFinal extends HBox implements Design{
    private final SearchBoxPane searchBox=new SearchBoxPane("Search Product...");
    private Label totalBillNumber =createAlignedBlackLabel("0");
    private Label moneyCollected=createAlignedBlackLabel("0");
    private Label taxCollected=createAlignedBlackLabel("0");
    private Label customerId=createAlignedBlackLabel("00000000");
    private Label loyaltyPoints=createAlignedBlackLabel("0");
    private Button clearCart=createGeneralButton("Clear Cart");
    private Label generatedDateTime=createAlignedBlackLabel("0");
    private Label billId=createAlignedBlackLabel("0");
    private Label collectedMoney=createAlignedBlackLabel("0");
    private Label changeMoney=createAlignedBlackLabel("0");
    private Button calculateCashButton=createCustomizedButtonForVBox("/Images/cashIcon.png","Cash");
    private Button creditCardButton=createCustomizedButtonForVBox("/Images/creditCardIcon.png","Card");
    private Button customerInfoButton=createCustomizedButtonForVBox("/Images/clientIcon.png","Customer");
    private Label noTaxTotal=createAlignedBlackLabel("0");
    private Label taxAmount=createAlignedBlackLabel("0");
    private Label totalAmount=createAlignedBlackLabel("0");
    private Button generateBillButton=createGeneralButton("Generate Bill");
    private Button newBillButton=createGeneralButton("New Bill");

    public BillingSystemViewFinal(){
        setupView();
    }
    public void setupView(){
        //Scene will be divided into 2 parts, left will be product card and right checkout

        //Product Cart
        VBox productCartBox=new VBox(10);
        productCartBox.setStyle("-fx-background-color: transparent;");
        productCartBox.prefWidthProperty().bind(this.widthProperty().divide(2));
        productCartBox.prefHeightProperty().bind(this.heightProperty());

        Label title=createAlignedGreenBoldLabel("Billing System",150);

        //Total Sales Info Pane and Customer Info
        HBox infoBox=new HBox(10);
        Label todaySaleTitle=createAlignedGreenBoldLabel("Today's Sale");
        infoBox.getChildren().addAll(createTodaySalesInfoPane(),createCustomerInfoPane());

        //Header of product cart
        HBox headerBox=new HBox(20);
        Label productCartTitle=createAlignedGreenBoldLabel("Product Cart");
        headerBox.getChildren().addAll(productCartTitle,searchBox,clearCart);

        //Table for product cart

        productCartBox.getChildren().addAll(title,todaySaleTitle,infoBox,headerBox);


        //Right part for checkout
        GridPane checkOutPane=new GridPane();
        checkOutPane.setHgap(20);
        checkOutPane.setVgap(10);
        checkOutPane.setStyle("-fx-background-color: white;");
        checkOutPane.prefWidthProperty().bind(this.widthProperty().divide(2));
        checkOutPane.prefHeightProperty().bind(this.heightProperty());
        checkOutPane.setPadding(new Insets(20,20,20,20));

        //Titles
        Label checkoutTitle=createAlignedGreenBoldLabel("Checkout\n");
        Label dateTitle=createAlignedGreenBoldLabel("Date");
        Label billIdTitle=createAlignedGreenBoldLabel("Bill Id");

        //For displaying money that customer give and how much cashier will return
        VBox collectAmountBox=createStyledVBoxForInfo("Collected Money", collectedMoney);
        VBox changeAmountBox=createStyledVBoxForInfo("Change Money", changeMoney);

        //VBox for buttons to change
        VBox buttonBox=new VBox(0);
        buttonBox.getChildren().addAll(calculateCashButton,creditCardButton,customerInfoButton);

        //Footer Part
        Label noTaxTitle=createAlignedBlackBoldLabel("No-Tax Total");
        Label taxesTitle=createAlignedBlackBoldLabel("Taxes");
        Label totalTitle=createAlignedGreenBoldLabel("Total");

        //Adding to checkout gridpane
        checkOutPane.add(checkoutTitle,0,0);
        checkOutPane.add(dateTitle,0,1);
        checkOutPane.add(generatedDateTime,1,1);
        checkOutPane.add(billIdTitle,0,2);
        checkOutPane.add(billId,1,2);
        checkOutPane.add(collectAmountBox,0,3);
        checkOutPane.add(changeAmountBox,1,3);
        checkOutPane.add(buttonBox,0,4);
        //Add InteractivePane
        checkOutPane.add(noTaxTitle,0,5);
        checkOutPane.add(noTaxTotal,1,5);
        checkOutPane.add(taxesTitle,0,6);
        checkOutPane.add(taxAmount,1,6);
        checkOutPane.add(totalTitle,0,7);
        checkOutPane.add(totalAmount,1,7);
        checkOutPane.add(newBillButton,0,8);
        checkOutPane.add(generateBillButton,1,8);

        //Arranging view

        //Styling

        this.setPadding(new Insets(10,10,10,10));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        //Adding Nodes
        this.setSpacing(0);
        this.getChildren().addAll(productCartBox,checkOutPane);


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
    public VBox createStyledVBoxForInfo(String titleText, Label content) {
        VBox box = new VBox(5);
        Label title = createAlignedBlackBoldLabel(titleText);
        box.getChildren().addAll(title, content);
        box.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;");
        box.setPrefWidth(250);
        box.setPrefHeight(50);
        box.setPadding(new Insets(20, 20, 20, 20));
        return box;
    }
    public Button createCustomizedButtonForVBox(String imagePath,String text){
        Button button=new Button();
        button.setPrefWidth(150);
        button.setPrefHeight(100);
        button.setStyle("-fx-background-color: rgba(167,246,8,0.1);" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;" +
                "-fx-font-family: Bahnschrift;");
        ImageView icon=new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        button.setGraphic(icon);
        button.setText(text);
        return button;
    }
}



