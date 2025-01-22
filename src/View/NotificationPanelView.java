package View;

import Model.NotificationType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;

public class NotificationPanelView extends VBox implements Design {
    private Button displayNotificationButton = createGeneralButton("Display Notifications");
    private Button clearNotification = createGeneralButton("Clear Notification");
    private ScrollPane notificationPanel = new ScrollPane();
    private VBox notificationDisplay = new VBox(10);

    public NotificationPanelView() {setUpView();}

    public void setUpView() {

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-padding: 10;" +
                " -fx-background-color:transparent;");
        this.notificationPanel.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        this.notificationDisplay.setStyle("-fx-background-color:rgba(167,246,8,0.15);");
        this.notificationDisplay.setAlignment(Pos.CENTER);

        this.notificationPanel.setContent(notificationDisplay);

        this.setSpacing(20);
        this.notificationDisplay.setAlignment(Pos.TOP_CENTER);

        this.getChildren().addAll(displayNotificationButton,clearNotification,notificationPanel);
    }

    public VBox createNotification(NotificationType subject, String message, LocalDateTime dateCreated)
    {
        VBox notificationMessage = new VBox(10);
        notificationMessage.setAlignment(Pos.CENTER);
        notificationMessage.setStyle("-fx-background-color:white;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;");
        Label title = createAlignedGreenBoldLabel("New Notification !");
        Label displayTime = createAlignedBlackLabel("Date :"+dateCreated.getDayOfMonth()+" "+dateCreated.getMonth()+" "+dateCreated.getYear()+"\t"+dateCreated.getHour()+" : "+dateCreated.getMinute());
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