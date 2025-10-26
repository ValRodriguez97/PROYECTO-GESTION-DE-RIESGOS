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
    opens com.example.proyecto_final.gui.controllers to javafx.fxml;
    opens com.example.proyecto_final.model to javafx.fxml;
    opens com.example.proyecto_final.structures to javafx.fxml;
    opens com.example.proyecto_final.enums to javafx.fxml;
    opens com.example.proyecto_final.interfaces to javafx.fxml;
    
    exports com.example.proyecto_final;
    exports com.example.proyecto_final.gui.controllers;
    exports com.example.proyecto_final.model;
    exports com.example.proyecto_final.structures;
    exports com.example.proyecto_final.enums;
    exports com.example.proyecto_final.interfaces;
}