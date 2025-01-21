package Control;

import Model.User;
import View.ProfileInformationView;

public class ProfileInformationController {
    private ProfileInformationView view=new ProfileInformationView();
    private User user;
    public ProfileInformationController(User user){
        this.user=user;
        setDisplayInfoListener();
    }
    public void setDisplayInfoListener(){
        this.view.getEmployeeId().setText(String.valueOf(user.getId()));
        this.view.getName().setText(user.getFirstName()+" "+user.getLastName());
        this.view.getEmail().setText(user.getEmail());
        this.view.getUsername().setText(user.getUsername());
        this.view.getRole().setText(user.getRole().toString());
        this.view.getPhoneNumber().setText(user.getPhoneNumber());
        this.view.getDateEmployed().setText(user.getDateEmployed().getDayOfMonth()+" "+user.getDateEmployed().getMonth()+" "+user.getDateEmployed().getYear());
    }

    public ProfileInformationView getView() {
        return view;
    }

    public User getUser() {
        return user;
    }
}
