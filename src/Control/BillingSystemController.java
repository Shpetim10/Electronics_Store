package Control;

import Database.Database;
import Exceptions.InsufficientStockException;
import Exceptions.InvalidPaymentArgumentsException;
import Exceptions.OutOfStockException;
import Model.*;
import View.BillingSystemView.BillingSystemView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


import java.io.FileNotFoundException;


public class BillingSystemController implements Alertable{
    private BillingSystemView view = new BillingSystemView();
    private Cashier cashier;
    private Bill bill;
    private ObservableList<ItemBought> itemsBought = FXCollections.observableArrayList();
    private ObservableList<Item> inventory=FXCollections.observableArrayList(Database.getDatabase().getInventory());

    public BillingSystemController(Cashier cashier) {
        try {
            this.cashier = cashier;
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
            setSwitchTableListener();
        } catch (NullPointerException ex) {
            showAlert(Alert.AlertType.ERROR,"Action Forbidden!","There is no planned shift for you!\nPlease start your shift in Home Page!");
        }
    }

    //Useful No-Action Methods
    public void setUpGeneralData() {
        try {
            bill = new Bill();
            bill.setBillId((this.cashier.getActiveShift().getBills().size() + 1));
            bill.setCashier(this.cashier);
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }

        //Set Labels
        setUpTodaySales();
        view.getCheckOutPane().getGeneratedDateTime().setText(
                bill.getDateGenerated().getDayOfMonth() + " " +
                        bill.getDateGenerated().getMonth() + " " +
                        bill.getDateGenerated().getYear() + "\t" +
                        bill.getTimeGenerated().getHour() + ":" +
                        bill.getTimeGenerated().getMinute()
        );
        view.getCheckOutPane().getBillId().setText(String.valueOf(bill.getBillId()));
    }

    public void setUpTodaySales() {
        view.getProductCartBox().getTotalBillNumber().setText(String.valueOf(this.cashier.getActiveShift().getBills().size()));
        view.getProductCartBox().getMoneyCollected().setText(String.valueOf(this.cashier.getActiveShift().getTotalMoneyCollected()));
        view.getProductCartBox().getTaxCollected().setText(String.valueOf(this.cashier.getActiveShift().getTotalTaxCollected()));
    }

    public void setUpBillPricingData() {
        view.getCheckOutPane().getTotalAmount().setText(String.valueOf(bill.getTotalOfBill()));
        view.getCheckOutPane().getTaxAmount().setText(String.valueOf(bill.getTotalTaxOfBill()));
        view.getCheckOutPane().getNoTaxTotal().setText(String.valueOf(bill.getNoTaxTotal()));
    }

    public void clearAllFields() {
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
    public void setCustomerInfoButtonListener() {
        view.getCheckOutPane().getCustomerInfoButton().setOnAction(
                e -> {
                    view.getCheckOutPane().getTemporaryPane().getChildren().clear();
                    view.getCheckOutPane().getTemporaryPane().setCenter(view.getCheckOutPane().createCustomerBox());
                }
        );
    }

    public void setCreditCardButtonListener() {

        view.getCheckOutPane().getCreditCardButton().setOnAction(
                e -> {
                    if (view.getCheckOutPane().getPayByCreditCardRb().isSelected()) {
                        view.getCheckOutPane().getTemporaryPane().getChildren().clear();
                        view.getCheckOutPane().getTemporaryPane().setCenter(view.getCheckOutPane().createCreditCardBox());
                    } else {
                        showAlert(Alert.AlertType.WARNING,"Wrong Payment Method!","Please select Credit Card as payment method!");
                    }
                }
        );
    }

    public void setCashButtonListener() {
        view.getCheckOutPane().getCalculateCashButton().setOnAction(
                e -> {
                    if (view.getCheckOutPane().getPayCashRb().isSelected()) {
                        view.getCheckOutPane().getTemporaryPane().getChildren().clear();
                        view.getCheckOutPane().getTemporaryPane().setCenter(view.getCheckOutPane().createCashAndChangeBox());
                    } else {
                        showAlert(Alert.AlertType.WARNING,"Wrong Payment Method!","Please select pay cash as payment method!");
                    }
                }
        );
    }

    public void setPayCashRbListener() {
        view.getCheckOutPane().getPayCashRb().setOnAction(
                e -> {
                    view.getCheckOutPane().getPayCashRb().setSelected(true);
                    this.bill.setPaymentMethod(PaymentMethod.CASH);
                }
        );
    }

    public void setPayByCreditCardRbListener() {
        view.getCheckOutPane().getPayByCreditCardRb().setOnAction(
                e -> {
                    view.getCheckOutPane().getPayByCreditCardRb().setSelected(true);
                    this.bill.setPaymentMethod(PaymentMethod.CARD);
                }
        );
    }

    public void setAddButtonListener() {
        // Get the search button
        view.getProductCartBox().getSearchBox().getSearchButton().setOnAction(e -> {
            try {
                Item selectedItem = view.getProductCartBox().getInventoryTable().getTable().getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    throw new NullPointerException("No item selected!");
                }

                ItemBought itemBought = new ItemBought(selectedItem);

                if (!checkCartForProductPresence(itemBought)) {
                    // Check inventory stock if there is any in inventory
                    itemBought.getItem().checkInventoryStockAvailable();

                    // Update cart table and bill
                    itemsBought.add(itemBought);
                    view.getProductCartBox().getProductCartTable().setItems(itemsBought);
                    this.bill.getItemBought().add(itemBought);
                    setUpBillPricingData();

                    // Update the table view to display the updated cart table
                    view.getProductCartBox().getTablePane().getChildren().clear();
                    view.getProductCartBox().getTablePane().getChildren().add(view.getProductCartBox().getProductCartTable());
                } else {
                    showAlert(Alert.AlertType.WARNING,"Wrong Payment Method!","This item is already added to the cart!");
                }
            } catch (OutOfStockException ex) {
                showAlert(Alert.AlertType.WARNING,"Inventory Alert!",ex.getMessage());
            } catch (NullPointerException ex) {
                showAlert(Alert.AlertType.WARNING,"Error!","Something wrong happened!");
            }
        });
    }
    public boolean checkCartForProductPresence(ItemBought itemBought){
        for(ItemBought item:bill.getItemBought()){
            if(item.getProductId()==itemBought.getProductId()){
                return true;
            }
        }
        return false;
    }
    public void setCollectedCashListener() {
        view.getCheckOutPane().getCollectedMoneyTf().setOnAction(
                e -> {
                    try {
                        double moneyCollected = Double.parseDouble(view.getCheckOutPane().getCollectedMoneyTf().getText());
                        if (moneyCollected < 0) {
                            throw new InvalidPaymentArgumentsException();
                        } else if (moneyCollected < bill.getTotalOfBill()) {
                            throw new InvalidPaymentArgumentsException(moneyCollected);
                        }
                        view.getCheckOutPane().getCollectedMoney().setText(String.valueOf(moneyCollected));
                        view.getCheckOutPane().getChangeMoneyTf().setText(String.valueOf(moneyCollected - bill.getTotalOfBill()));
                        view.getCheckOutPane().getChangeMoney().setText(String.valueOf(moneyCollected - bill.getTotalOfBill()));

                        //Set bill data fields
                        this.bill.setMoneyCollected(Double.parseDouble(view.getCheckOutPane().getCollectedMoneyTf().getText()));
                        this.bill.setChange(Double.parseDouble(view.getCheckOutPane().getChangeMoney().getText()));
                    } catch (InvalidPaymentArgumentsException ex) {
                        showAlert(Alert.AlertType.WARNING,"Invalid Payment!",ex.getMessage());
                    } catch (NumberFormatException ex) {
                        showAlert(Alert.AlertType.WARNING,"Wrong Input!","Invalid input entered in cash field!");
                    }
                }
        );
    }

    public void setCreditCardSaveListener() {
        view.getCheckOutPane().getSaveCreditCard().setOnAction(
                e -> {
                    String temp;
                    boolean allValid = true;

                    if (Validator.validateCreditCardName(temp = view.getCheckOutPane().getCreditCardName().getText())) {
                        this.bill.setCardName(temp);
                    } else {
                        showAlert(Alert.AlertType.WARNING,"Wrong Payment Credentials!","Credit card name is set incorrectly!");
                        allValid = false;
                    }

                    if (Validator.validateCreditCardNumber(temp = view.getCheckOutPane().getCreditCardNumber().getText())) {
                        this.bill.setCreditCardNr(temp);
                    } else {
                        showAlert(Alert.AlertType.WARNING,"Wrong Payment Credentials!","Credit card number is set incorrectly!");
                        allValid = false;
                    }

                    if (Validator.validateExpirationDateFormat(temp = view.getCheckOutPane().getCreditCardExpDate().getText())) {
                        this.bill.setExpDate(temp);
                    } else {
                        showAlert(Alert.AlertType.WARNING,"Wrong Payment Credentials!","Credit card expiration date is set incorrectly!");
                        allValid = false;
                    }

                    if (Validator.validateCreditCardCvv(temp = view.getCheckOutPane().getCreditCardCVV().getText())) {
                        this.bill.setCvv(temp);
                    } else {
                        showAlert(Alert.AlertType.WARNING,"Wrong Payment Credentials!","Credit card cvv does not conform to its format!");
                        allValid = false;
                    }

                    if (allValid) {
                        showAlert(Alert.AlertType.WARNING,"Wrong Payment Credentials!","Credit Card data saved successfully!");
                    }

                }
                //Nested ifs to not overwrite error messages in case of multiple errors
        );
    }

    public void setCustomerInfoListener() {
        view.getCheckOutPane().getCustomerIdTf().setOnAction(
                e -> {
                    String customerId = view.getCheckOutPane().getCustomerIdTf().getText();
                    if (Validator.validateCustomerId(customerId)) {
                        this.bill.setCustomerIdCard(customerId);
                        int index = this.bill.validateCustomerExistance(customerId);
                        if (index == -1) { //Value returned for non existent
                            Database.getDatabase().getCustomers().add(customerId);
                            Database.getDatabase().getLoyaltyPoints().add(0);
                            index = Database.getDatabase().getCustomers().size() - 1;
                            showAlert(Alert.AlertType.INFORMATION,"New Customer!","A new customer was registered to customer loyalty program!\nHe will get his first loyalty points after bill is generated.");
                        }
                        view.getProductCartBox().getCustomerId().setText(customerId);
                        view.getProductCartBox().getLoyaltyPoints().setText(String.valueOf(Database.getDatabase().getLoyaltyPoints().get(index)));
                        view.getCheckOutPane().getLoyaltyPoints().setText(String.valueOf(Database.getDatabase().getCustomers().get(index)));
                        view.getCheckOutPane().getBillLoyalyPoints().setText(String.valueOf(this.bill.calulateLoyaltyPoints()));
                    } else {
                        showAlert(Alert.AlertType.WARNING,"Wrong Customer Id!","The customer id povided is incorrect! ");
                    }
                }
        );
    }

    public void setEditQuantityListener() {
        view.getProductCartBox().getQuantityColumn().setOnEditCommit(e -> {
            int rowIndex = e.getTablePosition().getRow();
            int newQuantity = e.getNewValue();

            try {
                ItemBought itemBought = itemsBought.get(rowIndex);
                Item item = itemBought.getItem();
                //Check new quanity entered
                if (newQuantity <= 0) {
                    throw new IllegalArgumentException("Quantity must be greater than zero.");
                }
                if (newQuantity > item.getStockQuantity()) {
                    throw new InsufficientStockException();
                }
                // Update the quantities
                int oldQuantity = itemBought.getQuantity();
                itemBought.setQuantity(newQuantity); //ItemBought object
                bill.getItemBought().get(rowIndex).setQuantity(newQuantity);
                item.decrementStock(newQuantity - oldQuantity);

                // Update the table view
                view.getProductCartBox().getProductCartTable().setItems(itemsBought);

                // Update pricing data
                view.getProductCartBox().getProductCartTable().refresh();
                view.getProductCartBox().getInventoryTable().getTable().refresh();
                setUpBillPricingData();

            } catch (IllegalArgumentException ex) {
                showAlert(Alert.AlertType.WARNING,"Wrong Input!",ex.getMessage());
            } catch (InsufficientStockException ex) {
                showAlert(Alert.AlertType.WARNING,"Wrong Payment Credentials!","Insufficient stock for required quantity!");
            }
        }
        );
    }

    public void setDeleteRowButtonListener() {
        view.getProductCartBox().getRemoveItemButton().setOnAction(e -> {
            // Get the selected items from the product cart table
            ObservableList<ItemBought> selectedItems = view.getProductCartBox()
                    .getProductCartTable()
                    .getSelectionModel()
                    .getSelectedItems();

            if (selectedItems.isEmpty()) {
                showAlert(Alert.AlertType.WARNING,"No Item Selected!","No item selected for removal.");
                return;
            }
            // Update stock and remove selected items
            for (ItemBought itemBought : selectedItems) {
                itemBought.getItem().incrementStock(itemBought.getQuantity());
                bill.getItemBought().remove(itemBought);
            }
            // Remove selected items from the cart
            itemsBought.removeAll(selectedItems);

            view.getProductCartBox().getProductCartTable().refresh();
            view.getProductCartBox().getInventoryTable().getTable().refresh();
            setUpBillPricingData();
        });
    }

    public void setClearCartButtonListener(){
        view.getProductCartBox().getClearCart().setOnAction(
                e->{
                    itemsBought.clear();
                    this.bill.clearCart();
                    view.getProductCartBox().getProductCartTable().getItems().clear();
                    setUpBillPricingData();
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
                                int index=Database.getDatabase().getCustomers().indexOf(this.bill.getCustomerIdCard());
                                int currentPoints=Database.getDatabase().getLoyaltyPoints().get(index);
                                Database.getDatabase().getLoyaltyPoints().set(index,currentPoints+this.bill.calulateLoyaltyPoints());

                                try{
                                    this.bill.generateBill();
                                    this.cashier.getActiveShift().getBills().add(bill);
                                    Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
                                    Database.getDatabase().updateCustomers(Database.getDatabase().getCustomers());
                                    Database.getDatabase().updateInventory(Database.getDatabase().getInventory());
                                    Database.getDatabase().updateLoyaltyPoints(Database.getDatabase().getLoyaltyPoints());
                                }
                                catch(FileNotFoundException ex){
                                    showAlert(Alert.AlertType.ERROR,"Error","Some error occured in file creation!");
                                }
                                showAlert(Alert.AlertType.INFORMATION,"New Bill created!","Bill Generated succesfully!");
                            }
                            else{
                                showAlert(Alert.AlertType.WARNING,"Customer Id is not filled!","Please set customer personal Id!");
                            }
                        }
                        else{
                            showAlert(Alert.AlertType.WARNING,"Wrong Payment Informationials!","Please set Payment method!");
                        }

                    }
                    else{
                        showAlert(Alert.AlertType.WARNING,"Empty Cart!","Product card is empty!");
                    }
                }
        );
    }

    public void searchBoxListener(){
        view.getProductCartBox().getSearchBox().getSearchField().setOnAction(
                e->{

                    String searchQuery=this.view.getProductCartBox().getSearchBox().getSearchField().getText().toLowerCase();
                    ObservableList<Item> filteredItems=FXCollections.observableArrayList();
                    for(Item item: view.getProductCartBox().getInventoryTable().getTable().getItems()){
                        if(item.getProductName().toLowerCase().contains(searchQuery)){
                            filteredItems.add(item);
                        }
                    }
                    this.view.getProductCartBox().getInventoryTable().getTable().setItems(filteredItems);
                }
        );
    }
    public void setSwitchTableListener(){
        view.getProductCartBox().getSearchBox().getSearchField().setOnMouseClicked(
                e->{
                    view.getProductCartBox().getInventoryTable().getTable().setItems(inventory);
                    view.getProductCartBox().getTablePane().getChildren().clear();
                    view.getProductCartBox().getTablePane().getChildren().add(view.getProductCartBox().getInventoryTable().getTable());
                    searchBoxListener();
                }
        );
        view.getProductCartBox().setOnMouseClicked(
                e->{
                    view.getProductCartBox().getInventoryTable().getTable().setItems(inventory);
                    view.getProductCartBox().getTablePane().getChildren().clear();
                    view.getProductCartBox().getTablePane().getChildren().add(view.getProductCartBox().getProductCartTable());
                }
        );
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

