package Database;

import Model.*;

import java.io.File;
import java.util.ArrayList;


public class Database {
    private static Database database=new Database();
    private ArrayList<User> users=new ArrayList<>();
    private ArrayList<Item> inventory;
    private ArrayList<Shift> completedShifts;
    private ArrayList<String> customers;
    private ArrayList<Integer> loyaltyPoints;
    private ArrayList<SectorType> sectors;
    private ArrayList<Cashier> cashiers;
    private ArrayList<Manager> managers;
    private ArrayList<Administrator> administrators;
    private ArrayList<RestockTransaction> transactions;
    private ArrayList<Supplier>suppliers;
    private Database(){
        this.inventory=FileHandler.getItemsOfInventory();
        this.customers=FileHandler.getCustomers();
        this.loyaltyPoints=FileHandler.getLoyaltyPoints();
        this.cashiers=FileHandler.getCashiers();
        this.suppliers= FileHandler.getSuppliersFromFile();
        this.managers=FileHandler.getManagers();
        this.administrators=FileHandler.getAdministrators();
        this.transactions=FileHandler.getRestockTransaction();
        this.users.addAll(this.cashiers);
        this.users.addAll(this.managers);
        this.users.addAll(this.administrators);
        setSectors();
    }
    public void updateInventory(ArrayList<Item> inventory){
        FileHandler.updateInventory(inventory);
    }
    public void updateSupplier(ArrayList<Supplier> suppliers){
        FileHandler.updateSupplierInFile(suppliers);
    }

    public void updateCashiers(ArrayList<Cashier> cashiers){
        FileHandler.updateCashiers(cashiers);
    }
    public void updateUsers(ArrayList<User> users){
        FileHandler.updateUsers(users);
    }
    public void updateCustomers(ArrayList<String> customers){
        FileHandler.writeCustomersToFile(customers);
    }

    public void updateLoyaltyPoints(ArrayList<Integer> loyaltyPoints){
        FileHandler.writeLoyaltyPointsToFile(loyaltyPoints);
    }

    public void updateManagers(ArrayList<Manager> manager){
        FileHandler.updateManagers(manager);
    }
    public void updateAdministrators(ArrayList<Administrator> administrators){
        FileHandler.updateAdministrators(administrators);
    }
    public static Database getDatabase(){
        return Database.database;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }

    public ArrayList<Shift> getCompletedShifts() {
        return completedShifts;
    }

    public ArrayList<String> getCustomers() {
        return customers;
    }

    public ArrayList<RestockTransaction> getRestockTransaction(){
        return transactions;
    }

    public void setUsers(ArrayList<User> users) {

    }

    public void setInventory(ArrayList<Item> inventory) {

    }

    public void setCompletedShifts(ArrayList<Shift> completedShifts) {

    }

    public void setCustomers(ArrayList<String> customers) {

    }

    public void setLoyaltyPoints(ArrayList<Integer> loyaltyPoints) {

    }

    public ArrayList<Integer> getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setSectors(){
        sectors=new ArrayList<>();
        sectors.add(SectorType.ACCESSORIES);
        sectors.add(SectorType.CAMERAS);
        sectors.add(SectorType.SMART_HOME);
        sectors.add(SectorType.HOME_APPLIANCES);
        sectors.add(SectorType.MOBILE_DEVICES);
        sectors.add(SectorType.GAMING);
        sectors.add(SectorType.KITCHEN_ELECTRONICS);
        sectors.add(SectorType.COMPUTERS);
        sectors.add(SectorType.ELECTRONICS);
    }

    public ArrayList<SectorType> getSectors() {
        return sectors;
    }

    public ArrayList<Cashier> getCashiers() {
        return cashiers;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public ArrayList<RestockTransaction> getTransactions() {
        return transactions;
    }

    //Write products
    public void saveProduct(Item item){
        FileHandler.writeProductToFile(item);
        getInventory().add(item);
    }
    public void saveCashier(Cashier cashier){
        FileHandler.writeCashierToFile(cashier);
        getCashiers().add(cashier);
    }
    public void saveManager(Manager manager){
        FileHandler.writeManagerToFile(manager);
        getManagers().add(manager);
    }
    public void saveAdministrator(Administrator admin){
        FileHandler.writeAdministratorsToFile(admin);
        getAdministrators().add(admin);
    }
    public void saveSupplier(Supplier supplier){
        FileHandler.writeSupplierToFile(supplier);
        getSuppliers().add(supplier);
    }
    public void saveRestockTransaction(RestockTransaction restockTransaction){
        FileHandler.writeTransactionToFile(restockTransaction);
        getRestockTransaction().add(restockTransaction);
    }
}
