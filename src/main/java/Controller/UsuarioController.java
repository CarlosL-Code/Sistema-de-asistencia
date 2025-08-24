package Controller;

import Model.Usuario;
import Repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

public class UsuarioController {

    private UsuarioRepository repo;

    public UsuarioController(UsuarioRepository repo) {
        this.repo = repo;
    }
    

    // LOGIN DE USUARIO
    public Usuario login(String email, String contrasenia) {
        try {
            Usuario user = repo.buscarPorEmail(email); 
            if (user != null && user.getPass().equals(contrasenia)) {
                return user; // login exitoso
            } else {
                return null; // usuario o contraseña incorrectos
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

    // CREAR USUARIO
    public void crearUsuario(String nombre, String email, String pass, String tipoUsuario) {
        Usuario user = new Usuario(); // solo con el constructor vacío
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPass(pass); 
        user.setTipoUsuario(tipoUsuario);

        try {
            repo.crearUsuario(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // EDITAR USUARIO
    public void editarUsuario(int id, String nombre, String email, String pass, String tipoUsuario) {
        Usuario user = new Usuario();
        user.setId(id);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPass(pass);
        user.setTipoUsuario(tipoUsuario);

        try {
            repo.editar(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    // ELIMINAR USUARIO
    public void eliminarUsuario(int id) {
        try {
            repo.eliminar(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    // OBTENER TODOS LOS USUARIOS
    public List<Usuario> obtenerTodos() {
        try {
            return repo.obtenerTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
    // BUSCAR USUARIO POR ID
    public Usuario buscarPorId(int id) {
        try {
            return repo.buscarPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
