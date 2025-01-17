package Control;

import View.UserManagementView.UserManagementView;
import View.UserManagementView.UserTable;
import View.UserManagementView.PermissionGrantingView;
import View.UserManagementView.UserAdd; // Import UserAdd view
import javafx.scene.layout.BorderPane;

public class UserManagementController {
    private UserManagementView view;
    private UserTable userTable;
    private PermissionGrantingView managePermissionsView;

    public UserManagementController(UserManagementView view) {
        this.view = view;
        this.userTable = new UserTable();
        this.managePermissionsView = new PermissionGrantingView(); // Initialize the view

        // Set initial view
        view.getTemporaryPane().setCenter(userTable);

        // Set button actions
        view.getManagePermissionsBtn().setOnAction(e -> showManagePermissionsView());
        view.getUserTableBtn().setOnAction(e -> showUserTable());
        view.getAddUserBtn().setOnAction(e -> openUserAddWindow());
    }

    private void showManagePermissionsView() {
        view.getTemporaryPane().setCenter(managePermissionsView); // Set the center to the permissions view
    }

    private void showUserTable() {
        view.getTemporaryPane().setCenter(userTable);
    }

    private void openUserAddWindow() {
        UserAdd userAddView = new UserAdd();
        userAddView.showUserAddWindow();
    }
}