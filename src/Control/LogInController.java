package Control;

import Database.Database;
import Exceptions.AccountLockedException;
import Model.User;
import View.LogInView;
import javafx.scene.control.Alert;

public class LogInController implements Alertable {
    private LogInView view=new LogInView();
    private int logInAttempts = 0;
    private User user=null;

    public LogInController() {
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

            boolean validCredentials=false;
            for (User user : Database.getDatabase().getUsers()) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    this.user = user;
                    this.logInAttempts = 0;
                    validCredentials = true;
                    break;
                }
            }

            if (!validCredentials) {
                logInAttempts++;
                showAlert(Alert.AlertType.WARNING, "Wrong Credentials!", "Incorrect username or password!");
                if (logInAttempts >= 5) {
                    showAlert(Alert.AlertType.WARNING, "Account Locked!", "You have exceeded 5 incorrect login attempts!");
                }
            }
        });
    }

    public LogInView getView() {
        return view;
    }

    public int getLogInAttempts() {
        return logInAttempts;
    }

    public User getUser() {
        return user;
    }

}
