package com.example.proyecto_final.gui.controllers;

import com.example.proyecto_final.model.*;
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
import java.util.ArrayList;
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
    @FXML private Label ubicacionesCountLabel, emergenciasCountLabel, equiposCountLabel, recursosCountLabel;
    @FXML private Label statusLabel, timeLabel;
    
    private List<Ubicacion> ubicaciones;
    private List<Emergencia> emergencias;
    private List<EquipoRescate> equipos;
    private List<Recurso> recursos;
    private Usuario usuarioActual;
    
    private Timeline timeUpdater;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        ubicaciones = new ArrayList<>();
        emergencias = new ArrayList<>();
        equipos = new ArrayList<>();
        recursos = new ArrayList<>();
        
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
        
        crearUbicacionesPrueba();
        
        crearEmergenciasPrueba();
        
        crearEquiposPrueba();
        
        crearRecursosPrueba();
        
        actualizarContadores();
        
        statusLabel.setText("Datos de prueba cargados exitosamente");
        
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos Cargados");
            alert.setHeaderText("Datos de prueba cargados exitosamente");
            alert.setContentText(String.format("Se cargaron %d ubicaciones, %d emergencias, %d equipos y %d recursos", ubicaciones.size(), emergencias.size(), equipos.size(), recursos.size()));
            alert.showAndWait();
        });
    }
    
    /**
     * Crea ubicaciones de prueba
     */
    private void crearUbicacionesPrueba() {
        ubicaciones.clear();
        
        ubicaciones.add(new Ubicacion("U001", "Ciudad Central", 100, 100, Ubicacion.TipoUbicacion.CIUDAD, 50000));
        ubicaciones.add(new Ubicacion("U002", "Refugio Norte", 150, 80, Ubicacion.TipoUbicacion.REFUGIO, 1000));
        ubicaciones.add(new Ubicacion("U003", "Hospital Principal", 120, 120, Ubicacion.TipoUbicacion.HOSPITAL, 500));
        ubicaciones.add(new Ubicacion("U004", "Centro de Ayuda Sur", 80, 150, Ubicacion.TipoUbicacion.CENTRO_AYUDA, 2000));
        ubicaciones.add(new Ubicacion("U005", "Base de Operaciones", 200, 200, Ubicacion.TipoUbicacion.BASE_OPERACIONES, 100));
        
        ubicaciones.get(0).setNivelUrgencia(Ubicacion.NivelUrgencia.ALTA);
        ubicaciones.get(1).setNivelUrgencia(Ubicacion.NivelUrgencia.MEDIA);
        ubicaciones.get(2).setNivelUrgencia(Ubicacion.NivelUrgencia.CRITICA);
        ubicaciones.get(3).setNivelUrgencia(Ubicacion.NivelUrgencia.BAJA);
        ubicaciones.get(4).setNivelUrgencia(Ubicacion.NivelUrgencia.MEDIA);
        
        System.out.println("Ubicaciones de prueba creadas: " + ubicaciones.size());
    }
    
    /**
     * Crea emergencias de prueba
     */
    private void crearEmergenciasPrueba() {
        emergencias.clear();
        
        emergencias.add(new Emergencia("E001", "U001", Emergencia.TipoEmergencia.TERREMOTO, Emergencia.NivelUrgencia.CRITICA, "Terremoto de magnitud 7.2", "admin"));
        emergencias.add(new Emergencia("E002", "U002", Emergencia.TipoEmergencia.INUNDACION, Emergencia.NivelUrgencia.ALTA, "Inundación por desbordamiento", "operador1"));
        emergencias.add(new Emergencia("E003", "U003", Emergencia.TipoEmergencia.INCENDIO, Emergencia.NivelUrgencia.MEDIA, "Incendio en sector residencial", "operador2"));
        
        emergencias.get(0).setPersonasAfectadas(15000);
        emergencias.get(1).setPersonasAfectadas(5000);
        emergencias.get(2).setPersonasAfectadas(2000);
        
        System.out.println("Emergencias de prueba creadas: " + emergencias.size());
    }
    
    /**
     * Crea equipos de rescate de prueba
     */
    private void crearEquiposPrueba() {
        equipos.clear();
        
        equipos.add(new EquipoRescate("EQ001", "Equipo Médico Alpha", EquipoRescate.TipoEquipo.MEDICO, "U003", 8, "Dr. García"));
        equipos.add(new EquipoRescate("EQ002", "Bomberos Bravo", EquipoRescate.TipoEquipo.BOMBEROS, "U005", 12, "Cpt. Rodríguez"));
        equipos.add(new EquipoRescate("EQ003", "Rescate Charlie", EquipoRescate.TipoEquipo.BUSQUEDA_RESCATE, "U001", 6, "Sgt. López"));
        equipos.add(new EquipoRescate("EQ004", "Evacuación Delta", EquipoRescate.TipoEquipo.EVACUACION, "U002", 10, "Lt. Martínez"));
        
        equipos.get(0).setPersonalAsignado(6);
        equipos.get(0).setExperienciaAnos(8);
        equipos.get(1).setPersonalAsignado(10);
        equipos.get(1).setExperienciaAnos(12);
        equipos.get(2).setPersonalAsignado(5);
        equipos.get(2).setExperienciaAnos(6);
        equipos.get(3).setPersonalAsignado(8);
        equipos.get(3).setExperienciaAnos(4);
        
        System.out.println("Equipos de prueba creados: " + equipos.size());
    }
    
    /**
     * Crea recursos de prueba
     */
    private void crearRecursosPrueba() {
        recursos.clear();
        
        recursos.add(new Recurso("R001", "Agua Potable", com.example.proyecto_final.enums.TipoRecurso.AGUA, 1000, "litros", "U001"));
        recursos.add(new Recurso("R002", "Medicinas Básicas", com.example.proyecto_final.enums.TipoRecurso.MEDICINA, 500, "unidades", "U003"));
        recursos.add(new Recurso("R003", "Alimentos No Perecederos", com.example.proyecto_final.enums.TipoRecurso.ALIMENTO, 2000, "kg", "U002"));
        recursos.add(new Recurso("R004", "Equipos de Comunicación", com.example.proyecto_final.enums.TipoRecurso.COMUNICACION, 50, "unidades", "U005"));
        recursos.add(new Recurso("R005", "Generadores", com.example.proyecto_final.enums.TipoRecurso.ENERGIA, 20, "unidades", "U004"));
        
        System.out.println("Recursos de prueba creados: " + recursos.size());
    }
    
    /**
     * Actualiza los contadores en la vista de inicio
     */
    private void actualizarContadores() {
        ubicacionesCountLabel.setText(String.valueOf(ubicaciones.size()));
        emergenciasCountLabel.setText(String.valueOf(emergencias.size()));
        equiposCountLabel.setText(String.valueOf(equipos.size()));
        recursosCountLabel.setText(String.valueOf(recursos.size()));
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
     * Obtiene la lista de ubicaciones
     */
    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }
    
    /**
     * Obtiene la lista de emergencias
     */
    public List<Emergencia> getEmergencias() {
        return emergencias;
    }
    
    /**
     * Obtiene la lista de equipos
     */
    public List<EquipoRescate> getEquipos() {
        return equipos;
    }
    
    /**
     * Obtiene la lista de recursos
     */
    public List<Recurso> getRecursos() {
        return recursos;
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
