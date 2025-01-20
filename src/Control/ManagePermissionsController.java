package Control;

import Model.Permission;
import Model.User;
import View.UserManagementView.PermissionGrantingView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;

public class ManagePermissionsController {

    private final PermissionGrantingView view;

    public ManagePermissionsController(PermissionGrantingView view) {
        this.view = view;

        // Add a listener to the TableView's selected item
        view.getTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    // Update the checkboxes based on the selected user's permissions
                    updatePermissionCheckboxes(newValue);
                }
            }
        });
    }

    /**
     * Updates the checkboxes in the permission VBox based on the user's permissions.
     *
     * @param user The user whose permissions need to be displayed.
     */
    private void updatePermissionCheckboxes(User user) {
        // Retrieve the user's permissions
        ArrayList<Boolean> permissionsBoolean = user.getPermissionsBoolean();

        // Clear all checkboxes and reapply based on the user's permissions
        for (int i = 0; i < permissionsBoolean.size(); i++) {
            boolean hasPermission = permissionsBoolean.get(i);

            // Update the corresponding checkbox in the PermissionGrantingView
            view.getPermissionsCheckBoxes().get(i).setSelected(hasPermission);
        }
    }
}
