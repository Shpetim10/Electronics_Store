package View.UserManagementView;

import Model.Permission; // Ensure you have the correct import for Permission
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PermissionGrantingView extends HBox {
    private TableView<String[]> table;

    public PermissionGrantingView() {
        initializeUI();
    }

    private void initializeUI() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);
        this.setStyle("-fx-background-color: rgba(167,246,8,0.3);");

        // Create the table
        table = new TableView<>();
        setupTable();

        // Create a HBox for checkboxes
        VBox permissionsVBox = createPermissionsCheckboxes();

        // Add components to the main layout
        this.getChildren().addAll(new Label("Manage Permissions"), table, permissionsVBox);
    }

    private void setupTable() {
        TableColumn<String[], String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));

        TableColumn<String[], String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));

        TableColumn<String[], String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));

        TableColumn<String[], String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[3]));

        table.getColumns().addAll(nameColumn, lastNameColumn, idColumn, roleColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Sample data
        ObservableList<String[]> data = FXCollections.observableArrayList(
                new String[]{"John", "Doe", "101", "Cashier"},
                new String[]{"Jane", "Smith", "102", "Manager"}
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