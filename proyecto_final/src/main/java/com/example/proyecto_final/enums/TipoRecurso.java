package com.example.proyecto_final.enums;

/**
 * Enum que representa los tipos de recursos en el sistema
 * 
 */
public enum TipoRecurso {
    ALIMENTO("Alimento", 3),
    MEDICINA("Medicina", 4),
    EQUIPO_RESCATE("Equipo de Rescate", 5),
    TRANSPORTE("Transporte", 2),
    COMBUSTIBLE("Combustible", 3),
    AGUA("Agua", 4),
    ROPA("Ropa", 2),
    HERRAMIENTAS("Herramientas", 3),
    COMUNICACION("Equipos de Comunicación", 4),
    ENERGIA("Generadores de Energía", 3);
    
    private final String descripcion;
    private final int prioridadBase;
    
    TipoRecurso(String descripcion, int prioridadBase) {
        this.descripcion = descripcion;
        this.prioridadBase = prioridadBase;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public int getPrioridadBase() {
        return prioridadBase;
    }
}
