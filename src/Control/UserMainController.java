package Control;

import View.UserMainView;

import java.util.ArrayList;
import Model.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

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
                            view.getDisplayPane().getChildren().add(control.getMainView());
                        }

                    }
            );
        }
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
    }

    public void setHomePage(){
        this.view.getWelcomeMessage().setText("Welcome back, "+user.getFirstName()+" "+user.getLastName()+"!");
        if(user instanceof Cashier){
            this.view.getDisplayPane().getChildren().add(getView().createCashierMainPage());
        }
        else{
            this.view.getDisplayPane().getChildren().add(getView().createManagerAndAdminHomePage());
        }
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
