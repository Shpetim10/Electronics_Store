package Control;

import Model.*;
import View.UserManagementView.AddUser;
import javafx.scene.control.Alert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddUserControl {
    private final AddUser view;

    public AddUserControl(AddUser view) {
        this.view = view;
        view.getAddBtn().setOnAction(e -> handleAddUser());
    }

    public void handleAddUser() {
        try {
            validateInputs();

            int ID = Integer.parseInt(view.getIDTxt().getText());
            String name = view.getNameTxt().getText();
            String lastName = view.getLastNameTxt().getText();
            Gender gender = Gender.valueOf(view.getGenderSelection().getValue());
            Date birthday = parseDate(view.getBirthday().getValue().toString());
            Double salary = Double.parseDouble(view.getSalary().getText());
            String username = view.getUsernameTxt().getText();
            String password = view.getPasswordTxt().getText();
            String email = view.getEmailTxt().getText();
            String phoneNumber = view.getPhoneTxt().getText();
            LocalDate dateEmployed = view.getDateEmployed().getValue();
            EmployeeRole role = EmployeeRole.valueOf(view.getRoleSelection().getValue().toUpperCase());
//            SectorType sector = SectorType.valueOf(view.getSectorSelection().getValue().toUpperCase());
            String photo = view.getImagePath().getText();

            // Create the Cashier object
            // Create the Cashier object


            User newUser = new User(
                    ID, name, lastName, gender, birthday, salary, username,
                    password, email, phoneNumber, dateEmployed, role, photo
            );

            // Write to the .dat file
            writeToFile(newUser);

            System.out.println("User created and written to .dat file successfully");
            view.closeWindow();

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "User was successfully added and saved to file!");

        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please ensure all numeric fields are correctly filled.");
            System.out.println("Number format exception: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", ex.getMessage());
            System.out.println("Illegal argument exception: " + ex.getMessage());
        } catch (ParseException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date", "Please ensure the date format is YYYY-MM-DD.");
            System.out.println("Parse exception: " + ex.getMessage());
        } catch (IOException ex) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Failed to save user to file. Please try again.");
            System.out.println("IOException: " + ex.getMessage());
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add user. Please try again.");
            System.out.println("General exception: " + ex.getMessage());
        }
    }

    private void validateInputs() {
        if (view.getIDTxt().getText().isEmpty() || view.getNameTxt().getText().isEmpty() || view.getUsernameTxt().getText().isEmpty()) {
            throw new IllegalArgumentException("ID, Name, and Username are required fields.");
        }
        if (view.getGenderSelection().getValue() == null || view.getRoleSelection().getValue() == null) {
            throw new IllegalArgumentException("Gender, Role, and Sector must be selected.");
        }
        if (view.getPhoneTxt().getText().isEmpty()) {
            throw new IllegalArgumentException("Phone Number is a required field.");
        }
    }

    public Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void writeToFile(User user) throws IOException {
        String filePath = "users1.dat";

        // Serialize the User object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            oos.writeObject(user);
        }
    }
}
