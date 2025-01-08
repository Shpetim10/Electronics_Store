package MainRoot;

import Model.*;
import Control.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Cashier cashier=new Cashier(2, "John","Doe", "johndoe",  "password123", "john.doe@example.com", "1234", new Date(), EmployeeRole.CASHIER, new ArrayList<>(), true);
        Shift shift=new Shift(1,cashier,new Date(), LocalTime.now(),LocalTime.now());
        shift.startShift();
        cashier.getShifts().add(shift);
        BillingSystemController control=new BillingSystemController(cashier);

        control.generateBill();
    }
}
