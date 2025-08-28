
package service;

import domain.Usuario;
import java.sql.SQLException;
import java.util.List;
import repository.UsuarioRepository;


public class UsuarioService implements IUsuarioService {
    
    private UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    // LOGIN DE USUARIO
    @Override
    public Usuario login(String email, String contrasenia) throws SQLException {
        Usuario user = repo.buscarPorEmail(email);
        if (user != null && user.getPass().equals(contrasenia)) {
            return user; // login exitoso
        }
        return null; // usuario o contraseña incorrectos
    }

    // CREAR USUARIO
    @Override
    public void crearUsuario(String nombre, String email, String pass, String tipoUsuario) throws SQLException {
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPass(pass);
        user.setTipoUsuario(tipoUsuario);

        repo.crearUsuario(user);
    }

    // EDITAR USUARIO
    @Override
    public void editarUsuario(int id, String nombre, String email, String pass, String tipoUsuario) throws SQLException {
        Usuario user = new Usuario();
        user.setId(id);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPass(pass);
        user.setTipoUsuario(tipoUsuario);

        repo.editar(user);
    }

    // ELIMINAR USUARIO
    @Override
    public void eliminarUsuario(int id) throws SQLException {
        repo.eliminar(id);
    }

    // OBTENER TODOS LOS USUARIOS
    @Override
    public List<Usuario> obtenerTodos() throws SQLException {
        return repo.obtenerTodos();
    }

    // BUSCAR USUARIO POR ID
    @Override
    public Usuario buscarPorId(int id) throws SQLException {
        return repo.buscarPorId(id);
    }
    
    public Usuario buscarPorEmail(String email) throws SQLException{
        return repo.buscarPorEmail(email);
    }
    
}
