package com.example.proyecto_final.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que representa una evaluación en el sistema de gestión de desastres
 * Permite evaluar el desempeño y efectividad de las operaciones
 */
public class Evaluacion {
    private String id;
    private String tipoEvaluacion;
    private String descripcion;
    private LocalDateTime fechaEvaluacion;
    private String evaluadorId;
    private int puntuacion;
    private int puntuacionMaxima;
    private String observaciones;
    private EstadoEvaluacion estado;
    private String entidadEvaluadaId; 
    private TipoEntidad tipoEntidad;
    
    /**
     * Enum para representar el estado de la evaluación
     */
    public enum EstadoEvaluacion {
        PENDIENTE("Pendiente"),
        EN_PROGRESO("En Progreso"),
        COMPLETADA("Completada"),
        CANCELADA("Cancelada");
        
        private final String descripcion;
        
        EstadoEvaluacion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    /**
     * Enum para representar el tipo de entidad evaluada
     */
    public enum TipoEntidad {
        ZONA("Zona"),
        RUTA("Ruta"),
        RECURSO("Recurso"),
        EQUIPO("Equipo"),
        EVACUACION("Evacuación"),
        SISTEMA("Sistema");
        
        private final String descripcion;
        
        TipoEntidad(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    public Evaluacion() {
        this.estado = EstadoEvaluacion.PENDIENTE;
        this.puntuacion = 0;
        this.puntuacionMaxima = 100;
        this.fechaEvaluacion = LocalDateTime.now();
    }
    
    public Evaluacion(String id, String tipoEvaluacion, String evaluadorId, String entidadEvaluadaId, TipoEntidad tipoEntidad) {
        this();
        this.id = id;
        this.tipoEvaluacion = tipoEvaluacion;
        this.evaluadorId = evaluadorId;
        this.entidadEvaluadaId = entidadEvaluadaId;
        this.tipoEntidad = tipoEntidad;
    }
    
    /**
     * Calcula el porcentaje de la evaluación
     */
    public double calcularPorcentaje() {
        if (puntuacionMaxima == 0) return 0.0;
        return (double) puntuacion / puntuacionMaxima * 100;
    }
    
    /**
     * Obtiene el nivel de desempeño basado en la puntuación
     */
    public NivelDesempeno obtenerNivelDesempeno() {
        double porcentaje = calcularPorcentaje();
        
        if (porcentaje >= 90) return NivelDesempeno.EXCELENTE;
        else if (porcentaje >= 80) return NivelDesempeno.BUENO;
        else if (porcentaje >= 70) return NivelDesempeno.SATISFACTORIO;
        else if (porcentaje >= 60) return NivelDesempeno.REGULAR;
        else return NivelDesempeno.DEFICIENTE;
    }
    
    /**
     * Enum para representar el nivel de desempeño
     */
    public enum NivelDesempeno {
        EXCELENTE("Excelente", 5),
        BUENO("Bueno", 4),
        SATISFACTORIO("Satisfactorio", 3),
        REGULAR("Regular", 2),
        DEFICIENTE("Deficiente", 1);
        
        private final String descripcion;
        private final int valor;
        
        NivelDesempeno(String descripcion, int valor) {
            this.descripcion = descripcion;
            this.valor = valor;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        public int getValor() {
            return valor;
        }
    }
    
    /**
     * Verifica si la evaluación está completada
     */
    public boolean estaCompletada() {
        return estado == EstadoEvaluacion.COMPLETADA;
    }
    
    /**
     * Verifica si la evaluación está en progreso
     */
    public boolean estaEnProgreso() {
        return estado == EstadoEvaluacion.EN_PROGRESO;
    }
    
    /**
     * Inicia la evaluación
     */
    public void iniciar() {
        if (estado == EstadoEvaluacion.PENDIENTE) {
            this.estado = EstadoEvaluacion.EN_PROGRESO;
        }
    }
    
    /**
     * Completa la evaluación
     */
    public void completar(int puntuacion, String observaciones) {
        if (estado == EstadoEvaluacion.EN_PROGRESO) {
            this.puntuacion = Math.max(0, Math.min(puntuacion, puntuacionMaxima));
            this.observaciones = observaciones;
            this.estado = EstadoEvaluacion.COMPLETADA;
        }
    }
    
    /**
     * Cancela la evaluación
     */
    public void cancelar(String motivo) {
        this.estado = EstadoEvaluacion.CANCELADA;
        this.observaciones = motivo;
    }
    
    /**
     * Calcula la calificación en letras
     */
    public String obtenerCalificacionLetras() {
        double porcentaje = calcularPorcentaje();
        
        if (porcentaje >= 97) return "A+";
        else if (porcentaje >= 93) return "A";
        else if (porcentaje >= 90) return "A-";
        else if (porcentaje >= 87) return "B+";
        else if (porcentaje >= 83) return "B";
        else if (porcentaje >= 80) return "B-";
        else if (porcentaje >= 77) return "C+";
        else if (porcentaje >= 73) return "C";
        else if (porcentaje >= 70) return "C-";
        else if (porcentaje >= 67) return "D+";
        else if (porcentaje >= 63) return "D";
        else if (porcentaje >= 60) return "D-";
        else return "F";
    }
    
    /**
     * Genera un reporte de la evaluación
     */
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE EVALUACIÓN ===\n");
        reporte.append("ID: ").append(id).append("\n");
        reporte.append("Tipo: ").append(tipoEvaluacion).append("\n");
        reporte.append("Entidad: ").append(tipoEntidad.getDescripcion())
               .append(" (").append(entidadEvaluadaId).append(")\n");
        reporte.append("Evaluador: ").append(evaluadorId).append("\n");
        reporte.append("Fecha: ").append(fechaEvaluacion).append("\n");
        reporte.append("Estado: ").append(estado.getDescripcion()).append("\n");
        reporte.append("Puntuación: ").append(puntuacion).append("/").append(puntuacionMaxima)
               .append(" (").append(String.format("%.1f%%", calcularPorcentaje())).append(")\n");
        reporte.append("Nivel: ").append(obtenerNivelDesempeno().getDescripcion()).append("\n");
        reporte.append("Calificación: ").append(obtenerCalificacionLetras()).append("\n");
        
        if (observaciones != null && !observaciones.trim().isEmpty()) {
            reporte.append("Observaciones: ").append(observaciones).append("\n");
        }
        
        return reporte.toString();
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }
    
    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }
    
    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
    
    public String getEvaluadorId() {
        return evaluadorId;
    }
    
    public void setEvaluadorId(String evaluadorId) {
        this.evaluadorId = evaluadorId;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = Math.max(0, Math.min(puntuacion, puntuacionMaxima));
    }
    
    public int getPuntuacionMaxima() {
        return puntuacionMaxima;
    }
    
    public void setPuntuacionMaxima(int puntuacionMaxima) {
        this.puntuacionMaxima = Math.max(1, puntuacionMaxima);
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public EstadoEvaluacion getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoEvaluacion estado) {
        this.estado = estado;
    }
    
    public String getEntidadEvaluadaId() {
        return entidadEvaluadaId;
    }
    
    public void setEntidadEvaluadaId(String entidadEvaluadaId) {
        this.entidadEvaluadaId = entidadEvaluadaId;
    }
    
    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }
    
    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }
    
    // Métodos equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluacion that = (Evaluacion) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Evaluacion{id='%s', tipo='%s', puntuacion=%d/%d, nivel=%s}", id, tipoEvaluacion, puntuacion, puntuacionMaxima, obtenerNivelDesempeno().getDescripcion());
    }
}
