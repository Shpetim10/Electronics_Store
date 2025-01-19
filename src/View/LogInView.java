package View;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class LogInView extends VBox implements Design {
        private final TextField usernameField = createTextField("Username...");
        private final PasswordField passwordField = createPasswordField();
        //private final CheckBox rememberMeCheckBox = new CheckBox("Remember me");
        private final Button loginButton = createGeneralButton("Log In");

    public LogInView() {
        setUpView();
    }

    public void setUpView(){
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        this.setSpacing(5);

        Label logInTitle=createAlignedGreenBoldLabel("Log In");
        Label usernameTitle=createAlignedBlackBoldLabel("Username");
        Label passwordTitle=createAlignedBlackBoldLabel("Password");
        Label profileIcon = new Label();
        profileIcon.setStyle("-fx-background-color: green; -fx-border-radius: 50; -fx-background-radius: 50;");
        profileIcon.setPrefSize(200, 200);
        profileIcon.setAlignment(Pos.CENTER);

        ImageView profileImage = new ImageView(new Image("Images/electronicsStoreIcon.png"));
        profileImage.setFitWidth(100);
        profileImage.setFitHeight(100);

        StackPane logoPane=new StackPane();
        logoPane.setStyle("-fx-background-color: transparent;");
        logoPane.getChildren().addAll(profileIcon,profileImage);

        usernameField.setPrefWidth(250);
        passwordField.setPrefWidth(250);
        loginButton.setPrefWidth(150);
        loginButton.setStyle(
                "-fx-background-color: green;" +
                        " -fx-text-fill: white;" +
                        " -fx-font-size: 14;" +
                        " -fx-background-radius: 5;");

        this.getChildren().addAll(logoPane, logInTitle,usernameTitle,usernameField, passwordTitle, passwordField,loginButton);
        this.setFillWidth(false);
    }
    public Scene createScene() {
            // Main layout


            // Profile icon

            //profileIconStack.getChildren().setAll(profileIcon, profileImage);

            // Email field
            ///TextField usernameField = new TextField();
            ///usernameField.setPromptText("Username");

            //usernameField.setStyle("-fx-background-radius: 5; -fx-border-color: lightgray; -fx-padding: 5;");

            // Password field

            //passwordField.setPromptText("Password");
            //passwordField.setPrefWidth(250);
            //passwordField.setStyle("-fx-background-radius: 5; -fx-border-color: lightgray; -fx-padding: 5;");

            // Remember me and Forgot Password

//            Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
//            forgotPasswordLink.setStyle("-fx-text-fill: gray; -fx-font-size: 12;");

//            HBox optionsLayout = new HBox(10, rememberMeCheckBox, forgotPasswordLink);
//            optionsLayout.setAlignment(Pos.CENTER_RIGHT);
//            optionsLayout.setPrefWidth(250);

            // Login button




            // Button action
            loginButton.setOnAction(event -> {
                String email = usernameField.getText();
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

//            StackPane root = new StackPane();
//            root.setAlignment(Pos.CENTER);
//            root.setPadding(new Insets(50));
//
//            root.getChildren().add(passwordField);

            // Assemble the layout


            class VisiblePasswordFieldSkin extends TextFieldSkin {

                private final Button actionButton = new Button("View");
                private final SVGPath actionIcon = new SVGPath();

                private boolean mask = true;

                public VisiblePasswordFieldSkin(PasswordField textField) {

                    super(textField);

                    actionButton.setId("actionButton");
                    actionButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    actionButton.setPrefSize(30,30);
                    actionButton.setFocusTraversable(false);
                    actionButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(0))));

                    getChildren().add(actionButton);
                    actionButton.setCursor(Cursor.HAND);
                    actionButton.toFront();

                    actionIcon.setContent(PasswViewIcon.VIEWER.getContent());
                    actionButton.setGraphic(actionIcon);

                    actionButton.setVisible(false);

                    actionButton.setOnMouseClicked(event -> {

                        if(mask) {
                            actionIcon.setContent(PasswViewIcon.VIEWER_OFF.getContent());
                            mask = false;
                        } else {
                            actionIcon.setContent(PasswViewIcon.VIEWER.getContent());
                            mask = true;
                        }
                        textField.setText(textField.getText());

                        textField.end();

                    });

                    textField.textProperty().addListener((observable, oldValue, newValue) -> actionButton.setVisible(!newValue.isEmpty()));

                }

                @Override
                protected void layoutChildren(double x, double y, double w, double h) {
                    super.layoutChildren(x, y, w, h);

                    layoutInArea(actionButton, x, y, w, h,0, HPos.RIGHT, VPos.CENTER);
                }

                @Override
                protected String maskText(String txt) {
                    if (getSkinnable() instanceof PasswordField && mask) {
                        int n = txt.length();
                        StringBuilder passwordBuilder = new StringBuilder(n);
                        for (int i = 0; i < n; i++) {
                            passwordBuilder.append('\u2022'); // Unicode for the bullet character
                        }
                        return passwordBuilder.toString();
                    } else {

                        return txt;
                    }
                }
            }

            passwordField.setSkin(new VisiblePasswordFieldSkin(passwordField));

            return new Scene(this);
        }


        // Validate login credentials
        private boolean validateLogin(String username, String password) {
            // Replace with actual validation logic
            return "user".equals(username) && "password".equals(password);
        }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }
}


