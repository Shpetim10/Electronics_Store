package Control;

import Database.Database;
import Model.*;
import View.PerformanceReportView.CustomReportView;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class CustomReportController implements Alertable{
    private CustomReportView view=new CustomReportView();
    private User user;
    private File report;
    public CustomReportController(User user){
        this.user=user;
        setUpGenerateReportButtonListener();
        setUpClearButtonListener();
    }

    public void setUpClearButtonListener(){
        view.getClear().setOnAction(
                e->{
                    view.getCashier().clear();
                    view.getSector().getSelectionModel().clearSelection();
                    view.getStartDate().getEditor().clear();
                    view.getEndDate().getEditor().clear();
                }
        );
    }
    public void setUpGenerateReportButtonListener(){
        view.getGenerate().setOnAction(
                e->{
                    try{
                        generateReport();
                        if(user instanceof Manager && report!=null){
                            ((Manager)user).getReportsGenerated().add(report);
                            Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
                        } else if (user instanceof Administrator && report!=null) {
                            ((Administrator)user).getReportsGenerted().add(report);
                            Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
                        }

                    }
                    catch(IOException ex){
                        System.out.println(ex.getLocalizedMessage());
                        showAlert(Alert.AlertType.ERROR,"Error!","Report could not be generated due to file handling issues!");
                    }
                }
        );
    }

    public void generateReport() throws IOException {
        String cashierText = view.getCashier().getText();
        String sector = view.getSector().getSelectionModel().getSelectedItem();
        LocalDate startDate = view.getStartDate().getValue();
        LocalDate endDate = view.getEndDate().getValue();

        // Validate input fields
        if (!validateInputs(cashierText, sector, startDate, endDate)) return;

        // Handle reports based on input combinations
        if (!cashierText.isBlank() && sector == null) {
            handleCashierReport(cashierText, startDate, endDate);
        } else if (!cashierText.isBlank() && sector != null) {
            handleCashierSectorReport(cashierText, sector, startDate, endDate);
        } else if (cashierText.isBlank() && sector != null) {
            handleSectorReport(sector, startDate, endDate);
        } else {
            showAlert(Alert.AlertType.WARNING, "Wrong Input!", "You provided invalid input!");
        }
    }

    private boolean validateInputs(String cashierText, String sector, LocalDate startDate, LocalDate endDate) {
        if (cashierText.isBlank() && sector == null && startDate == null && endDate == null) {
            showAlert(Alert.AlertType.WARNING, "No Data Entered!", "Please fill out the report fields!");
            return false;
        }

        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Date Range!", "Start date cannot be after end date.");
            return false;
        }

        return true;
    }

    private void handleCashierReport(String cashierText, LocalDate startDate, LocalDate endDate) throws IOException {
        Cashier cashier = validateCashierExistence(cashierText);
        if (cashier == null) {
            showAlert(Alert.AlertType.ERROR, "Invalid Cashier!", "The selected cashier does not exist.");
            return;
        }

        if (isValidDateRange(startDate, endDate)) {
            report=ReportGenerator.generateCashierReport(cashier, startDate, endDate);
            showAlert(Alert.AlertType.INFORMATION, "Success!", "Cashier report generated successfully!");
        }
    }

    private void handleCashierSectorReport(String cashierText, String sector, LocalDate startDate, LocalDate endDate) throws IOException {
        Cashier cashier = validateCashierExistence(cashierText);
        if (cashier == null) {
            showAlert(Alert.AlertType.ERROR, "Invalid Cashier!", "The selected cashier does not exist.");
            return;
        }

        if (!sector.equalsIgnoreCase(cashier.getSector().toString())) {
            showAlert(Alert.AlertType.WARNING, "Sector Mismatch!", "The selected cashier does not belong to the specified sector.");
            return;
        }

        if (isValidDateRange(startDate, endDate)) {
            report=ReportGenerator.generateCashierReport(cashier, startDate, endDate);
            showAlert(Alert.AlertType.INFORMATION, "Success!", "Cashier sector report generated successfully!");
        }
    }

    private void handleSectorReport(String sector, LocalDate startDate, LocalDate endDate) throws IOException {
        if (sector.equalsIgnoreCase("Overall report(All cashiers selected)")) {
            ReportGenerator.writeOverallReport(startDate, endDate);
        } else {
            SectorType sectorType = getSectorType(sector);
            report=ReportGenerator.writeSectorReport(sectorType, startDate, endDate);
        }
        showAlert(Alert.AlertType.INFORMATION, "Success!", "Sector report generated successfully!");
    }

    //Date Validation
    private boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate) && !startDate.isAfter(LocalDate.now());
    }


    public Cashier validateCashierExistence(String id){
        for(Cashier user: Database.getDatabase().getCashiers()){
                if (user.getId() == Integer.parseInt(id)) {
                    return user;
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
