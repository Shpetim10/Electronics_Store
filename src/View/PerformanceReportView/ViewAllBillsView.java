package View.PerformanceReportView;

import View.Design;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewAllBillsView extends HBox implements Design {
    private VBox buttonBox=new VBox(20);
    private TextArea displayBill=new TextArea();
    private Label title=createAlignedGreenBoldLabel("All Generated Bills");
    public ViewAllBillsView(){
        setUpView();
    }

    public void setUpView(){
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        this.setPadding(new Insets(50,50,50,50));
        this.setSpacing(30);


        ScrollPane scrollPane=new ScrollPane();
        scrollPane.prefWidthProperty().bind(this.widthProperty());
        scrollPane.prefHeightProperty().bind(this.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-background-radius: 40;" +
                "-fx-border-radius: 40;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;");

        createBillsButtonBox();

        scrollPane.setContent(buttonBox);
        scrollPane.setStyle("-fx-alignment: center;");

        displayBill.setStyle("-fx-alignment: center;" +
                "-fx-background-color: transparent;");
        displayBill.setPrefWidth(140);
        displayBill.prefHeightProperty().bind(this.heightProperty());
        displayBill.prefWidthProperty().bind(this.widthProperty());


        this.getChildren().addAll(scrollPane,displayBill);
    }

    public void createBillsButtonBox(){
        buttonBox.setPrefWidth(500);
        buttonBox.setPrefHeight(400);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setPadding(new Insets(30,30,30,30));
        buttonBox.setStyle("-fx-background-color: rgba(167,246,8,0.3);");
        buttonBox.prefHeightProperty().bind(this.heightProperty());
        buttonBox.prefWidthProperty().bind(this.widthProperty());

        buttonBox.getChildren().addAll(title);
    }

    public VBox getButtonBox() {
        return buttonBox;
    }

    public void setButtonBox(VBox buttonBox) {
        this.buttonBox = buttonBox;
    }

    public TextArea getDisplayBill() {
        return displayBill;
    }

    public void setDisplayBill(TextArea displayBill) {
        this.displayBill = displayBill;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }
}
