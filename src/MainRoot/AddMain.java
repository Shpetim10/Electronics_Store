package MainRoot;

import Control.AddController;
import View.AddView;
import View.AddView;
import javafx.application.Application;
import javafx.stage.Stage;


public class AddMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        AddView view=new AddView();
        primaryStage.setTitle("Add/Edit");
        AddController addController = new AddController(view);
        primaryStage.setScene(view.createScene());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}