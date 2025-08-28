package service;

import domain.Asistencia;
import domain.TipoEvento;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import repository.AsistenciaRepository;

public class AsistenciaService implements IAsistenciaService {

    private AsistenciaRepository repo;

    public AsistenciaService(AsistenciaRepository repo) {
        this.repo = repo;
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
                .filter(a -> TipoEvento.ATRASO.equals(a.getTipo()))
                .collect(Collectors.toList());
    }

    // FILTRAR SALIDAS ANTICIPADAS
    @Override
    public List<Asistencia> obtenerSalidasAnticipadas(LocalDate desde, LocalDate hasta) throws SQLException {
        return obtenerReporte(desde, hasta).stream()
                .filter(a -> TipoEvento.SALIDA_ANTICIPADA.equals(a.getTipo()))
                .collect(Collectors.toList());
    }

    // FILTRAR INASISTENCIAS
    @Override
    public List<Asistencia> obtenerInasistencias(LocalDate desde, LocalDate hasta) throws SQLException {
        return obtenerReporte(desde, hasta).stream()
                .filter(a -> TipoEvento.INASISTENCIA.equals(a.getTipo()))
                .collect(Collectors.toList());
    }
}
