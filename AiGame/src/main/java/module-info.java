module com.example.aigame {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.example.aigame to javafx.fxml;
    exports com.example.aigame;
}