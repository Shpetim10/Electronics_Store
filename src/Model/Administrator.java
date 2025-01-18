package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Administrator extends User implements InventoryManagement, Serializable {
    @Serial
    private static final long serialVersionUID = 7443029647372729361L;
    private ArrayList<User> employees;

    //Constructor with custom argument
    public Administrator(int id,String firstName, String lastName, String username, String password, String email,
                         String phoneNumber, Date dateEmployed,EmployeeRole role,ArrayList<Permission> permissions,
                         boolean isActive){
        super(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role, permissions, isActive);
        this.employees=new ArrayList<>();
    }
    //All Argument Constructor
    public Administrator(int id,String firstName, String lastName, String username, String password, String email,
                         String phoneNumber, Date dateEmployed,String photo,EmployeeRole role,ArrayList<Permission> permissions,
                         boolean isActive){
        this(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role, permissions, isActive);
        this.setPhoto(photo);
    }

    public ArrayList<User> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<User> employees) {
        this.employees = employees;
    }


}
