package MainRoot;

import Control.LogInController;

import Control.UserMainController;
import Database.Database;
import Database.FileHandler;
import Model.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MainUser extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Administrator admin=Database.getDatabase().getAdministrators().getFirst();
        UserMainController controller=new UserMainController(admin);
        Scene scene=new Scene(controller.getView());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Electronics System");

        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
