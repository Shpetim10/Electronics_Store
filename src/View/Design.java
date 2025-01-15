package View;

import Model.SectorType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface Design {
    default Button createNumberButton(String content,String style){
        Button button=new Button(content);
        button.setPrefWidth(80);
        button.setPrefHeight(80);
        button.setStyle(style);
        return button;
    }
    default Button createGeneralButton(String text){
        String buttonStyle=
                "-fx-background-color: white ;" +
                        "-fx-border-radius: 20 ;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-color: yellowgreen;"+
                        " -fx-border-width: 2;" +
                        "-fx-font-family: Bahnschrift;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 13;";
        Button button=new Button(text);
        button.setStyle(buttonStyle);
        button.setPrefHeight(25);

        return button;
    }
    default ComboBox<String> createComboBox(String prompt){
        ComboBox<String> box=new ComboBox<>();
        box.setPromptText(prompt);
        box.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: black;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 2;");
    box.setVisibleRowCount(10);
        return box;
    }
    default TextField createTextField(String prompt){
        TextField field=new TextField();
        field.setPromptText(prompt);
        field.setStyle("-fx-background-radius: 10;" +
                "-fx-background-color: white;" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-text-fill: black;");
        field.setPrefWidth(150);
        return field;
    }
    default Label createAlignedBlackLabel(String text, int width){
        Label label=new Label(text);
        label.setPrefWidth(width);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }
    default Label createAlignedBlackLabel(String text){
        Label label=new Label(text);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }
    default Label createAlignedBlackBoldLabel(String text){
        Label label=new Label(text);
        label.setFont(Font.font("Bahnschrift",FontWeight.BOLD,13));
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }
    default Label createAlignedGreenBoldLabel(String text, int width){
        Label label=new Label(text);
        label.setPrefWidth(width);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-text-fill: green;" +
                "-fx-font-family: Bahnschrift;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;");
        return label;
    }
    default Label createAlignedGreenBoldLabel(String text){
        Label label=new Label(text);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-text-fill: green;" +
                "-fx-font-family: Bahnschrift;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 18;");
        return label;
    }
    default TextField createTransparentTextField(){
        TextField output=new TextField();
        output.setEditable(true);
        output.setStyle("-fx-background-color: transparent;"+
                "-fx-font-family: Bahnschrift;" +
                "-fx-font-size: 15;");
        return output;
    }
    default CheckBox createCheckBox(){
        CheckBox select=new CheckBox();
        select.setPrefSize(5,5);
        select.setStyle("-fx-border-radius: 2;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: green;");
        return select;
    }
    default Button createLargeButton(String text){
        Button button=new Button(text);
        button.setStyle("-fx-background-color: white ;" +
                "-fx-border-radius: 20 ;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: yellowgreen;"+
                " -fx-border-width: 2;" +
                "-fx-font-family: Bahnschrift;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 15;");
        button.setMinHeight(100);
        button.setMinWidth(400);
        button.setFont(Font.font("Bahnschrift", FontWeight.BOLD,20));
        button.prefWidthProperty().bind(new SimpleDoubleProperty(400));
        button.prefHeightProperty().bind(new SimpleDoubleProperty(100));
        return button;
    }
    default DatePicker createDatePicker(String prompt){
        DatePicker date=new DatePicker();
        date.setPromptText(prompt);
        date.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;");

        return date;
    }
    default ListView createListview(){
        ListView<SectorType> list=new ListView<>();

        list.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: black;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 2;");

        return list;
    }

    default TableView<String[]> createTable() {
        TableView<String[]> table = new TableView<>();
        String tableStyle =
                "-fx-background-color: rgba(167,246,8,0.15);" +
                        "-fx-border-color: darkgreen;"+
                        "-fx-font-size: 20;"
                ;
        table.setStyle(tableStyle);
        table.setPrefSize(165, 500);
        return table;
    }

    default TableColumn<String[], String> createTableColumn(String string) {
        TableColumn<String[],String> tableColumn = new TableColumn<>(string);
        String tableStyle =
                "-fx-background-color: rgba(167,246,8,0.15);" +
                        "-fx-border-color: darkgreen;"+
                        "-fx-alignment: CENTER;"
                ;
        tableColumn.setStyle(tableStyle);
        return tableColumn;
    }



    default PasswordField createPasswordField(){
        PasswordField password=new PasswordField();
        password.setPromptText("Password");
        password.setStyle("-fx-background-radius: 10;" +
                "-fx-background-color: white;" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-text-fill: black;");
        password.setPrefWidth(150);
        return password;
    }
}
