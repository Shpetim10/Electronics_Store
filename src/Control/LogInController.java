package Control;

import Database.Database;
import Model.Administrator;
import Model.Cashier;
import Model.Manager;
import Model.User;
import View.LogInView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LogInController implements Alertable {
    private LogInView view=new LogInView();
    private int logInAttempts = 0;
    private boolean validCredentials=false;
    private User user=null;
    private Stage primaryStage;
    public LogInController(Stage primaryStage) {
        this.primaryStage=primaryStage;
        setUpLogInViewButton();
    }
    public LogInController(){
        setUpLogInViewButton();
    }
    private void setUpLogInViewButton() {
        this.view.getLoginButton().setOnAction(e -> {
            if (logInAttempts >= 5) {
                showAlert(Alert.AlertType.WARNING, "Account Locked!", "You have exceeded 5 incorrect login attempts!");
                return;
            }

            String username = this.view.getUsernameField().getText();
            String password = this.view.getPasswordField().getText();
            //Check if Cashier
            if(!validCredentials) {
                for (Cashier cashier : Database.getDatabase().getCashiers()) {
                    if (cashier.getUsername().equals(username) && cashier.getPassword().equals(password)) {
                        this.user = cashier;
                        this.logInAttempts = 0;
                        validCredentials = true;
                        break;
                    }
                }
            }
            //Check If Manager
            if(!validCredentials){
                for (Manager manager : Database.getDatabase().getManagers()) {
                    if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                        this.user = manager;
                        this.logInAttempts = 0;
                        validCredentials = true;
                        break;

                    }
                }
            }
            //Check if Administrator
            if(!validCredentials){
                for (Administrator administrator : Database.getDatabase().getAdministrators()) {
                    if (administrator.getUsername().equals(username) && administrator.getPassword().equals(password)) {
                        this.user = administrator;
                        this.logInAttempts = 0;
                        validCredentials = true;
                        break;
                    }
                }
            }


            if (!validCredentials) {
                logInAttempts++;
                showAlert(Alert.AlertType.WARNING, "Wrong Credentials!", "Incorrect username or password!");
                if (logInAttempts >= 5) {
                    showAlert(Alert.AlertType.WARNING, "Account Locked!", "You have exceeded 5 incorrect login attempts!");
                }
            }
            setSwitchStage();
        });
    }
    public void setSwitchStage(){
            if (validCredentials) {
                showAlert(Alert.AlertType.INFORMATION, "Logged In!", "You logged in successfully!");

                // Main controller
                UserMainController mainController = new UserMainController(user);
                Scene mainScene = new Scene(mainController.getView());
                this.primaryStage.setScene(mainScene);
                this.primaryStage.setMaximized(true);

                //Log out
                mainController.getView().getLogOutIcon().setOnMouseClicked(e -> {
                    LogInController backScene=new LogInController(primaryStage);
                    this.primaryStage.setScene(new Scene(backScene.getView(),500,500));

                    showAlert(Alert.AlertType.INFORMATION, "Log out!", "You logged out successfully!");
                });
            } else {
                // Notify the user if credentials are invalid
                showAlert(Alert.AlertType.ERROR, "Login Failed!", "Invalid username or password. Please try again.");
            }
        }



    public LogInView getView() {
        return view;
    }

    public int getLogInAttempts() {
        return logInAttempts;
    }

    public boolean isValidCredentials() {
        return validCredentials;
    }

    public User getUser() {
        return user;
    }
}
