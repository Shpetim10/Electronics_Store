package Control;

import Model.*;
import View.PerformanceReportView.ViewAllBillsView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.*;
import java.util.Scanner;

public class ViewAllReportsController implements Alertable{
    private ViewAllBillsView view=new ViewAllBillsView();
    private User user;

    public ViewAllReportsController(){
        setViewReportsButtonListener();
    }
    public ViewAllReportsController(User user){
        this.user=user;
        setViewReportsButtonListener();
    }

    public void setViewReportsButtonListener(){
        if(user instanceof Manager){
            Manager manager=(Manager) user;
            managerReport(manager);
        }
        else{
            Administrator administrator=(Administrator) user;
            administratorReport(administrator);
        }
    }

    public void managerReport(Manager manager){
        for(File report: manager.getReportsGenerated()){
            Button button=view.createLargeButton(report.getName());{
                button.setOnAction(
                        e->{
                            writeReportToArea(report);
                        }
                );
                view.getButtonBox().getChildren().add(button);
            }
        }
    }
    public void administratorReport(Administrator administrator){
        for(File report: administrator.getReportsGenerted()){
            Button button=view.createLargeButton(report.getName());{
                button.setOnAction(
                        e->{
                            writeReportToArea(report);
                        }
                );
                view.getButtonBox().getChildren().add(button);
            }
        }
    }
    public void writeReportToArea(File report){
        try(FileInputStream reader=new FileInputStream(report);){
            Scanner scan=new Scanner(reader);
            StringBuilder billText=new StringBuilder();
            while(scan.hasNextLine()){
                billText.append(scan.nextLine()+"\n");
            }
            view.getDisplayBill().setText(billText.toString());
        } catch(EOFException ex){

        }
        catch (FileNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,"IO Error!","Bill file was not found!");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR,"IO Error!","IO Error just happened!");
        }
    }

    public ViewAllBillsView getView() {
        return view;
    }

    public User getUser() {
        return user;
    }
}
