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
    private BorderPane homePage=createHomePage();
    private Button startShift=createLargeButton("Start Shift");
    private Button endShift=createLargeButton("End Shift");
    private final Menu mainMenu=new Menu("Main Menu");
    private Label welcomeMessage=createAlignedGreenBoldLabel("");

    private final MenuItem billingSystemItem=createMenuItem("Billing System","Images/billingSystemIcon.png");
    private final MenuItem generateReportItem=createMenuItem("Generate Report","Images/generateReportIcon.png");
    private final MenuItem viewAllBillsItem=createMenuItem("View Bills", "Images/viewAllBillsIcon.png");
    private final MenuItem userManagementItem=createMenuItem("Billing System","Images/billingSystemIcon.png");
    private final MenuItem inventoryManagementItem=createMenuItem("Inventory Management","Images/generateReportIcon.png");
    private final MenuItem supplierManagementItem=createMenuItem("Supplier Management", "Images/manageSuppliersIcon.png");
    private final MenuItem permissionGrantingItem=createMenuItem("Permission Granting","Images/managePermissions.png");
    private final MenuItem viewAllReportsItem=createMenuItem("View Reports","Images/viewReportsIcon.png");
    private final MenuItem addProductItem=createMenuItem("Add Product","Images/addProductInventoryIcon.png");
    private final MenuItem manageInventory=createMenuItem("Manage Inventory","Images/manageInventoryIcon.png");
    private final MenuItem addEmployee=createMenuItem("Add Employee","Images/addUserIcon.png");
    private final MenuItem manageEmployee=createMenuItem("Staff Management","Images/manageUsersIcon.png");

    private final ImageView profileLogo=new ImageView(new Image("Images/yourProfileIcon.png"));
    private final ImageView storeLogo=new ImageView(new Image("Images/electronicsStoreIcon.png"));
    private final ImageView notificationLogo=new ImageView(new Image("Images/notificationIcon.png"));
    private final ImageView logOutIcon=new ImageView(new Image("Images/logOutIcon.png"));
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
        logOutIcon.setFitHeight(30);
        logOutIcon.setFitWidth(30);
        profileBox.getChildren().addAll(notificationLogo,profileLogo,logOutIcon);


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
        buttonsBox.getChildren().addAll(startShift,endShift);

        ImageView storeIconClone=new ImageView(new Image("Images/electronicsStoreIcon.png"));

        pane.setTop(welcomeMessage);
        pane.setLeft(buttonsBox);
        pane.setRight(storeIconClone);
        return pane;
    }
    public BorderPane createManagerAndAdminHomePage(){
        BorderPane pane=new BorderPane();
        pane.setCenter(storeLogo);
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

    public MenuItem getAddProductItem() {
        return addProductItem;
    }

    public MenuItem getManageInventory() {
        return manageInventory;
    }

    public MenuItem getAddEmployee() {
        return addEmployee;
    }

    public MenuItem getManageEmployee() {
        return manageEmployee;
    }

    public ImageView getLogOutIcon() {
        return logOutIcon;
    }

    public MenuItem getViewAllReportsItem() {
        return viewAllReportsItem;
    }

    public Label getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setHomePage(BorderPane homePage) {
        this.homePage = homePage;
    }
}

