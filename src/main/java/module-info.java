module com.example.pendataansiswa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pendataansiswa to javafx.fxml;
    exports com.example.pendataansiswa;
}