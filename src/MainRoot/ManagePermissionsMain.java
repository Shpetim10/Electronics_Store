package MainRoot;

import Control.ManagePermissionsController;
import View.UserManagementView.PermissionGrantingView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagePermissionsMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        PermissionGrantingView view = new PermissionGrantingView();
        new ManagePermissionsController(view);

        Scene scene = new Scene(view, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Permission Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
