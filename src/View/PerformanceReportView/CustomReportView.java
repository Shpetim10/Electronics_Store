package View.PerformanceReportView;

import Model.SectorType;
import View.Design;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class CustomReportView extends StackPane implements Design {
    private TextField cashier=createTextField("Enter cashier's id...");
    private ComboBox<String> sector=createComboBox("Select Sector...");;
    private DatePicker startDate=createDatePicker("Start Date...");
    private DatePicker endDate=createDatePicker("End Date...");
    private Button clear=createGeneralButton("Clear");
    private Button generate=createGeneralButton("Generate Report");
    private Label error=createAlignedGreenBoldLabel("");
    private Label note=createAlignedBlackLabel("");

    public CustomReportView(){
        setUpView();
    }

    public void setUpView(){
        this.setStyle("-fx-background-color: rgba(167,246,8,0.15);");
        this.setPadding(new Insets(50,50,50,50));

        GridPane grid=new GridPane();
        grid.setHgap(40);
        grid.setVgap(25);
        grid.setPadding(new Insets(30,30,30,30));
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: rgba(167,246,8,0.3);" +
                "-fx-background-radius: 40;" +
                "-fx-border-radius: 40;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;");

        Label title=createAlignedGreenBoldLabel("Select custom report fields");
        Label selectCashier=createAlignedGreenBoldLabel("Cashier");
        Label selectSector=createAlignedGreenBoldLabel("Sector");
        Label selectStartDate=createAlignedGreenBoldLabel("Start Date");
        Label selectEndDate=createAlignedGreenBoldLabel("End Date");

        this.sector.setPromptText("Select Sector...");
        this.sector.setItems(FXCollections.observableArrayList(
                SectorType.ACCESSORIES.toString(),
                SectorType.CAMERAS.toString(),
                SectorType.COMPUTERS.toString(),
                SectorType.ELECTRONICS.toString(),
                SectorType.GAMING.toString(),
                SectorType.HOME_APPLIANCES.toString(),
                SectorType.KITCHEN_ELECTRONICS.toString(),
                SectorType.MOBILE_DEVICES.toString(),
                SectorType.SMART_HOME.toString(),
                "Overall report(All cashiers selected)".toUpperCase()
        ));

        this.note.setFont(Font.font("bahnschrift", FontPosture.ITALIC, 13));
        this.note.setText(
                "Specifications\n" +
                "~Report can be generated for 1 sector and 1 cashier of that sector\n" +
                "~Report can be generated for 1 sector(All cashiers of that sector)\n" +
                "~Report can be generated for 1 cashier(Sector is actomatically selected)\n" +
                "~Report, statistics and costs can only be generated for overall performance(All sectors and cashiers)\n" +
                "~Note: End date has to be later than start Date"
        );

        error.setTextFill(Color.RED);
        grid.add(title,0,0);
        grid.add(selectCashier,0,1);
        grid.add(cashier,1,1);
        grid.add(selectSector,0,2);
        grid.add(sector,1,2);
        grid.add(selectStartDate,0,3);
        grid.add(startDate,0,4);
        grid.add(selectEndDate,1,3);
        grid.add(endDate,1,4);
        grid.add(clear,0,5);
        grid.add(generate,1,5);
        grid.add(error,0,6);
        grid.add(note,1,6);
        this.getChildren().add(grid);
    }

    public Label getError() {
        return error;
    }

    public void setError(Label error) {
        this.error = error;
    }

    public Button getGenerate() {
        return generate;
    }

    public void setGenerate(Button generate) {
        this.generate = generate;
    }

    public Button getClear() {
        return clear;
    }

    public void setClear(Button clear) {
        this.clear = clear;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public void setEndDate(DatePicker endDate) {
        this.endDate = endDate;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public void setStartDate(DatePicker startDate) {
        this.startDate = startDate;
    }

    public ComboBox<String> getSector() {
        return sector;
    }

    public void setSector(ComboBox<String> sector) {
        this.sector = sector;
    }

    public TextField getCashier() {
        return cashier;
    }

    public void setCashier(TextField cashier) {
        this.cashier = cashier;
    }

}
