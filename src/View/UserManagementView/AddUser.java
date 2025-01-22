package View.UserManagementView;

import Database.Database;
import Model.EmployeeRole;
import Model.SectorType;
import View.Design;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddUser extends GridPane implements Design {
    private TextField IDTxt;
    private TextField nameTxt;
    private TextField lastNameTxt;
    private ComboBox<String> genderSelection;
    private TextField salary;
    private DatePicker birthday;
    private TextField usernameTxt;
    private TextField passwordTxt;
    private TextField emailTxt;
    private TextField phoneTxt;
    private DatePicker dateEmployed;
    private ComboBox<String> roleSelection;
    //Sector and role box
    //For Cashier
    private ComboBox<String> cashierSectorSelection=createComboBox("Sector...");
    //For Manager
    private VBox sectorButtons = new VBox();
    private ArrayList<RadioButton> managerSectorSelection=new ArrayList<>();

    private VBox sectorV=new VBox();
    private TextField imagePath;

    private Button addBtn;

    private CheckBox homeAppliancesCB;
    private CheckBox electronicsCB;
    private CheckBox computersCB;
    private CheckBox mobileDevicesCB;
    private CheckBox accessoriesCB;
    private CheckBox gamingCB;
    private CheckBox camerasCB;
    private CheckBox kitchenElectronicsCB;
    private CheckBox smartHomeCB;

    private VBox checkBoxContainerManager;

    public AddUser() {
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
        VBox formLayout = new VBox(5);
        formLayout.setAlignment(Pos.CENTER_LEFT);
        Label IDLabel = createAlignedGreenBoldLabel("ID", 5000);
        Label nameLabel = createAlignedGreenBoldLabel("Name", 5000);
        Label lastNameLabel = createAlignedGreenBoldLabel("Last Name", 5000);
        Label genderLabel = createAlignedGreenBoldLabel("Gender", 5000);
        Label salaryLabel = createAlignedGreenBoldLabel("Salalry", 5000);
        Label birthdayLabel = createAlignedGreenBoldLabel("Birthday", 5000);
        Label usernameLabel = createAlignedGreenBoldLabel("Username", 5000);
        Label passwordLabel = createAlignedGreenBoldLabel("Password", 5000);
        Label emailLabel = createAlignedGreenBoldLabel("Email", 5000);
        Label phoneLabel = createAlignedGreenBoldLabel("Phone Number", 5000);
        Label dateLabel = createAlignedGreenBoldLabel("Date Employed", 5000);
        Label roleLabel = createAlignedGreenBoldLabel("Role", 5000);
        Label sectorLabel = createAlignedGreenBoldLabel("Sector",5000 );

        IDTxt = createTextField("ID");
        nameTxt = createTextField("Name");
        lastNameTxt = createTextField("Last Name");
        genderSelection = createComboBox("FEMALE");
        genderSelection.getItems().addAll("MALE", "FEMALE");
        salary = createTextField("Salary");
        birthday = createDatePicker("Birthday");
        usernameTxt = createTextField("Username");
        passwordTxt = createPasswordField();
        emailTxt = createTextField("Email");
        phoneTxt = createTextField("Phone Number");
        dateEmployed = createDatePicker("Date Employed");
        roleSelection = createComboBox("Role");
        for (EmployeeRole st : EmployeeRole.values()) {
            roleSelection.getItems().add(st.toString());
        }
        //Set sectors
        for(SectorType sector : Database.getDatabase().getSectors()){
            //Cashier
            cashierSectorSelection.getItems().add(sector.toString());
            //Manager
            RadioButton button=new RadioButton(sector.toString());
            managerSectorSelection.add(button);
            sectorButtons.getChildren().add(button);

        }

        // Setting gender, birthday, and status on the same row
        VBox genderV = new VBox();
        genderV.getChildren().addAll(genderLabel, genderSelection);
        VBox birthdayV = new VBox();
        birthdayV.getChildren().addAll(birthdayLabel, birthday);
        VBox salaryV = new VBox();
        salaryV.getChildren().addAll(salaryLabel, salary);
        HBox gender_birthday_status = new HBox();
        gender_birthday_status.setAlignment(Pos.CENTER);
        gender_birthday_status.setSpacing(50);
        gender_birthday_status.getChildren().addAll(genderV, birthdayV, salaryV);

        // Setting date employed and role on the same row
        VBox dateV = new VBox();
        dateV.getChildren().addAll(dateLabel, dateEmployed);

        VBox roleV=new VBox();
        roleV.getChildren().addAll(roleLabel,roleSelection);

        this.sectorV.getChildren().add(sectorLabel);


        HBox date_role_sector = new HBox();
        date_role_sector.setAlignment(Pos.CENTER);
        date_role_sector.getChildren().addAll(dateV, roleV,sectorV);

        /// creating checkboxes for sector
        homeAppliancesCB = new CheckBox("HOME APPLIANCES");
        homeAppliancesCB.setStyle("-fx-border-radius: 2;" +
                "-fx-font-size: 12;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        homeAppliancesCB.setSelected(false);

        electronicsCB = new CheckBox("ELECTRONICS");
        electronicsCB.setStyle(homeAppliancesCB.getStyle());
        electronicsCB.setSelected(false);

        computersCB = new CheckBox("COMPUTERS");
        computersCB.setStyle(homeAppliancesCB.getStyle());
        computersCB.setSelected(false);

        mobileDevicesCB = new CheckBox("MOBILE DEVICES");
        mobileDevicesCB.setStyle(homeAppliancesCB.getStyle());
        mobileDevicesCB.setSelected(false);

        accessoriesCB = new CheckBox("ACCESSORIES");
        accessoriesCB.setStyle(homeAppliancesCB.getStyle());
        accessoriesCB.setSelected(false);

        gamingCB = new CheckBox("GAMING");
        gamingCB.setStyle(homeAppliancesCB.getStyle());
        gamingCB.setSelected(false);

        camerasCB = new CheckBox("CAMERAS");
        camerasCB.setStyle(homeAppliancesCB.getStyle());
        camerasCB.setSelected(false);

        kitchenElectronicsCB = new CheckBox("KITCHEN ELECTRONICS");
        kitchenElectronicsCB.setStyle(homeAppliancesCB.getStyle());
        kitchenElectronicsCB.setSelected(false);

        smartHomeCB = new CheckBox("SMART HOME");
        smartHomeCB.setStyle(homeAppliancesCB.getStyle());
        smartHomeCB.setSelected(false);

        /// creating checkbox container

        checkBoxContainerManager = new VBox();
        checkBoxContainerManager.getChildren().addAll(homeAppliancesCB, electronicsCB, computersCB, mobileDevicesCB,
                accessoriesCB, gamingCB, camerasCB, kitchenElectronicsCB, smartHomeCB);
        checkBoxContainerManager.setMinWidth(100);

        formLayout.getChildren().addAll(
                IDLabel, IDTxt,
                nameLabel, nameTxt,
                lastNameLabel, lastNameTxt,
                gender_birthday_status,
                usernameLabel, usernameTxt,
                passwordLabel, passwordTxt,
                emailLabel, emailTxt,
                phoneLabel, phoneTxt,
                date_role_sector
//                sectorLabel, sectorSelection
        );

        // Right side layout (image and buttons)
        VBox rightLayout = new VBox(20);
        rightLayout.setAlignment(Pos.CENTER);

        Image image = new Image("/Images/Avatar.jpg");
        ImageView imgView = new ImageView(image);
        imgView.setFitWidth(350);
        imgView.setFitHeight(350);
        imgView.setPreserveRatio(true);

        imagePath = createTextField("Image path");

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
    }

    public void closeWindow() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

    public TextField getIDTxt() {
        return IDTxt;
    }

    public TextField getNameTxt() {
        return nameTxt;
    }

    public TextField getLastNameTxt() {
        return lastNameTxt;
    }

    public ComboBox<String> getGenderSelection() {
        return genderSelection;
    }

    public TextField getSalary() {
        return salary;
    }

    public DatePicker getBirthday() {
        return birthday;
    }

    public TextField getUsernameTxt() {
        return usernameTxt;
    }

    public TextField getPasswordTxt() {
        return passwordTxt;
    }

    public TextField getEmailTxt() {
        return emailTxt;
    }

    public TextField getPhoneTxt() {
        return phoneTxt;
    }

    public DatePicker getDateEmployed() {
        return dateEmployed;
    }

    public ComboBox<String> getRoleSelection() {
        return roleSelection;
    }

//    public ComboBox<String> getSectorSelection() {
//        return sectorSelection;
//    }

    public TextField getImagePath() {
        return imagePath;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public ComboBox<String> getCashierSectorSelection() {
        return cashierSectorSelection;
    }

    public ArrayList<RadioButton> getManagerSectorSelection() {
        return managerSectorSelection;
    }

    public VBox getSectorButtons() {
        return sectorButtons;
    }

    public VBox getSectorV() {
        return sectorV;
    }

    public CheckBox getHomeAppliancesCB() {
        return homeAppliancesCB;
    }

    public CheckBox getElectronicsCB() {
        return electronicsCB;
    }

    public CheckBox getComputersCB() {
        return computersCB;
    }

    public CheckBox getMobileDevicesCB() {
        return mobileDevicesCB;
    }

    public CheckBox getAccessoriesCB() {
        return accessoriesCB;
    }

    public CheckBox getGamingCB() {
        return gamingCB;
    }

    public CheckBox getCamerasCB() {
        return camerasCB;
    }

    public CheckBox getKitchenElectronicsCB() {
        return kitchenElectronicsCB;
    }

    public CheckBox getSmartHomeCB() {
        return smartHomeCB;
    }

    public VBox getCheckBoxContainerManager() {
        return checkBoxContainerManager;
    }
}