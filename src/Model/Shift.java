package Model;

import java.io.File;
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
    private Date shiftDate;
    private LocalTime startHour;
    private LocalTime endHour;
    private File report;
    private ShiftStatus shiftStatus;

    public Shift(int shiftId, Cashier cashier, Date shiftDate, LocalTime startHour, LocalTime endHour) {
        this.shiftId = shiftId;
        this.cashier = cashier;
        this.shiftDate = shiftDate;
        this.startHour = startHour;
        this.endHour = endHour;
        this.bills=new ArrayList<>();
        this.nrOfItemsSold=this.nrOfRefunds=this.nrOfReturns=0;
        this.report=null;
    }

    public void startShift(){
        this.setShiftStatus(ShiftStatus.ACTIVE);
    }
    public void endShift(){
        this.setShiftStatus(ShiftStatus.COMPLETED);
    }
    public File generateShiftReport(){
        File reprot=null;

        return report;
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

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
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
