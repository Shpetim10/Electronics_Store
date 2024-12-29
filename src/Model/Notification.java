package Model;

import java.util.Date;

public class Notification  {
    private String message;
    private Date dateCreated;
    private boolean isRead;
    private NotificationType subject;


    public Notification(NotificationType subject, String message){
        this.subject=subject;
        this.message=message;
        this.isRead=false;
        this.dateCreated=new Date();
    }

    public Notification(String message){
        this(NotificationType.OTHER,message);
    }

    public String getMessage() {return this.message;}

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreated() {return this.dateCreated;}

    public void setDateCreated(Date dateCreated) {
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