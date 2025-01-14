package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class LogInView implements Design {

    public Scene createScene() {
        // Create elements
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Log In");

        // Set font and style for labels and buttons
        userLabel.setFont(new Font("Arial", 14));
        passLabel.setFont(new Font("Arial", 14));
        loginButton.setFont(new Font("Arial", 14));

        // Create GridPane for layout
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Add elements to the grid
        grid.add(userLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Handle login button click
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (validateLogin(username, password)) {
                //showAlert("Login Successful", "Welcome " + username + "!");
            } else {
                //showAlert("Login Failed", "Invalid username or password.");
            }
        });

        // Create and return the scene
        return new Scene(grid, 400, 300);
    }

    // Validate login credentials
    private boolean validateLogin(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}
