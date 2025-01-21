package Database;

import Model.*;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private final static File PRODUCT_FILE=new File("src/Database/Files/DAO/items.dat");
    private final static File SUPPLIERS_FILE=new File("src/Database/Files/DAO/suppliers.dat");
    private final static File USERS_FILE=new File("src/Database/Files/DAO/users.dat");
    private final static File CUSTOMERS_FILE=new File("src/Database/Files/DAO/customers.dat");
    private final static String BILLS_DIRECTORY=new String("src/Database/Files/Bills");
    private final static File LOYALTY_POINTS=new File("src/Database/Files/DAO/loyaltyPoints.dat");
    private final static File CASHIERS_FILE=new File("src/Database/Files/DAO/cashiers.dat");
    private final static File MANAGERS_FILE=new File("src/Database/Files/DAO/manager.dat");
    private final static File ADMINISTRATOR_FILE=new File("src/Database/Files/DAO/administrator.dat");
    private final static File TRANSACTION_FILE=new File("src/Database/Files/DAO/restockTransaction.dat");

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
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            while (true) {
                try {
                    Object obj = (User) reader.readObject();
                    if (obj instanceof User) {
                        users.add((User) obj);
                    } else {
                        System.err.println("Warning: Found a non-User object in the file.");
                    }
                } catch (EOFException eof) {
                    System.out.println("EOF reached!");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: Unable to find a class definition. " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error: Unable to read from file. " + ex.getMessage());
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

    //Managers
    public static ArrayList<Manager> getManagers() {
        ArrayList<Manager> managers=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(MANAGERS_FILE))){
            Manager manager;
            while(true){
                manager=(Manager) reader.readObject();
                managers.add(manager);
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
        return managers;
    }

    public static boolean updateManagers(ArrayList<Manager> managers){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(MANAGERS_FILE))) {
            for (Manager manager : managers) {
                outputStream.writeObject(manager);
            }//Do ti shkruash nga e para
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean writeManagerToFile(Manager manager){
        try(FileOutputStream outputStream=new FileOutputStream(MANAGERS_FILE,true)){
            ObjectOutputStream writer;
            if(CASHIERS_FILE.length()>0){
                writer=new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer=new ObjectOutputStream(outputStream);
            }
            writer.writeObject(manager);
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }

    //Administrators
    public static ArrayList<Administrator> getAdministrators() {
        ArrayList<Administrator> administrators=new ArrayList<>();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(ADMINISTRATOR_FILE))){
            Administrator administrator;
            while(true){
                administrator=(Administrator) reader.readObject();
                administrators.add(administrator);
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
        return administrators;
    }

    public static boolean updateAdministrators(ArrayList<Administrator> administrators){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ADMINISTRATOR_FILE))) {
            for (Administrator administrator : administrators) {
                outputStream.writeObject(administrator);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean writeAdministratorsToFile(Administrator administrator){
        try(FileOutputStream outputStream=new FileOutputStream(ADMINISTRATOR_FILE,true)){
            ObjectOutputStream writer;
            if(ADMINISTRATOR_FILE.length()>0){
                writer=new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer=new ObjectOutputStream(outputStream);
            }
            writer.writeObject(administrator);
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

    public static boolean writeTransactionToFile(RestockTransaction restock) {
        try (FileOutputStream outputStream = new FileOutputStream(TRANSACTION_FILE, true)) {
            ObjectOutputStream writer;
            if (TRANSACTION_FILE.length()> 0) {
                writer = new HeaderlessObjectOutputStream(outputStream);
            } else {
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(restock);
            writer.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error writing product to file " + ex.getLocalizedMessage());
        }
        return false;
    }

    public static ArrayList<RestockTransaction> getRestockTransaction(){
        ArrayList<RestockTransaction> inventory=new ArrayList();
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(TRANSACTION_FILE))){
            RestockTransaction restock;
            while(true){
                restock=(RestockTransaction) reader.readObject();
                inventory.add(restock);
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

    public static boolean updateRestockTransactions(ArrayList<RestockTransaction> transactions) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))) {
            for (RestockTransaction restock : transactions) {
                outputStream.writeObject(restock);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
        }
    public static boolean updateSupplierInFile(ArrayList<Supplier> suppliers) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SUPPLIERS_FILE))) {
            for (Supplier supplier :suppliers) {
                outputStream.writeObject(supplier);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean writeSupplierToFile(Supplier supplier) {
        try (FileOutputStream outputStream = new FileOutputStream(FileHandler.SUPPLIERS_FILE, true)) {
            ObjectOutputStream writer;
            if (FileHandler.SUPPLIERS_FILE.length() > 0) {
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
//    public static void  deleteSupplier(ObservableList<Supplier> selectedSupplier) {
//        ArrayList<Supplier> suppliers = getSuppliersFromFile(); // Load existing data
//
//        // Remove selected items
//        suppliers.removeAll(selectedSupplier);
//
//        // Overwrite the file with updated data
//        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))) {
//            for (Supplier supplier : suppliers) {
//                writer.writeObject(supplier);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
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

    }
