package com.example.proyecto_final;

import com.example.proyecto_final.Enums.NivelUrgencia;
import com.example.proyecto_final.Enums.TipoRuta;
import com.example.proyecto_final.Model.SistemaGestionDesastres;
import com.example.proyecto_final.Model.Zona;

/**
 * Clase de prueba (modo consola) para verificar el funcionamiento
 * del sistema de gestión de desastres sin usar la interfaz gráfica.
 */
public class Main {
    public static void main(String[] args) {
        SistemaGestionDesastres s = new SistemaGestionDesastres();
        s.inicializarSistema();

        // Crear zonas
        s.agregarZona(new Zona("Z1", "Centro", NivelUrgencia.ALTA));
        s.agregarZona(new Zona("Z2", "Norte", NivelUrgencia.MEDIA));

        // Conectar zonas
        s.conectarZonas("R1", "Z1", "Z2", 15, 0.8, TipoRuta.TERRESTRE);

        // Planificar y procesar una evacuación
        s.planificarEvacuacionEntreZonas("EV-01", "Z1", "Z2", 500, NivelUrgencia.ALTA, "Capitán Pérez");
        s.procesarSiguienteEvacuacion();

        // Imprimir reporte general
        System.out.println(s.generarReporteGeneral());
    }
}
