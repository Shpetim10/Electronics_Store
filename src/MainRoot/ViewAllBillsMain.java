package MainRoot;

import View.PerformanceReportView.ViewAllBills;
import javafx.application.Application;
import javafx.stage.Stage;

public class ViewAllBillsMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage primaryStage){
        ViewAllBills view=new ViewAllBills();

        primaryStage.setTitle("View All Bills");
        primaryStage.setScene(view.createScene());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
