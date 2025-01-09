module Electronics.Store {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens MainRoot;
    exports Control;
    exports View;
    exports Model;
    //exports Files;
    exports Exceptions;
    exports MainRoot;
    exports View.PerformanceReportView;
    opens View.PerformanceReportView;
    opens View;
}