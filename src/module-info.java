module Electronics.Store {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens MainRoot;
    opens Images;

    exports Control;
    exports View;
    exports Model;
    exports Exceptions;
    exports View.BillingSystemView;
}