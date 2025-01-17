package View.UserManagementView;

import Model.EmployeeRole;
import Model.Permission;
import Model.SectorType;
import View.Design;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

public class UserAdd extends GridPane implements Design {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Date dateEmployed;
    private String photo;
    private EmployeeRole role;
    private ArrayList<Permission> permissions;

    private Button addBtn;

    public UserAdd() {
        initializeUI();
    }

    private void initializeUI() {
        this.setPadding(new Insets(50));
        this.setHgap(50);
        this.setVgap(20);
        this.setStyle("-fx-background-color: rgba(167,246,8,0.3);");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(100, 250, 100, 250));
        pane.setHgap(70);
        pane.setVgap(10);
        pane.setStyle("-fx-background-color: rgba(167,246,8,0.3);");

        // Left side layout (form fields)
        VBox formLayout = new VBox(15);
        formLayout.setAlignment(Pos.CENTER_LEFT);
        Label IDLabel = createAlignedGreenBoldLabel("ID", 5000);
        Label nameLabel = createAlignedGreenBoldLabel("Name", 5000);
        Label lastNameLabel = createAlignedGreenBoldLabel("Last Name", 5000);
        Label genderLabel = createAlignedGreenBoldLabel("Gender", 5000);
        Label statusLabel = createAlignedGreenBoldLabel("Status", 5000);
        Label birthdayLabel = createAlignedGreenBoldLabel("Birthday", 5000);
        Label usernameLabel = createAlignedGreenBoldLabel("Username", 5000);
        Label passwordLabel = createAlignedGreenBoldLabel("Password", 5000);
        Label emailLabel = createAlignedGreenBoldLabel("Email", 5000);
        Label phoneLabel = createAlignedGreenBoldLabel("Phone Number", 5000);
        Label dateLabel = createAlignedGreenBoldLabel("Date Employed", 5000);
        Label roleLabel = createAlignedGreenBoldLabel("Role", 5000);
        Label sectorLabel = createAlignedGreenBoldLabel("Sector", 5000);

        TextField IDTxt = createTextField("ID");
        TextField nameTxt = createTextField("Name");
        TextField lastNameTxt = createTextField("Last Name");
        ComboBox<String> genderSelection = createComboBox("FEMALE");
        genderSelection.getItems().addAll("MALE", "FEMALE");
        ComboBox<String> status = createComboBox( "ACTIVE");
        status.getItems().addAll("ACTIVE", "INACTIVE");
        DatePicker birthday = createDatePicker("Birthday");
        TextField usernameTxt = createTextField("Username");
        TextField passwordTxt = createPasswordField();
        TextField emailTxt = createTextField("Email");
        TextField phoneTxt = createTextField("Phone Number");
        DatePicker dateEmployed= createDatePicker("Date Employed");
        ComboBox<String> roleSelection = createComboBox("Role");
        for (EmployeeRole st : EmployeeRole.values()) {
            roleSelection.getItems().add(st.toString());
        }
        ComboBox<String> sectorSelection = createComboBox("Sector");
        for (SectorType st : SectorType.values()) {
            sectorSelection.getItems().add(st.toString());
        }

        // Setting gender, birthday, and status on the same row
        VBox genderV = new VBox();
        genderV.getChildren().addAll(genderLabel, genderSelection);
        VBox birthdayV = new VBox();
        birthdayV.getChildren().addAll(birthdayLabel, birthday);
        VBox statusV = new VBox();
        statusV.getChildren().addAll(statusLabel, status);
        HBox gender_birthday_status = new HBox();
        gender_birthday_status.setAlignment(Pos.CENTER);
        gender_birthday_status.setSpacing(50);
        gender_birthday_status.getChildren().addAll(genderV, birthdayV, statusV);

        // Setting date employed and role on the same row
        VBox dateV = new VBox();
        dateV.getChildren().addAll(dateLabel, dateEmployed);
        VBox roleV = new VBox();
        roleV.getChildren().addAll(roleLabel, roleSelection);
        HBox date_role = new HBox();
        date_role.setAlignment(Pos.CENTER);
        date_role.getChildren().addAll(dateV, roleV);

        formLayout.getChildren().addAll(
                IDLabel, IDTxt,
                nameLabel, nameTxt,
                lastNameLabel, lastNameTxt,
                gender_birthday_status,
                usernameLabel, usernameTxt,
                passwordLabel, passwordTxt,
                emailLabel, emailTxt,
                phoneLabel, phoneTxt,
                date_role,
                sectorLabel, sectorSelection
        );

//        pane.add(formLayout, 0, 0);


        // Right side layout (image and buttons)
        VBox rightLayout = new VBox(20);
        rightLayout.setAlignment(Pos.CENTER);

        Image image = new Image("/Images/Avatar.jpg");
        ImageView imgView = new ImageView(image);
        imgView.setFitWidth(350);
        imgView.setFitHeight(350);
        imgView.setPreserveRatio(true);

        TextField imagePath = createTextField("Image path");

        HBox buttonLayout = new HBox(200);
        buttonLayout.setAlignment(Pos.CENTER);

        addBtn = createGeneralButton("ADD");
        Button closeBtn = createGeneralButton("CLOSE");
        closeBtn.setOnAction(event -> closeWindow());

        buttonLayout.getChildren().addAll(addBtn, closeBtn);
        rightLayout.getChildren().addAll(imgView, imagePath, buttonLayout);

        // Adding layouts to the grid pane
        this.add(formLayout, 0, 0);
        this.add(rightLayout, 1, 0);

        addController();
    }

    private void addController() {
        addBtn.setOnAction(event -> handleAddUser());
    }

    private void handleAddUser() {
        System.out.println("User added: " + username); // Replace with actual logic
        closeWindow();
    }


    private void closeWindow() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

    public void showUserAddWindow() {
        Stage userAddStage = new Stage();
        userAddStage.setTitle("Add User");
        userAddStage.setScene(new Scene(this, 800, 600));
        userAddStage.setMaximized(true);
        userAddStage.show();
    }
}