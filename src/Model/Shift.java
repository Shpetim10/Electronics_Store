package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Shift {
    private int shiftId;
    private Cashier cashier;
    private ArrayList<Bill> bills;
    private int nrOfItemsSold;
    private int nrOfRefunds;
    private int nrOfReturns;
    private LocalDate shiftDate;
    private LocalTime startHour;
    private LocalTime endHour;
    private File report;
    private ShiftStatus shiftStatus;

    public Shift(int shiftId, Cashier cashier, LocalDate shiftDate, LocalTime startHour, LocalTime endHour) {
        this.shiftId = shiftId;
        this.cashier = cashier;
        this.shiftDate = shiftDate;
        this.startHour = startHour;
        this.endHour = endHour;
        this.bills=new ArrayList<>();
        this.nrOfItemsSold=this.nrOfRefunds=this.nrOfReturns=0;
        this.report=null;
        this.shiftStatus=ShiftStatus.PLANNED;
    }

    public double getTotalMoneyCollected(){
        double result=0;
        for(Bill bill: bills){
            result+=bill.getTotalOfBill();
        }
        return result;
    }
    public double getTotalTaxCollected(){
        double result=0;
        for(Bill bill: bills){
            result+=bill.getTotalTaxOfBill();
        }
        return result;
    }
    public void startShift(){
        if(getShiftStatus().equals(ShiftStatus.PLANNED)){
            this.setShiftStatus(ShiftStatus.ACTIVE);
            this.startHour=LocalTime.now();
        }
        else{
            System.out.println("Shift is active or has already finished!");
        }
    }
    public void endShift(){
        this.setShiftStatus(ShiftStatus.COMPLETED);
        this.endHour=LocalTime.now();
        generateShiftReport();
        System.out.println("Shift report was generated! "); //TBC
    }
    //Will be generated automaticaly when the shift is completed
    public void generateShiftReport(){
        File report=null;
        String reportName="src/Files/Reports/Cashier"+cashier.getId()+"/Shift"+
                shiftId+"_"+shiftDate.getDayOfMonth()+"_"+shiftDate.getMonth()+"_"+shiftDate.getYear()+"txt";
        report=new File(reportName);
        try(PrintWriter output=new PrintWriter(new FileOutputStream(report))){
            output.println("\t\t\t\t\tShift Report\n");
            output.println("-".repeat(20));
            output.println("ShiftId:\t\t"+getShiftId());
            output.println("CashierId:\t\t"+cashier.getId());
            output.println("Cashier Full Name:\t\t"+cashier.getFirstName()+" "+ cashier.getLastName());
            output.println("Date of Report:\t\t"+getShiftDate());
            output.println("Start Hour:\t\t"+getStartHour());
            output.println("End Hour:\t\t"+getEndHour());
            output.println("-".repeat(20));
            output.println("");
            double totalSales=0,totalTax=0;
            for(Bill bill : bills){
                for(ItemBought item: bill.getItemBought()){
                    totalSales+=item.getTotalPrice();
                    totalTax+=item.getTotalTax();
                }
            }
            output.println("\n\nTotal sales:\t\t"+totalSales);
            output.println("Total tax paid:\t\t"+totalTax);
            output.println("\n\n\n");
            output.println("Number of items sold:\t\t"+nrOfItemsSold);
            output.println("Number of items returned:\t\t"+nrOfReturns);
            output.println("Number of items to refunded:\t\t"+nrOfRefunds);
        }
        catch(IOException ex){
            System.out.println("There was an error in generating report file!");
        }

        this.report=report;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public int getNrOfItemsSold() {
        return nrOfItemsSold;
    }

    public void setNrOfItemsSold(int nrOfItemsSold) {
        this.nrOfItemsSold = nrOfItemsSold;
    }

    public int getNrOfRefunds() {
        return nrOfRefunds;
    }

    public void setNrOfRefunds(int nrOfRefunds) {
        this.nrOfRefunds = nrOfRefunds;
    }

    public int getNrOfReturns() {
        return nrOfReturns;
    }

    public void setNrOfReturns(int nrOfReturns) {
        this.nrOfReturns = nrOfReturns;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public File getReport() {
        return report;
    }

    public void setReport(File report) {
        this.report = report;
    }

    public ShiftStatus getShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(ShiftStatus shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    // Add equals Method


    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Shift)) return false;

        return ((Shift)o).getShiftId()==this.getShiftId() && ((Shift)o).getCashier().equals(this.getCashier());
    }

    @Override
    public String toString() {
        return "Shift Details: " +
                "shiftId=" + shiftId +
                ", cashier=" + cashier +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", shiftStatus=" + shiftStatus; // Other data is included in report
    }

}
