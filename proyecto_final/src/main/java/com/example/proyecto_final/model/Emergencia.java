package com.example.proyecto_final.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Modelo que representa una emergencia en el sistema de gestión de desastres.
 */
public class Emergencia {
    private String id;
    private String ubicacionId;
    private TipoEmergencia tipo;
    private NivelUrgencia nivelUrgencia;
    private String descripcion;
    private LocalDateTime fechaReporte;
    private LocalDateTime fechaActualizacion;
    private EstadoEmergencia estado;
    private String reportadoPor;
    private int personasAfectadas;
    private int personasEvacuadas;
    private String observaciones;
    private String recursosNecesarios;
    
    /**
     * Enum para representar el tipo de emergencia
     */
    public enum TipoEmergencia {
        TERREMOTO("Terremoto"),
        INUNDACION("Inundación"),
        INCENDIO("Incendio"),
        HURACAN("Huracán"),
        TSUNAMI("Tsunami"),
        DESLIZAMIENTO("Deslizamiento"),
        SEQUIA("Sequía"),
        EPIDEMIA("Epidemia"),
        ACCIDENTE("Accidente"),
        OTRO("Otro");
        
        private final String descripcion;
        
        TipoEmergencia(String descripcion) {
        this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
        return descripcion;
        }
    }
    
    /**
     * Enum para representar el nivel de urgencia
     */
    public enum NivelUrgencia {
        BAJA(1, "Baja", "Verde"),
        MEDIA(2, "Media", "Amarillo"),
        ALTA(3, "Alta", "Naranja"),
        CRITICA(4, "Crítica", "Rojo");
        
        private final int valor;
        private final String descripcion;
        private final String color;
        
        NivelUrgencia(int valor, String descripcion, String color) {
            this.valor = valor;
            this.descripcion = descripcion;
            this.color = color;
        }
        
        public int getValor() {
        return valor;
        }
        
        public String getDescripcion() {
        return descripcion;
        }
        
        public String getColor() {
        return color;
        }
    }
    
    /**
     * Enum para representar el estado de la emergencia
     */
    public enum EstadoEmergencia {
        REPORTADA("Reportada"),
        EN_EVALUACION("En Evaluación"),
        EN_PROGRESO("En Progreso"),
        CONTROLADA("Controlada"),
        RESUELTA("Resuelta"),
        CANCELADA("Cancelada");
        
        private final String descripcion;
        
        EstadoEmergencia(String descripcion) {
        this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
        return descripcion;
        }
    }
    
    public Emergencia() {
        this.fechaReporte = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        this.estado = EstadoEmergencia.REPORTADA;
        this.personasAfectadas = 0;
        this.personasEvacuadas = 0;
    }
    
    public Emergencia(String id, String ubicacionId, TipoEmergencia tipo, 
        NivelUrgencia nivelUrgencia, String descripcion, String reportadoPor) {
        this();
        this.id = id;
        this.ubicacionId = ubicacionId;
        this.tipo = tipo;
        this.nivelUrgencia = nivelUrgencia;
        this.descripcion = descripcion;
        this.reportadoPor = reportadoPor;
    }
    
    /**
     * Calcula el porcentaje de evacuación
     */
    public double calcularPorcentajeEvacuacion() {
        if (personasAfectadas == 0) return 0.0;
        return (double) personasEvacuadas / personasAfectadas * 100;
    }
    
    /**
     * Calcula el tiempo transcurrido desde el reporte
     */
    public long calcularTiempoTranscurrido() {
        return java.time.Duration.between(fechaReporte, LocalDateTime.now()).toHours();
    }
    
    /**
     * Verifica si la emergencia requiere atención inmediata
     */
    public boolean requiereAtencionInmediata() {
        return nivelUrgencia == NivelUrgencia.CRITICA || 
            (nivelUrgencia == NivelUrgencia.ALTA && calcularTiempoTranscurrido() > 2);
    }
    
    /**
     * Actualiza el estado de la emergencia
     */
    public void actualizarEstado(EstadoEmergencia nuevoEstado, String observaciones) {
        this.estado = nuevoEstado;
        this.fechaActualizacion = LocalDateTime.now();
        if (observaciones != null && !observaciones.trim().isEmpty()) {
        this.observaciones = observaciones;
        }
    }
    
    /**
     * Registra la evacuación de personas
     */
    public void registrarEvacuacion(int cantidad) {
        if (cantidad > 0) {
            this.personasEvacuadas = Math.min(personasAfectadas, personasEvacuadas + cantidad);
            actualizarEstado(EstadoEmergencia.EN_PROGRESO, 
            "Evacuadas " + cantidad + " personas adicionales");
        }
    }
    
    /**
     * Verifica si la emergencia está resuelta
     */
    public boolean estaResuelta() {
        return estado == EstadoEmergencia.RESUELTA || 
        (personasEvacuadas >= personasAfectadas && personasAfectadas > 0);
    }
    
    /**
     * Calcula la prioridad de atención basada en múltiples factores
     */
    public int calcularPrioridadAtencion() {
        int prioridad = nivelUrgencia.getValor() * 10;
        
        long horasTranscurridas = calcularTiempoTranscurrido();
        if (horasTranscurridas > 24) prioridad += 20;
        else if (horasTranscurridas > 12) prioridad += 15;
        else if (horasTranscurridas > 6) prioridad += 10;
        else if (horasTranscurridas > 2) prioridad += 5;
        
        if (personasAfectadas > 1000) prioridad += 15;
        else if (personasAfectadas > 500) prioridad += 10;
        else if (personasAfectadas > 100) prioridad += 5;
        
        if (estado == EstadoEmergencia.EN_PROGRESO) prioridad -= 5;
        else if (estado == EstadoEmergencia.CONTROLADA) prioridad -= 10;
        
        return Math.max(1, prioridad);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUbicacionId() {
        return ubicacionId;
    }
    
    public void setUbicacionId(String ubicacionId) {
        this.ubicacionId = ubicacionId;
    }
    
    public TipoEmergencia getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoEmergencia tipo) {
        this.tipo = tipo;
    }
    
    public NivelUrgencia getNivelUrgencia() {
        return nivelUrgencia;
    }
    
    public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }
    
    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
    
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public EstadoEmergencia getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoEmergencia estado) {
        this.estado = estado;
    }
    
    public String getReportadoPor() {
        return reportadoPor;
    }
    
    public void setReportadoPor(String reportadoPor) {
        this.reportadoPor = reportadoPor;
    }
    
    public int getPersonasAfectadas() {
        return personasAfectadas;
    }
    
    public void setPersonasAfectadas(int personasAfectadas) {
        this.personasAfectadas = Math.max(0, personasAfectadas);
    }
    
    public int getPersonasEvacuadas() {
        return personasEvacuadas;
    }
    
    public void setPersonasEvacuadas(int personasEvacuadas) {
        this.personasEvacuadas = Math.max(0, Math.min(personasEvacuadas, personasAfectadas));
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getRecursosNecesarios() {
        return recursosNecesarios;
    }
    
    public void setRecursosNecesarios(String recursosNecesarios) {
        this.recursosNecesarios = recursosNecesarios;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emergencia that = (Emergencia) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Emergencia{id='%s', tipo=%s, urgencia=%s, estado=%s, afectados=%d, evacuados=%d}", id, tipo.getDescripcion(), nivelUrgencia.getDescripcion(), estado.getDescripcion(), personasAfectadas, personasEvacuadas);
    }
}
