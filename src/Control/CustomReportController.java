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
                    }
                    catch(IOException ex){
                        showAlert(Alert.AlertType.ERROR,"Error!","Report could not be generated due to file handling issues!");
                    }
                }
        );
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
        } else if(!cashierText.isBlank() && sector!=null && startDate!=null && endDate!=null) {
            Cashier cashier=validateCashierExistence(cashierText);
            if(cashier!=null && sector.toString().equals(cashier.getSector().toString()) && isValidDateRange(startDate,endDate)){
                ReportGenerator.generateCashierReport(cashier,startDate,endDate);
            }
            else{
                showAlert(Alert.AlertType.WARNING,"Sector Mismatch!","The selected cashier is not part of selected sector!");
            }
        }
        else if(cashierText.length()==0 && sector!=null && startDate!=null && endDate!=null){
            if(sector.equalsIgnoreCase("Overall report(All cashiers selected)")){
                ReportGenerator.writeOverallReport(startDate,endDate);
            }
            else{
                SectorType sectorType=getSectorType(sector.toString());
                ReportGenerator.writeSectorReport(sectorType,startDate,endDate);
            }
        }
        else{
            showAlert(Alert.AlertType.WARNING,"Wrong input!","You provided wrong Input!");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION,"Information!","Report generated successfully!");
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
