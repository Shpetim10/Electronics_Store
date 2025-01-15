package MainRoot;

import View.InventoryManagementView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class InvetoryManagementMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        InventoryManagementView view=new InventoryManagementView();
        primaryStage.setTitle("Main");
        primaryStage.setScene(new Scene(view));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
