package Control;

import Model.Bill;
import Model.Cashier;
import View.PerformanceReportView.ViewAllBills;
import javafx.scene.control.Button;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ViewAllBillsController {
    private ViewAllBills view=new ViewAllBills();
    private Cashier cashier;

    public ViewAllBillsController(){
        setViewBillButtonListener();
    }
    public ViewAllBillsController(Cashier cashier){
        this.cashier=cashier;
        setViewBillButtonListener();
    }

    public void setViewBillButtonListener(){
        for(Bill bill: this.cashier.getActiveShift().getBills()){
            Button button=view.createLargeButton("Bill "+bill.getBillId());{
                button.setOnAction(
                        e->{
                            writeBillToArea(bill);
                        }
                );
            view.getButtonBox().getChildren().add(button);
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
            displayError("Bill file was not found!");
        } catch (IOException e) {
            displayError("Process failed!");
        }
    }
    public void displayError(String message){
        view.getErrorMessage().setText(message);
    }

    public ViewAllBills getView() {
        return view;
    }

    public Cashier getCashier() {
        return cashier;
    }
}
