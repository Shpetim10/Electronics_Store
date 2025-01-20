package MainRoot;

import Control.AddCashierControl;
import View.UserManagementView.AddCashier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCashierMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the UserAdd view
        AddCashier userAddView = new AddCashier();

        // Attach the controller to the view
        AddCashierControl control = new AddCashierControl(userAddView);

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
