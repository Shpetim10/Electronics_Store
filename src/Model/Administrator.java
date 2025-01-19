package Model;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Administrator extends User implements InventoryManagement, Serializable {

    @Serial
    private static final long serialVersionUID = 4216350566416415776L;
    private ArrayList<User> employees;
    private ArrayList<File> reportsGenerted;
    //Constructor with custom argument
    public Administrator(int id, String firstName, String lastName, String username, String password, String email,
                         String phoneNumber, LocalDate dateEmployed, EmployeeRole role, ArrayList<Permission> permissions,
                         boolean isActive){
        super(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role, permissions, isActive);
        this.employees=new ArrayList<>();
        this.reportsGenerted=new ArrayList<>();
    }
    //All Argument Constructor
    public Administrator(int id,String firstName, String lastName, String username, String password, String email,
                         String phoneNumber, LocalDate dateEmployed,String photo,EmployeeRole role,ArrayList<Permission> permissions,
                         boolean isActive){
        this(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role, permissions, isActive);
        this.setPhoto(photo);
        this.reportsGenerted=new ArrayList<>();
    }

    public ArrayList<User> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<User> employees) {
        this.employees = employees;
    }

    public ArrayList<File> getReportsGenerted() {
        return reportsGenerted;
    }

    public void setReportsGenerted(ArrayList<File> reportsGenerted) {
        this.reportsGenerted = reportsGenerted;
    }
}
