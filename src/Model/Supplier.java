package Model;
import Database.Database;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.util.*;
//ca ben kjo serialazable?Kjo tregon qe objekti mund te shkruhet ne fileok//Dale te bej dhe nje gje tjeter qe me duhet
public class Supplier implements Serializable {

    @Serial
    private static final long serialVersionUID = 860981446897975582L;
    private transient SimpleIntegerProperty supplierId;
    private transient SimpleStringProperty companyName;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty phoneNumber;
    private transient SimpleStringProperty address;



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
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(supplierId.getValue());
        out.writeUTF(companyName.getValueSafe());
        out.writeUTF(email.getValue());
        out.writeUTF(phoneNumber.getValue());
        out.writeUTF(address.getValue());

    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        supplierId = new SimpleIntegerProperty(in.readInt());
        companyName = new SimpleStringProperty((String) in.readUTF());
        email = new SimpleStringProperty((String) in.readUTF());
        phoneNumber = new SimpleStringProperty((String) in.readUTF());
        address = new SimpleStringProperty((String) in.readUTF());

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

   
}

