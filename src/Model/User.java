package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -4865159324924847611L;
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber; //String because it can have chars such as +,-, etc
    private Date dateEmployed;
    private String photo; // reference(address) to photo
    private EmployeeRole role;
    private ArrayList<Permission> permissions;
    private boolean isActive;
    private ArrayList<Notification> notifications;

    //All-Argument Constructor
    protected User(int id, String firstName, String lastName, String username,
                String password, String email, String phoneNumber, Date dateEmployed, String photo,
                EmployeeRole role, ArrayList<Permission> permissions, boolean isActive, ArrayList<Notification> notifications) {
        this(id,firstName,lastName,username,password,email,phoneNumber,dateEmployed,role,permissions, isActive);
        this.photo = photo;
    }

    //Constructor without photo

    public User(int id, String firstName, String lastName, String username, String password, String email,
                String phoneNumber, Date dateEmployed, EmployeeRole role, ArrayList<Permission> permissions, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateEmployed = dateEmployed;
        this.role = role;
        this.permissions = permissions;
        this.isActive = isActive;
        String message=firstName+" "+lastName+", Welcome to our Electronics Store System! " +
                "\nWe hope we have a strong collaboration and get customers' experience even better! " +
                "\nIf any concern arises, feel free to notify your assigned sector's manager. " +
                "\n\nGreetings from stores' Management board! ";
        this.notifications=new ArrayList<>();
        notifications.add(new Notification(NotificationType.OTHER ,message));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(Date dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && ((User)o).getFirstName().equals(this.getFirstName())
                && ((User)o).getLastName().equals(this.getLastName()) && ((User)o).getUsername().equals(this.getUsername())
                && ((User)o).getPassword().equals(this.getPassword()) && ((User)o).getEmail().equals(this.getEmail())
                && ((User)o).getPhoneNumber().equals(this.getPhoneNumber()) && Objects.equals(getDateEmployed(), user.getDateEmployed())
                && Objects.equals(getRole(), user.getRole());
    }

    @Override
    public String toString() {
        return "User Details:  " +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateEmployed=" + dateEmployed +
                ", role=" + role +
                ", isActive=" + isActive;
    }
}
