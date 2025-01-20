package Model;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Manager extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = -923537105079691357L;
    private ArrayList<SectorType> sectors=new ArrayList<>();
    private ArrayList<File> reportsGenerated = new ArrayList<>();

    public Manager(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber,
                   LocalDate dateEmployed, String photo, EmployeeRole role, double salary,ArrayList<Permission> permissions, Boolean isActive,
                   ArrayList<Notification> notifications, ArrayList<SectorType> sectors){
        super(id, firstName, lastName,username,password, email, phoneNumber,dateEmployed, photo, role,salary, permissions, isActive, notifications);
        this.sectors=sectors;
    }

    public Manager(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber, LocalDate dateEmployed, EmployeeRole role,double salary, ArrayList<Permission> permissions, boolean isActive, ArrayList<SectorType> sectors) {
        super(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role,salary, permissions, isActive);
        this.sectors = sectors;
    }

    public ArrayList<SectorType> getSectors() {
        return sectors;
    }

    public void setSectors(ArrayList<SectorType> sectors) {
        this.sectors = sectors;
    }

    public ArrayList<File> getReportsGenerated() {
        return reportsGenerated;
    }

    public void setReportsGenerated(ArrayList<File> reportsGenerated) {
        this.reportsGenerated = reportsGenerated;
    }
}
