package Model;

import java.util.ArrayList;

public class Database {
    private static Database database=new Database();
    private ArrayList<User> users;
    private ArrayList<Item> inventory;
    private ArrayList<Shift> completedShifts;
    private ArrayList<String> customers;
    private ArrayList<Integer> loyaltyPoints;
    private ArrayList<SectorType> sectors;
    private ArrayList<Cashier> cashiers;
    private Database(){
        this.inventory=FileHandler.getItemsOfInventory();
        //this.completedShifts=FileHandler.getCompletedShifts();
        this.customers=FileHandler.getCustomers();
        this.loyaltyPoints=FileHandler.getLoyaltyPoints();
        this.cashiers=FileHandler.getCashiers();
        setSectors();
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

    public ArrayList<Shift> getCompletedShifts() {
        return completedShifts;
    }

    public ArrayList<String> getCustomers() {
        return customers;
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
}
