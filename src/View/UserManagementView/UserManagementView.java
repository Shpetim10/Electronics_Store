package View.UserManagementView;

import Database.Database;
import MainRoot.UserManagementMain;
import Model.*;
import View.Design;
import View.SearchBoxPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Control.UserManagementController;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;


public class UserManagementView extends GridPane implements Design {
    private TableView<User> table;

    private final TableColumn<User, Integer> idColumn;
    private final TableColumn<User, String> nameColumn;
    private final TableColumn<User, String> lastNameColumn;
    private final TableColumn<User, String> usernameColumn;
    private final TableColumn<User, String> emailColumn;
    private final TableColumn<User, String> phoneNumberColumn;
    private final TableColumn<User, Double> salaryColumn;
    private final TableColumn<User, EmployeeRole> roleColumn;

    private Button addUserBtn;
    private Button managePermissionsBtn;
    private Button userTableBtn;
    private Button deleteBtn;
    private final SearchBoxPane searchBox=new SearchBoxPane("Search User...");

    public UserManagementView() {


        table = new TableView<>();
        table.setEditable(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-background-color: rgba(167,246,8,0.15);" +
                "-fx-border-color: darkgreen;"+
                "-fx-font-size: 20;");
        table.setMinHeight(500);

        // Create columns
        idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setMinWidth(100);
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<EmployeeRole>() {

            @Override
            public String toString(EmployeeRole employeeRole) {
                return "";
            }

            @Override
            public EmployeeRole fromString(String s) {
                return null;
            }
        }));

        // Add columns to the table
        table.getColumns().addAll(idColumn, nameColumn, lastNameColumn, usernameColumn, emailColumn, phoneNumberColumn, roleColumn, salaryColumn);

        table.setItems(getSampleData());

        // Configure the main layout
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setHgap(50);
        this.setVgap(20);
        this.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: rgba(256,246,8,0.3);" +
                "-fx-border-width: 1;");

        this.setAlignment(Pos.CENTER_LEFT);


        VBox searchPane = new VBox();
        searchPane.setAlignment(Pos.CENTER_LEFT);
        searchPane.setSpacing(20);
        searchPane.setPadding(new Insets(80,0,0,0));
        searchPane.getChildren().addAll(searchBox, table);

        this.add(searchPane,1,0);

        // Create buttons with icons
        addUserBtn = createCustomizedButtonForVBox("/Images/addUserIcon.png", "Add User");
        managePermissionsBtn = createCustomizedButtonForVBox("/Images/managePermissions.png", "Manage Permissions");
        userTableBtn = createCustomizedButtonForVBox("/Images/viewUsers.png", "View Users");
        deleteBtn = createCustomizedButtonForVBox("/Images/xIcon.png", "Delete");

        // VBox to hold buttons

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMinSize(200,150);
        buttonBox.getChildren().addAll(addUserBtn, managePermissionsBtn, deleteBtn);

        // Add the button box to the main grid
        this.add(buttonBox, 0, 0);
    }

    private ObservableList<User> getSampleData() {
        ArrayList<User> users = new ArrayList<>();

        // Add all users from different roles to the list
        users.addAll(Database.getDatabase().getCashiers());
        users.addAll(Database.getDatabase().getManagers());
        users.addAll(Database.getDatabase().getAdministrators());

        // Convert to ObservableList
        return FXCollections.observableArrayList(users);
    }


    public Button createCustomizedButtonForVBox(String imagePath, String text) {

        Button button = new Button();
        button.setPrefWidth(250);
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

    public Button getAddUserBtn() {
        return addUserBtn;
    }

    public Button getManagePermissionsBtn() {
        return managePermissionsBtn;
    }

    public Button getUserTableBtn() {
        return userTableBtn;
    }

    public TableView<User> getTable() {
        return table;
    }

    public TableColumn<User, Integer> getIdColumn() {
        return idColumn;
    }

    public TableColumn<User, String> getNameColumn() {
        return nameColumn;
    }

    public TableColumn<User, String> getLastNameColumn() {
        return lastNameColumn;
    }

    public TableColumn<User, String> getUsernameColumn() {
        return usernameColumn;
    }

    public TableColumn<User, String> getEmailColumn() {
        return emailColumn;
    }

    public TableColumn<User, String> getPhoneNumberColumn() {
        return phoneNumberColumn;
    }

    public TableColumn<User, Double> getSalaryColumn() {
        return salaryColumn;
    }

    public TableColumn<User, EmployeeRole> getRoleColumn() {
        return roleColumn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public SearchBoxPane getSearchBox() {
        return searchBox;
    }
}


