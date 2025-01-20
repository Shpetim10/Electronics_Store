package Control;

import MainRoot.AddCashierMain;
import MainRoot.ManagePermissionsMain;
import MainRoot.UserManagementMain;
import View.UserManagementView.UserManagementView;
import View.UserManagementView.UserTable;
import View.UserManagementView.PermissionGrantingView;
import View.UserManagementView.AddCashier;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserManagementController {
    private UserManagementView view;
    private UserTable userTable;
    private PermissionGrantingView managePermissionsView;

    public UserManagementController(UserManagementView view) {
        this.view = view;
        this.userTable = new UserTable();
        this.managePermissionsView = new PermissionGrantingView();

        // Set initial view
        view.getTemporaryPane().setCenter(userTable);

        // Set button actions
        view.getManagePermissionsBtn().setOnAction(e -> showManagePermissionsView());
        view.getUserTableBtn().setOnAction(e -> showUserTable());
        view.getAddUserBtn().setOnAction(e -> openUserAddWindow());
    }

    private void showManagePermissionsView() {
        ManagePermissionsMain main = new ManagePermissionsMain();
        Stage primaryStage = new Stage();
        main.start(primaryStage);
    }

    private void showUserTable() {
        view.getTemporaryPane().setCenter(userTable);
    }

    private void openUserAddWindow() {
        AddCashierMain main = new AddCashierMain();
        Stage primaryStage = new Stage();
        main.start(primaryStage);
    }
}
