package MainRoot;

import Control.LogInController;
import View.LogInView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LogInFormMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        LogInController control=new LogInController(primaryStage);
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(control.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
