package MainRoot;

import Control.AdminManagerViewBillController;
import Control.ViewAllBillsController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ViewAllBillsMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {


        ViewAllBillsController control=new ViewAllBillsController();


        primaryStage.setTitle("View All Bills");
        primaryStage.setScene(new Scene(control.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
