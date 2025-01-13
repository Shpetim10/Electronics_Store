package Control;

import Exceptions.InvalidPaymentArgumentsException;
import Exceptions.ItemNotFoundException;
import Exceptions.InsuffitientStockException;
import Exceptions.OutOfStockException;
import Model.*;
import View.BillingSystemView.BillingSystemView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class BillingSystemController {
    private BillingSystemView view=new BillingSystemView();
    private Cashier cashier;
    private Bill bill;
    private ObservableList<ItemBought> itemsBought=FXCollections.observableArrayList();

    public BillingSystemController(Cashier cashier){
        try{
            this.cashier=cashier;
            setUpGeneralData();
            setCashButtonListener();
            setCreditCardButtonListener();
            setCustomerInfoButtonListener();
            setPayByCreditCardRbListener();
            setPayCashRbListener();
            setNewBillButtonListener();
            setAddButtonListener();
            setCollectedCashListener();

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
        view.getProductCartBox().getTotalBillNumber().setText(String.valueOf(cashier.getActiveShift().getBills().size()));
        view.getProductCartBox().getMoneyCollected().setText(String.valueOf(cashier.getActiveShift().getTotalMoneyCollected()));
        view.getProductCartBox().getTaxCollected().setText(String.valueOf(cashier.getActiveShift().getTotalTaxCollected()));
        view.getCheckOutPane().getGeneratedDateTime().setText(
                bill.getDateGenerated().getDayOfMonth()+" "+
                        bill.getDateGenerated().getMonth()+" "+
                        bill.getDateGenerated().getYear()+"\t"+
                        bill.getTimeGenerated().getHour()+":"+
                        bill.getTimeGenerated().getMinute()
        );
        view.getCheckOutPane().getBillId().setText(String.valueOf(bill.getBillId()));
    }

    public void setNewBillButtonListener(){
        view.getCheckOutPane().getNewBillButton().setOnAction(
                e->{
                    setUpGeneralData();
                }
        );
    }

    public void setUpBillPricingData(){
        view.getCheckOutPane().getTotalAmount().setText(String.valueOf(bill.getTotalOfBill()));
        view.getCheckOutPane().getTaxAmount().setText(String.valueOf(bill.getTotalTaxOfBill()));
        view.getCheckOutPane().getNoTaxTotal().setText(String.valueOf(bill.getNoTaxTotal()));
    }

    public void clearMessageLabel(){
        view.getProductCartBox().getErrorMessage().setText("");
    }
    public void displayMessage(){

    }
    public void clearAllFields(){

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
                }
        );
    }

    public void setPayByCreditCardRbListener(){
        view.getCheckOutPane().getPayByCreditCardRb().setOnAction(
                e->{
                    clearMessageLabel();
                    view.getCheckOutPane().getPayByCreditCardRb().setSelected(true);
                }
        );
    }

    public void setAddButtonListener(){
        view.getProductCartBox().getSearchBox().getSearchButton().setOnAction(
                e->{
                    clearMessageLabel();
                    try{
                        ItemBought itemBought=bill.addProductToCart(view.getProductCartBox().getSearchBox().getSearchField().getText());
                        if(!itemsBought.contains(itemBought)){
                            view.getProductCartBox().getProductCartTable().setItems(itemsBought);
                        }
                        else{
                            view.getProductCartBox().getErrorMessage().setText("This item is already added to cart!");
                        }
                    } catch (OutOfStockException ex) {
                        view.getProductCartBox().getErrorMessage().setText(ex.getMessage());
                    } catch (ItemNotFoundException ex) {
                        view.getProductCartBox().getErrorMessage().setText(ex.getMessage());
                    }
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
                        }
                        catch(InvalidPaymentArgumentsException ex){
                            view.getProductCartBox().getErrorMessage().setText(ex.getMessage());
                        }
                        catch(NumberFormatException ex){
                            view.getProductCartBox().getErrorMessage().setText("Please provide neccesary data for getting forward with process!");
                        }
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

