package View.UserManagementView;

import Database.Database;
import Model.Cashier;
import Model.EmployeeRole;
import Model.User;
import View.Design;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;

public class UserTable extends VBox implements Design {
    private TableView<User> table;

    private final TableColumn< User, Integer> idColumn;
    private final TableColumn<User, String> nameColumn;
    private final TableColumn<User, String> lastNameColumn;
    private final TableColumn<User, String> usernameColumn;
    private final TableColumn<User, String> emailColumn;
    private final TableColumn<User, String> phoneNumberColumn;
    private final TableColumn<User, Double> salaryColumn;
    private final TableColumn<User, EmployeeRole> roleColumn;



    public UserTable() {

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

        // Sample data

        table.setItems(getCashierData());
        this.getChildren().add(table);
    }

    public ObservableList<User> getCashierData(){
        ArrayList<User> cashiers=new ArrayList<>();

        cashiers.addAll(Database.getDatabase().getCashiers());
        return FXCollections.observableArrayList(cashiers);
    }
    // User class to represent user data
//    public static class User {
//        private int id;
//        private String firstName;
//        private String lastName;
//        private String username;
//        private String email;
//        private String phoneNumber;
//        private Double salary;
//        private User user;

//        public User(int id, String firstName, String lastName, String username, String email, String phoneNumber,Double salary) {
//            this.id = id;
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.username = username;
//            this.email = email;
//            this.phoneNumber = phoneNumber;
//            this.salary = salary;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public String getPhoneNumber() {
//            return phoneNumber;
//        }
//
//        public Double getSalary() {
//            return salary;
//        }
//
//        public Model.User getUser(){
//            for(Model.User user: Database.getDatabase().getUsers()){
//                if(user.getId()==this.id){
//                    return user;
//                }
//            }
//            return null;
//        }
//
//    }

    public TableView<User> getTable() {
        return table;
    }
}