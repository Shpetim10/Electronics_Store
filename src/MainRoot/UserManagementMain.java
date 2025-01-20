package MainRoot;

import Control.UserManagementController;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

import View.UserManagementView.UserManagementView;

public class UserManagementMain extends Application {

    UserManagementView view = new UserManagementView();
    UserManagementController control = new UserManagementController(view);
    @Override

    public void start(Stage primaryStage) {
        UserManagementView userManagementView = new UserManagementView();
        Scene scene = new Scene(userManagementView);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("User Management");
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }

}