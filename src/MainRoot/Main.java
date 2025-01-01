package MainRoot;

import View.Design;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application implements Design {
    public static void main(String[] args) {
       Application.launch();
    }

    @Override
    public void start(Stage primaryStage){
        Scene scene=new Scene(createGeneralButton("Hello"));
        primaryStage.setTitle("MainRoot.Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
