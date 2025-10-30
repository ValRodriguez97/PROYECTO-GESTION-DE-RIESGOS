package com.example.proyecto_final.enums;

/**
 * Enum que representa los tipos de ruta en el sistema
 */
public enum TipoRuta {
    TERRESTRE("Terrestre"),
    AEREA("Aérea"),
    MARITIMA("Marítima");
    
    private final String descripcion;
    
    TipoRuta(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}
