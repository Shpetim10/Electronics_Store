package MainRoot;

import Control.LogInController;

import Control.UserMainController;
import Database.Database;
import Database.FileHandler;
import Model.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MainUser extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
//        int id = 1;
//        String firstName = "Shpetim";
//        String lastName = "Shabanaj";
//        String username = "Shpetim10";
//        String password = "password123";
//        String email = "shabanajshpetim@gmail.com";
//        String phoneNumber = "0675466333";
//        LocalDate dateEmployed = LocalDate.now();
//        EmployeeRole role = EmployeeRole.CASHIER;
//        ArrayList<Permission> permissions = new ArrayList<>();
//        boolean isActive = true;
//        Cashier cashier = new Cashier(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role, permissions, isActive);
//        cashier.setSector(SectorType.ELECTRONICS);
//
//        Shift activeShift = new Shift(1, cashier, LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(17, 0));
//        cashier.getShifts().add(activeShift);
//        activeShift.startShift();
//
//        cashier.getPermissions().add(Permission.BILLING_SYSTEM);
//        cashier.getPermissions().add(Permission.REPORT_GENERATOR);
//        cashier.getPermissions().add(Permission.VIEW_ALL_BILLS);
//        cashier.getPermissions().add(Permission.VIEW_ALL_REPORTS);
//        FileHandler.writeCashierToFile(cashier);
        Cashier cashier= Database.getDatabase().getCashiers().get(0);
        LogInController logIn=new LogInController();
        UserMainController controller=new UserMainController(cashier);

        Scene scene=new Scene(controller.getView());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Electronics System");

        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
