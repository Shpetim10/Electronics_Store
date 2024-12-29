package Model;
import java.util.*;

public class Supplier {
    private int supplierId;
    private String companyName;
    private String contactName;
    private String email;
    private long phoneNumber;
    private String address;
    private String paymentDetailsIBAN;
    private ArrayList<Item> productList;
    private int minimumOrderQuantity;

    public Supplier() {
    }

    public Supplier(int supplierId, String companyName, String contactName, String email, long phoneNumber, String address,
                    String paymentDetailsIBAN, ArrayList<Item> productList, int minimumOrderQuantity) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.paymentDetailsIBAN = paymentDetailsIBAN;
        this.productList = productList;
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentDetailsIBAN() {
        return paymentDetailsIBAN;
    }

    public void setPaymentDetailsIBAN(String paymentDetailsIBAN) {
        this.paymentDetailsIBAN = paymentDetailsIBAN;
    }

    public ArrayList<Item> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Item> productList) {
        this.productList = productList;
    }

    public int getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(int minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }
}

