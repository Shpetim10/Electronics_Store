package View.UserManagementView;

import Database.Database;
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
    private CheckBox billingSystemCB;
    private CheckBox productInformationCB;
    private CheckBox notificationPanelCB;
    private CheckBox viewAllBillsCB;
    private CheckBox supplierManagementCB;
    private CheckBox inventoryManagementCB;
    private CheckBox userManagementCB;
    private CheckBox reportGeneratorCB;
    private CheckBox viewAllReportsCB;
    private CheckBox permissionGrantingCB;

    public PermissionGrantingView() {
        this.table = new TableView<>();
        initializeUI();
    }

    private void initializeUI() {
        this.setPadding(new Insets(20, 20, 20, 200));
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
//        VBox sectorCashierVBox = createSectorCashier();

        // Add components to the main layout
        this.getChildren().addAll(tableSearch, permissionsVBox);
    }

    private void setupTable() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(80);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(120);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setMinWidth(150);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(160);
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
        roleColumn.setMinWidth(185);
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
    private ObservableList<User> getSampleData() {
        ArrayList<User> users = new ArrayList<>();

        // Add all users from different roles to the list
        users.addAll(Database.getDatabase().getCashiers());
        users.addAll(Database.getDatabase().getManagers());
        users.addAll(Database.getDatabase().getAdministrators());


        // Convert to ObservableList
        return FXCollections.observableArrayList(users);
    }

    private VBox createPermissionsCheckboxes() {
        VBox permissionsVBox = new VBox(10);
        permissionsVBox.setAlignment(Pos.CENTER_LEFT);
        permissionsVBox.setSpacing(10);

        //Create checkboxes
        billingSystemCB = new CheckBox("BILLING SYSTEM");
        billingSystemCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        billingSystemCB.setSelected(false);

        productInformationCB = new CheckBox("PRODUCT INFORMATION");
        productInformationCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        productInformationCB.setSelected(false);

        notificationPanelCB = new CheckBox("NOTIFICATION PANEL");
        notificationPanelCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        notificationPanelCB.setSelected(false);

        viewAllBillsCB = new CheckBox("VIEW ALL BILLS");
        viewAllBillsCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        viewAllBillsCB.setSelected(false);

        supplierManagementCB = new CheckBox("SUPPLIER MANAGEMENT");
        supplierManagementCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        supplierManagementCB.setSelected(false);

        inventoryManagementCB = new CheckBox("INVENTORY MANAGEMENT");
        inventoryManagementCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        inventoryManagementCB.setSelected(false);

        userManagementCB = new CheckBox("USER MANAGEMENT");
        userManagementCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        userManagementCB.setSelected(false);

        reportGeneratorCB = new CheckBox("REPORT GENERATOR");
        reportGeneratorCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        reportGeneratorCB.setSelected(false);

        viewAllReportsCB = new CheckBox("VIEW ALL REPORTS");
        viewAllReportsCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        viewAllReportsCB.setSelected(false);

        permissionGrantingCB = new CheckBox("PERMISSION GRANTING");
        permissionGrantingCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 15;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        permissionGrantingCB.setSelected(false);

        permissionsVBox.getChildren().addAll(billingSystemCB, productInformationCB, notificationPanelCB,
                viewAllBillsCB, supplierManagementCB, inventoryManagementCB, userManagementCB,
                reportGeneratorCB, viewAllReportsCB, permissionGrantingCB);

        return permissionsVBox;
    }



    // Create checkboxes for permissions
//        for (Permission p : Permission.values()) {
//            CheckBox checkBox = new CheckBox(p.toString());
//            checkBox.setStyle("-fx-border-radius: 2;" +
//                    "-fx-font-size: 15;" +
//                    "-fx-border-color: yellowgreen;" +
//                    "-fx-border-width: 1;" +
//                    "-fx-background-color: white;" +
//                    "-fx-text-fill: green;");
//            permissionsVBox.getChildren().add(checkBox);
//            permissionsCheckBoxes.add(checkBox); // Store in the list for later access
//        }
//        return permissionsVBox;
//    }

    /*
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

*/




//    private ArrayList<Boolean> getCashierPermissions() {
//        ArrayList<Boolean> permissions = new ArrayList<>();
//        for (int i = 0; i < Permission.values().length; i++) {
//            permissions.add(i<4);
//        }
//        return permissions;
//    }
//
//    private ArrayList<Boolean> getManagerPermissions() {
//        ArrayList<Boolean> permissions = new ArrayList<>();
//        for (int i = 0; i < Permission.values().length; i++) {
//              permissions.add(i > 2);
//        }
//        return permissions;
//    }
//
//    private ArrayList<Boolean> getAdministratorPermissions() {
//        ArrayList<Boolean> permissions = new ArrayList<>();
//        for (int i = 0; i < Permission.values().length; i++) {
//            permissions.add(i > 0 );
//        }
//        return permissions;
//    }

    public TableView<User> getTable() {
        return table;
    }

    public ArrayList<CheckBox> getPermissionsCheckBoxes() {
        return permissionsCheckBoxes;
    }

    public SearchBoxPane getSearch() {
        return search;
    }

    public CheckBox getBillingSystemCB() {
        return billingSystemCB;
    }

    public void setBillingSystemCB(CheckBox billingSystemCB) {
        this.billingSystemCB = billingSystemCB;
    }

    public CheckBox getProductInformationCB() {
        return productInformationCB;
    }

    public void setProductInformationCB(CheckBox productInformationCB) {
        this.productInformationCB = productInformationCB;
    }

    public CheckBox getNotificationPanelCB() {
        return notificationPanelCB;
    }

    public void setNotificationPanelCB(CheckBox notificationPanelCB) {
        this.notificationPanelCB = notificationPanelCB;
    }

    public CheckBox getViewAllBillsCB() {
        return viewAllBillsCB;
    }

    public void setViewAllBillsCB(CheckBox viewAllBillsCB) {
        this.viewAllBillsCB = viewAllBillsCB;
    }

    public CheckBox getSupplierManagementCB() {
        return supplierManagementCB;
    }

    public void setSupplierManagementCB(CheckBox supplierManagementCB) {
        this.supplierManagementCB = supplierManagementCB;
    }

    public CheckBox getInventoryManagementCB() {
        return inventoryManagementCB;
    }

    public void setInventoryManagementCB(CheckBox inventoryManagementCB) {
        this.inventoryManagementCB = inventoryManagementCB;
    }

    public CheckBox getUserManagementCB() {
        return userManagementCB;
    }

    public void setUserManagementCB(CheckBox userManagementCB) {
        this.userManagementCB = userManagementCB;
    }

    public CheckBox getReportGeneratorCB() {
        return reportGeneratorCB;
    }

    public void setReportGeneratorCB(CheckBox reportGeneratorCB) {
        this.reportGeneratorCB = reportGeneratorCB;
    }

    public CheckBox getViewAllReportsCB() {
        return viewAllReportsCB;
    }

    public void setViewAllReportsCB(CheckBox viewAllReportsCB) {
        this.viewAllReportsCB = viewAllReportsCB;
    }

    public CheckBox getPermissionGrantingCB() {
        return permissionGrantingCB;
    }

    public void setPermissionGrantingCB(CheckBox permissionGrantingCB) {
        this.permissionGrantingCB = permissionGrantingCB;
    }
}
