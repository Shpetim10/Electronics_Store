package Control;

import Database.Database;
import MainRoot.AddCashierMain;
import MainRoot.ManagePermissionsMain;
import MainRoot.UserManagementMain;
import Model.*;
import View.UserManagementView.UserManagementView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserManagementController {
    private UserManagementView view=new UserManagementView();
    private ArrayList<User> users = Database.getDatabase().getUsers();

    public UserManagementController() {
        setEditListeners();
        setDeleteActions();
        searchBoxListener();

        // Set button actions after the view is initialized
//        view.getManagePermissionsBtn().setOnAction(e -> showManagePermissionsView());
//        view.getAddUserBtn().setOnAction(e -> openUserAddWindow());
    }

//    private void showManagePermissionsView() {
//        ManagePermissionsMain main = new ManagePermissionsMain();
//        Stage primaryStage = new Stage();
//        main.start(primaryStage);
//    }
//
//    private void openUserAddWindow() {
//        AddCashierMain main = new AddCashierMain();
//        Stage primaryStage = new Stage();
//        main.start(primaryStage);
//    }

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
            Database.getDatabase().updateUsers(users);
        });

        this.view.getLastNameColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newLastName = e.getNewValue();
            if (!Validator.validateName(newLastName)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Last Name", "Last name must contain only letters and numbers.");
                return;
            }
            user.setLastName(newLastName);
            Database.getDatabase().updateUsers(users);
        });

        this.view.getUsernameColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newUsername = e.getNewValue();
            if (!Validator.validateName(newUsername)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Username", "Username must be unique and alphanumeric.");
                return;
            }
            user.setUsername(newUsername);
            Database.getDatabase().updateUsers(users);
        });

        this.view.getEmailColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newEmail = e.getNewValue();
            if (!Validator.isValidEmail(newEmail)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Email", "Please provide a valid email address.");
                return;
            }
            user.setEmail(newEmail);
            Database.getDatabase().updateUsers(users);
        });

        this.view.getPhoneNumberColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            String newPhoneNumber = e.getNewValue();
            if (!Validator.isValidPhoneNumber(newPhoneNumber)) {
                showAlert(Alert.AlertType.WARNING, "Invalid Phone Number", "Please provide a valid phone number.");
                return;
            }
            user.setPhoneNumber(newPhoneNumber);
            Database.getDatabase().updateUsers(users);
        });

        this.view.getSalaryColumn().setOnEditCommit(e -> {
            User user = e.getRowValue();
            Double newSalary = e.getNewValue();
            if (newSalary == null || newSalary <= 0) {
                showAlert(Alert.AlertType.WARNING, "Invalid Salary", "Salary must be a positive number.");
                return;
            }
            user.setSalary(newSalary);
            Database.getDatabase().updateUsers(users);
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
            Database.getDatabase().updateUsers(users);
        });
    }

    private void setDeleteActions() {
        this.view.getDeleteBtn().setOnAction(event -> userDelete());// Set delete action once
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
        users.remove(selectedUser);

        Database.getDatabase().updateUsers(users);

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

    public UserManagementView getView() {
        return view;
    }
}
