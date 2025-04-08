module com.example.guiclientserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guiclientserver to javafx.fxml;
    exports com.example.guiclientserver;
}