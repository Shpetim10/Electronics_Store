package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;


public class ReportGenerator {
    public static void writeSectorReport(File reportFile, LocalDate startDate, LocalDate endDate, ArrayList<Cashier> cashiers) throws IOException {
        try (PrintWriter output = new PrintWriter(reportFile)) {
            output.println("\t\t\t\t\tSector Report\n");
            output.println("-".repeat(50));
            output.println("Report Period: " + startDate + " to " + endDate);
            output.println("Total Employees: " + cashiers.size());
            output.println("-".repeat(50));
            output.println("");

            double sectorTotalSales = 0, sectorTotalTax = 0;
            int sectorTotalItemsSold = 0, sectorTotalReturns = 0, sectorTotalRefunds = 0;

            for (Cashier cashier : cashiers) {
                output.println("Employee ID:\t\t" + cashier.getId());
                output.println("Employee Name:\t\t" + cashier.getFirstName() + " " + cashier.getLastName());
                output.println("-".repeat(40));
                output.println("");

                double employeeTotalSales = 0, employeeTotalTax = 0;
                int employeeTotalItemsSold = 0, employeeTotalReturns = 0, employeeTotalRefunds = 0;

                for (Shift shift : cashier.getShifts()) {
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

                        employeeTotalSales += shiftSales;
                        employeeTotalTax += shiftTax;
                        employeeTotalItemsSold += shift.getNrOfItemsSold();
                        employeeTotalReturns += shift.getNrOfReturns();
                        employeeTotalRefunds += shift.getNrOfRefunds();

                        output.println("Shift Sales:\t\t" + shiftSales);
                        output.println("Shift Tax Paid:\t\t" + shiftTax);
                        output.println("Shift No-Tax Earnings:\t\t" + (shiftSales - shiftTax));
                        output.println("Items Sold:\t\t" + shift.getNrOfItemsSold());
                        output.println("Items Returned:\t\t" + shift.getNrOfReturns());
                        output.println("Items Refunded:\t\t" + shift.getNrOfRefunds());
                        output.println("-".repeat(40));
                        output.println("");
                    }
                }

                sectorTotalSales += employeeTotalSales;
                sectorTotalTax += employeeTotalTax;
                sectorTotalItemsSold += employeeTotalItemsSold;
                sectorTotalReturns += employeeTotalReturns;
                sectorTotalRefunds += employeeTotalRefunds;

                output.println("\t\t\tEmployee Summary\n");
                output.println("-".repeat(40));
                output.println("Total Sales:\t\t" + employeeTotalSales);
                output.println("Total Tax Paid:\t\t" + employeeTotalTax);
                output.println("Employee No-Tax Earnings:\t\t" + (employeeTotalSales - employeeTotalTax));
                output.println("Total Items Sold:\t\t" + employeeTotalItemsSold);
                output.println("Total Returns:\t\t" + employeeTotalReturns);
                output.println("Total Refunds:\t\t" + employeeTotalRefunds);
                output.println("\n\n");
            }

            output.println("\t\t\tSector Overall Summary\n");
            output.println("-".repeat(50));
            output.println("Total Sales:\t\t" + sectorTotalSales);
            output.println("Total Tax Paid:\t\t" + sectorTotalTax);
            output.println("Sector No-Tax Earnings:\t\t" + (sectorTotalSales - sectorTotalTax));
            output.println("Total Items Sold:\t\t" + sectorTotalItemsSold);
            output.println("Total Returns:\t\t" + sectorTotalReturns);
            output.println("Total Refunds:\t\t" + sectorTotalRefunds);
            output.println("\n\n");
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Report file not found: " + reportFile.getPath());
        } catch (IOException ex) {
            throw new IOException("Error writing to report file: " + reportFile.getPath());
        }
    }

    public static String createSectorReportFilePath(SectorType sector, LocalDate startDate, LocalDate endDate){
        String path = "src/Files/Reports/";
        path += getSectorReportName(sector,startDate,endDate).replace(" ","_")+".txt";
        return path;
    }

    public static String getSectorReportName(SectorType sector, LocalDate startDate, LocalDate endDate){
        return "Report Of Sector "+sector.toString() +" StartDate "+startDate.toString()+" EndDate "+endDate.toString();
    }

    public static void generateCashierReport(File reportFile,Cashier cashier,LocalDate startDate, LocalDate endDate) throws IOException {
        try(PrintWriter output=new PrintWriter(reportFile)){
            output.println("\t\t\t\t\tCashier Report\n");
            output.println("-".repeat(40));
            output.println("Cashier ID:\t\t" + cashier.getId());
            output.println("Cashier Full Name:\t\t" + cashier.getFirstName() + " " + cashier.getLastName());
            output.println("Number of Shifts:\t\t" + cashier.getShifts().size());
            output.println("-".repeat(40));
            output.println("");

            double totalSales = 0, totalTax = 0;
            int totalItemsSold = 0, totalReturns = 0, totalRefunds = 0;

            for(Shift shift: cashier.getShifts()) {
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
    public static String createCashierReportFilePath(Cashier cashier, LocalDate startDate, LocalDate endDate){
        String path = "src/Files/Reports/";
        path += getCashierReportName(cashier,startDate,endDate).replace(" ","_")+".txt";
        return path;
    }

    public static String getCashierReportName(Cashier cashier, LocalDate startDate, LocalDate endDate){
        return "Report Of Cashier "+cashier.getId()+" StartDate "+startDate.toString()+" EndDate "+endDate.toString();
    }

    //Overall Report
    public void writeOverallReport(File reportFile,  ArrayList<SectorType> sectors, ArrayList<Cashier> cashiers ,LocalDate startDate, LocalDate endDate) throws IOException {
        try (PrintWriter output = new PrintWriter(reportFile)) {
            output.println("\t\t\t\t\tOverall Business Report\n");
            output.println("-".repeat(60));
            output.println("Report Period: " + startDate + " to " + endDate);
            output.println("Total Sectors: " + sectors.size());
            output.println("-".repeat(60));
            output.println("");

            double overallTotalSales = 0, overallTotalTax = 0;
            int overallTotalItemsSold = 0, overallTotalReturns = 0, overallTotalRefunds = 0;

            for (SectorType sector : sectors) {
                output.println("\t\t\tSector: " + sector.toString());
                output.println("-".repeat(50));
                output.println("");

                double sectorTotalSales = 0, sectorTotalTax = 0;
                int sectorTotalItemsSold = 0, sectorTotalReturns = 0, sectorTotalRefunds = 0;

                for (Cashier cashier : getSectorCashiers(sector,cashiers)) {
                    output.println("Cashier ID:\t\t" + cashier.getId());
                    output.println("Cashier Name:\t\t" + cashier.getFirstName() + " " + cashier.getLastName());
                    output.println("");

                    double cashierTotalSales = 0, cashierTotalTax = 0;
                    int cashierTotalItemsSold = 0, cashierTotalReturns = 0, cashierTotalRefunds = 0;

                    for (Shift shift : cashier.getShifts()) {
                        if ((shift.getShiftDate().isAfter(startDate) && shift.getShiftDate().isBefore(endDate))
                                || shift.getShiftDate().isEqual(startDate)
                                || shift.getShiftDate().isEqual(endDate)) {

                            double shiftSales = 0, shiftTax = 0;

                            for (Bill bill : shift.getBills()) {
                                for (ItemBought item : bill.getItemBought()) {
                                    shiftSales += item.getTotalPrice();
                                    shiftTax += item.getTotalTax();
                                }
                            }

                            cashierTotalSales += shiftSales;
                            cashierTotalTax += shiftTax;
                            cashierTotalItemsSold += shift.getNrOfItemsSold();
                            cashierTotalReturns += shift.getNrOfReturns();
                            cashierTotalRefunds += shift.getNrOfRefunds();
                        }
                    }

                    //Add to total of each sector
                    sectorTotalSales += cashierTotalSales;
                    sectorTotalTax += cashierTotalTax;
                    sectorTotalItemsSold += cashierTotalItemsSold;
                    sectorTotalReturns += cashierTotalReturns;
                    sectorTotalRefunds += cashierTotalRefunds;

                    output.println("Total Sales for Cashier:\t\t" + cashierTotalSales);
                    output.println("Total Tax Paid:\t\t" + cashierTotalTax);
                    output.println("Total No-Tax Earnings:\t\t" + (cashierTotalSales - cashierTotalTax));
                    output.println("Items Sold:\t\t" + cashierTotalItemsSold);
                    output.println("Items Returned:\t\t" + cashierTotalReturns);
                    output.println("Items Refunded:\t\t" + cashierTotalRefunds);
                    output.println("-".repeat(40));
                    output.println("");
                }

                //Get all results for all sectors
                overallTotalSales += sectorTotalSales;
                overallTotalTax += sectorTotalTax;
                overallTotalItemsSold += sectorTotalItemsSold;
                overallTotalReturns += sectorTotalReturns;
                overallTotalRefunds += sectorTotalRefunds;

                output.println("\t\tSector Summary for: " + sector.toString());
                output.println("-".repeat(50));
                output.println("Total Sales:\t\t" + sectorTotalSales);
                output.println("Total Tax Paid:\t\t" + sectorTotalTax);
                output.println("Total No-Tax Earnings:\t\t" + (sectorTotalSales - sectorTotalTax));
                output.println("Total Items Sold:\t\t" + sectorTotalItemsSold);
                output.println("Total Returns:\t\t" + sectorTotalReturns);
                output.println("Total Refunds:\t\t" + sectorTotalRefunds);
                output.println("\n");
            }

            //Overall report
            output.println("\t\t\tOverall Summary\n");
            output.println("-".repeat(60));
            output.println("Overall Total Sales:\t\t" + overallTotalSales);
            output.println("Overall Total Tax Paid:\t\t" + overallTotalTax);
            output.println("Overall No-Tax Earnings:\t\t" + (overallTotalSales - overallTotalTax));
            output.println("Overall Items Sold:\t\t" + overallTotalItemsSold);
            output.println("Overall Returns:\t\t" + overallTotalReturns);
            output.println("Overall Refunds:\t\t" + overallTotalRefunds);
            output.println("\n");
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Report file not found: " + reportFile.getPath());
        } catch (IOException ex) {
            throw new IOException("Error writing to report file: " + reportFile.getPath());
        }
    }

    public static ArrayList<Cashier> getSectorCashiers(SectorType sectorType, ArrayList<Cashier> cashiers){
        ArrayList<Cashier> result=new ArrayList<>();
        for(Cashier cashier: cashiers){
            if(cashier.getSector()==sectorType) result.add(cashier);
        }
        return result;
    }

    public static String createOverallReportFilePath(Cashier cashier, LocalDate startDate, LocalDate endDate){
        String path = "src/Files/Reports/";
        path += getOverallReportName(startDate,endDate).replace(" ","_")+".txt";
        return path;
    }

    public static String getOverallReportName(LocalDate startDate, LocalDate endDate){
        return "Overall report StartDate "+startDate.toString()+" EndDate "+endDate.toString();
    }
}
