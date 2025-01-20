package Model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Cashier extends User implements InventoryManagement,Serializable{

    @Serial
    private static final long serialVersionUID = -4746137643014146101L;
    private SectorType sector;
    private ArrayList<Shift> shifts;
    //private ArrayList<File> reportsGenerated=new ArrayList<>();

    /// Constructor to add - AddCashier
    public Cashier(int id, String firstName, String lastName, Gender gender, LocalDate birthday, Double salary, String username,
                   String password, String email, String phoneNumber, LocalDate dateEmployed, EmployeeRole role,
                    String photo){
        super(id, firstName, lastName, gender, birthday, salary, username,
                password, email, phoneNumber, dateEmployed, role, photo);
        //this.sector = sector;
        this.shifts=new ArrayList<>();
    }

    public Shift getActiveShift(){
        for(Shift shift: shifts){
            if(shift.getShiftStatus().equals(ShiftStatus.ACTIVE)){
                return shift;
            }
        }
        return null;
    }//Sh

    public void startShiftForCashier(){

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