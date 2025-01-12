package Control;

import Exceptions.ItemNotFoundException;
import Exceptions.InsuffitientStockException;
import Exceptions.OutOfStockException;
import Model.*;
import View.BillingSystemView;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    }

    //Useful Methods
    public File generateBill(){
        if(cashier.getActiveShift()==null){
            //view.getMessage().setText("There is no active shift to save the bill! Please contact administrator.");
            System.exit(1);  //Will clear page
        }

        //Create Bill object
        Bill bill=new Bill();
        bill.setBillId(cashier.getActiveShift().getBills().size()+1);
        bill.setCashierId(this.cashier.getId());
        bill.setDateGenerated(LocalDate.now());
        bill.setTimeGenerated(LocalTime.now());
        bill.setCustomerIdCard(1);                  //Will be gotten from text field
        bill.setPaymentMethod(PaymentMethod.CASH);  // Will be gotten from combo box

        //Methods for getting items of card
        bill.getItemBought().add(new ItemBought(101, "Laptop",4,2000));
        bill.getItemBought().add(new ItemBought(102, "Phone",2,1000));

        //Create absolutePath of file
        File billFile=new File(createBillPath(bill));

        try(PrintWriter output=new PrintWriter(new FileOutputStream(billFile,false))){
            writeBill(output,bill);
        }
        catch(FileNotFoundException ex){
            //view.getMessage().setText("File could not be generated! Please Report to Administrator.");
        }
        return billFile;
    }  //Sh
    public void writeBill(PrintWriter output, Bill bill){
        output.printf("%70s\n","Electronics Store");
        output.println("-".repeat(140)+"\n");
        output.println("Address:\t\tTirana, Albania\n");
        output.println("Date:\t\t"+bill.getDateGenerated());
        output.println("Time:\t\t"+bill.getTimeGenerated()+"\n\n");
        output.println("CashierId:\t\t"+bill.getCashierId());
        output.println("Customer Id:\t\t"+"\n\n");    //Update customer card id
        output.println("Customer Card Id:\t\t");      //Update
        output.printf("\n\n%70s\n\n","Fiscal Bill");
        output.printf("%15s%40s%15s%20s%15s%30s\n","Id","Product Name","Quantity","Price","Total Tax","Total Price");
        output.println("-".repeat(140)+"\n");

        if (bill.getItemBought() != null) {
            for (ItemBought itemBought : bill.getItemBought()) {
                output.printf("%15d%40s%15d%20.2f%15.2f%30.2f\n",
                        itemBought.getProductId(),
                        itemBought.getProductName(),
                        itemBought.getQuantity(),
                        itemBought.getSellingPrice(),
                        itemBought.getTotalTax(),
                        itemBought.getTotalPrice());
            }
        }
        else {
            output.println("No items purchased.\n");
        }

        output.println("-".repeat(140)+"\n\n");
        output.printf("%80s\t\t%20f\n","Total of Bill:",bill.getTotalOfBill());
        output.printf("%80s\t\t%20f\n","Tax of Bill:",bill.getTotalTaxOfBill());

        output.println("Payment Method:\t\t"+bill.getPaymentMethod());

        output.printf("%70s\n","Thank You for shopping with us!");
        output.println("-".repeat(140));
    } //Sh
    public String createBillPath(Bill bill){
        String absolutePath="C:\\Users\\Shpëtim Shabanaj\\OneDrive\\Desktop\\OOP Project\\ElectronicsStore_ShpëtimShabanaj\\Electronics_Store\\src\\Files\\Bills\\";
        absolutePath+="Cashier"+cashier.getId(); //Add to folder of the current cashier
        absolutePath+="\\Shift"+cashier.getActiveShift().getShiftId()+"\\";
        File directory=new File(absolutePath);

        if (!directory.exists()) {
            directory.mkdirs(); // Create directories if they do not exist
        }

        absolutePath+="Bill"+bill.getBillId()+"_"+bill.getDateGenerated().getDayOfMonth()+"_"+bill.getDateGenerated().getMonth()+"_"+bill.getDateGenerated().getYear()+".txt";
        return absolutePath;
    }  //Sh

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
        view.getCustomerInfoButton().setOnAction(
                e->{
                    view.getTemporaryPane().getChildren().clear();
                    view.getTemporaryPane().setCenter(view.createCustomerBox());
                }
        );
    }
    public void setCreditCardButtonListener(){
        view.getCreditCardButton().setOnAction(
                e-> {
                    view.getTemporaryPane().getChildren().clear();
                    view.getTemporaryPane().setCenter(view.createCreditCardBox());
                }
        );
    }
    public void setCashButtonListener(){
        view.getCalculateCashButton().setOnAction(
                e-> {
                    view.getTemporaryPane().getChildren().clear();
                    view.getTemporaryPane().setCenter(view.createCashAndChangeBox());
                }
        );
    }
}
