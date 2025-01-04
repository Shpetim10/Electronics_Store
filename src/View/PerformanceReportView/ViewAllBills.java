package View.PerformanceReportView;

import View.Design;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ViewAllBills implements Design {
    private ComboBox<String> cashierName;
    private ArrayList<Button> bills;

    public ViewAllBills(){
        cashierName=createComboBox("Select Cashier...");
        bills=new ArrayList<>();
        for(int i=0;i<100;i++)
            bills.add(createLargeButton("Bill "+i));
    }

    public Scene createScene(){
        StackPane pane=new StackPane();
        pane.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        pane.setPadding(new Insets(50,50,50,50));

        ScrollPane scrollPane=new ScrollPane();
        scrollPane.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefHeightProperty().bind(pane.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-background-radius: 40;" +
                "-fx-border-radius: 40;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;");

        VBox box=new VBox(25);
        box.setPrefWidth(500);
        box.setPrefHeight(400);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(30,30,30,30));
        box.setStyle("-fx-background-color: rgba(167,246,8,0.3);");
        box.prefHeightProperty().bind(pane.heightProperty());
        box.prefWidthProperty().bind(pane.widthProperty());

        box.getChildren().add(createAlignedGreenBoldLabel("All Generated Bills",100));
        box.getChildren().add(cashierName);
        for(Button bill: bills){
            bill.prefHeightProperty().bind(pane.heightProperty());
            bill.prefWidthProperty().bind(pane.widthProperty());
            box.getChildren().add(bill);
        }

        scrollPane.setContent(box);
        pane.getChildren().add(scrollPane);

        Scene scene=new Scene(pane);
        return scene;
    }

    public ComboBox<String> getCashierName() {
        return cashierName;
    }

    public void setCashierName(ComboBox<String> cashierName) {
        this.cashierName = cashierName;
    }

    public ArrayList<Button> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Button> bills) {
        this.bills = bills;
    }
}
