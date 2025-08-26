/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import com.formdev.flatlaf.FlatLightLaf;
import config.ConexionDb;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import repository.AsistenciaRepository;
import repository.UsuarioRepository;
import service.AsistenciaService;
import service.IAsistenciaService;
import service.IUsuarioService;
import service.UsuarioService;
import ui.controller.AsistenciaController;
import ui.controller.UsuarioController;
import ui.view.FrmLogin;

/**
 *
 * @author leandrofuentesvega
 */
public class App {

    public static void main(String[] args) {

        
         try {
            // Activar FlatLaf con estilo claro
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(); // si falla, imprime error
        }
         
        // -------------------------------
        // 1. Crear conexión
        // -------------------------------
        ConexionDb conexion = new ConexionDb();

        // -------------------------------
        // 2. Crear repositorios
        // -------------------------------
        UsuarioRepository usuarioRepo = new UsuarioRepository(conexion);
        AsistenciaRepository asistenciaRepo = new AsistenciaRepository(conexion);

        // -------------------------------
        // 3. Crear servicios
        // -------------------------------
        IUsuarioService usuarioService = new UsuarioService(usuarioRepo);
        IAsistenciaService asistenciaService = new AsistenciaService(asistenciaRepo);

        // -------------------------------
        // 4. Crear controladores
        // -------------------------------
        UsuarioController usuarioController = new UsuarioController(usuarioService);
        AsistenciaController asistenciaController = new AsistenciaController(asistenciaService);

        // -------------------------------
        // 5. Crear vista de login, pasando los controladores
        // -------------------------------
        FrmLogin login = new FrmLogin(usuarioController, asistenciaController);
        login.setVisible(true);
        
    }

}
