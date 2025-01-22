package Control;


import Database.Database;
import Model.*;
import View.PerformanceReportView.AdminManagerAllBillView;
import View.UserManagementView.UserTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;


public class AdminManagerViewBillController implements Alertable{
    private User admin;
    private AdminManagerAllBillView view=new AdminManagerAllBillView();

    public AdminManagerViewBillController() {
        setUpPaneSwitcher();
        searchBoxListener();
        getSearchButtonListener();
    }

    public AdminManagerViewBillController(User admin){
        this.admin=admin;
        this.view.getUserTable().getTable().setEditable(false);
        this.view.getUserTable().getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        if(admin instanceof Manager){
            this.view.getUserTable().getTable().setItems(getDataForManager());
        }
        setUpPaneSwitcher();
        searchBoxListener();//search
        getSearchButtonListener();//show bills
    }


    public void searchBoxListener(){
        view.getSearch().getSearchField().setOnAction(
                e->{
                    String searchQuery=this.view.getSearch().getSearchField().getText().toLowerCase();
                    ObservableList<User> filteredItems= FXCollections.observableArrayList();
                    for(User user: view.getUserTable().getTable().getItems()){
                        if(user.getFirstName().toLowerCase().contains(searchQuery) && user.getRole()!=EmployeeRole.MANAGER && user.getRole()!=EmployeeRole.ADMINISTRATOR){
                            filteredItems.add(user);
                        }
                    }
                    this.view.getUserTable().getTable().setItems(filteredItems);
                }
        );
    }

    public void getSearchButtonListener(){
        this.view.getSearch().getSearchButton().setOnAction(e->{
            User user=this.view.getUserTable().getTable().getSelectionModel().getSelectedItem();

            if(user!=null){
                if(user instanceof Cashier){
                    this.view.getBillView().setCashier((Cashier)user);
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(this.view.getBillView().getView());
                }
            }
            else {
                showAlert(Alert.AlertType.WARNING,"User does not exist!","This user does not exist!");
            }
        });
    }
    public Cashier getCashierById(String id) {
        for(Cashier cashier: Database.getDatabase().getCashiers()){
            if(cashier.getId()==Integer.parseInt(id)){
                return cashier;
            }
        }
        return null;
    }

    // Utility to display error messages (example implementation)

    public void setUpPaneSwitcher(){
        view.getSearch().getSearchField().setOnMouseClicked(
                e->{
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getUserTable().getTable().setItems(this.view.getUserTable().getCashierData());
                    this.view.getDisplayPane().getChildren().add(this.view.getUserTable().getTable());
                });
    }
    public User getAdmin() {
        return admin;
    }
    private ObservableList<User> getDataForManager(){
        ArrayList<Cashier> users = new ArrayList<>();
        ArrayList<User> neededUsers = new ArrayList<>();
        ArrayList<SectorType> sectors = ((Manager)admin).getSectors();

        users.addAll(Database.getDatabase().getCashiers());
        //check if the cashiers are part of manager's sectors
        for(int i = 0; i < sectors.size();i++ ) {
            //Add cashiers
            for(Cashier u: users) {
                if (sectors.contains(u.getSector()) && !neededUsers.contains(u)) {
                    neededUsers.add(u);
                }
            }
        }

        return FXCollections.observableArrayList(neededUsers);
    }
    public AdminManagerAllBillView getView() {
        return view;
    }
}
