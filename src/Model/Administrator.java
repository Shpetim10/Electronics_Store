package Model;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.Date;

public class Administrator extends User {
    private ArrayList<User> employees=new ArrayList<>();


    public Administrator(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber,
                   Date dateEmployed, String photo, EmployeeRole role, ArrayList<Permission> permissions, Boolean isActive,
                   ArrayList<Notification> notifications,  ArrayList<User> employees){
        super(id, firstName, lastName,username,password, email, phoneNumber,dateEmployed, photo, role, permissions, isActive, notifications);
        this.employees=employees;
    }

    public ArrayList<User> getEmployees() {
        return employees;
    }

    public void setShifts(ArrayList<User> employees) {
        this.employees = employees;
    }
}
