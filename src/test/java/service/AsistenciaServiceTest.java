package service;

import domain.Asistencia;
import domain.TipoEvento;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsistenciaServiceTest {

    // ----- Fake repository independiente -----
    static class FakeRepo {
        int entradas = 0;
        int salidas = 0;
        List<Asistencia> lista = new ArrayList<>();

        void registrarEntradaSalida(int idUsuario, String tipo) {
            if ("ENTRADA".equals(tipo)) entradas++;
            else if ("SALIDA".equals(tipo)) salidas++;
        }

        List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) {
            return lista;
        }
    }

    // ----- Fake AppProperties para test con horario real -----
    static class FakeProps extends config.AppProperties {
        @Override
        public String getJornadaInicio() { return "09:30"; }
        @Override
        public String getJornadaFin() { return "17:30"; }
    }

    FakeRepo repo;
    AsistenciaService service;

    @BeforeEach
    void setup() {
        repo = new FakeRepo();
        // Se usa un wrapper para adaptar FakeRepo a AsistenciaService
        service = new AsistenciaService(new AsistenciaRepositoryWrapper(repo), new FakeProps());
    }

    // ---------- Wrapper para que AsistenciaService funcione con FakeRepo ----------
    static class AsistenciaRepositoryWrapper extends repository.AsistenciaRepository {
        private final FakeRepo fake;

        AsistenciaRepositoryWrapper(FakeRepo fake) {
            super(null); // pasamos null porque no se usará la conexión real
            this.fake = fake;
        }

        @Override
        public void registrarEntradaSalida(int idUsuario, String tipo) {
            fake.registrarEntradaSalida(idUsuario, tipo);
        }

        @Override
        public List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) {
            return fake.obtenerReporte(desde, hasta);
        }
    }

    // ---------- Tests de registro ----------
    @Test
    void registrarIngreso_delegaEnRepo() throws SQLException {
        service.registrarIngreso(1);
        assertEquals(1, repo.entradas);
    }

    @Test
    void registrarSalida_delegaEnRepo() throws SQLException {
        service.registrarSalida(2);
        assertEquals(1, repo.salidas);
    }

    // ---------- Tests de reporte ----------
    @Test
    void obtenerReporte_retornaListaRepo() throws SQLException {
        Asistencia a1 = new Asistencia();
        Asistencia a2 = new Asistencia();
        repo.lista.add(a1);
        repo.lista.add(a2);

        List<Asistencia> res = service.obtenerReporte(LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals(2, res.size());
        assertSame(repo.lista, res);
    }

    @Test
    void obtenerReporte_fechaDesdeMayor_lanzaExcepcion() {
        LocalDate desde = LocalDate.now();
        LocalDate hasta = LocalDate.now().minusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                service.obtenerReporte(desde, hasta));
    }

    // ---------- Tests de filtrado ----------
    @Test
    void obtenerAtrasos_filtraCorrectamente() throws SQLException {
        Asistencia atrasos = new Asistencia();
        atrasos.setTipo(TipoEvento.ATRASO);

        Asistencia otra = new Asistencia();
        otra.setTipo(TipoEvento.SALIDA_ANTICIPADA);

        repo.lista.add(atrasos);
        repo.lista.add(otra);

        List<Asistencia> res = service.obtenerAtrasos(LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals(1, res.size());
        assertEquals(TipoEvento.ATRASO, res.get(0).getTipo());
    }

    @Test
    void obtenerSalidasAnticipadas_filtraCorrectamente() throws SQLException {
        Asistencia salida = new Asistencia();
        salida.setTipo(TipoEvento.SALIDA_ANTICIPADA);
        repo.lista.add(salida);

        List<Asistencia> res = service.obtenerSalidasAnticipadas(LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals(1, res.size());
        assertEquals(TipoEvento.SALIDA_ANTICIPADA, res.get(0).getTipo());
    }

    @Test
    void obtenerInasistencias_filtraCorrectamente() throws SQLException {
        Asistencia ina = new Asistencia();
        ina.setTipo(TipoEvento.INASISTENCIA);
        repo.lista.add(ina);

        List<Asistencia> res = service.obtenerInasistencias(LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals(1, res.size());
        assertEquals(TipoEvento.INASISTENCIA, res.get(0).getTipo());
    }
}
