package Model;

import java.io.Serializable;

public enum Permission implements Serializable {
    BILLING_SYSTEM,
    PRODUCT_INFORMATION,
    NOTIFICATION_PANEL,
    VIEW_ALL_BILLS,
    SUPPLIER_MANAGEMENT,
    INVENTORY_MANAGEMENT,
    USER_MANAGEMENT,
    REPORT_GENERATOR,
    VIEW_ALL_REPORTS,
    PERMISSION_GRANTING;
}
