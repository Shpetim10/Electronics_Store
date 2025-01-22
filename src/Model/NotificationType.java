package Model;

import java.io.Serializable;

public enum NotificationType implements Serializable {
    LOW_STOCK,
    RESTOCKED,
    NEW_PRODUCT,
    DISCONTINUED,
    OTHER;
}
