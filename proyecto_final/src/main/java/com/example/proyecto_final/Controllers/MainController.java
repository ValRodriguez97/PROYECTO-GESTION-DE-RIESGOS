package com.example.proyecto_final.Controllers;

import com.example.proyecto_final.Model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador principal de la aplicación de gestión de desastres
 */
public class MainController implements Initializable {
    
    @FXML private Label userInfoLabel, userStatusLabel;
    @FXML private Button inicioButton, adminButton, rutasButton, estadisticasButton, mapaButton;
    @FXML private Button loginButton, cargarDatosButton;
    @FXML private StackPane contentArea;
    @FXML private VBox inicioView, adminView, rutasView, estadisticasView, mapaView;
    @FXML private Label zonasCountLabel, equiposCountLabel, recursosCountLabel;
    @FXML private Label statusLabel, timeLabel;
    
    private SistemaGestionDesastres sistema;
    private Usuario usuarioActual;
    
    private Timeline timeUpdater;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        sistema = new SistemaGestionDesastres();
        sistema.inicializarSistema();
        
        configurarInterfazInicial();
        
        configurarActualizadorTiempo();
        
        mostrarVistaInicio();
        
        actualizarContadores();
        
        System.out.println("Controlador principal inicializado correctamente");
    }
    
    /**
     * Configura la interfaz inicial de la aplicación
     */
    private void configurarInterfazInicial() {
        
        configurarBotonesNavegacion();
        
        statusLabel.setText("Sistema iniciado - Listo para operaciones");
        userInfoLabel.setText("Usuario: No autenticado");
        userStatusLabel.setText("No autenticado");
        
        cargarDatosButton.setOnAction(event -> cargarDatosPrueba());
        loginButton.setOnAction(event -> showLoginDialog());
    }
    
    /**
     * Configura los botones de navegación
     */
    private void configurarBotonesNavegacion() {
        inicioButton.setOnAction(event -> mostrarVistaInicio());
        adminButton.setOnAction(event -> mostrarVistaAdmin());
        rutasButton.setOnAction(event -> mostrarVistaRutas());
        estadisticasButton.setOnAction(event -> mostrarVistaEstadisticas());
        mapaButton.setOnAction(event -> mostrarVistaMapa());
    }
    
    /**
     * Configura el actualizador de tiempo en tiempo real
     */
    private void configurarActualizadorTiempo() {
        timeUpdater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String tiempoActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            timeLabel.setText(tiempoActual);
        }));
        timeUpdater.setCycleCount(Timeline.INDEFINITE);
        timeUpdater.play();
    }
    
    @FXML
    private void showInicioTab() {
        mostrarVistaInicio();
    }
    
    @FXML
    private void showAdminTab() {
        mostrarVistaAdmin();
    }
    
    @FXML
    private void showRutasTab() {
        mostrarVistaRutas();
    }
    
    @FXML
    private void showEstadisticasTab() {
        mostrarVistaEstadisticas();
    }
    
    @FXML
    private void showMapaTab() {
        mostrarVistaMapa();
    }
    
    /**
     * Muestra la vista de inicio
     */
    private void mostrarVistaInicio() {
        ocultarTodasLasVistas();
        inicioView.setVisible(true);
        actualizarEstadoBoton(inicioButton, true);
        statusLabel.setText("Vista: Panel de Inicio");
    }
    
    /**
     * Muestra la vista de administración
     */
    private void mostrarVistaAdmin() {
        ocultarTodasLasVistas();
        adminView.setVisible(true);
        actualizarEstadoBoton(adminButton, true);
        statusLabel.setText("Vista: Panel de Administración");
    }
    
    /**
     * Muestra la vista de rutas
     */
    private void mostrarVistaRutas() {
        ocultarTodasLasVistas();
        rutasView.setVisible(true);
        actualizarEstadoBoton(rutasButton, true);
        statusLabel.setText("Vista: Panel de Rutas");
    }
    
    /**
     * Muestra la vista de estadísticas
     */
    private void mostrarVistaEstadisticas() {
        ocultarTodasLasVistas();
        estadisticasView.setVisible(true);
        actualizarEstadoBoton(estadisticasButton, true);
        statusLabel.setText("Vista: Panel de Estadísticas");
    }
    
    /**
     * Muestra la vista de mapa
     */
    private void mostrarVistaMapa() {
        ocultarTodasLasVistas();
        mapaView.setVisible(true);
        actualizarEstadoBoton(mapaButton, true);
        statusLabel.setText("Vista: Mapa Interactivo");
    }
    
    /**
     * Oculta todas las vistas
     */
    private void ocultarTodasLasVistas() {
        inicioView.setVisible(false);
        adminView.setVisible(false);
        rutasView.setVisible(false);
        estadisticasView.setVisible(false);
        mapaView.setVisible(false);
        
        // Resetear estado de botones
        actualizarEstadoBoton(inicioButton, false);
        actualizarEstadoBoton(adminButton, false);
        actualizarEstadoBoton(rutasButton, false);
        actualizarEstadoBoton(estadisticasButton, false);
        actualizarEstadoBoton(mapaButton, false);
    }
    
    /**
     * Actualiza el estado visual de un botón
     */
    private void actualizarEstadoBoton(Button boton, boolean activo) {
        if (activo) {
            boton.getStyleClass().add("active");
        } else {
            boton.getStyleClass().remove("active");
        }
    }
    
    /**
     * Carga datos de prueba en el sistema
     */
    @FXML
    private void cargarDatosPrueba() {
        statusLabel.setText("Cargando datos de prueba...");
        
        crearZonasPrueba();
        
        crearEquiposPrueba();
        
        crearRecursosPrueba();
        
        actualizarContadores();
        
        statusLabel.setText("Datos de prueba cargados exitosamente");
        
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos Cargados");
            alert.setHeaderText("Datos de prueba cargados exitosamente");
            alert.setContentText(String.format("Se cargaron %d zonas, %d equipos y %d recursos", sistema.getZonas().size(), sistema.getEquipos().size(), sistema.getRecursos().size()));
            alert.showAndWait();
        });
    }
    
    /**
     * Crea zonas de prueba
     */
    private void crearZonasPrueba() {
        Zona zona1 = new Zona("Z001", "Ciudad Central", com.example.proyecto_final.Enums.NivelUrgencia.ALTA);
        zona1.setCoordenadaX(100);
        zona1.setCoordenadaY(100);
        zona1.setPoblacionAfectada(15000);
        zona1.setDescripcion("Zona urbana principal afectada por terremoto");
        sistema.agregarZona(zona1);
        
        Zona zona2 = new Zona("Z002", "Refugio Norte", com.example.proyecto_final.Enums.NivelUrgencia.MEDIA);
        zona2.setCoordenadaX(150);
        zona2.setCoordenadaY(80);
        zona2.setPoblacionAfectada(2000);
        zona2.setDescripcion("Refugio temporal para evacuados");
        sistema.agregarZona(zona2);
        
        Zona zona3 = new Zona("Z003", "Hospital Principal", com.example.proyecto_final.Enums.NivelUrgencia.CRITICA);
        zona3.setCoordenadaX(120);
        zona3.setCoordenadaY(120);
        zona3.setPoblacionAfectada(500);
        zona3.setDescripcion("Hospital con heridos críticos");
        sistema.agregarZona(zona3);
        
        Zona zona4 = new Zona("Z004", "Centro de Ayuda Sur", com.example.proyecto_final.Enums.NivelUrgencia.BAJA);
        zona4.setCoordenadaX(80);
        zona4.setCoordenadaY(150);
        zona4.setPoblacionAfectada(1000);
        zona4.setDescripcion("Centro de distribución de ayuda");
        sistema.agregarZona(zona4);
        
        Zona zona5 = new Zona("Z005", "Base de Operaciones", com.example.proyecto_final.Enums.NivelUrgencia.MEDIA);
        zona5.setCoordenadaX(200);
        zona5.setCoordenadaY(200);
        zona5.setPoblacionAfectada(100);
        zona5.setDescripcion("Base de operaciones de emergencia");
        sistema.agregarZona(zona5);
        
        System.out.println("Zonas de prueba creadas: " + sistema.getZonas().size());
    }
    
    /**
     * Crea equipos de rescate de prueba
     */
    private void crearEquiposPrueba() {
        EquipoRescate equipo1 = new EquipoRescate("EQ001", "Equipo Médico Alpha", EquipoRescate.TipoEquipo.MEDICO, "Z003", 8, "Dr. García");
        equipo1.setPersonalAsignado(6);
        equipo1.setExperienciaAnos(8);
        sistema.agregarEquipo(equipo1);
        
        EquipoRescate equipo2 = new EquipoRescate("EQ002", "Bomberos Bravo", EquipoRescate.TipoEquipo.BOMBEROS, "Z005", 12, "Cpt. Rodríguez");
        equipo2.setPersonalAsignado(10);
        equipo2.setExperienciaAnos(12);
        sistema.agregarEquipo(equipo2);
        
        EquipoRescate equipo3 = new EquipoRescate("EQ003", "Rescate Charlie", EquipoRescate.TipoEquipo.BUSQUEDA_RESCATE, "Z001", 6, "Sgt. López");
        equipo3.setPersonalAsignado(5);
        equipo3.setExperienciaAnos(6);
        sistema.agregarEquipo(equipo3);
        
        EquipoRescate equipo4 = new EquipoRescate("EQ004", "Evacuación Delta", EquipoRescate.TipoEquipo.EVACUACION, "Z002", 10, "Lt. Martínez");
        equipo4.setPersonalAsignado(8);
        equipo4.setExperienciaAnos(4);
        sistema.agregarEquipo(equipo4);
        
        System.out.println("Equipos de prueba creados: " + sistema.getEquipos().size());
    }
    
    /**
     * Crea recursos de prueba
     */
    private void crearRecursosPrueba() {
        Recurso recurso1 = new Recurso("R001", "Agua Potable", com.example.proyecto_final.Enums.TipoRecurso.ALIMENTOS, 1000, "litros", "Z001");
        sistema.agregarRecurso(recurso1);
        
        Recurso recurso2 = new Recurso("R002", "Medicinas Básicas", com.example.proyecto_final.Enums.TipoRecurso.MEDICINAS, 500, "unidades", "Z003");
        sistema.agregarRecurso(recurso2);
        
        Recurso recurso3 = new Recurso("R003", "Alimentos No Perecederos", com.example.proyecto_final.Enums.TipoRecurso.ALIMENTOS, 2000, "kg", "Z002");
        sistema.agregarRecurso(recurso3);
        
        Recurso recurso4 = new Recurso("R004", "Equipos de Comunicación", com.example.proyecto_final.Enums.TipoRecurso.EQUIPOS, 50, "unidades", "Z005");
        sistema.agregarRecurso(recurso4);
        
        Recurso recurso5 = new Recurso("R005", "Generadores", com.example.proyecto_final.Enums.TipoRecurso.EQUIPOS, 20, "unidades", "Z004");
        sistema.agregarRecurso(recurso5);
        
        System.out.println("Recursos de prueba creados: " + sistema.getRecursos().size());
    }
    
    /**
     * Actualiza los contadores en la vista de inicio
     */
    private void actualizarContadores() {
        zonasCountLabel.setText(String.valueOf(sistema.getZonas().size()));
        equiposCountLabel.setText(String.valueOf(sistema.getEquipos().size()));
        recursosCountLabel.setText(String.valueOf(sistema.getRecursos().size()));
    }
    
    /**
     * Muestra el diálogo de login
     */
    @FXML
    private void showLoginDialog() {

        statusLabel.setText("Funcionalidad de login en desarrollo");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Sistema de Autenticación");
        alert.setContentText("La funcionalidad de login estará disponible en la siguiente versión.");
        alert.showAndWait();
    }
    
    /**
     * Obtiene el sistema de gestión de desastres
     */
    public SistemaGestionDesastres getSistema() {
        return sistema;
    }
    
    /**
     * Obtiene la lista de zonas
     */
    public List<Zona> getZonas() {
        return sistema.getZonas();
    }
    
    /**
     * Obtiene la lista de equipos
     */
    public List<EquipoRescate> getEquipos() {
        return sistema.getEquipos();
    }
    
    /**
     * Obtiene la lista de recursos
     */
    public List<Recurso> getRecursos() {
        return sistema.getRecursos();
    }
    
    /**
     * Obtiene el usuario actual
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    /**
     * Establece el usuario actual
     */
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        if (usuario != null) {
            userInfoLabel.setText("Usuario: " + usuario.obtenerNombreCompleto() + " (" + usuario.getRol().getDescripcion() + ")");
            userStatusLabel.setText("Conectado como " + usuario.getRol().getDescripcion());
        } else {
            userInfoLabel.setText("Usuario: No autenticado");
            userStatusLabel.setText("No autenticado");
        }
    }
}
