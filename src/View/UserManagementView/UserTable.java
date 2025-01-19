package View.UserManagementView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class UserTable extends VBox {
    private TableView<User> table;
    private ObservableList<User> data = FXCollections.observableArrayList();
    public UserTable() {
        table = new TableView<>();
        initializeTable();
    }

    private void initializeTable() {
        // Create columns
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Add columns to the table
        table.getColumns().addAll(idColumn, nameColumn, lastNameColumn, usernameColumn, emailColumn);

        // Sample data

        table.setItems(data);
        this.getChildren().add(table);
    }

    // User class to represent user data
    public static class User {
        private int id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;

        public User(int id, String firstName, String lastName, String username, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }

    public TableView<User> getTable() {
        return table;
    }
}