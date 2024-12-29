package Model;

import java.util.Date;

public class Notification  {
    private int id;
    private String message;
    private Date dateCreated;
    private boolean isRead;
    private Notification subject;

    public Notification() {};  //No arguments constructor
public Notification(int id,String message,Date dateCreated,  //All arguments constructor
                    boolean isRead,Notification subject){
    this.id=id;
    this.message=message;
    this.dateCreated=dateCreated;
    this.isRead=isRead;
    this.subject=subject;
}


    public int getId() {return this.id;}
    public void setId(int id) {
        this.id = id;
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

    public Notification getSubject() {return this.subject;}
    public void setSubject(Notification subject) {
        this.subject = subject;
    }

    public boolean markAsRead(){return isRead=true;}
}