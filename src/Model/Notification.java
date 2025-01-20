package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 2029610921586220545L;
    private String message;
    private LocalDateTime dateCreated;
    private boolean isRead;
    private NotificationType subject;


    public Notification(NotificationType subject, String message){
        this.subject=subject;
        this.message=message;
        this.isRead=false;
        this.dateCreated=LocalDateTime.now();
    }

    public Notification(String message){
        this(NotificationType.OTHER,message);
    }

    public String getMessage() {return this.message;}

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateCreated() {return this.dateCreated;}

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isRead() {return this.isRead;}
    public void setisRead(boolean isRead) {
        this.isRead = isRead;
    }

    public NotificationType getSubject() {return this.subject;}

    public void setSubject(NotificationType subject) {
        this.subject = subject;
    }

    public void markAsRead(){ isRead=true;}
}