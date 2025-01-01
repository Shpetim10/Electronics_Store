package MainRoot;

import View.PerformanceReportView.MainReportView;
import javafx.application.Application;
import javafx.stage.Stage;

public class ReportGeneratorMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage primaryStage){
        MainReportView view=new MainReportView();
        primaryStage.setTitle("Report");
        primaryStage.setScene(view.createScene());
        primaryStage.show();
    }
}
