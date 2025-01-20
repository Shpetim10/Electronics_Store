package MainRoot;


import Control.SupplierControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SupplierViewMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        // view=new SupplierView();
        primaryStage.setTitle("Supplier");
        SupplierControl control=new SupplierControl();
        primaryStage.setScene(new Scene(control.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}

