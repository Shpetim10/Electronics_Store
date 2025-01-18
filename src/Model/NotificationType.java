package Model;

import java.io.Serializable;

public enum NotificationType implements Serializable {
    REFUND,
    RETURN,
    LOW_STOCK,
    RESTOCKED,
    DISCONTINUED,
    OTHER;
}
