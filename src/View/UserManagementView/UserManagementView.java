package View.UserManagementView;



import MainRoot.UserManagementMain;

import View.Design;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.control.Button;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import Control.UserManagementController;



public class UserManagementView extends GridPane implements Design {

    private BorderPane temporaryPane = new BorderPane(); // Temporary pane for dynamic content

    private Button addUserBtn;

    private Button managePermissionsBtn;

    private Button userTableBtn;



    public UserManagementView() {

        setUpView();

        new UserManagementController(this); // Initialize the controller

    }



    public void setUpView() {

        // Configure the main layout

        this.setPadding(new Insets(20, 20, 20, 20));

        this.setHgap(20);

        this.setVgap(20);

        this.setStyle("-fx-background-color: white;" +

                "-fx-border-radius: 10;" +

                "-fx-background-radius: 10;" +

                "-fx-border-color: yellowgreen;" +

                "-fx-border-width: 1;");

        this.setAlignment(Pos.CENTER);



        // Temporary pane configuration

        temporaryPane.setPrefSize(1050, 900);

        this.add(temporaryPane, 1, 0);



        // Create buttons with icons

        addUserBtn = createCustomizedButtonForVBox("/Images/addUserIcon.png", "Add User");

        managePermissionsBtn = createCustomizedButtonForVBox("/Images/managePermissions.png", "Manage Permissions");

        userTableBtn = createCustomizedButtonForVBox("/Images/viewUsers.png", "View Users");



        // VBox to hold buttons

        VBox buttonBox = new VBox(10);

        buttonBox.setAlignment(Pos.CENTER);

        buttonBox.getChildren().addAll(addUserBtn, managePermissionsBtn, userTableBtn);



        // Add the button box to the main grid

        this.add(buttonBox, 0, 0);





    }



    public Button createCustomizedButtonForVBox(String imagePath, String text) {

        Button button = new Button();

        button.setPrefWidth(150);

        button.setPrefHeight(100);

        button.setStyle("-fx-background-color: rgba(167,246,8,0.1);" +

                "-fx-font-weight: bold;" +

                "-fx-font-size: 15;" +

                "-fx-font-family: Bahnschrift;");

        ImageView icon = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));

        icon.setFitHeight(40);

        icon.setFitWidth(40);

        button.setGraphic(icon);

        button.setText(text);

        return button;

    }



    // Getters for buttons and temporary pane

    public BorderPane getTemporaryPane() {

        return temporaryPane;

    }



    public Button getAddUserBtn() {

        return addUserBtn;

    }



    public Button getManagePermissionsBtn() {

        return managePermissionsBtn;

    }



    public Button getUserTableBtn() {

        return userTableBtn;

    }





}


