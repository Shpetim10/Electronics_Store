package Control;

import Database.Database;
import Model.*;
import View.UserManagementView.UserManagementView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class UserManagementController {
    private UserManagementView view=new UserManagementView();
    private User user;

    public UserManagementController(User user) {
        this.user=user;
        setEditListeners();
        setDeleteActions();
        searchBoxListener();
        if(user instanceof Manager){
            this.view.getTable().setItems(getDataForManager());
        }
    }


    private void setEditListeners() {
// Add edit commit handlers for table columns
        this.view.getNameColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newName = e.getNewValue();
            if (!Validator.validateName(newName)) {
                showAlert(Alert.AlertType.WARNING, "Invalid User Name", "User name must contain only letters and numbers.");
                return;
            }
            user.setFirstName(newName);
            if(user instanceof Cashier)
                Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            else if (user instanceof Manager)
                Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
            else
                Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        });

        this.view.getLastNameColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newLastName = e.getNewValue();
            if (!Validator.validateName(newLastName)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Last Name", "Last name must contain only letters and numbers.");
                return;
            }
            user.setLastName(newLastName);
            if(user instanceof Cashier)
                Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            else if (user instanceof Manager)
                Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
            else
                Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        });

        this.view.getUsernameColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newUsername = e.getNewValue();
            if (!Validator.validateName(newUsername)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Username", "Username must be unique and alphanumeric.");
                return;
            }
            user.setUsername(newUsername);
            if(user instanceof Cashier)
                Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            else if (user instanceof Manager)
                Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
            else
                Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        });

        this.view.getEmailColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newEmail = e.getNewValue();
            if (!Validator.isValidEmail(newEmail)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Email", "Please provide a valid email address.");
                return;
            }
            user.setEmail(newEmail);
            if(user instanceof Cashier)
                Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            else if (user instanceof Manager)
                Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
            else
                Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        });

        this.view.getPhoneNumberColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newPhoneNumber = e.getNewValue();
            if (!Validator.isValidPhoneNumber(newPhoneNumber)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Phone Number", "Please provide a valid phone number.");
                return;
            }
            user.setPhoneNumber(newPhoneNumber);
            if(user instanceof Cashier)
                Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            else if (user instanceof Manager)
                Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
            else
                Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        });

        this.view.getSalaryColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            Double newSalary = e.getNewValue();
            if (newSalary == null || newSalary <= 0) {
                showAlert(Alert.AlertType.WARNING, "Invalid Salary", "Salary must be a positive number.");
                return;
            }
            user.setSalary(newSalary);
            if(user instanceof Cashier)
                Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            else if (user instanceof Manager)
                Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
            else
                Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        });

        this.view.getRoleColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            EmployeeRole newRole;
            try {
                newRole = EmployeeRole.valueOf(e.getNewValue().toString().toUpperCase());
            } catch (IllegalArgumentException ex) {
                showAlert(Alert.AlertType.WARNING, "Invalid Role", "Please provide a valid role.");
                return;
            }
            user.setRole(newRole);
            if(user instanceof Cashier)
                Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
            else if (user instanceof Manager)
                Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
            else
                Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        });

    }

    private void setDeleteActions() {
        this.view.getDeleteBtn().setOnAction(event -> userDelete());
    }

    private void userDelete() {
        User selectedUser = this.view.getTable().getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No user selected for deletion.");
            alert.setTitle("Delete Warning");
            alert.show();
            return;
        }

        this.view.getTable().getItems().remove(selectedUser);


        if(selectedUser instanceof Cashier){
            Database.getDatabase().getCashiers().remove(selectedUser);
            Database.getDatabase().updateCashiers(Database.getDatabase().getCashiers());
        }
        else if (selectedUser instanceof Manager){
            Database.getDatabase().getManagers().remove(selectedUser);
            Database.getDatabase().updateManagers(Database.getDatabase().getManagers());
        }

        else{
            Database.getDatabase().getAdministrators().remove(selectedUser);
            Database.getDatabase().updateAdministrators(Database.getDatabase().getAdministrators());
        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted successfully.");
        alert.setTitle("Delete Result");
        alert.show();
    }


    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void searchBoxListener() {
        ObservableList<User> originalUsers = FXCollections.observableArrayList(view.getTable().getItems());

        view.getSearchBox().getSearchField().setOnAction(e -> {
            String searchQuery = this.view.getSearchBox().getSearchField().getText().toLowerCase();

            ObservableList<User> filteredUsers = FXCollections.observableArrayList();

            // Only filter if there's a search query
            if (!searchQuery.isEmpty()) {
                for (User user : originalUsers) {
                    // Check multiple fields (firstName, lastName, email, and ID)
                    if (user.getFirstName().toLowerCase().contains(searchQuery) ||
                            user.getLastName().toLowerCase().contains(searchQuery) ||
                            user.getEmail().toLowerCase().contains(searchQuery) ||
                            String.valueOf(user.getId()).contains(searchQuery)) {
                        filteredUsers.add(user);
                    }
                }
                this.view.getTable().setItems(filteredUsers);
            } else {
                // Reset to original list
                this.view.getTable().setItems(originalUsers);
            }
        });
    }

    private ObservableList<User> getDataForManager(){
        ArrayList<Cashier> users = new ArrayList<>();
        ArrayList<User> neededUsers = new ArrayList<>();
        ArrayList<SectorType> sectors = ((Manager)user).getSectors();

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
    public UserManagementView getView() {
        return view;
    }
}
