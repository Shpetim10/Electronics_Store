package View.PerformanceReportView;

import Control.AdminManagerViewBillController;
import Control.ViewAllBillsController;
import View.SearchBoxPane;
import View.UserManagementView.UserTable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AdminManagerAllBillView extends VBox {
      private SearchBoxPane search=new SearchBoxPane("Enter Cashier ID...");
      private StackPane displayPane=new StackPane();
      private ViewAllBillsController billView=new ViewAllBillsController();
      //private UserTable userTable=new UserTable();

      public AdminManagerAllBillView(){
          setUpView();
      }

      public void setUpView(){
          this.setStyle("-fx-background-color: transparent;");
          this.setSpacing(20);

          this.displayPane.setStyle("-fx-background-color: transparent;");

          this.getChildren().addAll(search, displayPane);
      }

    public SearchBoxPane getSearch() {
        return search;
    }

    public ViewAllBillsController getBillView() {
        return billView;
    }

    public StackPane getDisplayPane() {
        return displayPane;
    }
}
