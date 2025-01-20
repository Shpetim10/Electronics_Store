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


