module com.example.travailpratique3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.travailpratique3 to javafx.fxml;
    exports com.example.travailpratique3;
    exports controller;
    opens controller to javafx.fxml;
}