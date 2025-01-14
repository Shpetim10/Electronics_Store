package MainRoot;


import View.ProductInformationView;
import View.Design;
import javafx.application.Application;
import javafx.stage.Stage;

public class ProductInfoMain extends Application implements Design {
        public static void main(String[] args) {
            Application.launch();
        }

        @Override
        public void start(Stage primaryStage){
            ProductInformationView view=new ProductInformationView();
            primaryStage.setTitle("Product Information");
            primaryStage.setScene(view.createScene());
            primaryStage.setMaximized(true);
            primaryStage.show();
        }
    }

