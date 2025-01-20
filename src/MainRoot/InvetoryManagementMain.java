package MainRoot;


import Control.InventoryController;
import View.InventoryManagementView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class InvetoryManagementMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        //InventoryManagementView view=new InventoryManagementView();
        primaryStage.setTitle("Inventory");
        InventoryController control=new InventoryController();
        primaryStage.setScene(new Scene(control.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
