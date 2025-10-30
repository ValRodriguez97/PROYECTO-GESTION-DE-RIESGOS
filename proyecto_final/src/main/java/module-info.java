module com.example.proyecto_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.proyecto_final to javafx.fxml;
    opens com.example.proyecto_final.Controllers to javafx.fxml;
    opens com.example.proyecto_final.Model to javafx.fxml;
    opens com.example.proyecto_final.EstructurasDatos to javafx.fxml;
    opens com.example.proyecto_final.Enums to javafx.fxml;
    opens com.example.proyecto_final.Interfaces to javafx.fxml;
    
    exports com.example.proyecto_final;
    exports com.example.proyecto_final.Controllers;
    exports com.example.proyecto_final.Model;
    exports com.example.proyecto_final.EstructurasDatos;
    exports com.example.proyecto_final.Enums;
    exports com.example.proyecto_final.Interfaces;
}