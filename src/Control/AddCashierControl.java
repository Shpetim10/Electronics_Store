package Control;

import Model.*;
import View.UserManagementView.AddCashier;
import javafx.scene.control.Alert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddCashierControl {
    private final AddCashier view;

    public AddCashierControl(AddCashier view) {
        this.view = view;
//        view.getAddBtn().setOnAction(e -> handleAddCashier());
    }

    public void handleAddCashier() {
//        try {
//            validateInputs();
//
//            int ID = Integer.parseInt(view.getIDTxt().getText());
//            String name = view.getNameTxt().getText();
//            String lastName = view.getLastNameTxt().getText();
//            Gender gender = Gender.valueOf(view.getGenderSelection().getValue());
//            LocalDate birthday = view.getBirthday().getValue();
//            Double salary = Double.parseDouble(view.getSalary().getText());
//            String username = view.getUsernameTxt().getText();
//            String password = view.getPasswordTxt().getText();
//            String email = view.getEmailTxt().getText();
//            String phoneNumber = view.getPhoneTxt().getText();
//            LocalDate dateEmployed = view.getDateEmployed().getValue();
//            EmployeeRole role = EmployeeRole.valueOf(view.getRoleSelection().getValue().toUpperCase());
//            SectorType sector = SectorType.valueOf(view.getSectorSelection().getValue().toUpperCase());
//            String photo = view.getImagePath().getText();
//
//            // Create the Cashier object
//            // Create the Cashier object
//
//
//            System.out.println("Cashier created and written to .dat file successfully");
//            view.closeWindow();
//
//            // Show success message
//            showAlert(Alert.AlertType.INFORMATION, "Success", "Cashier was successfully added and saved to file!");
//
//        } catch (NumberFormatException ex) {
//            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please ensure all numeric fields are correctly filled.");
//            System.out.println("Number format exception: " + ex.getMessage());
//        } catch (IllegalArgumentException ex) {
//            showAlert(Alert.AlertType.ERROR, "Invalid Input", ex.getMessage());
//            System.out.println("Illegal argument exception: " + ex.getMessage());
//        } catch (IOException ex) {
//            showAlert(Alert.AlertType.ERROR, "File Error", "Failed to save cashier to file. Please try again.");
//            System.out.println("IOException: " + ex.getMessage());
//        } catch (Exception ex) {
//            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add cashier. Please try again.");
//            System.out.println("General exception: " + ex.getMessage());
//        }
//    }
//
//    private void validateInputs() {
//        if (view.getIDTxt().getText().isEmpty() || view.getNameTxt().getText().isEmpty() || view.getUsernameTxt().getText().isEmpty()) {
//            throw new IllegalArgumentException("ID, Name, and Username are required fields.");
//        }
//        if (view.getGenderSelection().getValue() == null || view.getRoleSelection().getValue() == null || view.getSectorSelection().getValue() == null) {
//            throw new IllegalArgumentException("Gender, Role, and Sector must be selected.");
//        }
//        if (view.getPhoneTxt().getText().isEmpty()) {
//            throw new IllegalArgumentException("Phone Number is a required field.");
//        }
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

    private void writeToFile(Cashier cashier) throws IOException {
        String filePath = "cashiers1.dat";

        // Serialize the Cashier object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            oos.writeObject(cashier);
        }
    }
}
