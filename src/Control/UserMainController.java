package Control;

import View.LogInView;
import View.UserMainView;

import java.util.ArrayList;
import Model.*;
import javafx.scene.Scene;
import javafx.scene.control.Menu;

public class UserMainController {
    private UserMainView view=new UserMainView();
    private ArrayList<Item> inventory=FileHandler.getItemsOfInventory();
    private User user;
    public UserMainController(User user){
        this.user=user;
        setHomePage();
        setUpMenu();
        setBillingSystemButtonListener();
        setViewBillsButtonListener();
        setGenerateReportButtonListener();
        setViewReportsButtonListener();
        setUpProfileInformationIconListener();
        setUpHomePageSwitcherListener();
        setUpHomePageSwitcherListener();
    }
    public void setBillingSystemButtonListener(){
        int index=getMenuItemViewIndex("Billing System");
        if(index!=-1){
            view.getMainMenu().getItems().get(index).setOnAction(
                    e->{
                        clearPane();
                        BillingSystemController billingSystem=new BillingSystemController((Cashier) user);
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
                        CustomReportController control=new CustomReportController();
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
                        if(this.user.getRole()==EmployeeRole.CASHIER){
                            ViewAllBillsController control=new ViewAllBillsController((Cashier)user);
                            view.getDisplayPane().getChildren().add(control.getView());
                        }
                        else {
                            AdminManagerViewBillController control=new AdminManagerViewBillController(user);
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
                            ViewAllReportsController control=new ViewAllReportsController(user);
                            view.getDisplayPane().getChildren().add(control.getView());
                    }
            );
        }
    }

    public void setUpProfileInformationIconListener(){
        this.view.getProfileLogo().setOnMouseClicked(
                e->{
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
            LogInView logIn = new LogInView();

            // Create a new Scene with the LogInView
            Scene loginScene = logIn.createScene();
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

    public void setUpMenu(){
        if(user.getPermissions().contains(Permission.BILLING_SYSTEM)){
            this.view.addToMainMenu(view.getBillingSystemItem());
        }
        if(user.getPermissions().contains(Permission.REPORT_GENERATOR)){
            this.view.addToMainMenu(view.getGenerateReportItem());
        }
        if(user.getPermissions().contains(Permission.VIEW_ALL_BILLS)){
            this.view.addToMainMenu(view.getViewAllBillsItem());
        }
        if(user.getPermissions().contains(Permission.VIEW_ALL_REPORTS)){
            this.view.addToMainMenu(view.getViewAllReportsItem());
        }
    }

    public void setHomePage(){
        this.view.getWelcomeMessage().setText("Welcome back, "+user.getFirstName()+" "+user.getLastName()+"!");
        if(user instanceof Cashier){
            this.view.setHomePage(this.view.createCashierMainPage());
        }
        else{
            this.view.setHomePage(this.view.createManagerAndAdminHomePage());
        }
        this.view.getDisplayPane().getChildren().add(this.view.getHomePage());
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
