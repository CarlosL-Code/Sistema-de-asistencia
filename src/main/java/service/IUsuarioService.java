/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domain.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author carlo
 */
public interface IUsuarioService {

    Usuario buscarPorEmail(String email) throws SQLException;

    // BUSCAR USUARIO POR ID
    Usuario buscarPorId(int id) throws SQLException;

    // CREAR USUARIO
    void crearUsuario(String nombre, String email, String pass, String tipoUsuario) throws SQLException;

    // EDITAR USUARIO
    void editarUsuario(int id, String nombre, String email, String pass, String tipoUsuario) throws SQLException;

    // ELIMINAR USUARIO
    void eliminarUsuario(int id) throws SQLException;

    // LOGIN DE USUARIO
    Usuario login(String email, String contrasenia) throws SQLException;

    // OBTENER TODOS LOS USUARIOS
    List<Usuario> obtenerTodos() throws SQLException;
    
}
