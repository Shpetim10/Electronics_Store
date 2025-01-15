package Model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Cashier extends User implements InventoryManagement{
    private SectorType sector;
    private ArrayList<Shift> shifts;
    private ArrayList<File> reportsGenerated=new ArrayList<>();

    public Cashier(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber,
                   Date dateEmployed, String photo, EmployeeRole role, ArrayList<Permission> permissions, Boolean isActive,
                   ArrayList<Notification> notifications, SectorType sector, ArrayList<Shift> shifts){
        super(id, firstName, lastName,username,password, email, phoneNumber,dateEmployed, photo, role, permissions, isActive, notifications);
        this.sector = sector;
        this.shifts = shifts;
    }

    public Cashier(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber, Date dateEmployed, EmployeeRole role, ArrayList<Permission> permissions, boolean isActive){
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

    public void generateCashierReport(LocalDate startDate, LocalDate endDate){
        File reportFile=new File(createCashierReportFilePath(startDate,endDate));

    }
    public void writeReport(File reportFile,LocalDate startDate, LocalDate endDate) throws IOException {
        try(PrintWriter output=new PrintWriter(reportFile)){
            output.println("\t\t\t\t\tCashier Report\n");
            output.println("-".repeat(40));
            output.println("Cashier ID:\t\t" + this.getId());
            output.println("Cashier Full Name:\t\t" + this.getFirstName() + " " + this.getLastName());
            output.println("Number of Shifts:\t\t" + shifts.size());
            output.println("-".repeat(40));
            output.println("");

            double totalSales = 0, totalTax = 0;
            int totalItemsSold = 0, totalReturns = 0, totalRefunds = 0;

            for(Shift shift: shifts) {
                if ((shift.getShiftDate().isAfter(startDate) && shift.getShiftDate().isBefore(endDate))
                        || shift.getShiftDate().isEqual(startDate)
                        || shift.getShiftDate().isEqual(endDate)) {
                    output.println("Shift ID:\t\t" + shift.getShiftId());
                    output.println("Shift Date:\t\t" + shift.getShiftDate());
                    output.println("Start Hour:\t\t" + shift.getStartHour());
                    output.println("End Hour:\t\t" + shift.getEndHour());
                    output.println("");

                    double shiftSales = 0, shiftTax = 0;
                    for (Bill bill : shift.getBills()) {
                        for (ItemBought item : bill.getItemBought()) {
                            shiftSales += item.getTotalPrice();
                            shiftTax += item.getTotalTax();
                        }
                    }

                    totalSales += shiftSales;
                    totalTax += shiftTax;
                    totalItemsSold += shift.getNrOfItemsSold();
                    totalReturns += shift.getNrOfReturns();
                    totalRefunds += shift.getNrOfRefunds();

                    output.println("Shift Sales:\t\t" + shiftSales);
                    output.println("Shift Tax Paid:\t\t" + shiftTax);
                    output.println("Shift No-Tax Earnings:\t\t" + (shiftSales-shiftTax));
                    output.println("Items Sold:\t\t" + shift.getNrOfItemsSold());
                    output.println("Items Returned:\t\t" + shift.getNrOfReturns());
                    output.println("Items Refunded:\t\t" + shift.getNrOfRefunds());
                    output.println("-".repeat(40));
                    output.println("");
                }
            }

                output.println("\t\t\tOverall Summary\n");
                output.println("-".repeat(40));
                output.println("Total Sales:\t\t" + totalSales);
                output.println("Total Tax Paid:\t\t" + totalTax);
                output.println("Shift No-Tax Earnings:\t\t" + (totalSales-totalTax));
                output.println("Total Items Sold:\t\t" + totalItemsSold);
                output.println("Total Returns:\t\t" + totalReturns);
                output.println("Total Refunds:\t\t" + totalRefunds);
                output.println("\n\n");

            } catch (FileNotFoundException ex) {
                throw new FileNotFoundException();
            } catch (IOException ex) {
                throw new IOException();
            }
    }
    public String createCashierReportFilePath(LocalDate startDate, LocalDate endDate){
        String path = "src/Files/Reports/";
        path +=getReportName(startDate,endDate).replace(" ","_")+".txt";
        return path;
    }

    public String getReportName(LocalDate startDate, LocalDate endDate){
        return "Report Of Cashier "+this.getId()+" StartDate "+startDate.toString()+" EndDate "+endDate.toString();
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
                "123-456-7890",new Date(), EmployeeRole.CASHIER, new ArrayList<>(), true);
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
            String reportPath= cashier.createCashierReportFilePath(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 2));
            File reportFile = new File(reportPath);
            cashier.writeReport(reportFile, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 2));
            System.out.println("Report generated: " + reportFile.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Failed to generate report: " + ex.getMessage());
        }
    }
}