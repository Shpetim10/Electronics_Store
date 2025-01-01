package MainRoot;

import View.BillingSystemView;
import View.Design;
import javafx.application.Application;
import javafx.stage.Stage;

public class BillingSystem extends Application implements Design {
    public static void main(String[] args) {
       Application.launch();
    }

    @Override
    public void start(Stage primaryStage){
        BillingSystemView view=new BillingSystemView();
        primaryStage.setTitle("Billing System");
        primaryStage.setScene(view.createScene());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
