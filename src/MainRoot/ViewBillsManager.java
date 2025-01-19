package MainRoot;

import Control.AdminManagerViewBillController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewBillsManager extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AdminManagerViewBillController control=new AdminManagerViewBillController();

        Scene scene=new Scene(control.getView());

        stage.setScene(scene);
        stage.show();
    }
}
