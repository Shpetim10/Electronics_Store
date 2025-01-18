package MainRoot;

import Control.BillingSystemController;
import Control.UserMainController;
import Model.*;
import View.UserMainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MainUser extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String username = "jdoe";
        String password = "password123";
        String email = "jdoe@example.com";
        String phoneNumber = "123-456-7890";
        Date dateEmployed = new Date();
        EmployeeRole role = EmployeeRole.CASHIER;
        ArrayList<Permission> permissions = new ArrayList<>();
        boolean isActive = true;
        Cashier cashier = new Cashier(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role, permissions, isActive);
        cashier.setSector(SectorType.ELECTRONICS);

        Shift activeShift = new Shift(1, cashier, LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(17, 0));
        cashier.getShifts().add(activeShift);
        activeShift.startShift();

        // Create items bought
        ArrayList<ItemBought> items1 = new ArrayList<>();
        items1.add(new ItemBought(101, "MacBook", 12, 1600));
        items1.add(new ItemBought(102, "Iphone 16", 1, 1200));

        ArrayList<ItemBought> items2 = new ArrayList<>();
        items2.add(new ItemBought(103, "Fridge", 1, 1800));

        ArrayList<ItemBought> items3 = new ArrayList<>();
        items3.add(new ItemBought(104, "Scouter", 3, 3000));

        ArrayList<ItemBought> items4 = new ArrayList<>();
        items4.add(new ItemBought(105, "Headphones", 5, 120));

        ArrayList<ItemBought> items5 = new ArrayList<>();
        items5.add(new ItemBought(106, "Charger", 6, 30));

        // Create bills
        Bill bill1 = new Bill();
        bill1.setBillId(1); bill1.setCashier(cashier);
        bill1.setItemBought(items1);
        bill1.generateBill();

        Bill bill2 = new Bill();
        bill2.setBillId(2);
        bill2.setCashier(cashier);
        bill2.setItemBought(items2);
        bill2.generateBill();

        Bill bill3 = new Bill();
        bill3.setBillId(3);
        bill3.setCashier(cashier);
        bill3.setItemBought(items3);
        bill3.generateBill();

        Bill bill4 = new Bill(); bill4.setBillId(4);
        bill4.setCashier(cashier);
        bill4.setItemBought(items4);
        bill4.generateBill();

        Bill bill5 = new Bill();
        bill5.setBillId(5);
        bill5.setCashier(cashier);
        bill5.setItemBought(items5);
        bill5.generateBill();

        // Add bills to shift
        activeShift.getBills().add(bill1);
        activeShift.getBills().add(bill2);
        activeShift.getBills().add(bill3);
        activeShift.getBills().add(bill4);
        activeShift.getBills().add(bill5);

        cashier.getPermissions().add(Permission.BILLING_SYSTEM);
        cashier.getPermissions().add(Permission.REPORT_GENERATOR);
        cashier.getPermissions().add(Permission.VIEW_ALL_BILLS);
        FileHandler.writeCashierToFile(cashier);
        System.out.println(cashier);
        System.out.println(cashier.getShifts().size());
        System.out.println(cashier.getActiveShift().getBills().getFirst().getTotalOfBill());

        UserMainController controller=new UserMainController(cashier);

        primaryStage.setTitle("Electronics System");
        primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
