package View.BillingSystemView;

import View.Design;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CheckoutView extends GridPane implements Design {
    private BorderPane temporaryPane=new BorderPane();
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
    private TextField collectedMoneyTf=createTextField("Collected Money...");
    private TextField changeMoneyTf=createTextField("Give Change...");
    private TextField creditCardName=createTextField("Full Name...");
    private TextField creditCardNumber=createTextField("Card Number...");
    private TextField creditCardExpDate=createTextField("Expiration day...");
    private PasswordField creditCardCVV=createPasswordField();
    private Button saveCreditCard=createGeneralButton("Save");
    private TextField customerIdTf=createTextField("Customer ID...");
    private Label loyaltyPoints=createAlignedBlackLabel("0");
    private Label billLoyalyPoints=createAlignedBlackLabel("");
    private RadioButton payCashRb=new RadioButton("Cash");
    private RadioButton payByCreditCardRb=new RadioButton("Credit Card");

    public CheckoutView(){
        setUpView();
    }
    public void setUpView(){
        this.setHgap(20);
        this.setVgap(10);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;");
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20,20,20,20));

        //Titles
        Label checkoutTitle=createAlignedGreenBoldLabel("Checkout\n");
        Label dateTitle=createAlignedGreenBoldLabel("Date");
        Label billIdTitle=createAlignedGreenBoldLabel("Bill Id");

        //For displaying money that customer give and how much cashier will return
        VBox collectAmountBox=createStyledVBoxForInfo("Collected Money", collectedMoney);
        VBox changeAmountBox=createStyledVBoxForInfo("Change Money", changeMoney);

        //Toggle Group
        ToggleGroup paymentMethod=new ToggleGroup();
        payCashRb.setToggleGroup(paymentMethod);
        payCashRb.setFont(Font.font("bahnschrift", FontWeight.BOLD,13));
        payByCreditCardRb.setToggleGroup(paymentMethod);
        payByCreditCardRb.setFont(Font.font("bahnschrift", FontWeight.BOLD,13));

        Label paymentMethodTitle=createAlignedGreenBoldLabel("Payment Method");
        HBox paymentBox=new HBox(10);
        paymentBox.getChildren().addAll(payCashRb,payByCreditCardRb);

        //VBox for buttons to change
        VBox buttonBox=new VBox(0);
        buttonBox.getChildren().addAll(calculateCashButton,creditCardButton,customerInfoButton);
        temporaryPane.setPrefSize(350,300);
        //Footer Part
        Label noTaxTitle=createAlignedBlackBoldLabel("No-Tax Total");
        Label taxesTitle=createAlignedBlackBoldLabel("Taxes");
        Label totalTitle=createAlignedGreenBoldLabel("Total");

        //Adding to checkout gridpane
        this.add(checkoutTitle,0,0);
        this.add(dateTitle,0,1);
        this.add(generatedDateTime,1,1);
        this.add(billIdTitle,0,2);
        this.add(billId,1,2);
        this.add(collectAmountBox,0,3);
        this.add(changeAmountBox,1,3);
        this.add(paymentMethodTitle,0,4);
        this.add(paymentBox,1,4);
        this.add(buttonBox,0,5);
        this.add(temporaryPane,1,5);
        this.add(noTaxTitle,0,6);
        this.add(noTaxTotal,1,6);
        this.add(taxesTitle,0,7);
        this.add(taxAmount,1,7);
        this.add(totalTitle,0,8);
        this.add(totalAmount,1,8);
        this.add(newBillButton,0,9);
        this.add(generateBillButton,1,9);
    }
    public VBox createStyledVBoxForInfo(String titleText, Label content) {
        VBox box = new VBox(5);
        Label title = createAlignedBlackBoldLabel(titleText);
        title.setTextFill(Color.WHITE);
        content.setTextFill(Color.WHITE);
        box.getChildren().addAll(title, content);
        box.setStyle("-fx-background-color: rgba(38,141,17,0.9);" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;");
        box.setPrefWidth(150);
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

    public VBox createCashAndChangeBox(){
        VBox box=new VBox(10);
        box.setPadding(new Insets(20,20,20,20));
        box.setAlignment(Pos.CENTER);

        Label collectedMoneyTitle=createAlignedGreenBoldLabel("Collected Money");
        Label changeMoneyTitle=createAlignedGreenBoldLabel("Give Change");
        changeMoneyTf.setEditable(false);
        box.getChildren().addAll(collectedMoneyTitle,collectedMoneyTf,changeMoneyTitle,changeMoneyTf);
        return box;
    }

    public GridPane createCreditCardBox(){
        GridPane grid=new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setAlignment(Pos.CENTER);

        Label nameTitle=createAlignedGreenBoldLabel("Full Name");
        Label cardNumberTitle=createAlignedGreenBoldLabel("Card Number");
        Label expDateTitle=createAlignedGreenBoldLabel("Expiration Date");
        Label cvvTitle= createAlignedGreenBoldLabel("CVV");

        creditCardCVV.setPromptText("CVV...");

        grid.add(nameTitle,0,0);
        grid.add(creditCardName,1,0);
        grid.add(cardNumberTitle,0,1);
        grid.add(creditCardNumber,1,1);
        grid.add(expDateTitle,0,2);
        grid.add(cvvTitle,1,2);
        grid.add(creditCardExpDate,0,3);
        grid.add(creditCardCVV,1,3);
        grid.add(saveCreditCard,0,4);
        return grid;
    }

    public GridPane createCustomerBox(){
        GridPane grid=new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setAlignment(Pos.CENTER);

        Label customerIdTitle=createAlignedGreenBoldLabel("Customer Id");

        grid.add(customerIdTitle,0,0);
        grid.add(customerIdTf,1,0);
        grid.add(createStyledVBoxForInfo("Bill points",billLoyalyPoints),0,1);
        grid.add(createStyledVBoxForInfo("Customer Points",loyaltyPoints),1,1);

        return grid;
    }

    public BorderPane getTemporaryPane() {
        return temporaryPane;
    }

    public void setTemporaryPane(BorderPane temporaryPane) {
        this.temporaryPane = temporaryPane;
    }

    public Label getGeneratedDateTime() {
        return generatedDateTime;
    }

    public Label getBillId() {
        return billId;
    }

    public Label getCollectedMoney() {
        return collectedMoney;
    }

    public Label getChangeMoney() {
        return changeMoney;
    }

    public Button getCalculateCashButton() {
        return calculateCashButton;
    }

    public Button getCreditCardButton() {
        return creditCardButton;
    }

    public Button getCustomerInfoButton() {
        return customerInfoButton;
    }

    public Label getNoTaxTotal() {
        return noTaxTotal;
    }

    public Label getTaxAmount() {
        return taxAmount;
    }

    public Label getTotalAmount() {
        return totalAmount;
    }

    public Button getGenerateBillButton() {
        return generateBillButton;
    }

    public Button getNewBillButton() {
        return newBillButton;
    }

    public TextField getCollectedMoneyTf() {
        return collectedMoneyTf;
    }

    public TextField getChangeMoneyTf() {
        return changeMoneyTf;
    }

    public TextField getCreditCardName() {
        return creditCardName;
    }

    public TextField getCreditCardNumber() {
        return creditCardNumber;
    }

    public TextField getCreditCardExpDate() {
        return creditCardExpDate;
    }

    public PasswordField getCreditCardCVV() {
        return creditCardCVV;
    }

    public Button getSaveCreditCard() {
        return saveCreditCard;
    }

    public TextField getCustomerIdTf() {
        return customerIdTf;
    }

    public Label getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public Label getBillLoyalyPoints() {
        return billLoyalyPoints;
    }

    public RadioButton getPayCashRb() {
        return payCashRb;
    }

    public RadioButton getPayByCreditCardRb() {
        return payByCreditCardRb;
    }

}
