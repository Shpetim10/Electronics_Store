package View;

import Model.Notification;
import Model.NotificationType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationPanelView implements Design {

    private VBox notificationPanel;


    public Scene createScene() {
        // Create the main notification panel
        notificationPanel = new VBox(10);
        notificationPanel.setAlignment(Pos.CENTER);
        notificationPanel.setStyle("-fx-padding: 10; -fx-background-color:white; -fx-border-color: #cccccc; -fx-border-width: 1;");
        notificationPanel.setPrefHeight(60);

        // Add a button to generate notifications
        Button addNotificationButton = new Button("Add Notification");
        addNotificationButton.setOnAction(event -> addNotification("This is a new notification!"));


        // Add a button to clear notifications
        Button clearNotificationsButton = new Button("Clear Notifications");
        clearNotificationsButton.setOnAction(event -> notificationPanel.getChildren().clear());

        // Layout for the main scene
        VBox root = new VBox(10, addNotificationButton, clearNotificationsButton, notificationPanel);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-padding: 15; -fx-background-color:rgba(167,246,8,0.15) ;");



        // Setup the scene and stage
        return new Scene(root,400,300);
    }

    public void addNotification(String message) {
        Label notificationLabel = new Label(message);
        notificationLabel.setStyle("-fx-text-fill:white;-fx-padding: 5; -fx-background-color:green; -fx-border-color: #b2ebf2; -fx-border-width: 1; -fx-border-radius: 3; -fx-background-radius: 3;");
        notificationPanel.getChildren().add(notificationLabel);
    }
    public VBox createNotification(NotificationType subject, String message, Date dateCreated)
    {
        VBox notificationMessage = new VBox(10);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormat.format(dateCreated);

        // Create a Label with the full message
        String fullMessage = subject.name() + ": " + message + ", " + formattedTime;
        Label notificationLabel = new Label(fullMessage);
        notificationLabel.setStyle("-fx-text-fill:white ; -fx-font-size: 14;");

        // Add the label to the HBox
        notificationMessage.getChildren().add(notificationLabel);

        return notificationMessage;
    }

    /*for(Notification notifications:users.Notifications)
    {
        VBox vBox=new VBox();
        createNotification();
    }
     */
}
