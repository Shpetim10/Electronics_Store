package Model;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.w3c.dom.ls.LSOutput;

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



    public FileHandler(){

    }

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

    public static ArrayList<Bill> getBillsByCashierAndShft(Cashier cashier, Shift shift){
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
    public static boolean writeSupplierToFile(Supplier supplier) {
        try (FileOutputStream outputStream = new FileOutputStream(SUPPLIERS_FILE, true)) {
            ObjectOutputStream writer;
            if (SUPPLIERS_FILE.length() > 0) {
                writer = new HeaderlessObjectOutputStream(outputStream);
            } else {
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(supplier);
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
    public static ArrayList<Supplier> getSuppliersFromFile(){
        ArrayList<Supplier> inventory=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(SUPPLIERS_FILE))){
            Supplier supplier;
            while(true){
                supplier=(Supplier)reader.readObject();
                inventory.add(supplier);
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

    public static void  deleteFromFile(ObservableList<Item> selectedItems) {
        ArrayList<Item> inventory = getItemsOfInventory(); // Load existing data

        // Remove selected items
        inventory.removeAll(selectedItems);

        // Overwrite the file with updated data
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))) {
            for (Item item : inventory) {
                writer.writeObject(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // Update the existing Item in the file
    public static void updateItemInFile(Item updatedItem) {
        ArrayList<Item> inventory = getItemsOfInventory();  // Get current items from file

        // Find the item to update and replace it
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getProductId() == updatedItem.getProductId()) {
                inventory.set(i, updatedItem);  // Update the item
                break;
            }
        }

        // Write the updated inventory back to the file
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))) {
            for (Item item : inventory) {
                writer.writeObject(item);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static void updateSupplierInFile(Supplier updatedSupplier) {
        ArrayList<Supplier> suppliers = getSuppliersFromFile();  // Get current suppliers from file

        // Find the supplier to update and replace it
        for (int i = 0; i < suppliers.size(); i++) {
            if (suppliers.get(i).getSupplierId() == updatedSupplier.getSupplierId()) {
                suppliers.set(i, updatedSupplier);  // Update the supplier
                break;
            }
        }

        // Write the updated suppliers back to the file
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(SUPPLIERS_FILE))) {
            for (Supplier supplier : suppliers) {
                writer.writeObject(supplier);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static void  deleteSupplier(ObservableList<Supplier> selectedSupplier) {
        ArrayList<Supplier> suppliers = getSuppliersFromFile(); // Load existing data

        // Remove selected items
        suppliers.removeAll(selectedSupplier);

        // Overwrite the file with updated data
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))) {
            for (Supplier supplier : suppliers) {
                writer.writeObject(supplier);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
