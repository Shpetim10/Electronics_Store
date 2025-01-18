package MainRoot;

import View.CustomTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomTableView table=new CustomTableView();
        Pane pane=new Pane();
        pane.getChildren().add(table.getTable());
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(pane));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
