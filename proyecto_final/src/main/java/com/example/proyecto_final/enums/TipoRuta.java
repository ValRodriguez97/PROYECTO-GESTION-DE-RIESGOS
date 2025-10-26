package com.example.proyecto_final.enums;

/**
 * Enum que representa los tipos de ruta en el sistema
 */
public enum TipoRuta {
    EVACUACION("Ruta de Evacuación"),
    SUMINISTRO("Ruta de Suministro"),
    RESCATE("Ruta de Rescate"),
    EMERGENCIA("Ruta de Emergencia"),
    ALTERNATIVA("Ruta Alternativa");
    
    private final String descripcion;
    
    TipoRuta(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}
