package Model;

import java.io.Serializable;

public enum Permission implements Serializable {
    BILLING_SYSTEM,
    PRODUCT_INFORMATION,
    NOTIFICATION_PANEL,
    SUPPLIER_MANAGEMENT,
    INVENTORY_MANAGEMENT,
    PERMISSION_GRANTING,
    USER_MANAGEMENT,
    REPORT_GENERATOR,
    VIEW_ALL_BILLS,
    VIEW_ALL_REPORTS;
}
