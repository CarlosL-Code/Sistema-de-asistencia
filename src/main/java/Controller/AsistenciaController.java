package Controller;

import Model.Asistencia;
import Repository.AsistenciaRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AsistenciaController {

    private AsistenciaRepository repo;

    public AsistenciaController(AsistenciaRepository repo) {
        this.repo = repo;
    }
    
    

    // REGISTRO DE INGRESO
    public void registrarIngreso(int idUsuario) {
        try {
            repo.registrarEntradaSalida(idUsuario, "ENTRADA");
        } catch (SQLException e) {
            System.err.println("Error al registrar ingreso: " + e.getMessage());
        }
    }
    
    

    // REGISTRO DE SALIDA
    public void registrarSalida(int idUsuario) {
        try {
            repo.registrarEntradaSalida(idUsuario, "SALIDA");
        } catch (SQLException e) {
            System.err.println("Error al registrar salida: " + e.getMessage());
        }
    }
    
    

    // OBTENER REPORTE COMPLETO
    public List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) {
        if (desde.isAfter(hasta)) {
            throw new IllegalArgumentException("La fecha 'desde' no puede ser mayor que la fecha 'hasta'");
        }

        try {
            return repo.obtenerReporte(desde, hasta);
        } catch (SQLException e) {
            System.err.println("Error al obtener reporte: " + e.getMessage());
            return null;
        }
    }

    
    
    // FILTRAR ATRASOS
    public List<Asistencia> obtenerAtrasos(LocalDate desde, LocalDate hasta) {
        List<Asistencia> reporte = obtenerReporte(desde, hasta);
        if (reporte != null) {
            return reporte.stream()
                    .filter(a -> "ATRASO".equals(a.getTipo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    
    
    
    // FILTRAR SALIDAS ANTICIPADAS
    public List<Asistencia> obtenerSalidasAnticipadas(LocalDate desde, LocalDate hasta) {
        List<Asistencia> reporte = obtenerReporte(desde, hasta);
        if (reporte != null) {
            return reporte.stream()
                    .filter(a -> "SALIDA ANTICIPADA".equals(a.getTipo()))
                    .collect(Collectors.toList());
        }
        return null;
    }
    
    

    // FILTRAR INASISTENCIAS
    public List<Asistencia> obtenerInasistencias(LocalDate desde, LocalDate hasta) {
        List<Asistencia> reporte = obtenerReporte(desde, hasta);
        if (reporte != null) {
            return reporte.stream()
                    .filter(a -> "INASISTENCIA".equals(a.getTipo()))
                    .collect(Collectors.toList());
        }
        return null;
    }
    
    
}
