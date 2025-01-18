package Control;

import Model.*;
import View.PerformanceReportView.CustomReportView;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomReportController implements Alertable{
    private CustomReportView view=new CustomReportView();
    private ArrayList<Cashier> cashiers=Database.getDatabase().getCashiers();
    public CustomReportController(){
        setUpGenerateReportButtonListener();
    }

    public void setUpGenerateReportButtonListener(){
        view.getGenerate().setOnAction(
                e->{
                    try{
                        //selectReportTypeAndGenerate();
                        generateReport();
                    }
                    catch(IOException ex){
                        showAlert(Alert.AlertType.ERROR,"Error!","Report could not be generated due to file handling issues!");
                    }
                }
        );
    }

    public void selectReportTypeAndGenerate() throws IOException {
        // Retrieve input values from the view
        String cashierText = view.getCashier().getText();
        Cashier cashier = validateCashierExistence(cashierText);
        String sector = view.getSector().getSelectionModel().getSelectedItem();
        LocalDate startDate = view.getStartDate().getValue();
        LocalDate endDate = view.getEndDate().getValue();

        // Date Validation
        if (startDate == null || endDate == null || !isValidDateRange(startDate, endDate)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Date Range",
                    "Start date must be before end date and not in the future!");
            return;
        }

        // Cashier Validation
        if (cashierText.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Cashier Field Blank",
                    "Please enter a valid cashier.");
            return;
        }

        if (cashier != null && sector == null) {
            if (cashier.getSector() != null) {
                sector = cashier.getSector().toString();
            } else {
                showAlert(Alert.AlertType.WARNING, "Cashier Sector Missing",
                        "The selected cashier does not have a sector assigned.");
                return;
            }
        }

        // Choose between 3 methods
        // Generate report for a specific cashier
        if (cashier != null) {
            if (sector != null && sector.equals(cashier.getSector().toString())) {
                ReportGenerator.generateCashierReport(cashier, startDate, endDate);
            } else {
                showAlert(Alert.AlertType.WARNING, "Cashier Sector Mismatch",
                        "The selected sector does not match the cashier's sector.\n"
                                + "The report will be generated for the selected cashier.");
            }
        } else {
            // Case: Generate overall or sector-specific report
            handleSectorOrOverallReport(sector, startDate, endDate);
        }
    }

    public void generateReport() throws IOException{
        String cashierText = view.getCashier().getText();
        String sector = view.getSector().getSelectionModel().getSelectedItem();
        LocalDate startDate = view.getStartDate().getValue();
        LocalDate endDate = view.getEndDate().getValue();

        if(cashierText.isBlank() && sector.isBlank() && startDate==null && endDate==null){
            showAlert(Alert.AlertType.WARNING ,"No data entered!","Please fill report fields!");
            return;
        }
        if(!cashierText.isBlank() && sector==null && startDate!=null && endDate!=null){
            Cashier cashier=validateCashierExistence(cashierText);
            if(cashier!=null){
                if(isValidDateRange(startDate,endDate)){
                    ReportGenerator.generateCashierReport(cashier,startDate,endDate);
                }
            }else{
                showAlert(Alert.AlertType.ERROR,"Incorret cashier selected!","The cashier you selected does not exist!");
                return;
            }
        }

    }

    //Date Validation
    private boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate) && !startDate.isAfter(LocalDate.now());
    }

    //Choose between sector or overall
    private void handleSectorOrOverallReport(String sector, LocalDate startDate, LocalDate endDate) throws IOException {
        if (sector == null || sector.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Sector Not Selected",
                    "Please select a valid sector or 'Overall Report' option.");
            return;
        }

        if (sector.equalsIgnoreCase("Overall report(All cashiers selected)")) {
            //Generate overall report
            ReportGenerator.writeOverallReport(startDate, endDate);
        } else {
            //Generate sector-specific report
            SectorType sectorType = getSectorType(sector);
            if (sectorType != null) {
                ReportGenerator.writeSectorReport(sectorType, startDate, endDate);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error",
                        "Invalid sector selection. Please try again.");
            }
        }
    }



    public Cashier validateCashierExistence(String id){
        for(Cashier cashier: cashiers){
                if (cashier.getId() == Integer.parseInt(id)) {
                    return cashier;
            }
        }
        return null;
    }
    public SectorType getSectorType(String sector){
        for(SectorType sectorType: Database.getDatabase().getSectors()){
            if(sectorType.toString().equals(sector)){
                return sectorType;
            }
        }
        return null;
    }
    public CustomReportView getView() {
        return view;
    }
}
