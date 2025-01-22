package Control;

import Model.Cashier;
import Model.Manager;
import Model.SectorType;
import Model.User;
import View.ProfileInformationView;
import javafx.scene.image.Image;

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
        this.view.getGender().setText(user.getGender().toString());
        this.view.getBirthday().setText(user.getBirthday().getDayOfMonth()+" "+user.getBirthday().getMonth()+" "+user.getBirthday().getYear());
        this.view.getPhoto().setImage(new Image("Images/Avatar.jpg"));

        if(user instanceof Cashier){
            this.view.getSector().setText(((Cashier)user).getSector().toString());
        }
        else if(user instanceof Manager){
            String sectors="";
            for(SectorType sector: ((Manager) user).getSectors()){
                sectors+=sector.toString()+", ";
            }
            this.view.getSector().setText(sectors);
        }
        else{
            this.view.getSector().setText("All");
        }
    }

    public ProfileInformationView getView() {
        return view;
    }

    public User getUser() {
        return user;
    }
}
