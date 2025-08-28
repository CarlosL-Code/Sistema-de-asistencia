package ui.controller;

import domain.Usuario;

import java.sql.SQLException;
import java.util.List;
import service.IUsuarioService;

public class UsuarioController {

    private IUsuarioService service;

    public UsuarioController(IUsuarioService service) {
        this.service = service;
    }

    // LOGIN DE USUARIO
    public Usuario login(String email, String contrasenia) throws SQLException {
        Usuario user = service.login(email, contrasenia);
        if (user != null && user.getPass().equals(contrasenia)) {
            return user; // login exitoso
        } else {
            return null; // usuario o contraseña incorrectos
        }
    }

    // CREAR USUARIO
    public void crearUsuario(String nombre, String email, String pass, String tipoUsuario) {
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPass(pass);
        user.setTipoUsuario(tipoUsuario);

        try {
            service.crearUsuario(nombre, email, pass, tipoUsuario);
        } catch (SQLException | IllegalArgumentException e) {
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
            service.editarUsuario(id, nombre, email, pass, tipoUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ELIMINAR USUARIO
    public void eliminarUsuario(int id) {
        try {
            service.eliminarUsuario(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // OBTENER TODOS LOS USUARIOS
    public List<Usuario> obtenerTodos() {
        try {
            return service.obtenerTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // BUSCAR USUARIO POR ID
    public Usuario buscarPorId(int id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Buscar por Email
    public Usuario buscarPorEmail(String email){
        try {
            return service.buscarPorEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
