package MainRoot;

import Model.User;
import View.UserMainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainUser extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage){
        UserMainView view=new UserMainView();

        primaryStage.setTitle("Header");
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }
}
