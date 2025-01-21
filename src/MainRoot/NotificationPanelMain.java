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


        Manager manager = new Manager( 1, "John",  "Doe",  Gender.MALE,
                 LocalDate.of(1980, 5, 15),  85000.00,
                "john.doe",
                 "securePassword123",  "john.doe@example.com",
                 "123-456-7890",
                LocalDate.of(2020, 1, 1),
                 EmployeeRole.MANAGER,
                 "path/to/photo.jpg"  );
        NotificationPanelController controller=new NotificationPanelController(manager);
        primaryStage.setTitle("Notification");
        primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
