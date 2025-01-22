package Control;

import Model.*;
import View.UserManagementView.AddUser;
import View.UserManagementView.UserManagementView;
import javafx.scene.control.Alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import Database.Database;
import javafx.scene.control.RadioButton;


public class AddUserControl implements Alertable{
    private final AddUser view=new AddUser();
    SectorType cashierSector=null;
    ArrayList<SectorType> managerSectors= new ArrayList<>();

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


            if(role==EmployeeRole.CASHIER){
                cashierSector=findSector(view.getCashierSectorSelection().getSelectionModel().getSelectedItem());



            }
            else if(role==EmployeeRole.MANAGER){

                managerSectors = new ArrayList<>();
                if (view.getHomeAppliancesCB().isSelected()) {
                    managerSectors.add(SectorType.HOME_APPLIANCES);
                }
                if (view.getElectronicsCB().isSelected()) {
                    managerSectors.add(SectorType.ELECTRONICS);
                }
                if (view.getComputersCB().isSelected()) {
                    managerSectors.add(SectorType.COMPUTERS);
                }
                if (view.getMobileDevicesCB().isSelected()) {
                    managerSectors.add(SectorType.MOBILE_DEVICES);
                }
                if (view.getAccessoriesCB().isSelected()) {
                    managerSectors.add(SectorType.ACCESSORIES);
                }
                if (view.getGamingCB().isSelected()) {
                    managerSectors.add(SectorType.GAMING);
                }
                if (view.getCamerasCB().isSelected()) {
                    managerSectors.add(SectorType.CAMERAS);
                }
                if (view.getKitchenElectronicsCB().isSelected()) {
                    managerSectors.add(SectorType.KITCHEN_ELECTRONICS);
                }
                if (view.getSmartHomeCB().isSelected()) {
                    managerSectors.add(SectorType.SMART_HOME);
                }


            }

//Creating users
            if(role==EmployeeRole.CASHIER){
                if(cashierSector!=null){
                    Cashier cashier=new Cashier(ID,name,lastName,gender,birthday,salary,username,password,email,phoneNumber,dateEmployed,role,photo,cashierSector);
                    setCashierDefaultPermissions(cashier);
                    Database.getDatabase().saveCashier(cashier);
                }
                else{
                    throw new IllegalArgumentException("Please select a sector for this employee!");
                }

            }
            else if(role==EmployeeRole.MANAGER){
                if(!managerSectors.isEmpty()){
                    Manager manager=new Manager(ID,name,lastName,gender,birthday,salary,username,password,email,phoneNumber,dateEmployed,role,photo, managerSectors);
                    setManagerDefaultPermissions(manager);
                    Database.getDatabase().saveManager(manager);
                }
                else{
                    throw new IllegalArgumentException("Please select a sector for this employee!");
                }

            }
            else{
                Administrator admin=new Administrator(ID,name,lastName,gender,birthday,salary,username,password,email,phoneNumber,dateEmployed,role,photo);
                setAdministratorDefaultPermissions(admin);
                Database.getDatabase().saveAdministrator(admin);
            }


            view.getIDTxt().clear();
            view.getNameTxt().clear();
            view.getLastNameTxt().clear();
            view.getGenderSelection().setValue(null);
            view.getBirthday().setValue(null);
            view.getSalary().clear();
            view.getUsernameTxt().clear();
            view.getPasswordTxt().clear();
            view.getEmailTxt().clear();
            view.getPhoneTxt().clear();
            view.getDateEmployed().setValue(null);
            view.getRoleSelection().setValue(null);
            view.getImagePath().clear();

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
        if (idExists(Integer.parseInt(view.getIDTxt().getText()))) {
            throw new IllegalArgumentException("A user with this ID already exists");
        }
        if (usernameExists(view.getUsernameTxt().getText())) {
            throw new IllegalArgumentException("A user with this ID already exists");
        }

        if (view.getGenderSelection().getValue() == null || view.getRoleSelection().getValue() == null) {
            throw new IllegalArgumentException("Gender, Role, and Sector must be selected.");
        }
        if (view.getPhoneTxt().getText().isEmpty()) {
            throw new IllegalArgumentException("Phone Number is a required field.");
        }
        if (!(view.getPhoneTxt().getText().matches("^\\+\\d{1,3}(\\s?\\d{2,4}){2,4}$"))){
            throw new IllegalArgumentException("Phone Number is invalid.");
        }
        if (!(view.getPasswordTxt().getText().length() > 8 &&
                view.getPasswordTxt().getText().matches(".*\\d.*") &&    //has digits
                view.getPasswordTxt().getText().matches(".*[A-Z].*") &&  //has upper case
                view.getPasswordTxt().getText().matches(".*[a-z].*") &&  //has lower case
                view.getPasswordTxt().getText().matches(".*[^a-zA-Z0-9 ].*"))) { //has special characters
            throw new IllegalArgumentException("Password should contain at least one UpperCase, one LowerCase, one number and one special character");
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

    private User user;

    private boolean idExists(int id) {
        UserManagementView users = new UserManagementView();
        for (User u : users.getTable().getItems()) {
            if (u.getId() == id) {
                return true; // Id already exists
            }
        }
        return false;
    }

    private boolean usernameExists(String username) {
        UserManagementView users = new UserManagementView();
        for (User u : users.getTable().getItems()) {
            if (u.getUsername().equals(username)) {
                return true; // User already exists
            }
        }
        return false;
    }

    //Handle cashier sector selection
    public void setCashierSectorDisplay(){
        this.view.getSectorV().getChildren().add(view.getCashierSectorSelection());
    }
    public void setManagerSectorDisplay(){
        this.view.getSectorV().getChildren().add(view.getCheckBoxContainerManager());
    }

    public void setRoleChoiceListener(){
        this.view.getRoleSelection().setOnAction(
                e->{
                    {
                        String role = this.view.getRoleSelection().getValue();
                        if(role!=null){
                            if (role.equals("CASHIER")) {
                                if (view.getSectorV().getChildren().size() == 2) {
                                    view.getSectorV().getChildren().remove(1); //Checks if the previous choice was admin
                                }
                                setCashierSectorDisplay();
                            } else if (role.equals("MANAGER")) {
                                if (view.getSectorV().getChildren().size() == 2) {
                                    view.getSectorV().getChildren().remove(1); //Checks if the previous choice was admin
                                }
                                setManagerSectorDisplay();
                            } else {
                                if (view.getSectorV().getChildren().size() != 0) {
                                    view.getSectorV().getChildren().remove(1); //Checks if the previous choice was admin
                                }

                            }
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
}
