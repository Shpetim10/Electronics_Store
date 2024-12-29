package Control;
import java.util.*;

public class PermissionGrantingController {
    ArrayList<Permission> permissions = new ArrayList<>();

    public ArrayList<Permission> giveCustomPermissions(Permission... permissions1){
        for(Permission p : permissions1){
            permissions.add(p);
        }
        return permissions;
    }

    public ArrayList<Permission> giveDefaultCashierPermissions(){
        /*
        BILLING_SYSTEM, PRODUCT_INFORMATION, NOTIFICATION_PANEL, PERFORMANCE_VIEW;
        Make sure to put this 4 in the beginning of the PERMISSION enum
        */
        for(Permission p : Permission.values()){
            if(p.ordinal() < 4){
                permissions.add(p);
            }
        }
        return permissions;
    }

    public ArrayList<Permission> giveDefaultManagerPermissions(){
        for(Permission p : Permission.values()){
            if(p.ordinal()>0){
                permissions.add(p);
            }
        }
        return permissions;
    }
}






