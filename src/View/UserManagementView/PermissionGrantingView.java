package View.UserManagementView;

import Model.*;
import View.Design;
import View.SearchBoxPane;
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

import java.util.ArrayList;

public class PermissionGrantingView extends HBox implements Design {

    private final TableView<User> table;
    private final ArrayList<CheckBox> permissionsCheckBoxes = new ArrayList<>();
    private SearchBoxPane search;

    public PermissionGrantingView() {
        this.table = new TableView<>();
        initializeUI();
    }

    private void initializeUI() {
        this.setPadding(new Insets(20, 20, 20, 150));
        this.setSpacing(20);
        this.setStyle("-fx-background-color: rgba(167,246,8,0.3);");

        // Set up the table
        setupTable();

        // Table and SearchBar
        search = new SearchBoxPane();

        VBox tableSearch = new VBox();
        tableSearch.setSpacing(10);
        tableSearch.getChildren().addAll(search, table);
        tableSearch.setAlignment(Pos.CENTER_LEFT);

        // Create a VBox for checkboxes
        VBox permissionsVBox = createPermissionsCheckboxes();

        // Create a VBox for sectors
        VBox sectorCashierVBox = createSectorCashier();

        // Add components to the main layout
        this.getChildren().addAll(tableSearch, permissionsVBox, sectorCashierVBox);
    }

    private void setupTable() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(80);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<User, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setMinWidth(150);
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<User, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(120);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<User, EmployeeRole> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(EmployeeRole role) {
                return role != null ? role.toString() : "";
            }

            @Override
            public EmployeeRole fromString(String string) {
                return EmployeeRole.valueOf(string.toUpperCase());
            }
        }));

        table.getColumns().addAll(idColumn, nameColumn, lastNameColumn, usernameColumn, roleColumn);

        table.setEditable(false);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(getSampleData());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-background-color: rgba(167,246,8,0.15);" +
                "-fx-border-color: darkgreen;" +
                "-fx-font-size: 20;");
    }

    private VBox createPermissionsCheckboxes() {
        VBox permissionsVBox = new VBox(10);
        permissionsVBox.setAlignment(Pos.CENTER_LEFT);
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
            permissionsCheckBoxes.add(checkBox); // Store in the list for later access
        }
        return permissionsVBox;
    }

    private VBox createSectorCashier() {
        VBox cashierSectorVBox = new VBox(10);
        cashierSectorVBox.setAlignment(Pos.CENTER_LEFT);
        cashierSectorVBox.setSpacing(10);
        ToggleGroup toggleGroup = new ToggleGroup();
        for (SectorType st : SectorType.values()) {
            RadioButton radioButton = new RadioButton(st.toString());
            radioButton.setStyle("-fx-border-radius: 2;" +
                    "-fx-font-size: 15;" +
                    "-fx-border-color: yellowgreen;" +
                    "-fx-border-width: 1;" +
                    "-fx-background-color: white;" +
                    "-fx-text-fill: green;");

            radioButton.setToggleGroup(toggleGroup);
            cashierSectorVBox.getChildren().add(radioButton);
        }

        return cashierSectorVBox;
    }




    private ObservableList<User> getSampleData() {
        // Replace this with actual data fetching
        return FXCollections.observableArrayList(
                new Cashier(1, "John", "Doe", "jdoe", EmployeeRole.CASHIER, getCashierPermissions()),
                new Manager(2, "Jane", "Smith", "jsmith", EmployeeRole.MANAGER, getManagerPermissions()),
                new Administrator(3, "Arjan", "Muka", "armuka", EmployeeRole.ADMINISTRATOR, getAdministratorPermissions())
        );
    }

    private ArrayList<Boolean> getCashierPermissions() {
        ArrayList<Boolean> permissions = new ArrayList<>();
        for (int i = 0; i < Permission.values().length; i++) {
            permissions.add(i<4);
        }
        return permissions;
    }

    private ArrayList<Boolean> getManagerPermissions() {
        ArrayList<Boolean> permissions = new ArrayList<>();
        for (int i = 0; i < Permission.values().length; i++) {
              permissions.add(i > 2);
        }
        return permissions;
    }

    private ArrayList<Boolean> getAdministratorPermissions() {
        ArrayList<Boolean> permissions = new ArrayList<>();
        for (int i = 0; i < Permission.values().length; i++) {
            permissions.add(i > 0 );
        }
        return permissions;
    }

    public TableView<User> getTable() {
        return table;
    }

    public ArrayList<CheckBox> getPermissionsCheckBoxes() {
        return permissionsCheckBoxes;
    }
}
