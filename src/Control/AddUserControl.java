package Control;

import Database.Database;
import Database.FileHandler;
import Model.*;
import View.UserManagementView.AddUser;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AddUserControl implements Alertable{
    private final AddUser view=new AddUser();

    public AddUserControl() {
        view.getAddBtn().setOnAction(e -> handleAddUser());
        setRoleChoiceListener();
    }

    public void handleAddUser() {
        try {
            validateInputs();

            int ID = Integer.parseInt(view.getIDTxt().getText());
            String name = view.getNameTxt().getText();
            String lastName = view.getLastNameTxt().getText();
            Gender gender = Gender.valueOf(view.getGenderSelection().getValue());
            LocalDate birthday =view.getBirthday().getValue();
            Double salary = Double.parseDouble(view.getSalary().getText());
            String username = view.getUsernameTxt().getText();
            String password = view.getPasswordTxt().getText();
            String email = view.getEmailTxt().getText();
            String phoneNumber = view.getPhoneTxt().getText();
            LocalDate dateEmployed = view.getDateEmployed().getValue();
            EmployeeRole role = EmployeeRole.valueOf(view.getRoleSelection().getValue().toUpperCase());
            String photo = view.getImagePath().getText();

            SectorType cashierSector=null;
            ArrayList<SectorType> managerSectors=null;
            if(role==EmployeeRole.CASHIER){
                cashierSector=findSector(view.getCashierSectorSelection().getSelectionModel().getSelectedItem());
            }
            else if(role==EmployeeRole.MANAGER){
                managerSectors=new ArrayList<>();
                ObservableList<String> selected=this.view.getManagerSectorSelection().getSelectionModel().getSelectedItems();
                for(String sector: selected){
                    managerSectors.add(findSector(sector));
                }
            }

            if(role==EmployeeRole.CASHIER){
                Cashier cashier=new Cashier(ID,name,lastName,gender,birthday,salary,username,password,email,phoneNumber,dateEmployed,role,photo,cashierSector);
                setCashierDefaultPermissions(cashier);
                FileHandler.writeCashierToFile(cashier);
            }
            else if(role==EmployeeRole.MANAGER){
                Manager manager=new Manager(ID,name,lastName,gender,birthday,salary,username,password,email,phoneNumber,dateEmployed,role,photo,managerSectors);
                setManagerDefaultPermissions(manager);
                FileHandler.writeManagerToFile(manager);
            }
            else{
                Administrator admin=new Administrator(ID,name,lastName,gender,birthday,salary,username,password,email,phoneNumber,dateEmployed,role,photo);
                setAdministratorDefaultPermissions(admin);
                FileHandler.writeAdministratorsToFile(admin);
            }


            System.out.println("User created and written to .dat file successfully");

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "User was successfully added and saved to file!");

        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please ensure all numeric fields are correctly filled.");
            System.out.println("Number format exception: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", ex.getMessage());
            System.out.println("Illegal argument exception: " + ex.getMessage());
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add user. Please try again.");
            System.out.println("General exception: " + ex.getMessage());
        }
    }

    private void validateInputs() {
        if (view.getIDTxt().getText().isEmpty() || view.getNameTxt().getText().isEmpty() || view.getUsernameTxt().getText().isEmpty()) {
            throw new IllegalArgumentException("ID, Name, and Username are required fields.");
        }
        if (view.getGenderSelection().getValue() == null || view.getRoleSelection().getValue() == null) {
            throw new IllegalArgumentException("Gender, Role, and Sector must be selected.");
        }
        if (view.getPhoneTxt().getText().isEmpty()) {
            throw new IllegalArgumentException("Phone Number is a required field.");
        }
    }

    public Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    public void setCashierDefaultPermissions(Cashier cashier){
        cashier.getPermissions().add(Permission.BILLING_SYSTEM);
        cashier.getPermissions().add(Permission.VIEW_ALL_BILLS);
        cashier.getPermissions().add(Permission.PRODUCT_INFORMATION);
        cashier.getPermissions().add(Permission.NOTIFICATION_PANEL);
    }
    public void setManagerDefaultPermissions(Manager manager){
        manager.getPermissions().add(Permission.REPORT_GENERATOR);
        manager.getPermissions().add(Permission.VIEW_ALL_REPORTS);
        manager.getPermissions().add(Permission.VIEW_ALL_BILLS);
        manager.getPermissions().add(Permission.NOTIFICATION_PANEL);
        manager.getPermissions().add(Permission.USER_MANAGEMENT);
        manager.getPermissions().add(Permission.INVENTORY_MANAGEMENT);
        manager.getPermissions().add(Permission.SUPPLIER_MANAGEMENT);
        manager.getPermissions().add(Permission.PRODUCT_INFORMATION);
    }
    public void setAdministratorDefaultPermissions(Administrator admin){
        admin.getPermissions().add(Permission.PERMISSION_GRANTING);
        admin.getPermissions().add(Permission.BILLING_SYSTEM);
        admin.getPermissions().add(Permission.VIEW_ALL_BILLS);
        admin.getPermissions().add(Permission.VIEW_ALL_REPORTS);
        admin.getPermissions().add(Permission.REPORT_GENERATOR);
        admin.getPermissions().add(Permission.NOTIFICATION_PANEL);
        admin.getPermissions().add(Permission.USER_MANAGEMENT);
        admin.getPermissions().add(Permission.INVENTORY_MANAGEMENT);
        admin.getPermissions().add(Permission.SUPPLIER_MANAGEMENT);
        admin.getPermissions().add(Permission.PRODUCT_INFORMATION);
    }

    //Handle cashier sector selection
    public void setCashierSectorDisplay(){
        this.view.getSectorV().getChildren().add(view.getCashierSectorSelection());
    }
    public void setManagerSectorDisplay(){
        this.view.getSectorV().getChildren().add(view.getManagerSectorSelection());
    }

    public void setRoleChoiceListener(){
        this.view.getRoleSelection().setOnAction(
                e->{
                    String role=this.view.getRoleSelection().getValue();
                    if(role.equals("CASHIER")){
                        setCashierSectorDisplay();
                    }
                    else if(role.equals("MANAGER")){
                        setManagerSectorDisplay();
                    }
                    else{
                        if(view.getSectorV().getChildren().size()!=0){
                            view.getSectorV().getChildren().remove(1); //Checks if the previous choice was admin
                        }

                    }
                }
        );
    }

    public SectorType findSector(String sectorString){
        for(SectorType sector:Database.getDatabase().getSectors()){
            if(sector.toString().equalsIgnoreCase(sectorString)){
                return sector;
            }
        }
        return null;
    }
    public AddUser getView() {
        return view;
    }

    public static void main(String[] args) {
        System.out.println(Database.getDatabase().getCashiers().getFirst().getSector().toString());
    }
}
