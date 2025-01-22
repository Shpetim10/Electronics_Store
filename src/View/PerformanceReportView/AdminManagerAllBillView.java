package View.PerformanceReportView;

import Control.AdminManagerViewBillController;
import Control.ViewAllBillsController;
import View.SearchBoxPane;
import View.UserManagementView.UserTable;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AdminManagerAllBillView extends VBox {
      private SearchBoxPane search=new SearchBoxPane("Enter Cashier ID...");
      private StackPane displayPane=new StackPane();
      private UserTable userTable=new UserTable();
      private ViewAllBillsController billView=new ViewAllBillsController();


      public AdminManagerAllBillView(){
          setUpView();
      }

      public void setUpView(){
          this.setStyle("-fx-background-color: transparent;");
          this.setSpacing(20);
          this.displayPane.setFocusTraversable(true);

          this.displayPane.setStyle("-fx-background-color: transparent;");
          this.displayPane.prefHeightProperty().bind(this.heightProperty());
          this.displayPane.prefWidthProperty().bind(this.widthProperty());

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

    public UserTable getUserTable() {
        return userTable;
    }
}
