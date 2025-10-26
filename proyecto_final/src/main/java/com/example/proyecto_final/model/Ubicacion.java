package com.example.proyecto_final.model;

import java.util.Objects;

/**
 * Modelo que representa una ubicación en el sistema de gestión de desastres.
 * Puede ser una ciudad, refugio, centro de ayuda, etc.
 */
public class Ubicacion {
    private String id;
    private String nombre;
    private double coordenadaX;
    private double coordenadaY;
    private NivelUrgencia nivelUrgencia;
    private int capacidadMaxima;
    private int personasActuales;
    private TipoUbicacion tipo;
    private boolean activa;
    
    /**
     * Enum para representar el nivel de urgencia de una ubicación
     */
    public enum NivelUrgencia {
        BAJA(1, "Baja"),
        MEDIA(2, "Media"),
        ALTA(3, "Alta"),
        CRITICA(4, "Crítica");
        
        private final int valor;
        private final String descripcion;
        
        NivelUrgencia(int valor, String descripcion) {
            this.valor = valor;
            this.descripcion = descripcion;
        }
        
        public int getValor() {
            return valor;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    /**
     * Enum para representar el tipo de ubicación
     */
    public enum TipoUbicacion {
        CIUDAD("Ciudad"),
        REFUGIO("Refugio"),
        CENTRO_AYUDA("Centro de Ayuda"),
        HOSPITAL("Hospital"),
        BASE_OPERACIONES("Base de Operaciones"),
        ZONA_EVACUACION("Zona de Evacuación");
        
        private final String descripcion;
        
        TipoUbicacion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    public Ubicacion() {
        this.activa = true;
        this.personasActuales = 0;
        this.nivelUrgencia = NivelUrgencia.BAJA;
    }
    
    public Ubicacion(String id, String nombre, double coordenadaX, double coordenadaY, TipoUbicacion tipo, int capacidadMaxima) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
    }
    
    /**
     * Calcula el porcentaje de ocupación de la ubicación
     */
    public double calcularPorcentajeOcupacion() {
        if (capacidadMaxima == 0) return 0.0;
        return (double) personasActuales / capacidadMaxima * 100;
    }
    
    /**
     * Verifica si la ubicación tiene capacidad disponible
     */
    public boolean tieneCapacidadDisponible() {
        return personasActuales < capacidadMaxima;
    }
    
    /**
     * Calcula la capacidad disponible
     */
    public int obtenerCapacidadDisponible() {
        return Math.max(0, capacidadMaxima - personasActuales);
    }
    
    /**
     * Calcula la distancia euclidiana a otra ubicación
     */
    public double calcularDistancia(Ubicacion otra) {
        double deltaX = this.coordenadaX - otra.coordenadaX;
        double deltaY = this.coordenadaY - otra.coordenadaY;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getCoordenadaX() {
        return coordenadaX;
    }
    
    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }
    
    public double getCoordenadaY() {
        return coordenadaY;
    }
    
    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
    
    public NivelUrgencia getNivelUrgencia() {
        return nivelUrgencia;
    }
    
    public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }
    
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
    
    public int getPersonasActuales() {
        return personasActuales;
    }
    
    public void setPersonasActuales(int personasActuales) {
        this.personasActuales = Math.max(0, Math.min(personasActuales, capacidadMaxima));
    }
    
    public TipoUbicacion getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoUbicacion tipo) {
        this.tipo = tipo;
    }
    
    public boolean isActiva() {
        return activa;
    }
    
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return Objects.equals(id, ubicacion.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Ubicacion{id='%s', nombre='%s', tipo=%s, urgencia=%s, ocupacion=%.1f%%}", id, nombre, tipo.getDescripcion(), nivelUrgencia.getDescripcion(), calcularPorcentajeOcupacion());
    }
}
