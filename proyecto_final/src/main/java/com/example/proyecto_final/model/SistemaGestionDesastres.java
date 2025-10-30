package com.example.proyecto_final.model;

import com.example.proyecto_final.structures.Ruta;
import com.example.proyecto_final.structures.GrafoDirigido;
import com.example.proyecto_final.structures.ColaPrioridad;
import com.example.proyecto_final.structures.ArbolDistribucion;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase central del sistema de gestión de desastres naturales.
 * Gestiona todas las entidades principales del sistema.
 */
public class SistemaGestionDesastres {
    private List<Usuario> usuarios;
    private List<Recurso> recursos;
    private List<Evacuacion> evacuaciones;
    private List<Ruta> rutas;
    private List<Zona> zonas;
    private List<EquipoRescate> equipos;
    private GrafoDirigido grafoDirigido;
    private ColaPrioridad colaPrioridad;
    private MapaRecursos mapaRecursos;
    private ArbolDistribucion arbolDistribucion;
    
    public SistemaGestionDesastres() {
        this.usuarios = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.evacuaciones = new ArrayList<>();
        this.rutas = new ArrayList<>();
        this.zonas = new ArrayList<>();
        this.equipos = new ArrayList<>();
        this.grafoDirigido = new GrafoDirigido();
        this.colaPrioridad = new ColaPrioridad();
        this.mapaRecursos = new MapaRecursos();
        this.arbolDistribucion = new ArbolDistribucion();
    }
    
    /**
     * Inicializa el sistema con datos básicos
     */
    public void inicializarSistema() {
        System.out.println("Inicializando Sistema de Gestión de Desastres...");
        
        // Inicializar estructuras de datos
        grafoDirigido = new GrafoDirigido();
        colaPrioridad = new ColaPrioridad();
        mapaRecursos = new MapaRecursos();
        arbolDistribucion = new ArbolDistribucion();
        
        System.out.println("Sistema inicializado correctamente");
    }
    
    /**
     * Agrega un usuario al sistema
     */
    public boolean agregarUsuario(Usuario usuario) {
        if (usuario != null && !usuarios.contains(usuario)) {
            usuarios.add(usuario);
            return true;
        }
        return false;
    }
    
    /**
     * Agrega un recurso al sistema
     */
    public boolean agregarRecurso(Recurso recurso) {
        if (recurso != null && !recursos.contains(recurso)) {
            recursos.add(recurso);
            // El recurso se agregará al mapa cuando se asigne a una ruta específica
            return true;
        }
        return false;
    }
    
    /**
     * Asigna un recurso a una ruta específica
     */
    public boolean asignarRecursoARuta(Recurso recurso, Ruta ruta) {
        if (recurso != null && ruta != null) {
            mapaRecursos.agregarRecurso(recurso, ruta);
            return true;
        }
        return false;
    }
    
    /**
     * Agrega una evacuación al sistema
     */
    public boolean agregarEvacuacion(Evacuacion evacuacion) {
        if (evacuacion != null && !evacuaciones.contains(evacuacion)) {
            evacuaciones.add(evacuacion);
            colaPrioridad.agregarEvacuacion(evacuacion);
            return true;
        }
        return false;
    }
    
    /**
     * Agrega una ruta al sistema
     */
    public boolean agregarRuta(Ruta ruta) {
        if (ruta != null && !rutas.contains(ruta)) {
            rutas.add(ruta);
            grafoDirigido.agregarArista(ruta);
            return true;
        }
        return false;
    }
    
    /**
     * Agrega una zona al sistema
     */
    public boolean agregarZona(Zona zona) {
        if (zona != null && !zonas.contains(zona)) {
            zonas.add(zona);
            return true;
        }
        return false;
    }
    
    /**
     * Agrega un equipo de rescate al sistema
     */
    public boolean agregarEquipo(EquipoRescate equipo) {
        if (equipo != null && !equipos.contains(equipo)) {
            equipos.add(equipo);
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene estadísticas generales del sistema
     */
    public String obtenerEstadisticasGenerales() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DEL SISTEMA ===\n");
        stats.append("Usuarios registrados: ").append(usuarios.size()).append("\n");
        stats.append("Recursos disponibles: ").append(recursos.size()).append("\n");
        stats.append("Evacuaciones activas: ").append(evacuaciones.size()).append("\n");
        stats.append("Rutas definidas: ").append(rutas.size()).append("\n");
        stats.append("Zonas monitoreadas: ").append(zonas.size()).append("\n");
        stats.append("Equipos de rescate: ").append(equipos.size()).append("\n");
        stats.append("\n").append(grafoDirigido.generarEstadisticas());
        stats.append("\n").append(colaPrioridad.generarEstadisticas());
        
        return stats.toString();
    }
    
    /**
     * Simula el funcionamiento del sistema
     */
    public void simularSistema() {
        System.out.println("=== SIMULACIÓN DEL SISTEMA ===");
        
        // Simular rutas
        grafoDirigido.simularRutas();
        
        // Mostrar estadísticas
        System.out.println(obtenerEstadisticasGenerales());
    }
    
    // Getters
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
    
    public List<Recurso> getRecursos() {
        return new ArrayList<>(recursos);
    }
    
    public List<Evacuacion> getEvacuaciones() {
        return new ArrayList<>(evacuaciones);
    }
    
    public List<Ruta> getRutas() {
        return new ArrayList<>(rutas);
    }
    
    public List<Zona> getZonas() {
        return new ArrayList<>(zonas);
    }
    
    public List<EquipoRescate> getEquipos() {
        return new ArrayList<>(equipos);
    }
    
    public GrafoDirigido getGrafoDirigido() {
        return grafoDirigido;
    }
    
    public ColaPrioridad getColaPrioridad() {
        return colaPrioridad;
    }
    
    public MapaRecursos getMapaRecursos() {
        return mapaRecursos;
    }
    
    public ArbolDistribucion getArbolDistribucion() {
        return arbolDistribucion;
    }
    
    @Override
    public String toString() {
        return String.format("SistemaGestionDesastres{usuarios=%d, recursos=%d, evacuaciones=%d, rutas=%d, zonas=%d, equipos=%d}", 
            usuarios.size(), recursos.size(), evacuaciones.size(), rutas.size(), zonas.size(), equipos.size());
    }
}
