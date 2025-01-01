package Model;

import java.util.ArrayList;
import java.util.Date;

public class Manager extends User{
    private ArrayList<SectorType> sectors=new ArrayList<>();


    public Manager(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber,
                         Date dateEmployed, String photo, EmployeeRole role, ArrayList<Permission> permissions, Boolean isActive,
                         ArrayList<Notification> notifications, ArrayList<SectorType> sectors){
        super(id, firstName, lastName,username,password, email, phoneNumber,dateEmployed, photo, role, permissions, isActive, notifications);
        this.sectors=sectors;
    }

    public ArrayList<SectorType> getSectors() {
        return sectors;
    }

    public void setSectors(ArrayList<SectorType> sectors) {
        this.sectors = sectors;
    }
}
