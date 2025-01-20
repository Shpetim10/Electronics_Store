package View;

import Model.Notification;
import Model.NotificationType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class NotificationPanelView extends VBox implements Design {
    private Button displayNotificationButton = createGeneralButton("Display Notifications");
    private Button clearNotification = createGeneralButton("Clear Notification");
    private ScrollPane notificationPanel = new ScrollPane();
    private VBox notificationDisplay = new VBox(10);

    public NotificationPanelView() {setUpView();}

    public void setUpView() {

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-padding: 10; -fx-background-color:transparent;");
        notificationPanel.setStyle("-fx-background-color:transparent;-fx-border-color:yellowgreen;-fx-border-width:1;");
        this.notificationDisplay.setStyle("-fx-background-color:transparent");
        this.notificationDisplay.setAlignment(Pos.TOP_CENTER);
        this.notificationPanel.setContent(notificationDisplay);

        this.getChildren().addAll(displayNotificationButton,clearNotification,notificationPanel);
    }


    public void addNotification(String message) {
        Label notificationLabel = new Label(message);
        notificationLabel.setStyle("-fx-text-fill:white;-fx-padding: 5; -fx-background-color:green; -fx-border-color: #b2ebf2; -fx-border-width: 1; -fx-border-radius: 3; -fx-background-radius: 3;");
    }
    public VBox createNotification(NotificationType subject, String message, LocalDateTime dateCreated)
    {
        VBox notificationMessage = new VBox(10);
        notificationMessage.setStyle("-fx-background-color:white;");
        Label title = createAlignedGreenBoldLabel("New Notification !");
        Label displayTime = createAlignedBlackLabel("Date :"+dateCreated.toString());
        Label displaySubject= createAlignedGreenBoldLabel(subject.toString());
        Label displayMessage = createAlignedBlackBoldLabel(message);

        notificationMessage.getChildren().addAll(title,displaySubject,displayMessage,displayTime);

        return notificationMessage;
    }

    public Button getDisplayNotificationButton() {
        return displayNotificationButton;
    }

    public Button getClearNotification() {
        return clearNotification;
    }

    public ScrollPane getNotificationPanel() {
        return notificationPanel;
    }
    public VBox getNotificationDisplay() {
        return notificationDisplay;
    }
}

