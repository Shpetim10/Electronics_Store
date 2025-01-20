package Model;

import java.io.*;
import java.io.Serializable;
import javafx.beans.property.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1574601041687009184L;
    private transient SimpleIntegerProperty id;
    private transient SimpleStringProperty firstName;
    private transient SimpleStringProperty lastName;
    private transient SimpleStringProperty username;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty phoneNumber;
    private transient EmployeeRole role; // Assume this is serializable
    private transient SimpleDoubleProperty salary;
    private transient String password;
    private transient LocalDate dateEmployed;
    private transient String photo; // reference(address) to photo
    private transient Date birthday;
    private transient Gender gender;
    private transient ArrayList<Permission> permissions;
    private transient ArrayList<Boolean> permissionsBoolean;
    private transient boolean isActive;
    private transient ArrayList<Notification> notifications;

    /// For Cashier
    public User(int id, String firstName, String lastName, String username, String password, String email,
                String phoneNumber, LocalDate dateEmployed, EmployeeRole role, ArrayList<Permission> permissions, boolean isActive) {

        // Use setters to set the values
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setDateEmployed(dateEmployed);
        this.setRole(role);
        this.setPermissions(permissions);
        this.setActive(isActive);

        // Initialize notifications
        String message = firstName + " " + lastName + ", Welcome to our Electronics Store System! " +
                "\nWe hope we have a strong collaboration and get customers' experience even better! " +
                "\nIf any concern arises, feel free to notify your assigned sector's manager. " +
                "\n\nGreetings from stores' Management board! ";

        setNotifications(new ArrayList<>());
        getNotifications().add(new Notification(NotificationType.OTHER, message));
    }


    //All-Argument Constructor
    protected User(int id, String firstName, String lastName, String username,
                   String password, String email, String phoneNumber, LocalDate dateEmployed, String photo,
                   EmployeeRole role, ArrayList<Permission> permissions, boolean isActive, ArrayList<Notification> notifications) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setDateEmployed(dateEmployed);
        this.setRole(role);
        this.setPermissions(permissions);
        this.setActive(isActive);

        this.setPhoto(photo);

        if (notifications != null) {
            this.setNotifications(notifications);
        }
    }


    /// Constructor to add to file
    public User(int id, String firstName, String lastName, Gender gender, Date birthday, Double salary, String username,
                String password, String email, String phoneNumber, LocalDate dateEmployed, EmployeeRole role,
                String photo) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.setBirthday(birthday);
        this.setSalary(salary);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setDateEmployed(dateEmployed);
        this.setRole(role);
        this.setPhoto(photo);
    }



    /// Constructor for Permissions
    public <E> User(int id, String firstName, String lastName, String username, EmployeeRole role, ArrayList<Boolean> permissionsBoolean) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setRole(role);

        if (permissionsBoolean == null || permissionsBoolean.isEmpty()) {
            ArrayList<Boolean> defaultPermissions = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                defaultPermissions.add(true);
            }
            this.setPermissionsBoolean(defaultPermissions);
        } else {
            this.setPermissionsBoolean(permissionsBoolean);
        }
    }


    public User() {
        this.id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.username = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
        this.role = null; // Assuming role is not a property but an object
        this.salary = new SimpleDoubleProperty();
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(id.get());
        out.writeUTF(firstName.getValueSafe());
        out.writeUTF(lastName.getValueSafe());
        out.writeUTF(username.getValueSafe());
        out.writeUTF(email.getValueSafe());
        out.writeUTF(phoneNumber.getValueSafe());
        out.writeObject(role); // Assuming role is a serializable object
        out.writeDouble(salary.get());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        id = new SimpleIntegerProperty(in.readInt());
        firstName = new SimpleStringProperty(in.readUTF());
        lastName = new SimpleStringProperty(in.readUTF());
        username = new SimpleStringProperty(in.readUTF());
        email = new SimpleStringProperty(in.readUTF());
        phoneNumber = new SimpleStringProperty(in.readUTF());
        role = (EmployeeRole) in.readObject(); // Assuming role is a serializable object
        salary = new SimpleDoubleProperty(in.readDouble());
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id=new SimpleIntegerProperty(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName= new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public double getSalary() {
        return salary.get();
    }

    public SimpleDoubleProperty salaryProperty() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = new SimpleDoubleProperty(salary);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public LocalDate getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(LocalDate dateEmployed) {
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


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ArrayList<Boolean> getPermissionsBoolean() {
        return permissionsBoolean;
    }

    public void setPermissionsBoolean(ArrayList<Boolean> permissionsBoolean) {
        this.permissionsBoolean = permissionsBoolean;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return getId() == ((User )o).getId() && ((User)o).getFirstName().equals(this.getFirstName())
                && ((User)o).getLastName().equals(this.getLastName()) && ((User)o).getUsername().equals(this.getUsername())
                && ((User)o).getPassword().equals(this.getPassword()) && ((User)o).getEmail().equals(this.getEmail())
                && ((User)o).getPhoneNumber().equals(this.getPhoneNumber()) && Objects.equals(getDateEmployed(), ((User )o).getDateEmployed())
                && Objects.equals(getRole(), ((User )o).getRole());
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
