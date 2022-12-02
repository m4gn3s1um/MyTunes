module com.example.mytunes
{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.mytunes to javafx.fxml;
    exports com.example.mytunes;
    exports com.example.mytunes.controller;
    opens com.example.mytunes.controller to javafx.fxml;
    exports com.example.mytunes.model;
    opens com.example.mytunes.model to javafx.fxml;
}