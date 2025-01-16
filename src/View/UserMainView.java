package View;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class UserMainView extends BorderPane {
    private Pane displayPane=new Pane();
    private Menu mainMenu=new Menu("Main Menu");
    private MenuItem billingSystemItem=new MenuItem("Billing System");
    private ImageView profileLogo=new ImageView(new Image("Images/yourProfileIcon.png"));
    private ImageView storeLogo=new ImageView(new Image("Images/electronicStoreIcon.png"));
    private ImageView notificationLogo=new ImageView(new Image("Images/notificationIcon.png"));

    public UserMainView(){
        setUpView();
    }

    public void setUpView(){
        HBox menuHeader = new HBox(0);
        menuHeader.prefWidthProperty().bind(this.widthProperty());
        menuHeader.setPrefHeight(40);
        menuHeader.setStyle("-fx-background-color: white;");
        menuHeader.spacingProperty().bind(this.widthProperty().divide(8).multiply(3));


        ImageView mainMenuIcon=new ImageView(new Image("Images/mainMenuIcon.png"));
        mainMenuIcon.setFitWidth(40);
        mainMenuIcon.setFitHeight(40);
        mainMenu.setGraphic(mainMenuIcon);


        ImageView billingSystemIcon=new ImageView(new Image("Images/billingSystemIcon.png"));
        billingSystemIcon.setFitWidth(40);
        billingSystemIcon.setFitHeight(40);
        billingSystemItem.setGraphic(mainMenuIcon);

        storeLogo.setFitHeight(60);
        storeLogo.setFitWidth(50);

        HBox profileBox=new HBox(10);
        notificationLogo.setFitHeight(40);
        notificationLogo.setFitWidth(40);
        profileLogo.setFitHeight(40);
        profileLogo.setFitWidth(40);
        profileBox.getChildren().addAll(notificationLogo,profileLogo);

        menuHeader.getChildren().addAll(createMenuView(),storeLogo,profileBox);

        this.setTop(menuHeader);
        this.setCenter(displayPane);
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
    }

    public MenuBar createMenuView(){
        MenuBar mainMenu=new MenuBar();

        this.mainMenu.getItems().add(billingSystemItem);
        mainMenu.getMenus().add(this.mainMenu);
        mainMenu.setStyle("-fx-background-color: white;");

        return mainMenu;
    }
}
