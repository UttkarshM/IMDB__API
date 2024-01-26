module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.desktop;
    requires org.json;
    requires json.simple;
    requires mongo.java.driver;
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}