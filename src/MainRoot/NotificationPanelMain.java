package MainRoot;

import Control.NotificationPanelController;
import Model.*;
import View.NotificationPanelView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class NotificationPanelMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        ArrayList<Notification> notifications = new ArrayList<>();
        Notification notification=new Notification(NotificationType.LOW_STOCK,"Test");
        notifications.add(notification);


//        Manager manager=new Manager(1,"Arlin","B","1234",
//                "abcd","abbccd","0132546", LocalDate.now(),"",EmployeeRole.MANAGER,new ArrayList<>(),true,notifications,new ArrayList<>());
        //NotificationPanelController controller=new NotificationPanelController(manager);
        primaryStage.setTitle("Notification");
        //primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
