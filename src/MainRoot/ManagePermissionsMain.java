package MainRoot;

import Control.ManagePermissionsController;
import Control.UserManagementController;
import View.UserManagementView.PermissionGrantingView;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

import View.UserManagementView.UserManagementView;

public class ManagePermissionsMain extends Application {

    PermissionGrantingView view = new PermissionGrantingView();
    ManagePermissionsController control = new ManagePermissionsController(view);
    @Override

    public void start(Stage primaryStage) {
        PermissionGrantingView view = new PermissionGrantingView();
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("User Management");
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }

}