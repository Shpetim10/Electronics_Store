package MainRoot;

import Model.*;
import Control.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Cashier cashier=new Cashier(2, "John","Doe", "johndoe",  "password123", "john.doe@example.com", "1234", new Date(), EmployeeRole.CASHIER, new ArrayList<>(), true);
        Shift shift=new Shift(1,cashier,LocalDate.now(), LocalTime.now(),LocalTime.now());
        shift.startShift();
        cashier.getShifts().add(shift);
        System.out.println(cashier.getActiveShift());
        BillingSystemController control=new BillingSystemController(cashier);

        //control.generateBill();
    }
}
