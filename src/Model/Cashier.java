package Model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Cashier extends User implements InventoryManagement,Serializable{

    @Serial
    private static final long serialVersionUID = 5900959332953954603L;
    private SectorType sector;
    private ArrayList<Shift> shifts;
    //private ArrayList<File> reportsGenerated=new ArrayList<>();

    public Cashier(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber,
                   LocalDate dateEmployed, String photo, EmployeeRole role, ArrayList<Permission> permissions, Boolean isActive,
                   ArrayList<Notification> notifications, SectorType sector, ArrayList<Shift> shifts){
        super(id, firstName, lastName,username,password, email, phoneNumber,dateEmployed, photo, role, permissions, isActive, notifications);
        this.sector = sector;
        this.shifts = shifts;
    }

    public Cashier(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber, LocalDate dateEmployed, EmployeeRole role, ArrayList<Permission> permissions, boolean isActive){
        super(id,firstName,lastName,username,password,email,phoneNumber,dateEmployed,role,permissions,isActive);
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

    public static void main(String[] args) {
        // Create some ItemBought objects
        ArrayList<ItemBought> items1 = new ArrayList<>();
        items1.add(new ItemBought(1,"MacBook", 2, 2500));
        items1.add(new ItemBought(2,"Iphone", 3, 1200));

        ArrayList<ItemBought> items2 = new ArrayList<>();
        items2.add(new ItemBought(3,"S24 Ulta", 1, 1000));
        items2.add(new ItemBought(4,"Scuter", 5, 3000));

        // Create some Bills
        Cashier cashier = new Cashier(1, "John", "Doe", "johndoe", "password123", "johndoe@example.com",
                "123-456-7890",LocalDate.now(), EmployeeRole.CASHIER, new ArrayList<>(), true);
        Bill bill1 = new Bill(1001, cashier, LocalDate.of(2025, 1, 1), LocalTime.of(10, 30), items1, PaymentMethod.CASH, "12345");
        Bill bill2 = new Bill(1002, cashier, LocalDate.of(2025, 1, 2), LocalTime.of(15, 45), items2, PaymentMethod.CARD, "54321");

        // Create some Shifts
        Shift shift1 = new Shift(1, cashier, LocalDate.of(2025, 1, 1), LocalTime.of(9, 0), LocalTime.of(17, 0));
        Shift shift2 = new Shift(2, cashier, LocalDate.of(2025, 1, 2), LocalTime.of(8, 30), LocalTime.of(16, 30));

        // Add bills to shifts
        shift1.getBills().add(bill1);
        shift2.getBills().add(bill2);

        // Update shift totals (if needed)
        shift1.setNrOfItemsSold(5);
        shift1.setNrOfReturns(1);
        shift1.setNrOfRefunds(0);

        shift2.setNrOfItemsSold(6);
        shift2.setNrOfReturns(0);
        shift2.setNrOfRefunds(1);

        // Add shifts to a Cashier's list of shifts
        cashier.shifts = new ArrayList<>();
        cashier.shifts.add(shift1);
        cashier.shifts.add(shift2);

        // Generate the report
        try {
            String reportPath= ReportGenerator.createCashierReportFilePath(cashier,LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 2));
            File reportFile = new File(reportPath);
            ReportGenerator.generateCashierReport(cashier,LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 2));
            System.out.println("Report generated: " + reportFile.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Failed to generate report: " + ex.getMessage());
        }
    }
}