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

        //Methods for getting items of card


        //Create absolutePath of file
        String absolutePath="C:\\Users\\Shpëtim Shabanaj\\OneDrive\\Desktop\\OOP Project\\ElectronicsStore_ShpëtimShabanaj\\Electronics_Store\\src\\Files\\Bills";
        absolutePath+="Cashier"+cashier.getId(); //Add to folder of the current cashier
        absolutePath+="\\"+cashier.getActiveShift().getShiftId()+"\\";
        absolutePath+=bill.getBillId()+"_"+bill.getDateGenerated();
        File billFile=new File(absolutePath);
        billFile.mkdirs(); //Create directories if they do not exist

        try(PrintWriter output=new PrintWriter(new FileOutputStream(billFile,false))){
            output.println("\t\t\t\t\tElectronics Store");
            output.println("-".repeat(140)+"\n");
            output.println("Address:\t\tTirana, Albania\n");
            output.println("Date:\t\t"+bill.getDateGenerated());
            output.println("Time:\t\t"+bill.getTimeGenerated()+"\n\n");
            output.println("CashierId:\t\t"+bill.getCashierId());
            output.println("Customer Id:\t\t"+"\n\n");    //Update customer card id
            output.println("Customer Card Id:\t\t");      //Update
            output.println("\n\nFiscal Bill\n\n");
            output.printf("%15s%40s%15s%20s%15s%30s\n","Id","Product Name","Quantity","Price","Total Tax","Total Price");
            output.println("-".repeat(140)+"\n");
            for(ItemBought itemBought: bill.getItemBought()){
                output.printf("%15d%40s%15d%20lf%15lf%30lf\n",itemBought.getItem().getProductId(),itemBought.getItem().getProductName(),
                        itemBought.getItem().getSellingPrice(),itemBought.getTotalTaxRate(),itemBought.getTotalProductPrice());
            }
            output.println("-".repeat(140)+"\n\n");
            output.println(" ".repeat(50)+"Total of Bill:\t\t"+bill.getTotalOfBill());
            output.println(" ".repeat(50)+"Tax of Bill:\t\t"+bill.getTotalTaxOfBill());

            output.println("Thank You for shopping with us!");
            output.println("-".repeat(140));

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
