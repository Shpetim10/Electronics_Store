package MainRoot;


import Control.InventoryController;
import Control.SupplierControl;
import View.InventoryManagementView;
import View.SupplierView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SupplierViewMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        SupplierView view=new SupplierView();
        primaryStage.setTitle("Supplier");
        SupplierControl control=new SupplierControl(view);
        primaryStage.setScene(new Scene(view));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}

