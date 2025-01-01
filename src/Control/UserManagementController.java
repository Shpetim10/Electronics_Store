package Control;

import Model.*;

import java.util.*;

public class UserManagementController {
    private String role;
    private ArrayList<Permission> permissions;
//    private Boolean hasARole = false;
//    private Boolean costumPermissions = false; // make sure that no matter the role it might have custom permissions

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

//    public Boolean getCostumPermissions() {
//        return costumPermissions;
//    }
//
//    public void setCostumPermissions(Boolean customPermissions) {
//        this.costumPermissions = customPermissions;
//    }
//
//    public Boolean getHasARole() {
//        return hasARole;
//    }
//
//    public void setHasARole(Boolean hasARole) {
//        this.hasARole = hasARole;
//    }
//
//    public Boolean assignUserRole() {
//        if (getRole() == null || getRole().equals("Invalid")) { // Doesn't have a role/ it is not a (regular) user
//            return false;
//        } else
//            return true;
//    }

    // Granting permissions

//    PermissionGrantingController p = new PermissionGrantingController();
//
//    public void initializePermissions() {
//        hasARole = assignUserRole(); // Chooses what permissions to give initially
//        if (hasARole == true) {
//            if(getCostumPermissions() == true){
//                permissions = p.giveCustomPermissions();
//            }
//            else if (getRole().equals("Cashier")) {
//                permissions = p.giveDefaultCashierPermissions();
//            }
//            else if(getRole().equals("Manager")){
//                permissions = p.giveDefaultManagerPermissions();
//            }
//        }
//    }

    // Administrator adds permissions
    public void grantPermission(Permission permission) {
        permissions.add(permission);
    }

    // Administrator removes permissions
    public void revokePermission(Permission permission) {
        permissions.remove(permission);
    }

    public Boolean validatePasswordStrength(String password) {
        if (password.length() < 10) {
            return false;
        }
        Boolean hasUppercase = false;
        Boolean hasLowercase = false;
        Boolean hasDigit = false;
        Boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialChar = true;
            }
        }
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

}