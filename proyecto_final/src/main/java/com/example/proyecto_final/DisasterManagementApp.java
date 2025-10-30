package com.example.proyecto_final;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Aplicación principal del Sistema de Gestión de Desastres Naturales
 */
public class DisasterManagementApp extends Application {
    
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        
        FXMLLoader fxmlLoader = new FXMLLoader(DisasterManagementApp.class.getResource("/com/example/proyecto_final/welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        
        stage.setTitle("Sistema de Gestión de Desastres Naturales");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        
        try {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/disaster-icon.png")));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el icono de la aplicación");
        }
        
        stage.setOnCloseRequest(event -> {
            System.out.println("Cerrando Sistema de Gestión de Desastres...");
            System.exit(0);
        });
        
        stage.show();
        
        System.out.println("Sistema de Gestión de Desastres Naturales iniciado correctamente");
    }
    
    /**
     * Obtiene la instancia del stage principal
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Método principal para ejecutar la aplicación
     */
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Gestión de Desastres Naturales...");
        launch();
    }
}
 