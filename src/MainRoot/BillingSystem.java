package MainRoot;

import Control.BillingSystemController;
import Model.*;
import View.Design;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class BillingSystem extends Application implements Design {
    public static void main(String[] args) {
       Application.launch();
    }

    @Override
    public void start(Stage primaryStage){
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String username = "jdoe";
        String password = "password123";
        String email = "jdoe@example.com";
        String phoneNumber = "123-456-7890";
        LocalDate dateEmployed = LocalDate.now();
        EmployeeRole role = EmployeeRole.CASHIER;
        ArrayList<Permission> permissions = new ArrayList<>();
        boolean isActive = true;
        Cashier cashier = new Cashier(id, firstName, lastName, username, password, email, phoneNumber, dateEmployed, role, permissions, isActive);
        Shift shift=new Shift(1,cashier, LocalDate.now(), LocalTime.now(),LocalTime.now());
        shift.startShift();
        cashier.getShifts().add(shift);

        BillingSystemController controller=new BillingSystemController(cashier);
        primaryStage.setTitle("Billing System");
        primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
