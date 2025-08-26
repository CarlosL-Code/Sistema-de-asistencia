package ui.controller;
import domain.Asistencia;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import service.AsistenciaService;
import service.IAsistenciaService;

public class AsistenciaController implements IAsistenciaService{

    private IAsistenciaService service;
    
 

    public AsistenciaController(IAsistenciaService service) {
        this.service = service;
    }

    public void registrarIngreso(int idUsuario) {
        try {
            service.registrarIngreso(idUsuario);
        } catch (Exception e) {
            System.err.println("Error al registrar ingreso: " + e.getMessage());
        }
    }

    public void registrarSalida(int idUsuario) {
        try {
            service.registrarSalida(idUsuario);
        } catch (Exception e) {
            System.err.println("Error al registrar salida: " + e.getMessage());
        }
    }

    public List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) {
        try {
            return service.obtenerReporte(desde, hasta);
        } catch (Exception e) {
            System.err.println("Error al obtener reporte: " + e.getMessage());
            return null;
        }
    }

    public List<Asistencia> obtenerAtrasos(LocalDate desde, LocalDate hasta) {
        try {
            return service.obtenerAtrasos(desde, hasta);
        } catch (Exception e) {
            System.err.println("Error al obtener atrasos: " + e.getMessage());
            return null;
        }
    }

    public List<Asistencia> obtenerSalidasAnticipadas(LocalDate desde, LocalDate hasta) {
        try {
            return service.obtenerSalidasAnticipadas(desde, hasta);
        } catch (Exception e) {
            System.err.println("Error al obtener salidas anticipadas: " + e.getMessage());
            return null;
        }
    }

    public List<Asistencia> obtenerInasistencias(LocalDate desde, LocalDate hasta) {
        try {
            return service.obtenerInasistencias(desde, hasta);
        } catch (Exception e) {
            System.err.println("Error al obtener inasistencias: " + e.getMessage());
            return null;
        }
    }
}
