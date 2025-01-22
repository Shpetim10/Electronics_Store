package Control;

import Model.Administrator;
import Model.Bill;
import Model.Cashier;
import View.PerformanceReportView.ViewAllBillsView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ViewAllBillsController implements Alertable{
    private ViewAllBillsView view=new ViewAllBillsView();
    private Cashier cashier=null;

    public ViewAllBillsController(){

    }
    public ViewAllBillsController(Cashier cashier){
        this.cashier=cashier;
        setViewBillButtonListener();
    }

    public void setViewBillButtonListener(){
        if(this.cashier.getActiveShift()!=null){
            for (Bill bill : this.cashier.getActiveShift().getBills()) {
                Button button = view.createLargeButton("Bill " + bill.getBillId());
                {
                    button.setOnAction(
                            e -> {
                                writeBillToArea(bill);
                            }
                    );
                    view.getButtonBox().getChildren().add(button);
                }
            }
        }
    }

    public void writeBillToArea(Bill bill){
        try(FileInputStream reader=new FileInputStream(bill.getBillFile());){
            Scanner scan=new Scanner(reader);
            StringBuilder billText=new StringBuilder();
            while(scan.hasNextLine()){
                billText.append(scan.nextLine()+"\n");
            }
            view.getDisplayBill().setText(billText.toString());
        } catch(EOFException ex){

        }
        catch (FileNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,"File error!","Bill file was not found!");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR,"Error!","Process failed!");
        }
    }


    public ViewAllBillsView getView() {
        return view;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
        setViewBillButtonListener();
    }
}
