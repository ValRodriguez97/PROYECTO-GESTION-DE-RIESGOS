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
        // --- Recursos ---
        var recurso1 = new com.example.proyecto_final.Model.Recurso(
                "R1", "Agua Potable", com.example.proyecto_final.Enums.TipoRecurso.ALIMENTOS, 1000, "litros", "Z1");
        s.agregarRecurso(recurso1);

        // --- Equipos ---
        var equipo1 = new com.example.proyecto_final.Model.EquipoRescate(
                "E1", "Equipo Médico Alfa",
                com.example.proyecto_final.Model.EquipoRescate.TipoEquipo.MEDICO,
                "Centro", 10, "Dra. Morales");
        s.agregarEquipo(equipo1);

        // --- Usuario administrador ---
        var admin = new com.example.proyecto_final.Model.Administrador(
                "U1", "Laura", "Ramírez", "laura@rescate.org", "lauraR", "1234", "Coordinación");
        s.agregarUsuario(admin);

        // --- Simular operación de equipo y recurso ---
        equipo1.asignarAEmergencia("EV-01");
        recurso1.reservar(200);

        // --- Mostrar reporte actualizado ---
        System.out.println(s.generarReporteGeneral());


        // Imprimir reporte general
        System.out.println(s.generarReporteGeneral());
    }
}
