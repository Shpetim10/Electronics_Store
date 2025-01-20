package MainRoot;

import Control.AddUserControl;
import View.UserManagementView.AddUser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddUserMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the UserAdd view
        AddUser userAddView = new AddUser();

        // Attach the controller to the view
        AddUserControl control = new AddUserControl(userAddView);

        // Configure the primary stage
        primaryStage.setTitle("Add Cashier");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(userAddView, 800, 600)); // Set the size of the view
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
