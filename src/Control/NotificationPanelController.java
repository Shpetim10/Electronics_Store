package Control;

import Model.Notification;
import Model.User;
import View.NotificationPanelView;
import javafx.scene.control.Button;

public class NotificationPanelController {

    private final NotificationPanelView view = new NotificationPanelView();
    private User user;

    public NotificationPanelController(User user) {
        this.user = user;
        setupDisplayNotification();
        setupClearNotification();

    }

    public Button setupDisplayNotification()
    {
        Button displayButton = view.getDisplayNotificationButton();

        this.view.getDisplayNotificationButton().setOnAction(e ->{
            view.getNotificationDisplay().getChildren().clear();
            for(Notification notification:user.getNotifications() )
            {
                view.getNotificationDisplay().getChildren().add(view.createNotification(
                        notification.getSubject(),notification.getMessage(),notification.getDateCreated()));
            }
        } );
        return displayButton;
    }
    public Button setupClearNotification()
    {
        Button clearButton = view.getClearNotification();
        this.view.getClearNotification().setOnAction(e -> {
            view.getNotificationDisplay().getChildren().clear();

            user.getNotifications().clear();

        });
        return clearButton;
    }

    public NotificationPanelView getView() {
        return view;
    }

    public User getUser() {
        return user;
    }
}