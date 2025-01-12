package Model;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileHandler {
    private final static File ITEMS_FILE=new File("src/Files/Data/items.dat");
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
        File BILLS_FILE=new File(BILLS_DIRECTORY+"/Cashier"+cashier.getId()+"/Shift"+shift.getShiftDate().getDay()+"_"+shift.getShiftDate().getMonth()+"_"+shift.getShiftDate().getYear()+"_Bills.dat");
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
        File BILLS_FILE=new File(BILLS_DIRECTORY+"/Cashier"+cashier.getId()+"/Shift"+shift.getShiftDate().getDay()+"_"+shift.getShiftDate().getMonth()+"_"+shift.getShiftDate().getYear()+"_Bills.dat");
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
}
