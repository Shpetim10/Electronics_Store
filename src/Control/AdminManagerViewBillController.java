package Control;


import Model.Administrator;
import Model.Cashier;
import Model.User;
import View.PerformanceReportView.ViewAllBills;
import View.SearchBoxPane;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AdminManagerViewBillController{
    private User admin;
    private ViewAllBills billView=new ViewAllBills();
    private SearchBoxPane searchBar=new SearchBoxPane("Enter Cashier name...");
    private VBox mainView=setUpView();
    public AdminManagerViewBillController(User admin){
        this.admin=admin;
    }

    public VBox setUpView(){
        VBox box=new VBox(10);
        box.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        HBox billPane=billView;

        billPane.prefHeightProperty().bind(box.heightProperty()) ;
        searchBar.setAlignment(Pos.TOP_CENTER);
        box.getChildren().addAll(searchBar,billPane);

        return box;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public SearchBoxPane getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(SearchBoxPane searchBar) {
        this.searchBar = searchBar;
    }

    public VBox getMainView() {
        return mainView;
    }

    public void setMainView(VBox mainView) {
        this.mainView = mainView;
    }


}
