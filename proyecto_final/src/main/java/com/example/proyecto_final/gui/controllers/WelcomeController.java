package com.example.proyecto_final.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * Controlador para la pantalla de bienvenida del Sistema de Gestión de Desastres Naturales
 */
public class WelcomeController implements Initializable {
    
    @FXML private Button ingresaButton;
    @FXML private Button crearCuentaButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarBotones();
        System.out.println("Controlador de bienvenida inicializado");
    }
    
    /**
     * Configura los botones de la pantalla de bienvenida
     */
    private void configurarBotones() {
        // Botón Ingresa Aquí
        ingresaButton.setOnAction(event -> {
            System.out.println("Botón 'Ingresa Aquí' presionado");
            cambiarAVistaPrincipal();
        });


        crearCuentaButton.setOnAction(event -> {
            System.out.println("Botón 'Crea tu cuenta' presionado");
            mostrarMensaje("Registro", "Funcionalidad de registro en desarrollo", 
                          "La funcionalidad de creación de cuenta estará disponible próximamente.");
        });
    }
    
    /**
     * Cambia a la vista principal del sistema
     */
    private void cambiarAVistaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyecto_final/main-view.fxml"));
            Scene mainScene = new Scene(loader.load(), 1200, 800);
            
            javafx.stage.Stage stage = (javafx.stage.Stage) ingresaButton.getScene().getWindow();
            stage.setScene(mainScene);
            
            System.out.println("Cambiando a la vista principal del sistema");
        } catch (IOException e) {
            System.err.println("Error al cargar la vista principal: " + e.getMessage());
            mostrarMensaje("Error", "Error de navegación", 
                          "No se pudo cargar la vista principal del sistema.");
        }
    }
    
    /**
     * Muestra un mensaje informativo
     */
    private void mostrarMensaje(String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    
    /**
     * Obtiene el botón de ingreso
     */
    public Button getIngresaButton() {
        return ingresaButton;
    }
    
    /**
     * Obtiene el botón de crear cuenta
     */
    public Button getCrearCuentaButton() {
        return crearCuentaButton;
    }
}
