package service;

import domain.Usuario;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    // ----- Fake repository independiente -----
    static class FakeRepo {
        List<Usuario> lista = new ArrayList<>();
        int ultimoId = 0;

        Usuario buscarPorEmail(String email) {
            return lista.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
        }

        void crearUsuario(Usuario u) {
            u.setId(++ultimoId);
            lista.add(u);
        }

        void editar(Usuario u) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == u.getId()) {
                    lista.set(i, u);
                    return;
                }
            }
        }

        void eliminar(int id) {
            lista.removeIf(u -> u.getId() == id);
        }

        List<Usuario> obtenerTodos() {
            return lista;
        }

        Usuario buscarPorId(int id) {
            return lista.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        }
    }

    FakeRepo repo;
    UsuarioService service;

    @BeforeEach
    void setup() {
        repo = new FakeRepo();
        service = new UsuarioService(new UsuarioRepositoryWrapper(repo));
    }

    // ---------- Wrapper para adaptar FakeRepo ----------
    static class UsuarioRepositoryWrapper extends repository.UsuarioRepository {
        private final FakeRepo fake;

        UsuarioRepositoryWrapper(FakeRepo fake) {
            super(null); // pasamos null porque no se usará la conexión real
            this.fake = fake;
        }

        @Override
        public Usuario buscarPorEmail(String email) {
            return fake.buscarPorEmail(email);
        }

        @Override
        public void crearUsuario(Usuario u) {
            fake.crearUsuario(u);
        }

        @Override
        public void editar(Usuario u) {
            fake.editar(u);
        }

        @Override
        public void eliminar(int id) {
            fake.eliminar(id);
        }

        @Override
        public List<Usuario> obtenerTodos() {
            return fake.obtenerTodos();
        }

        @Override
        public Usuario buscarPorId(int id) {
            return fake.buscarPorId(id);
        }
    }

    // ---------- Tests ----------

    @Test
    void login_exitoso() throws SQLException {
        service.crearUsuario("Carlos", "carlos@test.com", "1234", "admin");
        Usuario user = service.login("carlos@test.com", "1234");
        assertNotNull(user);
        assertEquals("Carlos", user.getNombre());
    }

    @Test
    void login_falla_contraseñaIncorrecta() throws SQLException {
        service.crearUsuario("Carlos", "carlos@test.com", "1234", "admin");
        Usuario user = service.login("carlos@test.com", "wrong");
        assertNull(user);
    }

    @Test
    void crearUsuario_agregaUsuarioCorrectamente() throws SQLException {
        service.crearUsuario("Ana", "ana@test.com", "abcd", "user");
        assertEquals(1, repo.lista.size());
        assertEquals("Ana", repo.lista.get(0).getNombre());
    }

    @Test
    void editarUsuario_modificaUsuarioCorrectamente() throws SQLException {
        service.crearUsuario("Ana", "ana@test.com", "abcd", "user");
        Usuario u = repo.lista.get(0);
        service.editarUsuario(u.getId(), "Ana Editada", "ana@test.com", "abcd", "user");
        assertEquals("Ana Editada", repo.lista.get(0).getNombre());
    }

    @Test
    void eliminarUsuario_quitaUsuarioCorrectamente() throws SQLException {
        service.crearUsuario("Ana", "ana@test.com", "abcd", "user");
        Usuario u = repo.lista.get(0);
        service.eliminarUsuario(u.getId());
        assertTrue(repo.lista.isEmpty());
    }

    @Test
    void obtenerTodos_retornaTodosLosUsuarios() throws SQLException {
        service.crearUsuario("Ana", "ana@test.com", "abcd", "user");
        service.crearUsuario("Carlos", "carlos@test.com", "1234", "admin");
        List<Usuario> todos = service.obtenerTodos();
        assertEquals(2, todos.size());
    }

    @Test
    void buscarPorId_retornaUsuarioCorrecto() throws SQLException {
        service.crearUsuario("Ana", "ana@test.com", "abcd", "user");
        Usuario u = repo.lista.get(0);
        Usuario encontrado = service.buscarPorId(u.getId());
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void buscarPorEmail_retornaUsuarioCorrecto() throws SQLException {
        service.crearUsuario("Ana", "ana@test.com", "abcd", "user");
        Usuario encontrado = service.buscarPorEmail("ana@test.com");
        assertEquals("Ana", encontrado.getNombre());
    }
}
