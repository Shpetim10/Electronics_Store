package View.PerformanceReportView;

import View.Design;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CustomReportView implements Design {
    private StackPane pane;
    private ComboBox<String> cashier;
    private ComboBox<String> sector;
    private DatePicker startDate;
    private DatePicker endDate;
    private Button cancel;
    private Button generate;
    private Label error;

    public CustomReportView(){
        this.pane=new StackPane();
        this.cashier=createComboBox("Select Cashier...");
        this.sector=createComboBox("Select Sector...");
        this.startDate=createDatePicker(pane);
        this.endDate=createDatePicker(pane);
        this.cancel=createGeneralButton("Cancel");
        this.generate=createGeneralButton("Generate Report");
        this.error=createAlignedGreenBoldLabel("",200);
        this.error.setTextFill(Color.RED);
    }

    public Scene createScene(){
        pane.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        pane.setPadding(new Insets(50,50,50,50));

        GridPane grid=new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30,30,30,30));
        grid.prefWidthProperty().bind(pane.widthProperty());
        grid.prefHeightProperty().bind(pane.heightProperty());
        grid.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-background-radius: 40;" +
                "-fx-border-radius: 40;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;");

        Label title=createAlignedGreenBoldLabel("Select custom report fields",250);
        Label selectCashier=createAlignedGreenBoldLabel("Cashier",100);
        Label selectSector=createAlignedGreenBoldLabel("Sector",100);
        Label selectStartDate=createAlignedGreenBoldLabel("Start Date",100);
        Label selectEndDate=createAlignedGreenBoldLabel("End Date",100);

        grid.add(title,0,0);
        grid.add(selectCashier,0,1);
        grid.add(cashier,1,1);
        grid.add(selectSector,0,2);
        grid.add(sector,1,2);
        grid.add(selectStartDate,0,3);
        grid.add(startDate,0,4);
        grid.add(selectEndDate,1,3);
        grid.add(endDate,1,4);
        grid.add(cancel,0,5);
        grid.add(generate,1,5);
        grid.add(error,0,6);
        pane.getChildren().add(grid);
        Scene scene=new Scene(pane);
        return scene;
    }

    public ComboBox<String> getCashier() {
        return cashier;
    }

    public void setCashier(ComboBox<String> cashier) {
        this.cashier = cashier;
    }

    public ComboBox<String> getSector() {
        return sector;
    }

    public void setSector(ComboBox<String> sector) {
        this.sector = sector;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public void setStartDate(DatePicker startDate) {
        this.startDate = startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public void setEndDate(DatePicker endDate) {
        this.endDate = endDate;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public Button getGenerate() {
        return generate;
    }

    public void setGenerate(Button generate) {
        this.generate = generate;
    }

    public Label getError() {
        return error;
    }

    public void setError(Label error) {
        this.error = error;
    }
}
