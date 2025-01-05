package Control;

import View.BillingSystemView;
import Model.*;

import java.io.*;
import java.time.LocalTime;
import java.util.Date;

public class BillingSystemController {
    private BillingSystemView view;
    private Cashier cashier;
    public BillingSystemController(Cashier cashier){
        this.view=new BillingSystemView();
        this.cashier=cashier;
    }

    //Useful Methods
    public File generateBill(){
        if(cashier.getActiveShift()==null){
            view.getMessage().setText("There is no active shift to save the bill! Please contact administrator.");
            System.exit(1);  //Will clear page
        }

        //Create Bill object
        Bill bill=new Bill();
        bill.setBillId(cashier.getActiveShift().getBills().size()+1);
        bill.setCashierId(this.cashier.getId());
        bill.setDateGenerated(new Date());
        bill.setTimeGenerated(LocalTime.now());
        bill.setCustomerIdCard(1); //Will be gotten from text field
        bill.setPaymentMethod(PaymentMethod.CASH); // Will be gotten from combo box

        //Create absolutePath of file
        String absolutePath="C:\\Users\\Shpëtim Shabanaj\\OneDrive\\Desktop\\OOP Project\\ElectronicsStore_ShpëtimShabanaj\\Electronics_Store\\src\\Files\\Bills";
        absolutePath+="Cashier"+cashier.getId(); //Add to folder of the current cashier
        absolutePath+="\\"+cashier.getActiveShift().getShiftId()+"\\";
        absolutePath+=bill.getBillId()+"_"+bill.getDateGenerated();
        File billFile=new File(absolutePath);
        try(PrintWriter output=new PrintWriter(new FileOutputStream(billFile,false))){

        }
        catch(FileNotFoundException ex){
            view.getMessage().setText("File could not be generated! Please Report to Administrator.");
        }

        return billFile;
    }






    public BillingSystemView getView() {
        return view;
    }

    public void setView(BillingSystemView view) {
        this.view = view;
    }
}
