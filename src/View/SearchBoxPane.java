package View;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SearchBoxPane extends HBox implements Design {
    private ImageView icon=new ImageView(new Image("Images/searchIcon.png"));;
    private String promptText;
    private final TextField searchField= createSearchTextField();;
    private final Button searchButton= createGeneralButton("Search");

    public SearchBoxPane() {
        this("Search...");
    }

    public SearchBoxPane(String promptText) {
        this.setPromptText(promptText);
        setupView();
    }

    public TextField createSearchTextField() {
        TextField searchBox = new TextField();
        searchBox.setPrefHeight(40);
        searchBox.setPrefWidth(350);
        searchBox.setPromptText(this.promptText);
        searchBox.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: yellowgreen;" +
                "-fx-border-width: 1;");
        return searchBox;
    }

    public void setupView() {
        this.setSpacing(10);
        icon.setFitWidth(40);
        icon.setFitHeight(40);
        this.getChildren().addAll(this.icon, this.searchField, this.searchButton);
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
        this.searchField.setPromptText(promptText);
    }

    public TextField getSearchField() {
        return searchField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public String getPromptText() {
        return promptText;
    }
}
