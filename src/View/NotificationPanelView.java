package View;
import Model.NotificationType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Main application with embedded notification utility
public class NotificationPanelView implements Design {

    // Utility method for showing notifications
    public static void showNotification(String title, String message, NotificationType type) {
        // Create and configure the Alert

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);  // No header
        alert.setContentText(message);

        // Display the Alert dialog
        alert.showAndWait();
    }


    public Scene createScene() {
        // Example button to trigger a notification
        Button notifyButton = new Button("Show Notification");
        notifyButton.setOnAction(e ->
                showNotification("Information", "You need to refund!", NotificationType.REFUND)
        );

        // Layout for the button
        StackPane root = new StackPane(notifyButton);
        Scene scene = new Scene(root, 300, 200);

        // Set up the Stage
        createScene().setTitle("Notification Panel Example");
        createScene().setScene(scene);
        createScene().show();
    }
}



