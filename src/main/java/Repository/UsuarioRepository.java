package Repository;

import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private ConexionDb conexion;

    public UsuarioRepository(ConexionDb conexion) {
        this.conexion = conexion;
    }

    // CREAR USUARIO
    public void crearUsuario(Usuario user) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, email, pass, tipoDeUsuario) VALUES (?,?,?,?)";

        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getNombre());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPass());
            ps.setString(4, user.getTipoUsuario());
            ps.executeUpdate();
        }
    }

    // Buscar por email 
    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email=?";
        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("ID_usuario"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("pass"),
                            rs.getString("tipoDeUsuario") // tu columna de rol
                    );
                } else {
                    return null;
                }
            }
        }
    }
    
    

    // EDITAR USUARIO
    public void editar(Usuario user) throws SQLException {
        String sql = "UPDATE usuario SET nombre=?, email=?, pass=?, tipoDeUsuario=? WHERE ID_usuario=?";
        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPass());
            ps.setString(4, user.getTipoUsuario());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        }
    }

    
    
    // ELIMINAR USUARIO
    public void eliminar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE ID_usuario=?";
        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        }
    }
    
    
    

    // OBTENER TODOS LOS USUARIOS
    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("ID_usuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("tipoDeUsuario")
                ));
            }
        }
        return lista;
    }
    
    

    // BUSCAR USUARIO POR ID
    public Usuario buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE ID_usuario=?";
        try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("ID_usuario"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("pass"),
                            rs.getString("tipoDeUsuario")
                    );
                } else {
                    return null;
                }
            }
        }
    }
    
    
}
