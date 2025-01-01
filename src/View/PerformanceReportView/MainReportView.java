package View.PerformanceReportView;

import View.Design;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainReportView implements Design {
    private Button viewAllBills;
    private Button overallReport;
    private Button customReport;

    public MainReportView(){
        viewAllBills=createLargeButton("View All Bills");
        overallReport=createLargeButton("Overall Report");
        customReport=createLargeButton("Custom Report");
    }

    public Scene createScene(){
        StackPane pane=new StackPane();
        pane.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        pane.setPadding(new Insets(50,50,50,50));

        VBox box=new VBox(50);
        box.setPrefWidth(500);
        box.setPrefHeight(400);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30,30,30,30));

        box.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-background-radius: 40;" +
                "-fx-border-radius: 40;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;");
        box.getChildren().addAll(viewAllBills,overallReport,customReport);

        pane.getChildren().add(box);
        Scene scene=new Scene(pane);
        return scene;
    }
}
