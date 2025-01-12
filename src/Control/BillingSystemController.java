package Control;

import Exceptions.ItemNotFoundException;
import Exceptions.InsuffitientStockException;
import Exceptions.OutOfStockException;
import Model.*;
import View.BillingSystemView.BillingSystemView;
import java.util.ArrayList;
import java.util.Map;

public class BillingSystemController {
    private BillingSystemView view=new BillingSystemView();
    private Cashier cashier;

    public BillingSystemController(Cashier cashier){
        this.cashier=cashier;
        setCashButtonListener();
        setCreditCardButtonListener();
        setCustomerInfoButtonListener();
        setPayByCreditCardRbListener();
        setPayCashRbListener();

    }

    //Useful Methods

    public void addProductToCart(String productName,Bill bill) throws ItemNotFoundException, OutOfStockException, InsuffitientStockException {
        Item bought=null;
        for(Item item: cashier.items){
            if(productName.equals(item.getProductName())){
                bought=item;
                break;
            }
        }
        if(bought==null) throw new ItemNotFoundException(productName);
        if(bought.getStockQuantity()==0) throw new OutOfStockException();

        int quantity=0;
        //Get Quantity from view and validate

        if(!checkInventoryStockAvailable(bought,quantity)) throw new InsuffitientStockException();

       // bill.getItemBought().add(new ItemBought(bought,quantity));
        bought.decrementStock(quantity);
    } //Sh

    public void removeProductFromCart(Bill bill,int productId){//Will get from ItemBoughtView when selected, use a for loop to check each item if selected
        for(ItemBought bought :bill.getItemBought()){
            //A condition to check checkbox can be added here
            if(bought.getProductId()==productId){
                bill.getItemBought().remove(bought);
                bought.getItem().incrementStock(bought.getQuantity());
                break;
            }
        }
    } //Sh

    public ArrayList<String> getProductNamesByCategory(SectorType type) throws ItemNotFoundException{
        ArrayList<String> result=new ArrayList<>();
        for(Item item: cashier.items){
            if(item.getSector()==type){
                result.add(item.getProductName());
            }
        }
        if(result.isEmpty()) throw new ItemNotFoundException("of type "+type+" ");

        return result;
    } //Sh

    public void clearCart(Bill bill){
        bill.setItemBought(new ArrayList<>());
    } //Sh

    public boolean checkInventoryStockAvailable(Item product, int quantity){
        return product.getStockQuantity()-quantity>0;
    }//Sh

    public boolean validateGiftCardExistance(Bill bill,String code){
        for(Map.Entry<String,Double> card: bill.giftCards.entrySet()){
            if(card.getKey().equals(code)) return true;
        }
        return false;
    } //Sh

    public boolean validateCustomerExistance(Bill bill,int code){
        for(Map.Entry<Integer,Integer> customer : bill.customers.entrySet()){
            if(customer.getKey()==code) return true;
        }
        return false;
    } //Sh

    public BillingSystemView getView() {
        return view;
    }

    public void setView(BillingSystemView view) {
        this.view = view;
    }

    //Setting Actions
    public void setCustomerInfoButtonListener(){
        view.getCheckOutPane().getCustomerInfoButton().setOnAction(
                e->{
                    view.getProductCartBox().getErrorMessage().setText("");
                    view.getCheckOutPane().getTemporaryPane().getChildren().clear();
                    view.getCheckOutPane().getTemporaryPane().setCenter(view.getCheckOutPane().createCustomerBox());
                }
        );
    }
    public void setCreditCardButtonListener(){

            view.getCheckOutPane().getCreditCardButton().setOnAction(
                    e -> {
                        if(view.getCheckOutPane().getPayByCreditCardRb().isSelected()){
                            view.getProductCartBox().getErrorMessage().setText("");
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
                            view.getProductCartBox().getErrorMessage().setText("");
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
                    view.getProductCartBox().getErrorMessage().setText("");
                    view.getCheckOutPane().getPayCashRb().setSelected(true);
                }
        );
    }
    public void setPayByCreditCardRbListener(){
        view.getCheckOutPane().getPayByCreditCardRb().setOnAction(
                e->{
                    view.getProductCartBox().getErrorMessage().setText("");
                    view.getCheckOutPane().getPayByCreditCardRb().setSelected(true);
                }
        );
    }
//    public void setDeleteButtonListener(){
//        view.getDeleteButton().setOnAction(e -> {
//            ItemBought item = (ItemBought) view.getProductCartTable().getSelectionModel().getSelectedItem();
//            if (item != null) {
//                view.getProductCartTable().getItems().remove(item);
//            }
//        });
//    }

}

