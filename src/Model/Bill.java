package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bill implements CustomerLoyalty  {
    private long billId;
    private int cashierId;
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


        public Bill() {
            this.itemBought=new ArrayList<>();
        }//No Argument constructor

        public Bill(long billId, int cashierId, LocalDate dateGenerated, LocalTime timeGenerated,
                    ArrayList<ItemBought> itemBought, PaymentMethod paymentMethod, int customerIdCard) {
            this.billId = billId;
            this.cashierId = cashierId;
            this.dateGenerated = dateGenerated;
            this.timeGenerated = timeGenerated;
            this.itemBought = itemBought;
            this.paymentMethod = paymentMethod;
            this.customerIdCard = customerIdCard;
        }

        public double getTotalOfBill(){
            double total=0;

            for(ItemBought item: itemBought){
                total+=item.getTotalPrice();
            }

            return total;
        }
        public double getTotalTaxOfBill(){
            double tax=0;

            for(ItemBought item: itemBought){
                tax+=item.getTotalTax();
            }

            return tax;
        }

        // Getters and Setters
        public long getBillId() {
            return this.billId;
        }

        public void setBillId(long billId) {
            this.billId = billId;
        }

        public int getCashierId() {
            return this.cashierId;
        }

        public void setCashierId(int cashierId) {
            this.cashierId = cashierId;
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

