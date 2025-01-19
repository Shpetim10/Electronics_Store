package Model;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileHandler {
    private final static File PRODUCT_FILE=new File("src/Files/Data/items.dat");
    private final static File SUPPLIERS_FILE=new File("src/Files/Data/suppliers.dat");
    private final static File USERS_FILE=new File("src/Files/Data/users.dat");
    private final static File SHIFTS_FILE=new File("src/Files/Data/shifts.dat");
    private final static File CUSTOMERS_FILE=new File("src/Files/Data/customers.dat");
    private final static String BILLS_DIRECTORY=new String("src/Files/Data/Bills");
    private final static File LOYALTY_POINTS=new File("src/Files/Data/loyaltyPoints.dat");
    private final static File CASHIERS_FILE=new File("src/Files/Data/cashiers.dat");
    public static ArrayList<Shift> getCompletedShifts(){
        ArrayList<Shift> shifts=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(SHIFTS_FILE))){
            Shift shift;
            while(true){
                shift=(Shift)reader.readObject();
                shifts.add(shift);
            }
        }
        catch(EOFException ex1){
            System.out.println("Reached end of file!");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return shifts;
    }
    public static boolean writeShiftToFile(Shift shift){
        try(FileOutputStream outputStream=new FileOutputStream(SHIFTS_FILE,true)){
            ObjectOutputStream writer;
            if(SHIFTS_FILE.length()>0){
                writer=new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer=new ObjectOutputStream(outputStream);
            }
            writer.writeObject(shift);
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }

    public static ArrayList<Bill> getBillsByCashierAndShift(Cashier cashier, Shift shift){
        ArrayList<Bill> bills=new ArrayList<>();
        File BILLS_FILE=new File(BILLS_DIRECTORY+"/Cashier"+cashier.getId()+"/Shift"+shift.getShiftDate().getDayOfMonth()+"_"+shift.getShiftDate().getMonth()+"_"+shift.getShiftDate().getYear()+"_Bills.dat");
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(BILLS_FILE))){
            Bill bill;
            while(true){
                bill=(Bill)reader.readObject();
                bills.add(bill);
            }
        }

        catch(EOFException ex){
            System.out.println("EOF reached");
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return bills;
    }
    public static boolean writeBillToFile(Cashier cashier,Shift shift,Bill bill){
        File BILLS_FILE=new File(BILLS_DIRECTORY+"/Cashier"+cashier.getId()+"/Shift"+shift.getShiftDate().getDayOfMonth()+"_"+shift.getShiftDate().getMonth()+"_"+shift.getShiftDate().getYear()+"_Bills.dat");
        try(FileOutputStream outputStream=new FileOutputStream(BILLS_FILE,true)){
            ObjectOutputStream writer;
            if(BILLS_FILE.length()>0){
                writer=new HeaderlessObjectOutputStream(outputStream);
            }
            else {
                writer=new ObjectOutputStream(outputStream);
            }
            writer.writeObject(bill);
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }

    public static boolean writeProductToFile(Item item) {
        try (FileOutputStream outputStream = new FileOutputStream(PRODUCT_FILE, true)) {
            ObjectOutputStream writer;
            if (PRODUCT_FILE.length() > 0) {
                writer = new HeaderlessObjectOutputStream(outputStream);
            } else {
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(item);
            writer.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error writing product to file: " + ex.getLocalizedMessage());
        }
        return false;
    }

    public static ArrayList<Item> getItemsOfInventory(){
    ArrayList<Item> inventory=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(PRODUCT_FILE))){
        Item item;
        while(true){
            item=(Item)reader.readObject();
            inventory.add(item);
        }
    }
        catch(EOFException ex1){
        System.out.println("Reached end of file!");
    }
        catch(ClassNotFoundException ex){
        ex.printStackTrace();
    }
        catch(IOException ex){
        ex.printStackTrace();
    }
        return inventory;
}

    public static boolean updateInventory(ArrayList<Item> inventory){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))) {
            for (Item item : inventory) {
                outputStream.writeObject(item);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static ArrayList<String> getCustomers() {
        ArrayList<String> customers=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(CUSTOMERS_FILE))){
            customers=(ArrayList<String>) reader.readObject();
        }
        catch(EOFException ex1){
            System.out.println("Reached end of file!");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return customers;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(USERS_FILE))){
            User user;
            Object o;
            while(true){
                o=reader.readObject();
                if(o instanceof Cashier){
                    user=(Cashier)o;
                }
                else if(o instanceof Manager){
                    user=(Manager)o;
                }
                else{
                    user=(Administrator)o;
                }

                users.add(user);
            }
        }
        catch(EOFException ex1){
            System.out.println("Reached end of file!");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return users;
    }

    public static boolean writeUserToFile(User user){
        try(FileOutputStream outputStream=new FileOutputStream(USERS_FILE,true)){
            ObjectOutputStream writer;
            if(USERS_FILE.length()>0){
                writer=new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer=new ObjectOutputStream(outputStream);
            }
            writer.writeObject(user);
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public static boolean updateUsers(ArrayList<User> users){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CASHIERS_FILE))) {
            for (User user : users) {
                outputStream.writeObject(user);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static ArrayList<Cashier> getCashiers() {
        ArrayList<Cashier> cashiers=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(CASHIERS_FILE))){
            Cashier cashier;
            while(true){
                cashier=(Cashier)reader.readObject();
                cashiers.add(cashier);
            }
        }
        catch(EOFException ex1){
            System.out.println("Reached end of file!");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return cashiers;
    }

    public static boolean updateCashiers(ArrayList<Cashier> cashiers){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CASHIERS_FILE))) {
            for (Cashier cashier : cashiers) {
                outputStream.writeObject(cashier);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean writeCashierToFile(Cashier cashier){
        try(FileOutputStream outputStream=new FileOutputStream(CASHIERS_FILE,true)){
            ObjectOutputStream writer;
            if(CASHIERS_FILE.length()>0){
                writer=new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer=new ObjectOutputStream(outputStream);
            }
            writer.writeObject(cashier);
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public static boolean writeCustomersToFile(ArrayList<String> customers){
        try (FileOutputStream outputStream = new FileOutputStream(CUSTOMERS_FILE, false)) {
            ObjectOutputStream writer=new ObjectOutputStream(outputStream);
            writer.writeObject(customers);
            writer.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error writing product to file: " + ex.getLocalizedMessage());
        }
        return false;
    }
    public static ArrayList<Integer> getLoyaltyPoints() {
        ArrayList<Integer> loyaltyPoints=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(LOYALTY_POINTS))){
            loyaltyPoints=(ArrayList<Integer>) reader.readObject();
        }
        catch(EOFException ex1){
            System.out.println("Reached end of file!");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return loyaltyPoints;
    }
    public static boolean writeLoyaltyPointsToFile(ArrayList<Integer> points){
        try (FileOutputStream outputStream = new FileOutputStream(LOYALTY_POINTS, false)) {
            ObjectOutputStream writer=new ObjectOutputStream(outputStream);
            writer.writeObject(points);
            writer.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error writing product to file: " + ex.getLocalizedMessage());
        }
        return false;
    }
}
