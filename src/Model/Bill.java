package Model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Bill {
    private long billId;
    private int cashierId;
    private Date dateGenerated;
    private LocalTime timeGenerated;
    private List<ItemBought> itemBought;
    private PaymentMethod paymentMethod;
    private int customerIdCard;


        public Bill() {}//No Argument constructor

        public Bill(long billId, int cashierId, Date dateGenerated, LocalTime timeGenerated,
                    List<ItemBought> itemBought, PaymentMethod paymentMethod, int customerIdCard) {
            this.billId = billId;
            this.cashierId = cashierId;
            this.dateGenerated = dateGenerated;
            this.timeGenerated = timeGenerated;
            this.itemBought = itemBought;
            this.paymentMethod = paymentMethod;
            this.customerIdCard = customerIdCard;
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

        public Date getDateGenerated() {
            return this.dateGenerated;
        }

        public void setDateGenerated(Date dateGenerated) {
            this.dateGenerated = dateGenerated;
        }

        public LocalTime getTimeGenerated() {
            return this.timeGenerated;
        }

        public void setTimeGenerated(LocalTime timeGenerated) {
            this.timeGenerated = timeGenerated;
        }

        public List<ItemBought> getItemBought() {
            return this.itemBought;
        }

        public void setItemBought(List<ItemBought> itemBought) {
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
    }

}
