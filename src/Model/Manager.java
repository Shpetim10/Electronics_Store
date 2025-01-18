package Model;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Manager extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 6803966091603793532L;
    private ArrayList<SectorType> sectors=new ArrayList<>();
    private ArrayList<File> reportsGenerated = new ArrayList<>();

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
