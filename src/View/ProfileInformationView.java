package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ProfileInformationView extends GridPane implements Design {
    private Label name=createAlignedBlackBoldLabel("");
    private Label userId =createAlignedBlackBoldLabel("");
    private Label username=createAlignedBlackBoldLabel("");
    private Label email=createAlignedBlackBoldLabel("");
    private Label phoneNumber=createAlignedBlackBoldLabel("");
    private Label dateEmployed=createAlignedBlackBoldLabel("");
    private Label role=createAlignedBlackBoldLabel("");
    private ImageView photo=new ImageView();
    private Label gender=createAlignedBlackBoldLabel("");
    private Label birthday=createAlignedBlackBoldLabel("");
    private Label sector=createAlignedBlackBoldLabel("");

    public ProfileInformationView(){
        setUpView();
    }

    public void setUpView(){
        Label title=createAlignedGreenBoldLabel("Profile Information");
        Label nameTitle=createAlignedGreenBoldLabel("Full name");
        Label idTitle=createAlignedGreenBoldLabel("Employee Id");
        Label usernameTitle=createAlignedGreenBoldLabel("Username");
        Label emailTitle=createAlignedGreenBoldLabel("Email");
        Label phoneNumberTitle=createAlignedGreenBoldLabel("Phone Number");
        Label dateEmployedTitle=createAlignedGreenBoldLabel("Employed Date");
        Label employeeRoleTitle=createAlignedGreenBoldLabel("Role");
        Label employeeGenderTitle=createAlignedGreenBoldLabel("Gender");
        Label employeeBirthdayTitle=createAlignedGreenBoldLabel("Birthday");
        Label sectorsTitle=createAlignedGreenBoldLabel("Sector");
        this.photo.setFitHeight(200);
        this.photo.setFitWidth(200);

        this.setStyle("-fx-background-color: transparent;");
        this.setHgap(50);
        this.setVgap(15);
        this.setAlignment(Pos.CENTER);
        this.add(title,0,0);
        this.add(idTitle,0,1);
        this.add(userId,1,1);
        this.add(usernameTitle,0,2);
        this.add(username,1,2);
        this.add(nameTitle,0,3);
        this.add(name,1,3);
        this.add(emailTitle,0,4);
        this.add(email,1,4);
        this.add(phoneNumberTitle,0,5);
        this.add(phoneNumber,1,5);
        this.add(dateEmployedTitle,0,6);
        this.add(dateEmployed,1,6);
        this.add(employeeGenderTitle,0,7);
        this.add(gender,1,7);
        this.add(employeeBirthdayTitle,0,8);
        this.add(birthday,1,8);
        this.add(employeeRoleTitle,0,9);
        this.add(role,1,9);
        this.add(sectorsTitle,0,10);
        this.add(sector,1,10);
        this.add(photo,2,0);

    }

    public Label getUserId() {
        return userId;
    }

    public Label getName() {
        return name;
    }

    public Label getEmployeeId() {
        return userId;
    }

    public Label getUsername() {
        return username;
    }

    public Label getEmail() {
        return email;
    }

    public Label getPhoneNumber() {
        return phoneNumber;
    }

    public Label getDateEmployed() {
        return dateEmployed;
    }

    public Label getRole() {
        return role;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public Label getGender() {
        return gender;
    }

    public Label getBirthday() {
        return birthday;
    }

    public Label getSector() {
        return sector;
    }
}
