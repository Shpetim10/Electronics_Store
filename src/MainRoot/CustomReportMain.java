package MainRoot;

import View.PerformanceReportView.CustomReportView;
import javafx.application.Application;
import javafx.stage.Stage;

public class CustomReportMain extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage){
        CustomReportView view=new CustomReportView();
        primaryStage.setTitle("Custom Report");
        primaryStage.setScene(view.createScene());
        primaryStage.show();
    }
}
