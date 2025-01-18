package View;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class UserMainView extends VBox implements Design{
    private final StackPane displayPane=new StackPane();
    //HomePage fields
    private final BorderPane homePage=createHomePage();
    private Button startShift=createLargeButton("Start Shift");
    private Button endShift=createLargeButton("End Shift");
    private Button logOut=createLargeButton("Log Out");
    private final Menu mainMenu=new Menu("Main Menu");
    private Label welcomeMessage=createAlignedGreenBoldLabel("");

    private final MenuItem billingSystemItem=createMenuItem("Billing System","Images/billingSystemIcon.png");
    private final MenuItem generateReportItem=createMenuItem("Generate Report","Images/generateReportIcon.png");
    private final MenuItem viewAllBillsItem=createMenuItem("View Bills", "Images/viewAllBillsIcon.png");
    private final MenuItem userManagementItem=createMenuItem("Billing System","Images/billingSystemIcon.png");
    private final MenuItem inventoryManagementItem=createMenuItem("Generate Report","Images/generateReportIcon.png");
    private final MenuItem supplierManagementItem=createMenuItem("View Bills", "Images/viewAllBillsIcon.png");
    private final MenuItem permissionGrantingItem=createMenuItem("Permission Granting","Images/billingSystemIcon.png");


    private final ImageView profileLogo=new ImageView(new Image("Images/yourProfileIcon.png"));
    private final ImageView storeLogo=new ImageView(new Image("Images/electronicsStoreIcon.png"));
    private final ImageView notificationLogo=new ImageView(new Image("Images/notificationIcon.png"));

    public UserMainView(){
        setUpView();
    }

    public void setUpView(){
        //All menu Bar
        HBox menuHeader = new HBox(0);
        menuHeader.prefWidthProperty().bind(this.widthProperty());
        menuHeader.setPrefHeight(30);
        menuHeader.setStyle("-fx-background-color: white;");
        menuHeader.spacingProperty().bind(this.widthProperty().divide(10).multiply(4));
        menuHeader.setPadding(new Insets(5,5,5,5));
        //Main menu icon
        ImageView mainMenuIcon=new ImageView(new Image("Images/mainMenuIcon.png"));
        mainMenuIcon.setFitWidth(30);
        mainMenuIcon.setFitHeight(30);
        mainMenu.setGraphic(mainMenuIcon);
        mainMenuIcon.setVisible(true);

        storeLogo.setFitHeight(50);
        storeLogo.setFitWidth(50);

        //HBox for right part
        HBox profileBox=new HBox(10);
        notificationLogo.setFitHeight(30);
        notificationLogo.setFitWidth(30);
        profileLogo.setFitHeight(30);
        profileLogo.setFitWidth(30);
        profileBox.getChildren().addAll(notificationLogo,profileLogo);


        menuHeader.getChildren().addAll(createMenuView(),storeLogo,profileBox);

        //Bind display pane with general pane
        displayPane.prefWidthProperty().bind(this.widthProperty());
        displayPane.prefHeightProperty().bind(this.heightProperty());

        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");

        this.displayPane.getChildren().add(homePage);

        this.getChildren().addAll(menuHeader,displayPane);
    }

    public MenuBar createMenuView(){
        MenuBar mainMenu=new MenuBar();
        mainMenu.getMenus().add(this.mainMenu);
        mainMenu.setStyle("-fx-background-color: white;" +
                "-fx-shadow-highlight: false;");
        return mainMenu;
    }

    public MenuItem createMenuItem(String content, String imagePath){
        MenuItem item=new MenuItem(content);
        ImageView icon=new ImageView(new Image(imagePath));
        icon.setFitWidth(30);
        icon.setFitHeight(30);
        item.setGraphic(icon);
        return item;
    }
    public void addToMainMenu(MenuItem item){
        this.mainMenu.getItems().add(item);
    }

    public BorderPane createHomePage(){
        BorderPane pane=new BorderPane();
        pane.setPadding(new Insets(40,40,40,40));
        pane.setStyle("-fx-background-color: transparent;");

        return pane;
    }
    //Create user mainView
    public BorderPane createCashierMainPage(){
        BorderPane pane=new BorderPane();
        pane.setPadding(new Insets(40,40,40,40));
        pane.setStyle("-fx-background-color: transparent;");
        //Content
        VBox buttonsBox=new VBox(40);
        buttonsBox.setAlignment(Pos.CENTER);
        Label homePageTitle=createAlignedGreenBoldLabel("Home Page");
        buttonsBox.getChildren().add(homePageTitle);
        if (startShift == null) {
            startShift = createLargeButton("Start Shift");
        }
        if (endShift == null){
            endShift = createLargeButton("End Shift");
        }
        if (logOut == null) {
            logOut = createLargeButton("Log Out");
        }
        buttonsBox.getChildren().addAll(startShift,endShift,logOut);

        ImageView storeIconClone=new ImageView(new Image("Images/electronicsStoreIcon.png"));

        pane.setTop(welcomeMessage);
        pane.setLeft(buttonsBox);
        pane.setRight(storeIconClone);
        return pane;
    }
    public BorderPane createManagerAndAdminHomePage(){
        BorderPane pane=new BorderPane();

        return pane;
    }
    public Pane getDisplayPane() {
        return displayPane;
    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public MenuItem getBillingSystemItem() {
        return billingSystemItem;
    }

    public ImageView getProfileLogo() {
        return profileLogo;
    }

    public ImageView getStoreLogo() {
        return storeLogo;
    }

    public ImageView getNotificationLogo() {
        return notificationLogo;
    }

    public BorderPane getHomePage() {
        return homePage;
    }

    public Button getStartShift() {
        return startShift;
    }

    public Button getEndShift() {
        return endShift;
    }

    public Button getLogOut() {
        return logOut;
    }

    public MenuItem getGenerateReportItem() {
        return generateReportItem;
    }

    public MenuItem getViewAllBillsItem() {
        return viewAllBillsItem;
    }

    public MenuItem getUserManagementItem() {
        return userManagementItem;
    }

    public MenuItem getInventoryManagementItem() {
        return inventoryManagementItem;
    }

    public MenuItem getSupplierManagementItem() {
        return supplierManagementItem;
    }

    public MenuItem getPermissionGrantingItem() {
        return permissionGrantingItem;
    }

    public Label getWelcomeMessage() {
        return welcomeMessage;
    }
}
