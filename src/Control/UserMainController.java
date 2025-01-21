package Control;

import Database.FileHandler;
import View.AddProductView;
import View.UserMainView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import Model.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;

public class UserMainController implements Alertable {
    private UserMainView view=new UserMainView();
    private ArrayList<Item> inventory= FileHandler.getItemsOfInventory();
    private User user;

    public UserMainController(User user){
        this.user=user;
        setHomePage();
        setUpMenu();
        setUpProfileInformationIconListener();
        setUpHomePageSwitcherListener();
    }
    public void setBillingSystemButtonListener(){
        int index=getMenuItemViewIndex("Billing System");
        if(index!=-1){
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                        clearPane();
                        BillingSystemController billingSystem=new BillingSystemController(user);
                        view.getDisplayPane().getChildren().add(billingSystem.getView());
                    }
            );
        }
    }

    public void setGenerateReportButtonListener(){
        int index=getMenuItemViewIndex("Generate Report");
        if(index!=-1){
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                        clearPane();
                        CustomReportController control=new CustomReportController(user);
                        view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }

    }

    public void setViewBillsButtonListener(){
        int index=getMenuItemViewIndex("View Bills");
        if (index != -1) {
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                        clearPane();
                        if(this.user instanceof Cashier){
                            ViewAllBillsController control=new ViewAllBillsController((Cashier)user);
                            view.getDisplayPane().getChildren().add(control.getView());
                        }
                        else if(user instanceof Manager){
                            AdminManagerViewBillController control=new AdminManagerViewBillController((Manager)user);
                            view.getDisplayPane().getChildren().add(control.getView());
                        }
                        else{
                            AdminManagerViewBillController control=new AdminManagerViewBillController((Administrator)user);
                            view.getDisplayPane().getChildren().add(control.getView());
                        }

                    }
            );
        }
    }

    public void setViewReportsButtonListener(){
        int index=getMenuItemViewIndex("View Reports");
        if (index != -1) {
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                            clearPane();
                            ViewAllReportsController control=new ViewAllReportsController(user);
                            view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }
    }

    public void setAddProductButtonListener(){
        int index=getMenuItemViewIndex("Inventory Management");
        Menu subMenu=null;
        if (index != -1) {
            subMenu = (Menu) view.getMainMenu().getItems().get(index);
        }
        subMenu.getItems().get(1).setOnAction( //it is second in submenu
                    e->{
                        clearPane();
                        AddProductController control=new AddProductController();
                        view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }

    public void setManageInventoryButtonListener(){
        int index=getMenuItemViewIndex("Inventory Management");
        Menu subMenu=null;
        if (index != -1) {
            subMenu = (Menu) view.getMainMenu().getItems().get(index);
        }
            subMenu.getItems().get(0).setOnAction(//it is first in submenu
                    e->{
                        clearPane();
                        InventoryManagementController control=new InventoryManagementController();
                        view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }

    public void setAddEmployeeButtonListener(){
        int index=getMenuItemViewIndex("Add Employee");
        if (index != -1) {
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                        clearPane();
                        AddUserControl control=new AddUserControl();
                        view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }
    }
//    public void setEditEmployeeButtonListener(){
//        int index=getMenuItemViewIndex("Staff Management");
//        if (index != -1) {
//            view.getMainMenu().getItems().get(index).setOnAction(
//                    e->{
//                        ViewAllReportsController control=new ViewAllReportsController(user);
//                        view.getDisplayPane().getChildren().add(control.getView());
//                    }
//            );
//        }
//    }

    public void setPermissionGrantingButtonListener(){
        int index=getMenuItemViewIndex("Permission Granting");
        if (index != -1) {
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                        clearPane();
                        // control=new ViewAllReportsController(user);
                        //view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }
    }

    public void setSupplierManagementButtonListener(){
        int index=getMenuItemViewIndex("Supplier Management");
        if (index != -1) {
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                        clearPane();
                        SupplierControl control=new SupplierControl();
                        view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }
    }


    public void setUpProfileInformationIconListener(){
        this.view.getProfileLogo().setOnMouseClicked(
                e->{
                    clearPane();
                    ProfileInformationController control=new ProfileInformationController(user);
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(control.getView());
                }
        );
    }

    public void setUpHomePageSwitcherListener(){
        this.view.getStoreLogo().setOnMouseClicked(
                e->{
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(this.view.getHomePage());
                }
        );
    }
    public void setUpLogOutIconListener() {
        this.view.getLogOutIcon().setOnMouseClicked(e -> {
            // Clear the current display pane
            this.view.getDisplayPane().getChildren().clear();

            // Create a new LogInView instance (ensure LogInView extends Parent or a compatible class)
            //LogInView logIn = new LogInView();

            // Create a new Scene with the LogInView
            //Scene loginScene = logIn.createScene();
            //MainUser.changeScene(loginScene);
        });
    }

    //This menu will return index of the item in menu
    public int getMenuItemViewIndex(String viewItem){

        Menu menu=view.getMainMenu();
        for(int i=0;i<menu.getItems().size();i++){
            if(menu.getItems().get(i).getText().equals(viewItem)){
                return i;
            }
        }
        return -1;
    }
    public void setUpMenu() {
        if (user.getPermissions().contains(Permission.BILLING_SYSTEM)) {
            this.view.addToMainMenu(view.getBillingSystemItem());
            setBillingSystemButtonListener();
        }
        if (user.getPermissions().contains(Permission.REPORT_GENERATOR)) {
            this.view.addToMainMenu(view.getGenerateReportItem());
            setGenerateReportButtonListener();
        }
        if (user.getPermissions().contains(Permission.VIEW_ALL_BILLS)) {
            this.view.addToMainMenu(view.getViewAllBillsItem());
            setViewBillsButtonListener();
        }
        if (user.getPermissions().contains(Permission.VIEW_ALL_REPORTS)) {
            this.view.addToMainMenu(view.getViewAllReportsItem());
            setViewReportsButtonListener();
        }
        if (user.getPermissions().contains(Permission.PERMISSION_GRANTING)) {
            this.view.addToMainMenu(view.getPermissionGrantingItem());
            setPermissionGrantingButtonListener();
        }
        if (user.getPermissions().contains(Permission.USER_MANAGEMENT)) {
            this.view.addToMainMenu(view.getUserManagementSubmenu());
            this.view.getUserManagementSubmenu().getItems().add(view.getAddEmployee());
            this.view.getUserManagementSubmenu().getItems().add(view.getManageEmployee());
            setAddEmployeeButtonListener();
            //usermanagement
        }
        if (user.getPermissions().contains(Permission.SUPPLIER_MANAGEMENT)) {
            this.view.addToMainMenu(view.getSupplierManagementItem());
            setSupplierManagementButtonListener();
        }
        if (user.getPermissions().contains(Permission.INVENTORY_MANAGEMENT)) {
            this.view.addToMainMenu(view.getInventoryManagementSubMenu());
            this.view.getInventoryManagementSubMenu().getItems().add(view.getInventoryManagementItem());
            this.view.getInventoryManagementSubMenu().getItems().add(view.getAddProductItem());
            setManageInventoryButtonListener();
            setAddProductButtonListener();
        }
    }

    //HomePage
    public void setHomePage(){
        this.view.getWelcomeMessage().setText("Welcome back, "+user.getFirstName()+" "+user.getLastName()+"!");
        if(user instanceof Cashier){
            this.view.setHomePage(this.view.createCashierMainPage());
            setUpCashierButtonListeners();
        }
        else{
            this.view.setHomePage(this.view.createManagerAndAdminHomePage());
        }
        this.view.getDisplayPane().getChildren().add(this.view.getHomePage());
    }
    //Cashier buttons

    public void setUpCashierButtonListeners(){
        this.view.getStartShift().setOnAction(e->{
            if(((Cashier)user).getActiveShift()==null){
                Shift shift=new Shift(
                        ((Cashier)user).getShifts().size()+1,
                        ((Cashier)user),
                        LocalDate.now(),
                        LocalTime.now(),
                        LocalTime.now()
                );
                shift.setShiftStatus(ShiftStatus.ACTIVE);
                ((Cashier)user).getShifts().add(shift);
                showAlert(Alert.AlertType.INFORMATION,"Action completed!","You just started a shift!");
            }
            else{
                showAlert(Alert.AlertType.WARNING,"Warning!","You cannot start a new shift because you already have an active one!\n" +
                        "In Case you want to start a new one first end the previous one.");
            }
        });

        this.view.getEndShift().setOnAction(e->{
            if(((Cashier)user).getActiveShift()!=null){
                ((Cashier)user).getActiveShift().setEndHour(LocalTime.now());
                ((Cashier)user).getActiveShift().setEndHour(LocalTime.now());
                ((Cashier)user).getActiveShift().generateShiftReport();
                ((Cashier)user).getActiveShift().setShiftStatus(ShiftStatus.COMPLETED);
                showAlert(Alert.AlertType.INFORMATION,"Action Completed!","You just finished your shift!\nHave a nice day!");
            }
            else{
                showAlert(Alert.AlertType.WARNING,"Warning","You do not have an active shift!");
            }
        });
    }


    public void clearPane(){
        this.view.getDisplayPane().getChildren().clear();
    }

    public UserMainView getView() {
        return view;
    }

    public void setView(UserMainView view) {
        this.view = view;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public User getUser() {
        return user;
    }

    public void setCashier(User user) {
        this.user = user;
    }
}
