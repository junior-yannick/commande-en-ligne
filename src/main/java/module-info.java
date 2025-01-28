module org.implementation {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.implementation to javafx.fxml;
    exports org.implementation;
}