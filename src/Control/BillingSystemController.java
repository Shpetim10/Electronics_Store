package Control;

import Exceptions.InvalidPaymentArgumentsException;
import Exceptions.ItemNotFoundException;
import Exceptions.OutOfStockException;
import Model.*;
import View.BillingSystemView.BillingSystemView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class BillingSystemController {
    private BillingSystemView view=new BillingSystemView();
    private Cashier cashier;
    private Bill bill;
    private ObservableList<ItemBought> itemsBought=FXCollections.observableArrayList();
    private ArrayList<Button> deleteRowButtons=new ArrayList<>();

    public BillingSystemController(Cashier cashier){
        try{
            this.cashier=cashier;
            setUpGeneralData();
            setCashButtonListener(); //Displays pane
            setCreditCardButtonListener(); //Displays Pane
            setCustomerInfoButtonListener(); //Displays pane
            setPayByCreditCardRbListener();
            setPayCashRbListener();
            setNewBillButtonListener();
            setAddButtonListener();
            setCollectedCashListener();
            setCreditCardSaveListener();
            setCustomerInfoListener();
            setClearCartButtonListener();
            setGenerateBillButtonListener();
            setEditQuantityListener();
            setDeleteRowButtonListener();
        }
        catch (NullPointerException ex){
            view.getProductCartBox().getErrorMessage().setText("There is no planned shift for you!\nPlease contact Administrator to activate your shift!");
        }
    }

    //Useful No-Action Methods
    public void setUpGeneralData(){
        try{
            bill=new Bill();
            bill.setBillId(this.cashier.getActiveShift().getBills().size()+1);
            bill.setCashier(this.cashier);
        }
        catch(NullPointerException ex){
            throw new NullPointerException();
        }

        //Set Labels
        view.getProductCartBox().getErrorMessage().setText("");
        setUpTodaySales();
        view.getCheckOutPane().getGeneratedDateTime().setText(
                bill.getDateGenerated().getDayOfMonth()+" "+
                        bill.getDateGenerated().getMonth()+" "+
                        bill.getDateGenerated().getYear()+"\t"+
                        bill.getTimeGenerated().getHour()+":"+
                        bill.getTimeGenerated().getMinute()
        );
        view.getCheckOutPane().getBillId().setText(String.valueOf(bill.getBillId()));
    }

    public void setUpTodaySales(){
        view.getProductCartBox().getTotalBillNumber().setText(String.valueOf(cashier.getActiveShift().getBills().size()));
        view.getProductCartBox().getMoneyCollected().setText(String.valueOf(cashier.getActiveShift().getTotalMoneyCollected()));
        view.getProductCartBox().getTaxCollected().setText(String.valueOf(cashier.getActiveShift().getTotalTaxCollected()));
    }

    public void setUpBillPricingData(){
        view.getCheckOutPane().getTotalAmount().setText(String.valueOf(bill.getTotalOfBill()));
        view.getCheckOutPane().getTaxAmount().setText(String.valueOf(bill.getTotalTaxOfBill()));
        view.getCheckOutPane().getNoTaxTotal().setText(String.valueOf(bill.getNoTaxTotal()));
    }

    public void clearMessageLabel(){
        view.getProductCartBox().getErrorMessage().setText("");
    }

    public void displayMessage(String message){
        view.getProductCartBox().getErrorMessage().setText(message);
    }

    public void clearAllFields(){
        view.getProductCartBox().getProductCartTable().getItems().clear();
        view.getCheckOutPane().getCollectedMoney().setText("");
        view.getCheckOutPane().getChangeMoney().setText("");
        view.getCheckOutPane().getCollectedMoneyTf().clear();
        view.getCheckOutPane().getChangeMoneyTf().clear();
        view.getCheckOutPane().getCustomerIdTf().clear();
        view.getCheckOutPane().getLoyaltyPoints().setText("");
        view.getCheckOutPane().getBillLoyalyPoints().setText("");
        view.getCheckOutPane().getCreditCardNumber().clear();
        view.getCheckOutPane().getCreditCardName().clear();
        view.getCheckOutPane().getCreditCardExpDate().clear();
        view.getCheckOutPane().getCreditCardCVV().clear();
        view.getProductCartBox().getCustomerId().setText("");
        view.getProductCartBox().getLoyaltyPoints().setText("");
        view.getCheckOutPane().getPayCashRb().setSelected(false);
        view.getCheckOutPane().getPayByCreditCardRb().setSelected(false);

        setUpGeneralData();
        setUpBillPricingData();
    }
    //Setting Actions
    public void setCustomerInfoButtonListener(){
        view.getCheckOutPane().getCustomerInfoButton().setOnAction(
                e->{
                    clearMessageLabel();
                    view.getCheckOutPane().getTemporaryPane().getChildren().clear();
                    view.getCheckOutPane().getTemporaryPane().setCenter(view.getCheckOutPane().createCustomerBox());
                }
        );
    }

    public void setCreditCardButtonListener(){

            view.getCheckOutPane().getCreditCardButton().setOnAction(
                    e -> {
                        if(view.getCheckOutPane().getPayByCreditCardRb().isSelected()){
                            clearMessageLabel();
                            view.getCheckOutPane().getTemporaryPane().getChildren().clear();
                            view.getCheckOutPane().getTemporaryPane().setCenter(view.getCheckOutPane().createCreditCardBox());
                        }
                        else{
                            view.getProductCartBox().getErrorMessage().setText("Please select Credit Card as payment method!");
                        }
                    }
            );
    }

    public void setCashButtonListener(){
            view.getCheckOutPane().getCalculateCashButton().setOnAction(
                    e-> {
                        if(view.getCheckOutPane().getPayCashRb().isSelected()){
                            clearMessageLabel();
                            view.getCheckOutPane().getTemporaryPane().getChildren().clear();
                            view.getCheckOutPane().getTemporaryPane().setCenter(view.getCheckOutPane().createCashAndChangeBox());
                        }
                        else{
                            view.getProductCartBox().getErrorMessage().setText("Please select pay cash as payment method!");
                        }
                    }
            );
        }

    public void setPayCashRbListener(){
        view.getCheckOutPane().getPayCashRb().setOnAction(
                e->{
                    clearMessageLabel();
                    view.getCheckOutPane().getPayCashRb().setSelected(true);
                    this.bill.setPaymentMethod(PaymentMethod.CASH);
                }
        );
    }

    public void setPayByCreditCardRbListener(){
        view.getCheckOutPane().getPayByCreditCardRb().setOnAction(
                e->{
                    clearMessageLabel();
                    view.getCheckOutPane().getPayByCreditCardRb().setSelected(true);
                    this.bill.setPaymentMethod(PaymentMethod.CARD);
                }
        );
    }

    public void setAddButtonListener() {
        view.getProductCartBox().getSearchBox().getSearchButton().setOnAction(
                e -> {
                    clearMessageLabel();
//                    try{
//                        ItemBought itemBought=bill.addProductToCart(view.getProductCartBox().getSearchBox().getSearchField().getText());
//                        if(!itemsBought.contains(itemBought)){
//                            view.getProductCartBox().getProductCartTable().setItems(itemsBought);
//                              this.bill.getItemBought().add(itemBought);
//                              setUpBillPricingData();
//                        }
//                        else{
//                            view.getProductCartBox().getErrorMessage().setText("This item is already added to cart!");
//                        }
//                    } catch (OutOfStockException ex) {
//                        view.getProductCartBox().getErrorMessage().setText(ex.getMessage());
//                    } catch (ItemNotFoundException ex) {
//                        view.getProductCartBox().getErrorMessage().setText(ex.getMessage());
//                    }
                    ItemBought item1 = new ItemBought(1, "MacBook", 1, 2500);
                    ItemBought item2 = new ItemBought(2, "Scuter", 2, 3000);
                    ItemBought item3 = new ItemBought(3, "Fridge", 3, 1325);
                    itemsBought.addAll(item1, item2, item3);
                    this.bill.getItemBought().add(item1);
                    this.bill.getItemBought().add(item2);
                    this.bill.getItemBought().add(item3);
                    view.getProductCartBox().getProductCartTable().setItems(itemsBought);
                    setUpBillPricingData();
                }
        );
    }

    public void setCollectedCashListener(){
            view.getCheckOutPane().getCollectedMoneyTf().setOnAction(
                    e->{
                        clearMessageLabel();
                        try{
                            double moneyCollected=Double.parseDouble(view.getCheckOutPane().getCollectedMoneyTf().getText());
                            if (moneyCollected<0){
                                throw new InvalidPaymentArgumentsException();
                            }
                            else if (moneyCollected<bill.getTotalOfBill()) {
                                throw  new InvalidPaymentArgumentsException(moneyCollected);
                            }
                            view.getCheckOutPane().getCollectedMoney().setText(String.valueOf(moneyCollected));
                            view.getCheckOutPane().getChangeMoneyTf().setText(String.valueOf(moneyCollected- bill.getTotalOfBill()));
                            view.getCheckOutPane().getChangeMoney().setText(String.valueOf(moneyCollected- bill.getTotalOfBill()));

                            //Set bill data fields
                            this.bill.setMoneyCollected(Double.parseDouble(view.getCheckOutPane().getCollectedMoneyTf().getText()));
                            this.bill.setChange(Double.parseDouble(view.getCheckOutPane().getChangeMoney().getText()));
                        }
                        catch(InvalidPaymentArgumentsException ex){
                            view.getProductCartBox().getErrorMessage().setText(ex.getMessage());
                        }
                        catch(NumberFormatException ex){
                            view.getProductCartBox().getErrorMessage().setText("Invalid input entered in cash field!");
                        }
                    }
        );
    }

    public void setCreditCardSaveListener(){
        view.getCheckOutPane().getSaveCreditCard().setOnAction(
                e->{
                    clearMessageLabel();
                    String temp;
                    boolean allValid = true;

                    if (Validator.validateCreditCardName(temp = view.getCheckOutPane().getCreditCardName().getText())) {
                        this.bill.setCardName(temp);
                    } else {
                        displayMessage("Credit card name is set incorrectly!");
                        allValid = false;
                    }

                    if (Validator.validateCreditCardNumber(temp = view.getCheckOutPane().getCreditCardNumber().getText())) {
                        this.bill.setCreditCardNr(temp);
                    } else {
                        displayMessage("Credit card number is set incorrectly!");
                        allValid = false;
                    }

                    if (Validator.validateExpirationDateFormat(temp = view.getCheckOutPane().getCreditCardExpDate().getText())) {
                        this.bill.setExpDate(temp);
                    } else {
                        displayMessage("Credit card expiration date is set incorrectly!");
                        allValid = false;
                    }

                    if (Validator.validateCreditCardCvv(temp = view.getCheckOutPane().getCreditCardCVV().getText())) {
                        this.bill.setCvv(temp);
                    } else {
                        displayMessage("Credit card cvv does not conform to its format!");
                        allValid = false;
                    }

                    if (allValid) {
                        displayMessage("Credit Card data saved successfully!");
                    }

                }
    //Nested ifs to not overwrite error messages in case of multiple errors
        );
    }

    public void setCustomerInfoListener(){
        view.getCheckOutPane().getCustomerIdTf().setOnAction(
                e->{
                    clearMessageLabel();
                    String customerId=view.getCheckOutPane().getCustomerIdTf().getText();
                    if(Validator.validateCustomerId(customerId)){
                        this.bill.setCustomerIdCard(customerId);
                        int index=this.bill.validateCustomerExistance(customerId);
                        if(index==-1){ //Value returned for non existent
                            this.bill.customers.add(customerId);
                            this.bill.loyaltyPoints.add(0);
                            index=this.bill.customers.size()-1;
                            displayMessage("A new customer was registered to customer loyalty program!\nHe will get his first loyalty points after bill is generated.");
                        }
                        view.getProductCartBox().getCustomerId().setText(customerId);
                        view.getProductCartBox().getLoyaltyPoints().setText(String.valueOf(this.bill.loyaltyPoints.get(index)));
                        view.getCheckOutPane().getLoyaltyPoints().setText(String.valueOf(this.bill.loyaltyPoints.get(index)));
                        view.getCheckOutPane().getBillLoyalyPoints().setText(String.valueOf(this.bill.calulateLoyaltyPoints()));
                    }
                    else{
                        displayMessage("The customer id povided is incorrect! ");
                    }
                }
        );
    }

    public void setEditQuantityListener() {
            this.view.getProductCartBox().getQuantityColum().setOnEditCommit(e -> {
            ItemBought item = e.getRowValue();
            item.setQuantity(e.getNewValue());
            setUpBillPricingData();
        });
    }

    public void setClearCartButtonListener(){
        view.getProductCartBox().getClearCart().setOnAction(
                e->{
                    itemsBought.clear();
                    this.bill.clearCart();
                    view.getProductCartBox().getProductCartTable().getItems().clear();
                }
        );
    }

    public void setNewBillButtonListener(){
        view.getCheckOutPane().getNewBillButton().setOnAction(
                e->{
                    clearAllFields();
                    this.bill=new Bill();
                    setUpGeneralData();
                    setUpBillPricingData();
                }
        );
    }

    public void setGenerateBillButtonListener(){
        view.getCheckOutPane().getGenerateBillButton().setOnAction(
                e->{
//                    for(ItemBought itemBought: this.bill.getItemBought()){
//                        itemBought.getItem().decrementStock(itemBought.getQuantity());
//                    }
                    if(!itemsBought.isEmpty()) {
                        //Check if payment method is empty
                        if(
                                this.bill.getPaymentMethod() != null &&
                                        (this.bill.getMoneyCollected() != 0 ||
                                                (this.bill.getCardName() != null &&
                                                        this.bill.getCreditCardNr() != null &&
                                                        this.bill.getExpDate() != null &&
                                                        this.bill.getCvv() != null))
                        )
                        {
                            //check if customer info is empty
                            if(
                               this.bill.getCustomerIdCard()!=null
                            ){
                                setUpTodaySales();
                                int index=this.bill.customers.indexOf(this.bill.getCustomerIdCard());
                                int currentPoints=this.bill.loyaltyPoints.get(index);
                                this.bill.loyaltyPoints.set(index,currentPoints+this.bill.calulateLoyaltyPoints());

                                try{
                                    this.bill.generateBill();
                                }
                                catch(FileNotFoundException ex){
                                    displayMessage("Some error occured in file creation!");
                                }


                                this.cashier.getActiveShift().getBills().add(this.bill);
                                displayMessage("Bill Generated succesfully!");
                            }
                            else{
                                displayMessage("Please set customer personal Id!");
                            }

                        }
                        else{
                            displayMessage("Please set Payment method!");
                        }

                    }
                    else{
                        displayMessage("Product card is empty!");
                    }
                }
        );
    }

    public void setDeleteRowButtonListener() {
        TableColumn<ItemBought, Void> buttonColumn = view.getProductCartBox().getButtonColumn();

        buttonColumn.setCellFactory(e -> {
            TableCell<ItemBought, Void> cell = new TableCell<>() {
                private final Button deleteButton = view.getProductCartBox().createDeleteRowButton();
                {
                    deleteButton.setOnAction(event -> {
                        int index = getIndex();
                        getTableView().getItems().remove(index);
                        bill.getItemBought().remove(index);
                        setUpBillPricingData();
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                   setGraphic(empty ? null : deleteButton);
                }
            };
            return cell;
        });
    }

    //Getters Setters
    public BillingSystemView getView() {
        return view;
    }

    public void setView(BillingSystemView view) {
        this.view = view;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}

