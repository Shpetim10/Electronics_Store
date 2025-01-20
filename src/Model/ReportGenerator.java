package Model;

import Database.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


public class ReportGenerator {
    public static void writeSectorReport(SectorType sector,LocalDate startDate, LocalDate endDate) throws IOException {
        File reportFile=new File(createSectorReportFilePath(sector,startDate,endDate));
        ArrayList<Cashier> cashiers= Database.getDatabase().getCashiers();
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
                int employeeTotalItemsSold = 0;

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
                                employeeTotalItemsSold+=item.getQuantity();
                            }
                        }

                        employeeTotalSales += shiftSales;
                        employeeTotalTax += shiftTax;

                        output.println("Shift Sales:\t\t" + shiftSales);
                        output.println("Shift Tax Paid:\t\t" + shiftTax);
                        output.println("Shift No-Tax Earnings:\t\t" + (shiftSales - shiftTax));
                        output.println("Items Sold:\t\t" + shift.getNrOfItemsSold());
                        output.println("-".repeat(40));
                        output.println("");
                    }
                }

                sectorTotalSales += employeeTotalSales;
                sectorTotalTax += employeeTotalTax;
                sectorTotalItemsSold += employeeTotalItemsSold;

                output.println("\t\t\tEmployee Summary\n");
                output.println("-".repeat(40));
                output.println("Total Sales:\t\t" + employeeTotalSales);
                output.println("Total Tax Paid:\t\t" + employeeTotalTax);
                output.println("Employee No-Tax Earnings:\t\t" + (employeeTotalSales - employeeTotalTax));
                output.println("Total Items Sold:\t\t" + employeeTotalItemsSold);
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
        String path = "src/Database/Files/Reports/";
        path += getSectorReportName(sector,startDate,endDate).replace(" ","_")+".txt";
        return path;
    }

    public static String getSectorReportName(SectorType sector, LocalDate startDate, LocalDate endDate){
        return "Report Of Sector "+sector.toString() +" StartDate "+startDate.toString()+" EndDate "+endDate.toString();
    }

    public static void generateCashierReport(Cashier cashier,LocalDate startDate, LocalDate endDate) throws IOException {
        File reportFile=new File(createCashierReportFilePath(cashier,startDate,endDate));
        try(PrintWriter output=new PrintWriter(reportFile)){
            output.println("\t\t\t\t\tCashier Report\n");
            output.println("-".repeat(40));
            output.println("Cashier ID:\t\t" + cashier.getId());
            output.println("Cashier Full Name:\t\t" + cashier.getFirstName() + " " + cashier.getLastName());
            output.println("Number of Shifts:\t\t" + cashier.getShifts().size());
            output.println("-".repeat(40));
            output.println("");

            double totalSales = 0, totalTax = 0;
            int totalItemsSold = 0;

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
                            totalItemsSold+=item.getQuantity();
                        }
                    }

                    totalSales += shiftSales;
                    totalTax += shiftTax;

                    output.println("Shift Sales:\t\t" + shiftSales);
                    output.println("Shift Tax Paid:\t\t" + shiftTax);
                    output.println("Shift No-Tax Earnings:\t\t" + (shiftSales-shiftTax));
                    output.println("Items Sold:\t\t" + shift.getNrOfItemsSold());
                    output.println("-".repeat(40));
                    output.println("");
                }
            }

            output.println("\t\t\tOverall Sales Summary\n");
            output.println("-".repeat(40));
            output.println("Total Sales:\t\t" + totalSales);
            output.println("Total Tax Paid:\t\t" + totalTax);
            output.println("Shift No-Tax Earnings:\t\t" + (totalSales-totalTax));
            output.println("Total Items Sold:\t\t" + totalItemsSold);
            output.println("\n\n");

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException();
        } catch (IOException ex) {
            throw new IOException();
        }
    }
    public static String createCashierReportFilePath(Cashier cashier, LocalDate startDate, LocalDate endDate){
        String path = "src/Database/Files/Reports/";
        path += getCashierReportName(cashier,startDate,endDate).replace(" ","_")+".txt";
        return path;
    }

    public static String getCashierReportName(Cashier cashier, LocalDate startDate, LocalDate endDate){
        return "Report Of Cashier "+cashier.getId()+" StartDate "+startDate.toString()+" EndDate "+endDate.toString();
    }

    //Overall Report
    public static void writeOverallReport(LocalDate startDate, LocalDate endDate) throws IOException {
        File reportFile = new File(createOverallReportFilePath(startDate, endDate));
        ArrayList<SectorType> sectors = Database.getDatabase().getSectors();
        ArrayList<Cashier> cashiers = Database.getDatabase().getCashiers();

        // Variables to track most sold item and highest revenue item
        String mostSoldItem = "None";
        int mostSoldQuantity = 0;
        String highestRevenueItem = "None";
        double highestRevenue = 0.0;

        try (PrintWriter output = new PrintWriter(reportFile)) {
            output.println("\t\t\t\t\tOverall Business Report\n");
            output.println("-".repeat(60));
            output.println("Report Period: " + startDate + " to " + endDate);
            output.println("Total Sectors: " + sectors.size());
            output.println("-".repeat(60));
            output.println("");

            double overallTotalSales = 0, overallTotalTax = 0;
            int overallTotalItemsSold = 0;

            for (SectorType sector : sectors) {
                output.println("\t\t\tSector: " + sector.toString());
                output.println("-".repeat(50));
                output.println("");

                double sectorTotalSales = 0, sectorTotalTax = 0;
                int sectorTotalItemsSold = 0;

                for (Cashier cashier : getSectorCashiers(sector, cashiers)) {
                    output.println("Cashier ID:\t\t" + cashier.getId());
                    output.println("Cashier Name:\t\t" + cashier.getFirstName() + " " + cashier.getLastName());
                    output.println("");

                    double cashierTotalSales = 0, cashierTotalTax = 0;
                    int cashierTotalItemsSold = 0;

                    for (Shift shift : cashier.getShifts()) {
                        if ((shift.getShiftDate().isAfter(startDate) && shift.getShiftDate().isBefore(endDate))
                                || shift.getShiftDate().isEqual(startDate)
                                || shift.getShiftDate().isEqual(endDate)) {

                            double shiftSales = 0, shiftTax = 0;

                            for (Bill bill : shift.getBills()) {
                                for (ItemBought item : bill.getItemBought()) {
                                    String itemName = item.getItem().getProductName();
                                    double totalPrice = item.getTotalPrice();
                                    int quantity = item.getQuantity();

                                    shiftSales += totalPrice;
                                    shiftTax += item.getTotalTax();
                                    cashierTotalItemsSold += quantity;

                                    // Update most sold item
                                    if (quantity > mostSoldQuantity) {
                                        mostSoldItem = itemName;
                                        mostSoldQuantity = quantity;
                                    }

                                    // Update highest revenue item
                                    if (totalPrice > highestRevenue) {
                                        highestRevenueItem = itemName;
                                        highestRevenue = totalPrice;
                                    }
                                }
                            }

                            cashierTotalSales += shiftSales;
                            cashierTotalTax += shiftTax;
                        }
                    }

                    // Add to total of each sector
                    sectorTotalSales += cashierTotalSales;
                    sectorTotalTax += cashierTotalTax;
                    sectorTotalItemsSold += cashierTotalItemsSold;

                    output.println("Total Sales for Cashier:\t\t" + cashierTotalSales);
                    output.println("Total Tax Paid:\t\t" + cashierTotalTax);
                    output.println("Total No-Tax Earnings:\t\t" + (cashierTotalSales - cashierTotalTax));
                    output.println("Items Sold:\t\t" + cashierTotalItemsSold);
                    output.println("-".repeat(40));
                    output.println("");
                }

                // Add sector totals to overall totals
                overallTotalSales += sectorTotalSales;
                overallTotalTax += sectorTotalTax;
                overallTotalItemsSold += sectorTotalItemsSold;

                output.println("\t\tSector Summary for: " + sector.toString());
                output.println("-".repeat(50));
                output.println("Total Sales:\t\t" + sectorTotalSales);
                output.println("Total Tax Paid:\t\t" + sectorTotalTax);
                output.println("Total No-Tax Earnings:\t\t" + (sectorTotalSales - sectorTotalTax));
                output.println("Total Items Sold:\t\t" + sectorTotalItemsSold);
                output.println("\n");
            }

            // Overall report
            output.println("\t\t\tOverall Sales Summary\n");
            output.println("-".repeat(60));
            output.println("Overall Total Sales:\t\t" + overallTotalSales);
            output.println("Overall Total Tax Paid:\t\t" + overallTotalTax);
            output.println("Overall No-Tax Earnings:\t\t" + (overallTotalSales - overallTotalTax));
            output.println("Overall Items Sold:\t\t" + overallTotalItemsSold);
            output.println("\n");

            output.println("\t\t\tOverall Cost Summary\n");
            output.println("-".repeat(60));
            double restockCost=0;
            for(RestockTransaction transaction: Database.getDatabase().getRestockTransaction()){
                if(transaction.getTransactionDate().isAfter(startDate) ||
                    transaction.getTransactionDate().isEqual(startDate) ||
                    transaction.getTransactionDate().isBefore(endDate) ||
                    transaction.getTransactionDate().isEqual(endDate)){
                    restockCost+=transaction.calculateTotalRestockPayment();
                }
            }
            output.println("");
            double staffCost=0;
            int numberOfMonths = Math.max(1, startDate.until(endDate).getDays() / 30);//exclude 0

            for (Cashier cashier : Database.getDatabase().getCashiers()) {
                double monthlySalary = cashier.getSalary();
                staffCost += monthlySalary * numberOfMonths;
            }
            for (Manager manager : Database.getDatabase().getManagers()) {
                double monthlySalary = manager.getSalary();
                staffCost += monthlySalary * numberOfMonths;
            }
            for (Administrator administrator : Database.getDatabase().getAdministrators()) {
                double monthlySalary = administrator.getSalary();
                staffCost += monthlySalary * numberOfMonths;
            }

            double overallCost=staffCost+restockCost;
            output.println("Estimated Restock Cost:\t\t"+restockCost);
            output.println("Estimated Staff Cost:\t\t"+staffCost);
            output.println("Estimated Total Cost:\t\t"+overallCost);
            output.println("Estimated Profit:\t\t"+(overallTotalSales-overallCost));

            // Statistics
            output.println("Most Sold Item:\t\t" + mostSoldItem + " (Quantity: " + mostSoldQuantity + ")");
            output.println("Highest Revenue Item:\t" + highestRevenueItem + " (Revenue: " + highestRevenue + ")");
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
            if(cashier.getSector()==sectorType)
                result.add(cashier);
        }
        return result;
    }

    public static String createOverallReportFilePath(LocalDate startDate, LocalDate endDate){
        String path = "src/Database/Files/Reports/";
        path += getOverallReportName(startDate,endDate).replace(" ","_")+".txt";
        return path;
    }
    public static String getOverallReportName(LocalDate startDate, LocalDate endDate){
        return "Overall report StartDate "+startDate.toString()+" EndDate "+endDate.toString();
    }
}
