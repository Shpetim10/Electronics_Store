package Model;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Manager extends User implements Serializable {


    @Serial
    private static final long serialVersionUID = -937962226369155240L;
    private ArrayList<SectorType> sectors=new ArrayList<>();
    private ArrayList<File> reportsGenerated = new ArrayList<>();

    public Manager(int id, String firstName, String lastName, Gender gender, LocalDate birthday, Double salary, String username,
                   String password, String email, String phoneNumber, LocalDate dateEmployed, EmployeeRole role,
                   String photo,ArrayList<SectorType> sectors){
        super(id, firstName, lastName, gender, birthday, salary, username,
                password, email, phoneNumber, dateEmployed, role, photo);
        this.sectors = sectors;
        this.reportsGenerated=new ArrayList<>();
    }

    /// Add Constructor
    public Manager(int id, String firstName, String lastName, String username, EmployeeRole role, ArrayList<Boolean> permissions){
        super(id, firstName, lastName, username, role, permissions);
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
