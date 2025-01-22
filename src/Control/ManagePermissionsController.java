package Control;

import Database.Database;
import Model.*;
import View.UserManagementView.PermissionGrantingView;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagePermissionsController {

    private PermissionGrantingView view=new PermissionGrantingView();
    User selectedUser;
    private ArrayList<User> users = Database.getDatabase().getUsers();
    public ManagePermissionsController() {

        view.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedUser = (User) newSelection;
                System.out.println(selectedUser.getFirstName());

                if (selectedUser.getPermissions().contains(Permission.BILLING_SYSTEM)) {
                    view.getBillingSystemCB().setSelected(true);
                } else {
                    view.getBillingSystemCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.PRODUCT_INFORMATION)) {
                    view.getProductInformationCB().setSelected(true);
                } else {
                    view.getProductInformationCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.NOTIFICATION_PANEL)) {
                    view.getNotificationPanelCB().setSelected(true);
                } else {
                    view.getNotificationPanelCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.VIEW_ALL_BILLS)) {
                    view.getViewAllBillsCB().setSelected(true);
                } else {
                    view.getViewAllBillsCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.SUPPLIER_MANAGEMENT)) {
                    view.getSupplierManagementCB().setSelected(true);
                } else {
                    view.getSupplierManagementCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.INVENTORY_MANAGEMENT)) {
                    view.getInventoryManagementCB().setSelected(true);
                } else {
                    view.getInventoryManagementCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.USER_MANAGEMENT)) {
                    view.getUserManagementCB().setSelected(true);
                } else {
                    view.getUserManagementCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.REPORT_GENERATOR)) {
                    view.getReportGeneratorCB().setSelected(true);
                } else {
                    view.getReportGeneratorCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.VIEW_ALL_REPORTS)) {
                    view.getViewAllReportsCB().setSelected(true);
                } else {
                    view.getViewAllReportsCB().setSelected(false);
                }

                if (selectedUser.getPermissions().contains(Permission.PERMISSION_GRANTING)) {
                    view.getPermissionGrantingCB().setSelected(true);
                } else {
                    view.getPermissionGrantingCB().setSelected(false);
                }
            }
        });


        view.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (selectedUser != null) {
                System.out.println("OK");
                selectedUser = (User) newSelection;
                //EmployeeRole role = selectedUser.getRole();


                // Add listeners to checkboxes to update permissions and save to file
                view.getBillingSystemCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.BILLING_SYSTEM);
                    } else {
                        selectedUser.getPermissions().remove(Permission.BILLING_SYSTEM);
                    }

                    Database.getDatabase().updateUsers(users);
                });

                view.getProductInformationCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.PRODUCT_INFORMATION);
                    } else {
                        selectedUser.getPermissions().remove(Permission.PRODUCT_INFORMATION);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getNotificationPanelCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.NOTIFICATION_PANEL);
                    } else {
                        selectedUser.getPermissions().remove(Permission.NOTIFICATION_PANEL);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getViewAllBillsCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.VIEW_ALL_BILLS);
                    } else {
                        selectedUser.getPermissions().remove(Permission.VIEW_ALL_BILLS);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getSupplierManagementCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.SUPPLIER_MANAGEMENT);
                    } else {
                        selectedUser.getPermissions().remove(Permission.SUPPLIER_MANAGEMENT);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getInventoryManagementCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.INVENTORY_MANAGEMENT);
                    } else {
                        selectedUser.getPermissions().remove(Permission.INVENTORY_MANAGEMENT);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getUserManagementCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.USER_MANAGEMENT);
                    } else {
                        selectedUser.getPermissions().remove(Permission.USER_MANAGEMENT);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getReportGeneratorCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.REPORT_GENERATOR);
                    } else {
                        selectedUser.getPermissions().remove(Permission.REPORT_GENERATOR);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getViewAllReportsCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.VIEW_ALL_REPORTS);
                    } else {
                        selectedUser.getPermissions().remove(Permission.VIEW_ALL_REPORTS);
                    }
                    Database.getDatabase().updateUsers(users);
                });

                view.getPermissionGrantingCB().selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        selectedUser.getPermissions().add(Permission.PERMISSION_GRANTING);
                    } else {
                        selectedUser.getPermissions().remove(Permission.PERMISSION_GRANTING);
                    }
                    Database.getDatabase().updateUsers(users);
                });



            } else {
                System.out.println("Null");
            }
        });
        searchBoxListener();
    }

    public void searchBoxListener() {
        ObservableList<User> originalUsers = FXCollections.observableArrayList(view.getTable().getItems());

        view.getSearch().getSearchField().setOnAction(e -> {
            String searchQuery = this.view.getSearch().getSearchField().getText().toLowerCase();

            ObservableList<User> filteredUsers = FXCollections.observableArrayList();

            // Only filter if there's a search query
            if (!searchQuery.isEmpty()) {
                for (User user : originalUsers) {
                    // Check multiple fields (firstName, lastName, email, and ID)
                    if (user.getFirstName().toLowerCase().contains(searchQuery) ||
                            user.getLastName().toLowerCase().contains(searchQuery) ||
                            user.getEmail().toLowerCase().contains(searchQuery) ||
                            String.valueOf(user.getId()).contains(searchQuery)) {
                        filteredUsers.add(user);
                    }
                }
                this.view.getTable().setItems(filteredUsers);
            } else {
                // Reset to original list
                this.view.getTable().setItems(originalUsers);
            }
        });
    }
    public PermissionGrantingView getView() {
        return view;
    }
}



