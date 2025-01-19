package View;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LogInView extends Parent implements Design {

        public Scene createScene() {
            // Main layout
            VBox mainLayout = new VBox(15);
            mainLayout.setAlignment(Pos.CENTER);
            mainLayout.setStyle("-fx-background-color: rgba(167,246,8,0.15);");

            // Profile icon
            Label profileIcon = new Label();
            profileIcon.setStyle("-fx-background-color: green; -fx-border-radius: 50; -fx-background-radius: 50;");
            profileIcon.setPrefSize(100, 100);
            profileIcon.setAlignment(Pos.CENTER);

            Label profileSymbol = new Label("👤");
            profileSymbol.setFont(new Font("Arial", 32));
            profileSymbol.setTextFill(Color.WHITE);
            StackPane profileIconStack = new StackPane(profileIcon, profileSymbol);

            // Email field
            TextField emailField = new TextField();
            emailField.setPromptText("Email ID");
            emailField.setPrefWidth(250);
            emailField.setStyle("-fx-background-radius: 5; -fx-border-color: lightgray; -fx-padding: 5;");

            // Password field
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            passwordField.setPrefWidth(250);
            passwordField.setStyle("-fx-background-radius: 5; -fx-border-color: lightgray; -fx-padding: 5;");

            // Remember me and Forgot Password
            CheckBox rememberMeCheckBox = new CheckBox("Remember me");
            Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
            forgotPasswordLink.setStyle("-fx-text-fill: gray; -fx-font-size: 12;");

            HBox optionsLayout = new HBox(10, rememberMeCheckBox, forgotPasswordLink);
            optionsLayout.setAlignment(Pos.CENTER_RIGHT);
            optionsLayout.setPrefWidth(250);

            // Login button
            Button loginButton = new Button("LOGIN");
            loginButton.setPrefWidth(250);
            loginButton.setStyle(
                    "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 14; -fx-background-radius: 5;");


            // Button action
            loginButton.setOnAction(event -> {
                String email = emailField.getText();
                String password = passwordField.getText();

                if (validateLogin(email, password)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Welcome!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid credentials. Please try again.");
                    alert.showAndWait();
                }
            });

            // Assemble the layout
            mainLayout.getChildren().addAll(profileIconStack, emailField, passwordField, optionsLayout, loginButton);
            mainLayout.setFillWidth(false);

            return new Scene(mainLayout);
        }

        // Validate login credentials
        private boolean validateLogin(String username, String password) {
            // Replace with actual validation logic
            return "user".equals(username) && "password".equals(password);
        }
    }


