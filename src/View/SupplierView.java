package View;

import Model.Item;
import Model.SectorType;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;

public class SupplierView extends GridPane implements Design {
    private final TableView<Supplier> table = new TableView<>();

    private final TableColumn<Supplier, Integer> supplierId;
    private final TableColumn<Supplier, String> companyName;
    private final TableColumn<Supplier, String> email;
    private final TableColumn<Supplier, String> phoneNumber;
    private final TableColumn<Supplier, String> address;
    private final Button delete;
    private final Button add;
    private TextField sId;
    private TextField cName;
    private TextField Email;
    private TextField phone;
    private TextField Address;

    SearchBoxPane search=new SearchBoxPane();
    Label label=createAlignedGreenBoldLabel("Supplier Management",200);

    public SupplierView() {
        table.setEditable(true);
        table.setPrefHeight(800);
        table.setPrefWidth(3000);
        table.setStyle("-fx-background-color:white ;" +
                "-fx-border-radius:10;" +
                "-fx-border-color:yellowgreen;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        this.supplierId = new TableColumn<>("Supplier ID");
        supplierId.setMinWidth(100);
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        supplierId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        this.companyName = new TableColumn<>("Company Name");
        companyName.setMinWidth(150);
        companyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.email = new TableColumn<>("Email");
        email.setMinWidth(200);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        this.phoneNumber = new TableColumn<>("Phone Number");
        phoneNumber.setMinWidth(120);
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());

        this.address = new TableColumn<>("Address");
        address.setMinWidth(200);
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        address.setCellFactory(TextFieldTableCell.forTableColumn());

        table.getColumns().addAll(supplierId,companyName,email,phoneNumber,address);

        delete=createGeneralButton("Delete");
        add=createGeneralButton("Add");

        this.sId = createTextField("Supplier Id");
        this.cName = createTextField("Company Name");
        this.Email = createTextField("Email");
        this.phone = createTextField("Phone Number");
        this.Address = createTextField("Address");

        table.setItems(getSampleData());
        setUpView();

    }
    private ObservableList<Supplier> getSampleData() {
        return FXCollections.observableArrayList(
                new Supplier(1, "Apple", "@gmail","1234","rruga"));

    }



    public void setUpView(){
        this.setPadding(new Insets(10,10,10,10));
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        HBox box=new HBox(10);
        box.getChildren().addAll(label,search);
        this.setHgap(20);

        GridPane grid=new GridPane();
        grid.setVgap(25);
        grid.setHgap(25);
        grid.add(createAlignedGreenBoldLabel("Supplier Id ", 100), 0, 0);
        grid.add(sId, 1, 0);
        grid.add(createAlignedGreenBoldLabel("Company Name ", 100), 2, 0);
        grid.add(cName, 3, 0);
        grid.add(createAlignedGreenBoldLabel("Email ", 100), 4, 0);
        grid.add(Email, 5, 0);
        grid.add(createAlignedGreenBoldLabel("Phone Number ", 100), 6, 0);
        grid.add(phone, 7, 0);
        grid.add(createAlignedGreenBoldLabel("Address ", 100), 8, 0);
        grid.add(Address, 9, 0);
        grid.add(add,5,1);
        grid.add(delete,9,1);



//
//        HBox buttons=new HBox(10);
//        buttons.getChildren().addAll(add,delete);


        this.add(box,0,0);
        this.add(table,0,3);
        this.add(grid,0,4);

    }
    public Button getDelete() {
        return delete;
    }
    public Button getAdd() {
        return add;
    }

    public TableView<Supplier> getTable() {
        return table;
    }

    public TableColumn<Supplier, Integer> getSupplierId() {
        return supplierId;
    }

    public TableColumn<Supplier, String> getCompanyName() {
        return companyName;
    }

    public TableColumn<Supplier, String> getEmail() {
        return email;
    }

    public TableColumn<Supplier, String> getPhoneNumber() {
        return phoneNumber;
    }

    public TableColumn<Supplier, String> getAddress() {
        return address;
    }

    public TextField getsId() {
        return sId;
    }

    public void setsId(TextField sId) {
        this.sId = sId;
    }

    public TextField getcName() {
        return cName;
    }

    public void setcName(TextField cName) {
        this.cName = cName;
    }

    public void setEmail(TextField email) {
        Email = email;
    }

    public TextField getPhone() {
        return phone;
    }

    public void setPhone(TextField phone) {
        this.phone = phone;
    }

    public void setAddress(TextField address) {
        Address = address;
    }
}
