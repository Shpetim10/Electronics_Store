package MainRoot;

import View.BillingSystemView;
import View.BillingSystemViewFinal;
import View.Design;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BillingSystem extends Application implements Design {
    public static void main(String[] args) {
       Application.launch();
    }

    @Override
    public void start(Stage primaryStage){
        BillingSystemViewFinal view=new BillingSystemViewFinal();
        primaryStage.setTitle("Billing System");
        primaryStage.setScene(new Scene(view));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
