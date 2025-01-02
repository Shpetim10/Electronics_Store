module Electronics.Store {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens MainRoot;
    exports Control;
    exports View;
    exports Model;
    //exports Files;
    exports Exceptions;
}