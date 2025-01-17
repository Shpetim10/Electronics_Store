package MainRoot;



import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

import View.UserManagementView.UserManagementView;



public class UserManagementMain extends Application {

    @Override

    public void start(Stage primaryStage) {

        UserManagementView userManagementView = new UserManagementView();

        Scene scene = new Scene(userManagementView); // Set appropriate size

        primaryStage.setScene(scene);

        primaryStage.setMaximized(true);

        primaryStage.setTitle("User Management");

        primaryStage.show();

    }



    public static void main(String[] args) {

        launch(args);

    }

}