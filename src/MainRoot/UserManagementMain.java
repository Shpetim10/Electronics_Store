package MainRoot;

import Control.UserManagementController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import View.UserManagementView.UserManagementView;

public class UserManagementMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the view
        UserManagementView view = new UserManagementView();

        // Create and initialize the controller
        UserManagementController controller = new UserManagementController();

        // Set up the scene and stage
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
