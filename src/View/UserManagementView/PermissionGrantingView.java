package View.UserManagementView;

import Model.EmployeeRole;
import Model.Permission; // Ensure you have the correct import for Permission
import Model.User;
import View.SearchBoxPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class PermissionGrantingView extends HBox {
    private TableView<User> table;

    private final SearchBoxPane searchBox=new SearchBoxPane("Search User...");
    private TableColumn<User, Integer> idColumn;
    private TableColumn<User, String> nameColumn;
    private TableColumn<User, String> lastNameColumn;
    private TableColumn<User, String> usernameColumn;
    private TableColumn<User, String> emailColumn;
    private TableColumn<User, String> phoneNumberColumn;
    private TableColumn<User, Double> salaryColumn;
    private TableColumn<User, EmployeeRole> roleColumn;

    public PermissionGrantingView() {
        initializeUI();
    }

    private void initializeUI() {
        this.setPadding(new Insets(20,20,20,150));
        this.setSpacing(20);
        this.setStyle("-fx-background-color: rgba(167,246,8,0.3);");

        // Search bar


        // Create the table
        table = new TableView<>();
        setupTable();

        // Table and SearchBar
        VBox tableSearch = new VBox();
        tableSearch.setSpacing(10);
        tableSearch.getChildren().addAll(searchBox,table);
        tableSearch.setAlignment(Pos.CENTER_LEFT);

        // Create a HBox for checkboxes
        VBox permissionsVBox = createPermissionsCheckboxes();

        // Add components to the main layout
        this.getChildren().addAll(tableSearch, permissionsVBox);
    }

    private void setupTable() {
        idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(80);
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
        emailColumn.setMinWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setMinWidth(150);
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(120);
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

        table.getColumns().addAll(idColumn, nameColumn, lastNameColumn, usernameColumn, emailColumn, phoneNumberColumn, roleColumn, salaryColumn);

        table.setEditable(false);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-background-color: rgba(167,246,8,0.15);" +
                "-fx-border-color: darkgreen;"+
                "-fx-font-size: 20;");

        // Sample data
        ObservableList<User> data = FXCollections.observableArrayList(
        );

        table.setItems(data);
    }

    private VBox createPermissionsCheckboxes() {
        VBox permissionsVBox = new VBox(10);
        permissionsVBox.setAlignment(Pos.CENTER);
        permissionsVBox.setSpacing(10);

        // Create checkboxes for permissions
        for (Permission p : Permission.values()) {
            CheckBox checkBox = new CheckBox(p.toString());
            checkBox.setStyle("-fx-border-radius: 2;" +
                    "-fx-font-size: 15;" +
                    "-fx-border-color: yellowgreen;" +
                    "-fx-border-width: 1;" +
                    "-fx-background-color: white;" +
                    "-fx-text-fill: green;");
            permissionsVBox.getChildren().add(checkBox);
        }

        return permissionsVBox;
    }
}