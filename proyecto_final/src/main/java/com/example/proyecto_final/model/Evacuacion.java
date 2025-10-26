package com.example.proyecto_final.model;

import com.example.proyecto_final.structures.Ruta;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que representa una evacuación en el sistema de gestión de desastres
 */
public class Evacuacion {
    private String id;
    private Ruta ruta;
    private int personasEvacuadas;
    private int personasPendientes;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private EstadoEvacuacion estado;
    private String responsableId;
    private String observaciones;
    
    /**
     * Enum para representar el estado de la evacuación
     */
    public enum EstadoEvacuacion {
        PLANIFICADA("Planificada"),
        EN_PROGRESO("En Progreso"),
        COMPLETADA("Completada"),
        CANCELADA("Cancelada"),
        SUSPENDIDA("Suspendida");
        
        private final String descripcion;
        
        EstadoEvacuacion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    public Evacuacion() {
        this.estado = EstadoEvacuacion.PLANIFICADA;
        this.personasEvacuadas = 0;
        this.personasPendientes = 0;
        this.fechaInicio = LocalDateTime.now();
    }
    
    public Evacuacion(String id, Ruta ruta, int personasPendientes, String responsableId) {
        this();
        this.id = id;
        this.ruta = ruta;
        this.personasPendientes = personasPendientes;
        this.responsableId = responsableId;
    }
    
    /**
     * Calcula el porcentaje de evacuación completada
     */
    public double calcularPorcentajeCompletado() {
        int totalPersonas = personasEvacuadas + personasPendientes;
        if (totalPersonas == 0) return 0.0;
        return (double) personasEvacuadas / totalPersonas * 100;
    }
    
    /**
     * Calcula el tiempo transcurrido desde el inicio
     */
    public long calcularTiempoTranscurrido() {
        if (fechaInicio == null) return 0;
        LocalDateTime fin = fechaFin != null ? fechaFin : LocalDateTime.now();
        return java.time.Duration.between(fechaInicio, fin).toHours();
    }
    
    /**
     * Verifica si la evacuación está completada
     */
    public boolean estaCompletada() {
        return estado == EstadoEvacuacion.COMPLETADA || personasPendientes == 0;
    }
    
    /**
     * Verifica si la evacuación está en progreso
     */
    public boolean estaEnProgreso() {
        return estado == EstadoEvacuacion.EN_PROGRESO;
    }
    
    /**
     * Inicia la evacuación
     */
    public void iniciar() {
        if (estado == EstadoEvacuacion.PLANIFICADA) {
            this.estado = EstadoEvacuacion.EN_PROGRESO;
            this.fechaInicio = LocalDateTime.now();
        }
    }
    
    /**
     * Completa la evacuación
     */
    public void completar() {
        if (estado == EstadoEvacuacion.EN_PROGRESO) {
            this.estado = EstadoEvacuacion.COMPLETADA;
            this.fechaFin = LocalDateTime.now();
            this.personasPendientes = 0;
        }
    }
    
    /**
     * Cancela la evacuación
     */
    public void cancelar(String motivo) {
        this.estado = EstadoEvacuacion.CANCELADA;
        this.fechaFin = LocalDateTime.now();
        this.observaciones = motivo;
    }
    
    /**
     * Registra la evacuación de personas
     */
    public void registrarEvacuacion(int cantidad) {
        if (cantidad > 0 && cantidad <= personasPendientes && estaEnProgreso()) {
            this.personasEvacuadas += cantidad;
            this.personasPendientes -= cantidad;
            
            if (personasPendientes == 0) {
                completar();
            }
        }
    }
    
    /**
     * Calcula la prioridad de la evacuación
     */
    public int calcularPrioridad() {
        int prioridad = 1;
        
        if (ruta != null && ruta.getOrigen() != null) {
            prioridad += ruta.getOrigen().getNivelRiesgo().getValor();
        }
        
        if (personasPendientes > 1000) prioridad += 3;
        else if (personasPendientes > 500) prioridad += 2;
        else if (personasPendientes > 100) prioridad += 1;
        
        if (estaEnProgreso()) prioridad += 2;
        
        return prioridad;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Ruta getRuta() {
        return ruta;
    }
    
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
    
    public int getPersonasEvacuadas() {
        return personasEvacuadas;
    }
    
    public void setPersonasEvacuadas(int personasEvacuadas) {
        this.personasEvacuadas = Math.max(0, personasEvacuadas);
    }
    
    public int getPersonasPendientes() {
        return personasPendientes;
    }
    
    public void setPersonasPendientes(int personasPendientes) {
        this.personasPendientes = Math.max(0, personasPendientes);
    }
    
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public EstadoEvacuacion getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoEvacuacion estado) {
        this.estado = estado;
    }
    
    public String getResponsableId() {
        return responsableId;
    }
    
    public void setResponsableId(String responsableId) {
        this.responsableId = responsableId;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evacuacion that = (Evacuacion) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Evacuacion{id='%s', ruta=%s, evacuadas=%d, pendientes=%d, estado=%s}", id, ruta != null ? ruta.getId() : "null", personasEvacuadas, personasPendientes, estado.getDescripcion());
    }
}
