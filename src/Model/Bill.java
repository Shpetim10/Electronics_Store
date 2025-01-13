package Model;

import Exceptions.InsuffitientStockException;
import Exceptions.ItemNotFoundException;
import Exceptions.OutOfStockException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class Bill implements CustomerLoyalty  {
    private long billId;
    private Cashier cashier;
    private LocalDate dateGenerated;
    private LocalTime timeGenerated;
    private ArrayList<ItemBought> itemBought;
    private PaymentMethod paymentMethod;
    private int customerIdCard;

    //Only if payment method is credit card
    private String creditCardNr;
    private String cardName;
    private String expDate;
    private int cvv;
    //Only if payment is cash
    private int moneyCollected;
    private int change;

        public Bill() {
            this.dateGenerated=LocalDate.now();
            this.timeGenerated=LocalTime.now();
            this.itemBought=new ArrayList<>();
        }//No Argument constructor

        public Bill(long billId, Cashier cashier, LocalDate dateGenerated, LocalTime timeGenerated,
                    ArrayList<ItemBought> itemBought, PaymentMethod paymentMethod, int customerIdCard) {
            this.billId = billId;
            this.cashier = cashier;
            this.dateGenerated = dateGenerated;
            this.timeGenerated = timeGenerated;
            this.itemBought = itemBought;
            this.paymentMethod = paymentMethod;
            this.customerIdCard = customerIdCard;
        }

        //Useful Methods
        public File generateBill(){
            if(cashier.getActiveShift()==null){
                //view.getMessage().setText("There is no active shift to save the bill! Please contact administrator.");
                System.exit(1);  //Will clear page
            }

            //Consider Adding a radio Button
            //bill.setPaymentMethod(PaymentMethod.CASH);  // Will be gotten from combo box

            //Methods for getting items of card
            this.getItemBought().add(new ItemBought(101, "Laptop",4,2000));
            this.getItemBought().add(new ItemBought(102, "Phone",2,1000));

            //Create absolutePath of file
            File billFile=new File(createBillPath());

            try(PrintWriter output=new PrintWriter(new FileOutputStream(billFile,false))){
                writeBill(output);
            }
            catch(FileNotFoundException ex){
                //view.getMessage().setText("File could not be generated! Please Report to Administrator.");
            }
            return billFile;
        }  //Sh

        public void writeBill(PrintWriter output){
            output.printf("%70s\n", "Electronics Store");
            output.println("-".repeat(140) + "\n");
            output.println("Address:\t\tTirana, Albania\n");
            output.println("Date:\t\t" + this.getDateGenerated());
            output.println("Time:\t\t" + this.getTimeGenerated() + "\n\n");
            output.println("CashierId:\t\t" + this.getCashierId());
            output.println("\n\nCustomer Id:\t\t" + customerIdCard);
            output.println("Customer points:\t\t"+loyaltyPoints.get(customers.indexOf(customerIdCard)));
            output.printf("\n\n%70s\n\n", "Fiscal Bill");
            output.printf("%15s%40s%15s%20s%15s%30s\n", "Id", "Product Name", "Quantity", "Price", "Total Tax", "Total Price");
            output.println("-".repeat(140) + "\n");

            if (this.getItemBought() != null) {
                for (ItemBought itemBought : this.getItemBought()) {
                    output.printf("%15d%40s%15d%20.2f%15.2f%30.2f\n",
                            itemBought.getProductId(),
                            itemBought.getProductName(),
                            itemBought.getQuantity(),
                            itemBought.getSellingPrice(),
                            itemBought.getTotalTax(),
                            itemBought.getTotalPrice());
                }
            } else {
                output.println("No items purchased.\n");
            }

            output.println("-".repeat(140) + "\n\n");
            output.printf("%80s\t\t%20f\n", "Total of Bill:", this.getTotalOfBill());
            output.printf("%80s\t\t%20f\n", "Tax of Bill:", this.getTotalTaxOfBill());

            output.println("Payment Method:\t\t" + this.getPaymentMethod());
            if (this.getPaymentMethod().equals(PaymentMethod.CASH)) {
                output.println("Money given:\t\t"+moneyCollected);
                output.println("Change:\t\t"+change);
            } else if (this.getPaymentMethod().equals(PaymentMethod.CARD)) {
                output.println("Full Name:\t\t"+cardName);
                output.println("Card Number:\t\t"+creditCardNr);
            }
            output.printf("%70s\n", "Thank You for shopping with us!");
            output.println("-".repeat(140));
        }   //Sh

        public String createBillPath(){
            String path = "src/Files/Bills/";
            path += "Cashier" + cashier.getId(); //Add to folder of the current cashier
            path += "/Shift" + cashier.getActiveShift().getShiftId() + "/";
            File directory = new File(path);

            if (!directory.exists()) {
                directory.mkdirs(); // Create directories if they do not exist
            }

            path += "Bill" + this.getBillId() + "_" + this.getDateGenerated().getDayOfMonth() + "_" + this.getDateGenerated().getMonth() + "_" + this.getDateGenerated().getYear() + ".txt";
            return path;
    }  //Sh

        public double getTotalOfBill(){
            double total=0;

            for(ItemBought item: itemBought){
                total+=item.getTotalPrice();
            }

            return total;
        } //Sh

        public double getTotalTaxOfBill(){
            double tax=0;

            for(ItemBought item: itemBought){
                tax+=item.getTotalTax();
            }

            return tax;
        } //Sh

        public double getNoTaxTotal(){
            return getTotalOfBill()-getTotalTaxOfBill();
        }

        public ItemBought addProductToCart(String productName) throws ItemNotFoundException, OutOfStockException {
            Item bought = null;
            for (Item item : cashier.items) {
                if (productName.equals(item.getProductName())) {
                    bought = item;
                    break;
                }
            }
            if (bought == null) throw new ItemNotFoundException(productName);
            if (bought.getStockQuantity() == 0) throw new OutOfStockException();

            this.getItemBought().add(new ItemBought(bought));
            return new ItemBought(bought);
        } //Sh

        public void removeProductFromCart(int productId){//Will get from ItemBoughtView when selected, use a for loop to check each item if selected
            for(ItemBought bought :this.getItemBought()){
            //A condition to check checkbox can be added here
                if(bought.getProductId()==productId){
                    this.getItemBought().remove(bought);
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

        public void clearCart(){
            this.getItemBought().clear();
        } //Sh

        public boolean checkInventoryStockAvailable(Item product, int quantity){
            return product.getStockQuantity()-quantity>0;
        }//Sh

        public boolean validateCustomerExistance(String code){
        for(String customer : this.customers){
            if(customer.equals(code)) return true;
        }
        return false;
    } //Sh

        // Getters and Setters
        public long getBillId() {
            return this.billId;
        }

        public void setBillId(long billId) {
            this.billId = billId;
        }

        public Cashier getCashierId() {
            return this.cashier;
        }

        public void setCashier(Cashier cashierId) {
            this.cashier = cashier;
        }

        public LocalDate getDateGenerated() {
            return this.dateGenerated;
        }

        public void setDateGenerated(LocalDate dateGenerated) {
            this.dateGenerated = dateGenerated;
        }

        public LocalTime getTimeGenerated() {
            return this.timeGenerated;
        }

        public void setTimeGenerated(LocalTime timeGenerated) {
            this.timeGenerated = timeGenerated;
        }

        public ArrayList<ItemBought> getItemBought() {
            return this.itemBought;
        }

        public void setItemBought(ArrayList<ItemBought> itemBought) {
            this.itemBought = itemBought;
        }

        public PaymentMethod getPaymentMethod() {
            return this.paymentMethod;
        }

        public void setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public int getCustomerIdCard() {
            return this.customerIdCard;
        }

        public void setCustomerIdCard(int customerIdCard) {
            this.customerIdCard = customerIdCard;
        }

        public String getCreditCardNr() {
            return creditCardNr;
        }

        public void setCreditCardNr(String creditCardNr) {
            this.creditCardNr = creditCardNr;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getExpDate() {
            return expDate;
        }

        public void setExpDate(String expDate) {
            this.expDate = expDate;
        }

        public int getCvv() {
            return cvv;
        }

        public void setCvv(int cvv) {
            this.cvv = cvv;
        }
}

