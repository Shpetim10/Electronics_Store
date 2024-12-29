package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public abstract class User {
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
                String phoneNumber, Date dateEmployed, EmployeeRole role, ArrayList<Permission> permissions, boolean isActive
                ) {
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
        notifications.add(new Notification(NotificationType.OTHER ,message));
    }


    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String getPhoneNumber() {
        return phoneNumber;
    }

    protected void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected Date getDateEmployed() {
        return dateEmployed;
    }

    protected void setDateEmployed(Date dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    protected String getPhoto() {
        return photo;
    }

    protected void setPhoto(String photo) {
        this.photo = photo;
    }

    protected EmployeeRole getRole() {
        return role;
    }

    protected void setRole(EmployeeRole role) {
        this.role = role;
    }

    protected ArrayList<Permission> getPermissions() {
        return permissions;
    }

    protected void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    protected boolean isActive() {
        return isActive;
    }

    protected void setActive(boolean active) {
        isActive = active;
    }

    protected ArrayList<Notification> getNotifications() {
        return notifications;
    }

    protected void setNotifications(ArrayList<Notification> notifications) {
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
