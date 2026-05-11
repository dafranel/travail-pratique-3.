module com.example.travailpratique3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.travailpratique3 to javafx.fxml;
    opens com.example.travailpratique3.controller to javafx.fxml;

    exports com.example.travailpratique3;
    exports com.example.travailpratique3.controller;
    exports com.example.travailpratique3.model;
   // exports com.example.travailpratique3.util;
}