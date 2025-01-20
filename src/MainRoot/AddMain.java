package MainRoot;

import Control.AddProductController;
import View.AddProductView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AddMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        //AddProductView view=new AddProductView();
        primaryStage.setTitle("Add");
        AddProductController addController = new AddProductController();
        primaryStage.setScene(new Scene(addController.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}