package ui.controller;

import domain.Asistencia;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import service.IAsistenciaService;

import static org.junit.jupiter.api.Assertions.*;

class AsistenciaControllerTest {

    // ----- Fakes (sin frameworks de mock) -----

    /** Servicio fake que registra llamadas y devuelve datos controlados. */
    static class FakeOkService implements IAsistenciaService {
        int ingresos = 0;
        int salidas = 0;

        List<Asistencia> reporte      = new ArrayList<>();
        List<Asistencia> atrasos      = new ArrayList<>();
        List<Asistencia> salidasAnt   = new ArrayList<>();
        List<Asistencia> inasistencias= new ArrayList<>();

        @Override
        public void registrarIngreso(int idUsuario) {
            ingresos++;
        }

        @Override
        public void registrarSalida(int idUsuario) {
            salidas++;
        }

        @Override
        public List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) {
            return reporte;
        }

        @Override
        public List<Asistencia> obtenerAtrasos(LocalDate desde, LocalDate hasta) {
            return atrasos;
        }

        @Override
        public List<Asistencia> obtenerSalidasAnticipadas(LocalDate desde, LocalDate hasta) {
            return salidasAnt;
        }

        @Override
        public List<Asistencia> obtenerInasistencias(LocalDate desde, LocalDate hasta) {
            return inasistencias;
        }
    }

    /** Servicio fake que siempre lanza excepción para probar manejo de errores. */
    static class FakeFailService implements IAsistenciaService {
        final String message;
        FakeFailService(String message) { this.message = message; }

        @Override
        public void registrarIngreso(int idUsuario) {
            throw new RuntimeException(message);
        }

        @Override
        public void registrarSalida(int idUsuario) {
            throw new RuntimeException(message);
        }

        @Override
        public List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) {
            throw new RuntimeException(message);
        }

        @Override
        public List<Asistencia> obtenerAtrasos(LocalDate desde, LocalDate hasta) {
            throw new RuntimeException(message);
        }

        @Override
        public List<Asistencia> obtenerSalidasAnticipadas(LocalDate desde, LocalDate hasta) {
            throw new RuntimeException(message);
        }

        @Override
        public List<Asistencia> obtenerInasistencias(LocalDate desde, LocalDate hasta) {
            throw new RuntimeException(message);
        }
    }

    // ----- Captura de System.err -----
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalErr;

    @BeforeEach
    void hookErr() {
        originalErr = System.err;
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreErr() {
        System.setErr(originalErr);
        errContent.reset();
    }

    // ---------- Tests de rutas OK (sin excepciones) ----------

    @Test
    void registrarIngreso_delegaEnServicio() {
        FakeOkService fake = new FakeOkService();
        AsistenciaController controller = new AsistenciaController(fake);

        controller.registrarIngreso(10);

        assertEquals(1, fake.ingresos, "Debe delegar en el servicio registrarIngreso");
        assertTrue(errContent.toString().isBlank(), "No debe escribir en System.err");
    }

    @Test
    void registrarSalida_delegaEnServicio() {
        FakeOkService fake = new FakeOkService();
        AsistenciaController controller = new AsistenciaController(fake);

        controller.registrarSalida(20);

        assertEquals(1, fake.salidas, "Debe delegar en el servicio registrarSalida");
        assertTrue(errContent.toString().isBlank(), "No debe escribir en System.err");
    }

    @Test
    void obtenerReporte_retornaListaDelServicio() {
        FakeOkService fake = new FakeOkService();
        fake.reporte.add(new Asistencia());
        fake.reporte.add(new Asistencia());
        AsistenciaController controller = new AsistenciaController(fake);

        List<Asistencia> res = controller.obtenerReporte(LocalDate.now().minusDays(7), LocalDate.now());

        assertNotNull(res);
        assertEquals(2, res.size());
        assertSame(fake.reporte, res, "Debe retornar exactamente la lista del servicio");
        assertTrue(errContent.toString().isBlank());
    }

    @Test
    void obtenerAtrasos_retornaListaDelServicio() {
        FakeOkService fake = new FakeOkService();
        fake.atrasos.add(new Asistencia());
        AsistenciaController controller = new AsistenciaController(fake);

        List<Asistencia> res = controller.obtenerAtrasos(LocalDate.now().minusDays(7), LocalDate.now());

        assertNotNull(res);
        assertEquals(1, res.size());
        assertSame(fake.atrasos, res);
        assertTrue(errContent.toString().isBlank());
    }

    @Test
    void obtenerSalidasAnticipadas_retornaListaDelServicio() {
        FakeOkService fake = new FakeOkService();
        fake.salidasAnt.add(new Asistencia());
        fake.salidasAnt.add(new Asistencia());
        AsistenciaController controller = new AsistenciaController(fake);

        List<Asistencia> res = controller.obtenerSalidasAnticipadas(LocalDate.now().minusDays(7), LocalDate.now());

        assertNotNull(res);
        assertEquals(2, res.size());
        assertSame(fake.salidasAnt, res);
        assertTrue(errContent.toString().isBlank());
    }

    @Test
    void obtenerInasistencias_retornaListaDelServicio() {
        FakeOkService fake = new FakeOkService();
        AsistenciaController controller = new AsistenciaController(fake);

        List<Asistencia> res = controller.obtenerInasistencias(LocalDate.now().minusDays(7), LocalDate.now());

        assertNotNull(res);
        assertSame(fake.inasistencias, res);
        assertTrue(errContent.toString().isBlank());
    }

    // ---------- Tests de manejo de errores (con excepciones) ----------

    @Test
    void registrarIngreso_noPropagaExcepcion_yLoggeaEnErr() {
        FakeFailService failing = new FakeFailService("boom-ingreso");
        AsistenciaController controller = new AsistenciaController(failing);

        assertDoesNotThrow(() -> controller.registrarIngreso(1));
        assertTrue(errContent.toString().contains("Error al registrar ingreso: boom-ingreso"));
    }

    @Test
    void registrarSalida_noPropagaExcepcion_yLoggeaEnErr() {
        FakeFailService failing = new FakeFailService("boom-salida");
        AsistenciaController controller = new AsistenciaController(failing);

        assertDoesNotThrow(() -> controller.registrarSalida(1));
        assertTrue(errContent.toString().contains("Error al registrar salida: boom-salida"));
    }

    @Test
    void obtenerReporte_enFallo_retornaNull_yLoggea() {
        FakeFailService failing = new FakeFailService("err-reporte");
        AsistenciaController controller = new AsistenciaController(failing);

        List<Asistencia> res = controller.obtenerReporte(LocalDate.now().minusDays(1), LocalDate.now());

        assertNull(res);
        assertTrue(errContent.toString().contains("Error al obtener reporte: err-reporte"));
    }

    @Test
    void obtenerAtrasos_enFallo_retornaNull_yLoggea() {
        FakeFailService failing = new FakeFailService("err-atrasos");
        AsistenciaController controller = new AsistenciaController(failing);

        List<Asistencia> res = controller.obtenerAtrasos(LocalDate.now().minusDays(1), LocalDate.now());

        assertNull(res);
        assertTrue(errContent.toString().contains("Error al obtener atrasos: err-atrasos"));
    }

    @Test
    void obtenerSalidasAnticipadas_enFallo_retornaNull_yLoggea() {
        FakeFailService failing = new FakeFailService("err-salidas");
        AsistenciaController controller = new AsistenciaController(failing);

        List<Asistencia> res = controller.obtenerSalidasAnticipadas(LocalDate.now().minusDays(1), LocalDate.now());

        assertNull(res);
        assertTrue(errContent.toString().contains("Error al obtener salidas anticipadas: err-salidas"));
    }

    @Test
    void obtenerInasistencias_enFallo_retornaNull_yLoggea() {
        FakeFailService failing = new FakeFailService("err-inasistencias");
        AsistenciaController controller = new AsistenciaController(failing);

        List<Asistencia> res = controller.obtenerInasistencias(LocalDate.now().minusDays(1), LocalDate.now());

        assertNull(res);
        assertTrue(errContent.toString().contains("Error al obtener inasistencias: err-inasistencias"));
    }
}