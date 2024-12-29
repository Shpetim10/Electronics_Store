package Model;

import javax.management.Notification;
import java.util.*;

public class Cashier extends User {
    private SectorType sector;
    private ArrayList<Shift> shifts;

    public Cashier(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber,
                   Date dateEmployed, String photo, EmployeeRole role, ArrayList<Permission> permissions, Boolean isActive,
                   ArrayList<Notification> notifications, SectorType sector, ArrayList<Shift> shifts){
        super(id, firstName, lastName,username,password, email, phoneNumber,dateEmployed, photo, role, permissions, isActive, notifications);
        this.sector = sector;
        this.shifts = shifts;
    }

    public SectorType getSector() {
        return sector;
    }

    public void setSector(SectorType sector) {
        this.sector = sector;
    }

    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }


}