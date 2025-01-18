package Model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.*;

public class Supplier {
    private SimpleIntegerProperty supplierId;
    private SimpleStringProperty companyName;
    private SimpleStringProperty contactName;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;
    private String paymentDetailsIBAN;
    private ArrayList<Item> productList;
    private int minimumOrderQuantity;

    public Supplier() {
    }
    public Supplier(String companyName) {
    }

    public Supplier(int supplierId, String companyName, String email, String phoneNumber, String address) {
        this.setSupplierId(supplierId);
        this.setCompanyName(companyName);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
    }


    public int getSupplierId() {
        return supplierId.getValue();
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = new SimpleIntegerProperty(supplierId);
    }

    public String getCompanyName() {
        return companyName.getValue();
    }

    public void setCompanyName(String companyName) {
        this.companyName= new SimpleStringProperty(companyName);;
    }

    public String getContactName() {
        return contactName.get();
    }

    public SimpleStringProperty contactNameProperty() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName.set(contactName);
    }

    public String getEmail() {
        return email.getValue();
    }


    public void setEmail(String email) {
        this.email= new SimpleStringProperty(email);;
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber= new SimpleStringProperty(phoneNumber);;
    }

    public String getAddress() {
        return address.getValue();
    }



    public void setAddress(String address) {
        this.address= new SimpleStringProperty(address);;
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

