package MainRoot;

import View.NotificationPanelView;
import javafx.application.Application;
import javafx.stage.Stage;


public class NotificationPanelMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage){
        NotificationPanelView view=new NotificationPanelView();
        primaryStage.setTitle("Notification");
        primaryStage.setScene(view.createScene());
        primaryStage.setMaximized(false);
        primaryStage.show();
    }
}
