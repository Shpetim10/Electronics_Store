package MainRoot;

import View.LogInView;
import javafx.application.Application;
import javafx.stage.Stage;


public class LogInFormMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        LogInView view=new LogInView();
        primaryStage.setTitle("Log in");
        primaryStage.setScene(view.createScene());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
