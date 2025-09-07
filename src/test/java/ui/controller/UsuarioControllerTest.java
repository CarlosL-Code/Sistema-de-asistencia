package ui.controller;

import domain.Usuario;
import org.junit.jupiter.api.*;

import service.IUsuarioService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    // ----- Fakes -----

    /** Servicio fake que registra llamadas y devuelve datos controlados. */
    static class FakeOkService implements IUsuarioService {
        int creados = 0;
        int editados = 0;
        int eliminados = 0;

        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioLogin;
        Usuario usuarioBuscado;

        @Override
        public Usuario login(String email, String contrasenia) {
            return usuarioLogin;
        }

        @Override
        public void crearUsuario(String nombre, String email, String pass, String tipoUsuario) {
            creados++;
        }

        @Override
        public void editarUsuario(int id, String nombre, String email, String pass, String tipoUsuario) {
            editados++;
        }

        @Override
        public void eliminarUsuario(int id) {
            eliminados++;
        }

        @Override
        public List<Usuario> obtenerTodos() {
            return usuarios;
        }

        @Override
        public Usuario buscarPorId(int id) {
            return usuarioBuscado;
        }

        @Override
        public Usuario buscarPorEmail(String email) {
            return usuarioBuscado;
        }
    }

    /** Servicio fake que siempre lanza excepción para probar manejo de errores. */
    static class FakeFailService implements IUsuarioService {
        final String msg;
        FakeFailService(String msg) { this.msg = msg; }

        @Override
        public Usuario login(String email, String contrasenia) throws SQLException {
            throw new SQLException(msg);
        }

        @Override
        public void crearUsuario(String nombre, String email, String pass, String tipoUsuario) throws SQLException {
            throw new SQLException(msg);
        }

        @Override
        public void editarUsuario(int id, String nombre, String email, String pass, String tipoUsuario) throws SQLException {
            throw new SQLException(msg);
        }

        @Override
        public void eliminarUsuario(int id) throws SQLException {
            throw new SQLException(msg);
        }

        @Override
        public List<Usuario> obtenerTodos() throws SQLException {
            throw new SQLException(msg);
        }

        @Override
        public Usuario buscarPorId(int id) throws SQLException {
            throw new SQLException(msg);
        }

        @Override
        public Usuario buscarPorEmail(String email) throws SQLException {
            throw new SQLException(msg);
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

    // ---------- Tests de rutas OK ----------

    @Test
    void login_correctoDevuelveUsuario() throws SQLException {
        FakeOkService fake = new FakeOkService();
        Usuario u = new Usuario();
        u.setEmail("test@mail.com");
        u.setPass("1234");
        fake.usuarioLogin = u;

        UsuarioController controller = new UsuarioController(fake);

        Usuario res = controller.login("test@mail.com", "1234");

        assertNotNull(res);
        assertSame(u, res);
        assertTrue(errContent.toString().isBlank());
    }

    @Test
    void login_incorrectoDevuelveNull() throws SQLException {
        FakeOkService fake = new FakeOkService();
        Usuario u = new Usuario();
        u.setEmail("test@mail.com");
        u.setPass("otra");
        fake.usuarioLogin = u;

        UsuarioController controller = new UsuarioController(fake);

        Usuario res = controller.login("test@mail.com", "1234");

        assertNull(res);
    }

    @Test
    void crearUsuario_delegaEnServicio() {
        FakeOkService fake = new FakeOkService();
        UsuarioController controller = new UsuarioController(fake);

        controller.crearUsuario("Juan", "j@mail.com", "123", "admin");

        assertEquals(1, fake.creados);
    }

    @Test
    void editarUsuario_delegaEnServicio() {
        FakeOkService fake = new FakeOkService();
        UsuarioController controller = new UsuarioController(fake);

        controller.editarUsuario(1, "Pedro", "p@mail.com", "abc", "user");

        assertEquals(1, fake.editados);
    }

    @Test
    void eliminarUsuario_delegaEnServicio() {
        FakeOkService fake = new FakeOkService();
        UsuarioController controller = new UsuarioController(fake);

        controller.eliminarUsuario(10);

        assertEquals(1, fake.eliminados);
    }

    @Test
    void obtenerTodos_retornaListaDelServicio() {
        FakeOkService fake = new FakeOkService();
        fake.usuarios.add(new Usuario());
        UsuarioController controller = new UsuarioController(fake);

        List<Usuario> res = controller.obtenerTodos();

        assertNotNull(res);
        assertSame(fake.usuarios, res);
    }

    @Test
    void buscarPorId_retornaUsuarioDelServicio() {
        FakeOkService fake = new FakeOkService();
        Usuario u = new Usuario();
        fake.usuarioBuscado = u;
        UsuarioController controller = new UsuarioController(fake);

        Usuario res = controller.buscarPorId(1);

        assertSame(u, res);
    }

    @Test
    void buscarPorEmail_retornaUsuarioDelServicio() {
        FakeOkService fake = new FakeOkService();
        Usuario u = new Usuario();
        fake.usuarioBuscado = u;
        UsuarioController controller = new UsuarioController(fake);

        Usuario res = controller.buscarPorEmail("x@mail.com");

        assertSame(u, res);
    }

    // ---------- Tests de manejo de errores ----------

    @Test
    void login_conExcepcionPropagaSQLException() {
        FakeFailService failing = new FakeFailService("err-login");
        UsuarioController controller = new UsuarioController(failing);

        assertThrows(SQLException.class,
                () -> controller.login("a", "b"));
    }

    @Test
    void crearUsuario_conError_noPropagaExcepcion_yLoggea() {
        FakeFailService failing = new FakeFailService("err-crear");
        UsuarioController controller = new UsuarioController(failing);

        assertDoesNotThrow(() ->
                controller.crearUsuario("n", "e", "p", "t"));
        assertTrue(errContent.toString().contains("err-crear"));
    }

    @Test
    void editarUsuario_conError_noPropagaExcepcion_yLoggea() {
        FakeFailService failing = new FakeFailService("err-editar");
        UsuarioController controller = new UsuarioController(failing);

        assertDoesNotThrow(() ->
                controller.editarUsuario(1, "n", "e", "p", "t"));
        assertTrue(errContent.toString().contains("err-editar"));
    }

    @Test
    void eliminarUsuario_conError_noPropagaExcepcion_yLoggea() {
        FakeFailService failing = new FakeFailService("err-eliminar");
        UsuarioController controller = new UsuarioController(failing);

        assertDoesNotThrow(() -> controller.eliminarUsuario(1));
        assertTrue(errContent.toString().contains("err-eliminar"));
    }

    @Test
    void obtenerTodos_conError_retornaNull_yLoggea() {
        FakeFailService failing = new FakeFailService("err-todos");
        UsuarioController controller = new UsuarioController(failing);

        List<Usuario> res = controller.obtenerTodos();

        assertNull(res);
        assertTrue(errContent.toString().contains("err-todos"));
    }

    @Test
    void buscarPorId_conError_retornaNull_yLoggea() {
        FakeFailService failing = new FakeFailService("err-id");
        UsuarioController controller = new UsuarioController(failing);

        Usuario res = controller.buscarPorId(1);

        assertNull(res);
        assertTrue(errContent.toString().contains("err-id"));
    }

    @Test
    void buscarPorEmail_conError_retornaNull_yLoggea() {
        FakeFailService failing = new FakeFailService("err-email");
        UsuarioController controller = new UsuarioController(failing);

        Usuario res = controller.buscarPorEmail("x@mail.com");

        assertNull(res);
        assertTrue(errContent.toString().contains("err-email"));
    }
}
