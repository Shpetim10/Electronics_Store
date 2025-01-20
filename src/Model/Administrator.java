package Model;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Administrator extends User implements InventoryManagement, Serializable {


    @Serial
    private static final long serialVersionUID = -5092817987964467787L;
    private ArrayList<User> employees;
    private Shift shift=new Shift();
    private ArrayList<File> reportsGenerted;
    //Constructor with custom argument
    public Administrator(int id, String firstName, String lastName, Gender gender, LocalDate birthday, Double salary, String username,
                   String password, String email, String phoneNumber, LocalDate dateEmployed, EmployeeRole role,
                   String photo){
        super(id, firstName, lastName, gender, birthday, salary, username,
                password, email, phoneNumber, dateEmployed, role, photo);
        this.shift.setShiftStatus(ShiftStatus.ACTIVE);
        //this.sector = sector;
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

    public Shift getShift() {
        return shift;
    }
}
