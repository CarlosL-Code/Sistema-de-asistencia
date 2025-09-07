package service;

import domain.Asistencia;
import domain.TipoEvento;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import repository.AsistenciaRepository;
import config.AppProperties;


public class AsistenciaService implements IAsistenciaService {

    private AsistenciaRepository repo;
    private LocalTime jornadaInicio;
    private LocalTime jornadaFin;
    

    // Constructor recibe repo y AppProperties
    public AsistenciaService(AsistenciaRepository repo, AppProperties props) {
        this.repo = repo;

        // Convertir Strings de properties a LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.jornadaInicio = LocalTime.parse(props.getJornadaInicio(), formatter);
        this.jornadaFin = LocalTime.parse(props.getJornadaFin(), formatter);
    }

    // REGISTRO DE INGRESO
    @Override
    public void registrarIngreso(int idUsuario) throws SQLException {
        repo.registrarEntradaSalida(idUsuario, "ENTRADA");
    }

    // REGISTRO DE SALIDA
    @Override
    public void registrarSalida(int idUsuario) throws SQLException {
        repo.registrarEntradaSalida(idUsuario, "SALIDA");
    }

    // OBTENER REPORTE COMPLETO
    @Override
    public List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) throws SQLException {
        if (desde.isAfter(hasta)) {
            throw new IllegalArgumentException("La fecha 'desde' no puede ser mayor que la fecha 'hasta'");
        }
        return repo.obtenerReporte(desde, hasta);
    }

    // FILTRAR ATRASOS
    @Override
    public List<Asistencia> obtenerAtrasos(LocalDate desde, LocalDate hasta) throws SQLException {
        return obtenerReporte(desde, hasta).stream()
                .filter(a -> TipoEvento.ATRASO.equals(a.getTipo())
                        || (a.getHora() != null && a.getHora().isAfter(jornadaInicio)))
                .collect(Collectors.toList());
    }

    // FILTRAR SALIDAS ANTICIPADAS
    @Override
    public List<Asistencia> obtenerSalidasAnticipadas(LocalDate desde, LocalDate hasta) throws SQLException {
        return obtenerReporte(desde, hasta).stream()
                .filter(a -> TipoEvento.SALIDA_ANTICIPADA.equals(a.getTipo())
                        || (a.getHora() != null && a.getHora().isBefore(jornadaFin)))
                .collect(Collectors.toList());
    }

    // FILTRAR INASISTENCIAS
    @Override
    public List<Asistencia> obtenerInasistencias(LocalDate desde, LocalDate hasta) throws SQLException {
        return obtenerReporte(desde, hasta).stream()
                .filter(a -> TipoEvento.INASISTENCIA.equals(a.getTipo()))
                .collect(Collectors.toList());
    }

    // GETTERS DE HORARIOS (opcional, útil para tests)
    public LocalTime getJornadaInicio() {
        return jornadaInicio;
    }

    public LocalTime getJornadaFin() {
        return jornadaFin;
    }
}
