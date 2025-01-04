package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class BillingSystemView implements Design{
    private ComboBox<String> category;
    private ComboBox<String> products;
    private Button addButton;
    private TextField message;
    private ArrayList<ItemBoughtView> boughtItem;
    private TextField totalPrice;
    private Button[] numberLog;
    private Button clear;
    private Button delete;
    private Button generateBill;
    private TextField customerCardField;
    private ComboBox<String> paymentMethod;
    private TextField moneyField;
    private TextField changeField;

    public BillingSystemView() {
        this.category=createComboBox("Category...");
        this.products=createComboBox("Select Product...");
        this.addButton=createGeneralButton("Add");
        this.boughtItem=new ArrayList<>();
        this.totalPrice=createOutputTextField();

        String numberButtonStyle=
                "-fx-background-color: white ;" +
                        "-fx-border-radius: 40 ;" +
                        "-fx-background-radius: 40;" +
                        "-fx-border-color: yellowgreen;"+
                        " -fx-border-width: 4;" +
                        "-fx-font-family: Bahnschrift;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 30;";
        this.numberLog=new Button[12];  //For Designing Number log buttons
        for(int i=0;i<10;i++){
            this.numberLog[i]=createNumberButton(String.valueOf(i),numberButtonStyle);
        }
        this.numberLog[10]=createNumberButton("+",numberButtonStyle+"-fx-text-fill: green;");
        this.numberLog[11]=createNumberButton("-",numberButtonStyle+"-fx-text-fill: red;");

        this.clear=createGeneralButton("Clear");
        this.delete=createGeneralButton("Delete");
        this.generateBill=createGeneralButton("Generate Bill");

        this.customerCardField=createTextField("Customer Card...");
        this.paymentMethod=createComboBox("Payment Method...");
        this.moneyField=createTextField("Paid...");
        this.changeField=createTextField("Change...");
        this.changeField.setEditable(false);

        this.message=createOutputTextField();
        this.message.setStyle("-fx-text-fill: red;");
    }

    public HBox addMetadata(){
        HBox metadata=new HBox(20);
        metadata.setStyle("-fx-font-family: Bahnschrift;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;");

        //Labels
        Label tab= createAlignedBlackLabel("",30); //To align with x button
        Label id = createAlignedBlackLabel("ID", 40);
        Label name = createAlignedBlackLabel("Product", 200);
        Label quantity = createAlignedBlackLabel("Quantity", 80);
        Label sellingPrice = createAlignedBlackLabel("Price", 50);
        Label totalProductPrice = createAlignedBlackLabel("Total", 50);
        metadata.getChildren().addAll(tab,id,name,quantity,sellingPrice,totalProductPrice);
        return metadata;
    }
    public HBox addRow(ItemBoughtView item){
        HBox pane = new HBox(20);
        pane.setStyle("-fx-font-family: Bahnschrift;" +
                "-fx-font-size: 15;");
        pane.getChildren().addAll(item.getSelect(), item.getId(), item.getName(),item.getQuantity(),item.getSellingPrice(),item.getTotalProductPrice());
        return pane;
    }
    public GridPane createNumbersLog(){
        GridPane pane=new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);
        int cnt=1;
        //Add buttons 1-9
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                pane.add(numberLog[cnt],j,i);
                cnt++;
            }
        }
        //0 Button
        pane.add(numberLog[0],1,3);
        //+ Button
        pane.add(numberLog[10],0,3);
        //- Button
        pane.add(numberLog[11],2,3);

        return pane;
    }
    public VBox createProductLog(){
        VBox box=new VBox(10);
        box.setMinHeight(400);
        box.setMinWidth(600);
        box.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-font-family: Bahnschrift;" +
                "-fx-border-radius: 30;"+
                "-fx-background-radius: 30;" +
                "-fx-padding: 10;");
        box.setAlignment(Pos.TOP_CENTER);
        //Head of product Log
        Label headText=createAlignedGreenBoldLabel("Product List",150);
        //Body of product Log
        VBox productList=new VBox(5);
        productList.setStyle("-fx-padding: 10;");
        //Here is the section for products(ItemBought)
        boughtItem.add(new ItemBoughtView(1,"MacBook",2000));
        for(ItemBoughtView item: boughtItem){
            productList.getChildren().add(addRow(item));
        }
        //Footer
        Line line=new Line(0,0,480,0);
        line.setStyle("-fx-stroke: green;");
        HBox result =new HBox(25);
        result.setStyle("-fx-font-size: 16;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: green;" +
                "-fx-spacing: 10;");
        result.setAlignment(Pos.CENTER_RIGHT);

        Label total=createAlignedGreenBoldLabel("Total:",100);
        result.getChildren().addAll(total,this.totalPrice);

        box.getChildren().addAll(headText,addMetadata(),productList,line, result);
        return box;
    }
    public Scene createScene(){
        //Create Grid pane for overall display
        GridPane billingSystem= new GridPane();
        billingSystem.setHgap(300);
        billingSystem.setVgap(10);
        billingSystem.setPadding(new Insets(50,100,50,100));
        billingSystem.setStyle("-fx-background-color: rgba(167,246,8,0.15)");

        //Create Search Boxes
        HBox searchBox=new HBox(30);
        searchBox.getChildren().addAll(category,products,addButton);
        //Add product Log
        VBox productLog=createProductLog();

        //Add buttons clear, delete, generate bill
        HBox buttons=new HBox(180);
        buttons.getChildren().addAll(clear,delete,generateBill);

        //Create Numbers log
        GridPane numberLog=createNumbersLog();
        numberLog.setPadding(new Insets(100,20,10,20)); //Top is greater to align it in center, Bottom is less

        //Create A grid for Payment log
        GridPane payment=new GridPane();
        payment.setHgap(10);
        payment.setVgap(10);
        Label customerCard= createAlignedGreenBoldLabel("Customer card",150);
        Label paymentMethod= createAlignedGreenBoldLabel("Payment Method",150);
        Label enterMoney= createAlignedGreenBoldLabel("Enter Money:",150);
        Label getChange= createAlignedGreenBoldLabel("Get Change:",150);

        payment.add(customerCard,0,0);
        payment.add(customerCardField,0,1);
        payment.add(paymentMethod,1,0);
        payment.add(this.paymentMethod,1,1);
        payment.add(enterMoney,0,2);
        payment.add(moneyField,1,2);
        payment.add(getChange,0,3);
        payment.add(changeField,1,3);

        //Error Display
        Label errorMessage= createAlignedGreenBoldLabel("",150);
        errorMessage.setStyle("-fx-text-fill: red;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Bahnschrift;" +
                "-fx-font-size: 16;");

        //Getting All together
        billingSystem.add(searchBox,0,0);
        billingSystem.add(errorMessage,1,0); //General error box
        billingSystem.add(productLog,0,1);
        billingSystem.add(buttons,0,2);
        billingSystem.add(numberLog,1,1);
        billingSystem.add(payment,1,2);

        Scene scene=new Scene(billingSystem);
        return scene;
    }
    public ComboBox<String> getCategory() {
        return category;
    }

    public void setCategory(ComboBox<String> category) {
        this.category = category;
    }

    public ComboBox<String> getProducts() {
        return products;
    }

    public void setProducts(ComboBox<String> products) {
        this.products = products;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public TextField getMessage() {
        return message;
    }

    public void setMessage(TextField message) {
        this.message = message;
    }

    public ArrayList<ItemBoughtView> getBoughtItem() {
        return boughtItem;
    }

    public void setBoughtItem(ArrayList<ItemBoughtView> boughtItem) {
        this.boughtItem = boughtItem;
    }

    public TextField getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TextField totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Button[] getNumberLog() {
        return numberLog;
    }

    public void setNumberLog(Button[] numberLog) {
        this.numberLog = numberLog;
    }

    public Button getClear() {
        return clear;
    }

    public void setClear(Button clear) {
        this.clear = clear;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public Button getGenerateBill() {
        return generateBill;
    }

    public void setGenerateBill(Button generateBill) {
        this.generateBill = generateBill;
    }

    public TextField getCustomerCardField() {
        return customerCardField;
    }

    public void setCustomerCardField(TextField customerCardField) {
        this.customerCardField = customerCardField;
    }

    public ComboBox<String> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(ComboBox<String> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TextField getMoneyField() {
        return moneyField;
    }

    public void setMoneyField(TextField moneyField) {
        this.moneyField = moneyField;
    }

    public TextField getChangeField() {
        return changeField;
    }

    public void setChangeField(TextField changeField) {
        this.changeField = changeField;
    }
}
